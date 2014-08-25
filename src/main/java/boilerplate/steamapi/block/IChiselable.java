/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the BoilerCraft Mod for Minecraft.
 *
 * BoilerCraft is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 */
package boilerplate.steamapi.block;

import net.minecraft.block.Block;

/**
 * @author warlordjones
 *
 */
public interface IChiselable
{
	public Block getChiseledVariant();

	public int getChiseledVariantMeta();
}
