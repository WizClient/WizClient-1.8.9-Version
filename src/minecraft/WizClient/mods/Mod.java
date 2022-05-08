package WizClient.mods;

import WizClient.Client;
import WizClient.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
	
	private boolean isEnabled;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Client client;
	
	public Mod() {
		this.mc = Minecraft.getMinecraft();
		this.font = this.mc.fontRendererObj;
		this.client = Client.getInstance();
		
		this.isEnabled = true;
		
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		if(isEnabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
	}

	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void ToogleMod() {
		setEnabled(!this.isEnabled);
	}
	
}
