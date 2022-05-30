package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class ModPing extends ModDraggable {
	
	@Override
	public int getWidth() {
	    return font.getStringWidth("00 ms");
	}
	@Override
	public int getHeight() {
	    return font.FONT_HEIGHT;
	}
	@Override
	public void render(ScreenPosition pos) {
	    if(!MinecraftServer.getServer().isSinglePlayer()) {
	        font.drawString(Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime() + " ms", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
	    } else {
	        font.drawString("0 ms", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
	    }
	}

}
