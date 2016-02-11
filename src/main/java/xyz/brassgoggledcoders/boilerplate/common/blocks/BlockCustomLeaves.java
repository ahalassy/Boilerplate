
package xyz.brassgoggledcoders.boilerplate.common.blocks;

import java.util.List;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomLeaves extends BlockLeaves
{
	String type;

	public BlockCustomLeaves(String type)
	{
		super();
		this.type = type;
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
	}

	// @Override
	// public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	// {
	// return this.blockIcon;
	// }

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnumType getWoodType(int meta)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
