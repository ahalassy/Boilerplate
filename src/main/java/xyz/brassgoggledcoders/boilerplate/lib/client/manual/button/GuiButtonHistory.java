/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * 
 * File Created @ [Mar 2, 2015, 6:01:26 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.lib.client.manual.button;

import java.util.ArrayList;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.client.resources.I18n;
import xyz.brassgoggledcoders.boilerplate.lib.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.lib.client.manual.RenderHelper;

public class GuiButtonHistory extends GuiButtonLexicon {

	GuiLexicon gui;

	public GuiButtonHistory(int par1, int par2, int par3, String str, GuiLexicon gui) {
		super(par1, par2, par3, gui.bookmarkWidth(str) + 5, 11, str);
		this.gui = gui;
	}

	@Override
	public void drawButton(Minecraft mc, int par2, int par3) {
		hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
		int k = getHoverState(hovered);

		List<String> tooltip = new ArrayList<>();
		tooltip.add(I18n.format("botaniamisc.historyLong"));
		tooltip.add(TextFormatting.GRAY + I18n.format("botaniamisc.historyDesc"));

		int tooltipY = (tooltip.size() + 1) * 5;
		if(k == 2)
			RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
	}

}
