package WizClient;

import WizClient.event.EventManager;
import WizClient.event.EventTarget;
import WizClient.event.impl.ClientTickEvent;
import WizClient.gui.SplashProgress;
import WizClient.gui.hud.HUDManager;
import WizClient.mods.ModInstances;
import WizClient.mods.impl.ModFullBright;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.Config;


public class Client {

	private EntityPlayer player;
	
	private static final Client INSTANCE = new Client();
	public static final Client getInstance() { 
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	private HUDManager hudManager;
	
	
	public void init() {
		SplashProgress.setProgress(1, "Client - Initialising discord");
		discordRP.start();
		FileManager.init();
		EventManager.register(this);
		
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
		hudManager = HUDManager.getInstance();
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.isPressed()) {
			hudManager.openConfigScreen();
		}
		
		if(Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_TOGGLE_FULLBRIGHT.isPressed()) {
			ModInstances.getModFullBright().ToogleMod();

		}
	}
	
	
}



// NotificationManager.show(new Notification(NotificationType.INFO, "JUMP", "You jumped", 1)

// dans GuiIngame il faut mettre NotificationManager.render();

/*
package clientname.mods.impl;
import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.server.MinecraftServer;
public class ServerIPMod extends ModDraggable  {
private ScreenPosition pos;
public static Minecraft mc = Minecraft.getMinecraft();
@Override
public int getWidth() {
if(MinecraftServer.getServer().isSinglePlayer()) {
     return 50;
}
    return font.getStringWidth(getIP());
}
@Override
public int getHeight() {
    return font.FONT_HEIGHT;
}
@Override
public void render(ScreenPosition pos) {
    if(!MinecraftServer.getServer().isSinglePlayer()) {
        Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(),pos.getAbsoluteX() + getWidth() + 2,pos.getAbsoluteY() + getHeight() + 1, new Color(0, 0, 0, 102).getRGB());
        font.drawString("" + getIP(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1, -1);
    }
}
private String getIP() {
    String IP;
    IP = mc.getCurrentServerData().serverIP;
    return IP;
}
public void renderDummy(ScreenPosition pos) {
  font.drawString("Server IP", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 1, -1);
}
}

*/


/*

package clientname.mods.impl;
import net.minecraft.client.Minecraft;
import clientname.gui.hud.ScreenPosition;
import clientname.mods.ModDraggable;
public class ModBPS extends ModDraggable {
float bps; 
@Override
public void render(ScreenPosition pos) {
    float rat = Minecraft.getMinecraft().timer.ticksPerSecond * Minecraft.getMinecraft().timer.timerSpeed;
    this.bps = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * rat);
    font.drawStringWithShadow(String.valueOf(bps) + " BPS", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
}
@Override
public int getWidth() {
    return font.getStringWidth(String.valueOf(bps) + " BPS");
}
@Override
public int getHeight() {
    return font.FONT_HEIGHT;
}
}

 */

/*
ResourceLocation subTitle = new ResourceLocation("clientname/URTITLEIMAGE.png");
mc.getTextureManager().bindTexture(subTitle);
Gui.drawModalRectWithCustomSizedTexture(525, 315, 0.0F, 0.0F, this.width - 520, this.height - 310, this.width - 520, this.height - 310);
*/

/*
protected void writePing(int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo networkPlayerInfoIn) 
{
    
    Color pingColor = new Color(0,0,0);
    
    if(networkPlayerInfoIn.getResponseTime() < 150) pingColor = new Color(68, 183, 69);
    if(networkPlayerInfoIn.getResponseTime() >= 150) pingColor = new Color(0, 112, 0);
    if(networkPlayerInfoIn.getResponseTime() > 299) pingColor = new Color(221, 67, 67);
    
     Client.Client.textStatus.drawCenteredStringWithShadow(networkPlayerInfoIn.getResponseTime()  + " ", p_175245_2_ + p_175245_1_ - 11, p_175245_3_, pingColor.getRGB()); // replace with your fontrenderer
     
}
*/
