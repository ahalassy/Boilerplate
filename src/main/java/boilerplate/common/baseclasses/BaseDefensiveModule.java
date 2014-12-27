package boilerplate.common.baseclasses;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import boilerplate.client.ClientHelper;
import boilerplate.steamapi.item.IDefensiveArmorModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BaseDefensiveModule extends RootItem implements IDefensiveArmorModule
{
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (ClientHelper.isShiftKeyDown())
		{
			list.add("Module ID: " + this.getModuleId());
			list.add("Applicable Piece: " + this.getArmorPieceNameFromNumber(this.getApplicablePiece()));
			list.add("Effect Type: " + this.getEffectTypeStringFromEnum(this.getArmorEffectType()));
			list.add("Damage Absorbtion Ratio: " + this.getDamageAbsorbRatio());
			list.add("Max Absorbtion: " + this.getMaxDamageAbsorb());
			if (!StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc").contains("item."))
			{
				list.add("Module Effect: ");
				this.getWrappedDesc(list, stack);
			}
			if (this.getEnergyConsumedOnEffect() != 0)
				list.add("Energy Usage on Effect: " + this.getEnergyConsumedOnEffect());
			if (this.getSteamConsumedOnEffect() != 0)
				list.add("Steam Usage on Effect: " + this.getSteamConsumedOnEffect());
		}
		else
			list.add(ClientHelper.shiftForInfo);
	}

	public String getArmorPieceNameFromNumber(int number)
	{
		switch (number)
		{
		case -1:
			return "All";
		case 0:
			return "Helmet";
		case 1:
			return "Chestplate";
		case 2:
			return "Leggings";
		case 3:
			return "Boots";
		}
		return "Error!";
	}

	public String getEffectTypeStringFromEnum(EnumArmorEffectType type)
	{
		if (type == EnumArmorEffectType.ONTICK)
			return "On Equipped Tick";
		else if (type == EnumArmorEffectType.HUD)
			return "HUD Element";
		else if (type == EnumArmorEffectType.DEFENSIVE)
			return "Defensive";
		else
			return "Error!";
	}

	@Override
	public int getMaxDamageAbsorb()
	{
		return 0;
	}

	@Override
	public int getDamageAbsorbRatio()
	{
		return 0;
	}

	@Override
	public int getArmorToDisplay()
	{
		return 0;
	}
}