/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.common.blocks;

import boilerplate.common.tiles.TileEntitySided;
import boilerplate.common.utils.Tools;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author SkySom
 */
public abstract class SidedBlock extends BlockContainer
{
	public IIcon topIcons[] = new IIcon[3];
	public IIcon sideIcons[] = new IIcon[3];

	protected SidedBlock(Material material)
	{
		super(material);

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntitySided)
		{
			if(!world.isRemote)
			{
				TileEntitySided tileEntitySided = (TileEntitySided) te;
				if(Tools.isItemATool(player.getCurrentEquippedItem()))
				{
					if(player.isSneaking())
					{
						side = ForgeDirection.OPPOSITES[side];
					}
					tileEntitySided.toggleSide(side);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public abstract void registerBlockIcons(IIconRegister iconRegister);

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide)
	{
		TileEntitySided tileEntitySided = (TileEntitySided) world.getTileEntity(x, y, z);
		if(blockSide < 2)
		{
			return this.topIcons[tileEntitySided.getSideValue(blockSide).ordinal()];
		} else
		{
			return this.sideIcons[tileEntitySided.getSideValue(blockSide).ordinal()];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(side < 2)
		{
			return this.topIcons[1];
		} else {
			return this.sideIcons[1];
		}
	}
}
