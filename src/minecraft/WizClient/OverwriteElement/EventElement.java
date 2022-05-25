package WizClient.OverwriteElement;

import java.awt.Color;

import WizClient.event.EventTarget;
import WizClient.event.impl.ClientTickEvent;
import WizClient.gui.hud.HUDManager;
import WizClient.mods.ModInstances;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.network.NetworkPlayerInfo;

public class EventElement {

	
	private HUDManager hudManager;
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		hudManager = HUDManager.getInstance();
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_TOGGLE_FULLBRIGHT.isPressed()) {
			ModInstances.getModFullBright().ToogleMod();

		}
	}

	
	
	
	
}
