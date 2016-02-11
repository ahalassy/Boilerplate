
package xyz.brassgoggledcoders.boilerplate.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import xyz.brassgoggledcoders.boilerplate.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.common.utils.Utils;

/**
 * @author Surseance
 *
 */
public class BaseBlock extends Block
{
	IBoilerplateMod mod;

	public BaseBlock(Material mat)
	{
		super(mat);
		this.mod = Utils.getCurrentMod();
		this.setCreativeTab(mod.getCreativeTab());
		this.setHardness(1F);
	}

	/*
	 * TODO
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public void registerBlockIcons(IIconRegister ir) {
	 * this.blockIcon = ir.registerIcon(mod.getPrefix() +
	 * this.getUnlocalizedName().substring(5)); }
	 * 
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public IIcon getIcon(int p_149691_1_, int
	 * p_149691_2_) { return this.blockIcon; }
	 */
}
