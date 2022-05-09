package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.Minecraft;

public class ModPing extends ModDraggable {
	
	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("Ping: " + mc.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + "ms", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, Color.HSBtoRGB((float)(System.currentTimeMillis() % 2500L) / 2500F, 0.8F, 0.8F));
		
	}

}