package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class ModPing extends ModDraggable {
	
	@Override
	public int getWidth() {
	    return font.getStringWidth("[00 ms]");
	}
	@Override
	public int getHeight() {
	    return font.FONT_HEIGHT;
	}
	@Override
	public void render(ScreenPosition pos) {
	    if(!MinecraftServer.getServer().isSinglePlayer()) {
	        font.drawStringWithShadow("[" + mc.getCurrentServerData().pingToServer + " ms]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	    } else {
	        font.drawStringWithShadow("[0 ms]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
	    }
	}

}
