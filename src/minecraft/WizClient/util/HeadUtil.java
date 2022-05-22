package WizClient.util;

import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
public class HeadUtil {
	
	private static final HashMap<String, ResourceLocation> playerHeads = new HashMap<>();
	
	public static void drawPlayerHead(String name, int x, int y, int size) {
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	    ResourceLocation headLocation = getHeadLocation(name);
	    Minecraft.getMinecraft().getTextureManager().bindTexture(headLocation);
	    Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, size, size, size, size);
	}

	public static ResourceLocation getHeadLocation(String username) {
	    ResourceLocation playerHead = (ResourceLocation) playerHeads.getOrDefault(username, new ResourceLocation("WizClient/heads/" + username + ".png"));
	    
	    if (!playerHeads.containsKey(username)) {
	        ThreadDownloadImageData skinData = new ThreadDownloadImageData(null,"https://minotar.net/helm/" + username + "/32.png", new ResourceLocation("WizClient/heads/steve.png"), null);
	        (Minecraft.getMinecraft()).getTextureManager().loadTexture(playerHead, skinData);
	        playerHeads.put(username, playerHead);
	    } else { }
	    return playerHead;
	}
}
