package xyz.brassgoggledcoders.boilerplate.mod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.client.models.IHasModel;
import xyz.brassgoggledcoders.boilerplate.lib.common.blocks.IHasTileEntity;
import xyz.brassgoggledcoders.boilerplate.lib.common.items.BaseItem;
import xyz.brassgoggledcoders.boilerplate.mod.Boilerplate;
import xyz.brassgoggledcoders.boilerplate.mod.api.IDebuggable;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * @author SkySom
 */
public class ItemDebuggerStick extends BaseItem implements IHasModel
{
	public ItemDebuggerStick()
	{
		super("debuggerstick");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		MovingObjectPosition rayTrace = this.getMovingObjectPositionFromPlayer(world, entityPlayer, true);
		IDebuggable debuggable = null;
		if(rayTrace != null && rayTrace.typeOfHit != null)
		{
			if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
			{
				TileEntity tileEntity = world.getTileEntity(rayTrace.getBlockPos());
				{
					if (tileEntity instanceof IDebuggable)
					{
						debuggable = (IDebuggable)tileEntity;
					}
				}
			}
			else if (rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY)
			{
				Entity entity = rayTrace.entityHit;
				if (entity instanceof IDebuggable)
				{
					debuggable = (IDebuggable)entity;
				}
			}
		}

		if (debuggable != null)
		{
			LinkedHashMap<String, String> debugStrings = debuggable.getDebugStrings();
			if(debugStrings != null && !debugStrings.isEmpty())
			{
				Iterator<String> iterator = debugStrings.values().iterator();
				while(iterator.hasNext())
				{
					Boilerplate.logger.info(iterator.next());
				}
			}

		}

		return itemStack;
	}

	@Override
	public ResourceLocation[] getModelResourceLocations()
	{
		return new ResourceLocation[]{new ResourceLocation(BoilerplateLib.getMod().getPrefix() + "debuggerstick")};
	}
}
