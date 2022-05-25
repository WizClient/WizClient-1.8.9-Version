package WizClient.mods;

import WizClient.gui.hud.HUDManager;
import WizClient.mods.impl.ModArmorStatus;
import WizClient.mods.impl.ModCPS;
import WizClient.mods.impl.ModCoords;
import WizClient.mods.impl.ModCurrentTime;
import WizClient.mods.impl.ModFPS;
import WizClient.mods.impl.ModFullBright;
import WizClient.mods.impl.ModItemCounter;
import WizClient.mods.impl.ModKeyStrokes;
import WizClient.mods.impl.ModMemmory;
import WizClient.mods.impl.ModPing;
import WizClient.mods.impl.ModTargetHUD;
import WizClient.mods.impl.ModDirection.ModDirection;
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
	private static ModCPS modCPS;
	private static ModTargetHUD modTargetHUD;
	private static ModDirection modDirection;
	private static ModCurrentTime modCurrentTime;
	private static ModMemmory modMemmory;
	private static ModItemCounter modItemCounter;
	private static ModFullBright modFullBright;
	
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
		
		modCPS = new ModCPS();
		api.register(modCPS);
		
		modTargetHUD = new ModTargetHUD();
		api.register(modTargetHUD);
		
		modDirection = new ModDirection();
		api.register(modDirection);
		
		modCurrentTime = new ModCurrentTime();
		api.register(modCurrentTime);
		
		modMemmory = new ModMemmory();
		api.register(modMemmory);
		
		modItemCounter = new ModItemCounter();
		api.register(modItemCounter);
		
		modFullBright = new ModFullBright();
		api.register(modFullBright);

	}
	
	public static ModFullBright getModFullBright() {
		return modFullBright;
	}
	
	public static ModItemCounter getModItemCounter() {
		return modItemCounter;
	}
	
	public static ModMemmory getModMemmory() {
		return modMemmory;
	}
	
	public static ModCurrentTime getModCurrentTime() {
		return modCurrentTime;
	}
	
	public static ModDirection getModDirection() {
		return modDirection;
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
	
	public static ModCPS getModCPS() {
		return modCPS;
	}
	
	public static ModTargetHUD getModTargetHUD() {
		return modTargetHUD;
	}

}
