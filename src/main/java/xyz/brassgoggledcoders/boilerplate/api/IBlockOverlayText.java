package xyz.brassgoggledcoders.boilerplate.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

/**
 * @author IE Team
 */
public interface IBlockOverlayText
{
	public String[] getOverlayText(EntityPlayer player, MovingObjectPosition mop, boolean tool);
}