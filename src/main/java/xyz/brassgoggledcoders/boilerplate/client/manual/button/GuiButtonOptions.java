/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * File Created @ [Mar 6, 2015, 1:14:05 PM (GMT)]
 */
package xyz.brassgoggledcoders.boilerplate.client.manual.button;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import xyz.brassgoggledcoders.boilerplate.client.manual.GuiLexicon;
import xyz.brassgoggledcoders.boilerplate.client.manual.RenderHelper;

public class GuiButtonOptions extends GuiButtonLexicon {

	public GuiButtonOptions(int id, int x, int y) {
		super(id, x, y, 11, 11, "");
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		hovered = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
		int k = getHoverState(hovered);

		par1Minecraft.renderEngine.bindTexture(GuiLexicon.texture);
		GlStateManager.color(1F, 1F, 1F, 1F);
		drawTexturedModalRect(xPosition, yPosition, k == 2 ? 109 : 98, 180, 11, 11);

		List<String> tooltip = new ArrayList<>();
		for(int i = 0; i < 3; i++)
			tooltip.add((i == 0 ? TextFormatting.RED : TextFormatting.GRAY)
					+ I18n.format("botaniamisc.lexiconOptions" + i));

		int tooltipY = (tooltip.size() - 1) * 10;
		if(k == 2)
			RenderHelper.renderTooltip(par2, par3 + tooltipY, tooltip);
	}

}
