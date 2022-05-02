package WizClient.mods;

import WizClient.gui.hud.HUDManager;
import WizClient.mods.impl.ModArmorStatus;
import WizClient.mods.impl.ModFPS;

public class ModInstances {
	
	private static ModFPS modFPS;
	private static ModArmorStatus modArmorStatus;
	
	public static void register(HUDManager api) {
		
		modFPS = new ModFPS();
		api.register(modFPS);
		
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
	}
	

}
