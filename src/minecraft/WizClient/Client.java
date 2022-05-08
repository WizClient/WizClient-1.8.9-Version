package WizClient;

import WizClient.event.EventManager;
import WizClient.event.EventTarget;
import WizClient.event.impl.ClientTickEvent;
import WizClient.gui.SplashProgress;
import WizClient.gui.hud.HUDManager;
import WizClient.mods.ModInstances;
import WizClient.util.SessionChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;



public class Client {
	
	private boolean fullbright;
	
	private static final Client INSTANCE = new Client();
	public static final Client getInstance() { 
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;
	
	
	public void init() {
		SplashProgress.setProgress(1, "Client - Initialising discord");
		discordRP.start();
		EventManager.register(this);
		//FontUtil.bootstrap();
		//FontUtil.test();
		
	}
	
	public void start() {
		hudManager = HUDManager.getInstance();
		ModInstances.register(hudManager);
		
		
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
	
	@EventTarget
	public void onTick(ClientTickEvent e) {
		
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_TOGGLE_FULLBRIGHT.isPressed()) {

			//ModInstances.getModFPS().ToogleMod();
			
			if (!fullbright) {
				Minecraft.getMinecraft().gameSettings.saturation = 1000F;
				fullbright = true;
				System.out.println("Fullbright: ON");
			} else {
				Minecraft.getMinecraft().gameSettings.saturation = 1.0F;
				fullbright = false;
				System.out.println("Fullbright: OFF");
			}
			

		}
	}
	
}
