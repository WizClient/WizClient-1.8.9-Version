package WizClient;

import org.lwjgl.input.Keyboard;

import WizClient.event.EventManager;
import WizClient.event.EventTarget;
import WizClient.event.impl.ClientTickEvent;
import WizClient.gui.SplashProgress;
import WizClient.gui.hud.HUDManager;
import WizClient.mods.ModInstances;
import net.minecraft.client.Minecraft;



public class Client {
	
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
	}
	
}
