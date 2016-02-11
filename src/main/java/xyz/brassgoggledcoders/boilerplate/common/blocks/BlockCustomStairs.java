
package xyz.brassgoggledcoders.boilerplate.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;

/**
 * @author warlordjones
 *
 */
public class BlockCustomStairs extends BlockStairs
{
	public BlockCustomStairs(Block block)
	{
		super(block.getDefaultState());
		this.setCreativeTab(Utils.getCurrentMod().getCreativeTab());
		this.useNeighborBrightness = true;
	}
}