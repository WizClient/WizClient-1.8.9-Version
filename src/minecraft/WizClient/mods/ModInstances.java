package WizClient.mods;

import WizClient.gui.hud.HUDManager;
import WizClient.mods.impl.ModFPS;
import WizClient.mods.impl.TestMod;

public class ModInstances {

	private static TestMod testmod;
	
	private static ModFPS modFPS;
	
	public static void register(HUDManager api) {
		testmod = new TestMod();
		api.register(testmod);
		
		modFPS = new ModFPS();
		api.register(modFPS);
	}
	
	public static TestMod getTestMod() {
		return testmod;
	}
}
