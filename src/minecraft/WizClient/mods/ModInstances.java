package WizClient.mods;

import WizClient.gui.hud.HUDManager;
import WizClient.mods.impl.ModArmorStatus;
import WizClient.mods.impl.ModCoords;
import WizClient.mods.impl.ModFPS;
import WizClient.mods.impl.ModKeyStrokes;
import WizClient.mods.impl.ModPing;
import WizClient.mods.impl.ToggleSprintAndSneak.ModToggleSneak;
import WizClient.mods.impl.ToggleSprintAndSneak.ModToggleSprint;

public class ModInstances {
	
	private static ModFPS modFPS;
	private static ModArmorStatus modArmorStatus;
	private static ModPing modPing;
	private static ModCoords modCoords;
	private static ModKeyStrokes modKeyStrokes;
	private static ModToggleSprint modToggleSprint;
	private static ModToggleSneak modToggleSneak;
	
	public static void register(HUDManager api) {
		
		modFPS = new ModFPS();
		api.register(modFPS);
		
		modArmorStatus = new ModArmorStatus();
		api.register(modArmorStatus);
		
		modPing = new ModPing();
		api.register(modPing);
		
		modCoords = new ModCoords();
		api.register(modCoords);
		
		modKeyStrokes = new ModKeyStrokes();
		api.register(modKeyStrokes);
		
		modToggleSprint = new ModToggleSprint();
		api.register(modToggleSprint);
		
		modToggleSneak = new ModToggleSneak();
		api.register(modToggleSneak);
	}
	
	public static ModFPS getModFPS() {
		return modFPS;
	}
	
	public static ModArmorStatus getModArmorStatus() {
		return modArmorStatus;
	}
	
	public static ModPing getModPing() {
		return modPing;
	}
	
	 public static ModCoords getModCoords() {
		return modCoords;
	}
	 
	public static ModKeyStrokes getModKeyStrokes() {
		return modKeyStrokes;
	}
	
	public static ModToggleSprint getModToggleSprint() {
		return modToggleSprint;
	}
	
	public static ModToggleSneak getModToggleSneak() {
		return modToggleSneak;
	}
	

}
