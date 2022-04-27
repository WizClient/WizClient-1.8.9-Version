package WizClient.ui;

import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.mojang.realmsclient.dto.PlayerInfo;

import WizClient.Client;
import WizClient.gui.UnicodeFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;

public class WizClientMainMenu extends GuiScreen {
	
	private ResourceLocation buttonTexture = new ResourceLocation("WizClient/gui/Button.png");
	private ResourceLocation MainIcon = new ResourceLocation("WizClient/gui/clear.png");
	private static UnicodeFontRenderer ufr;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("WizClient/main_menu.png"));
		
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
        
		mc.fontRendererObj.drawString(mc.getSession().getUsername(), this.width - this.fontRendererObj.getStringWidth(mc.getSession().getUsername()) - 10, 5, -1);

		GlStateManager.popMatrix();
		Gui.drawRect(0, 0, 220, this.height, new Color(0, 0, 0, 170).getRGB());
		
		String ver = "WizClient 1.8.9";
		this.drawString(this.fontRendererObj, ver, 2, this.height - 10, -1);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawlogo();
	}
	
	@Override
	public void initGui() {
		Client.getInstance().getDiscordRP().update("Idle", "Main menu");
		Display.setTitle("Minecraft - WizClient 1.8.9 | " + this.mc.getMinecraft().getSession().getUsername());
		
		if(ufr == null) {
			ufr = UnicodeFontRenderer.getFontOnPC("Arial", 20);
		}
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		
		this.buttonList.add(new GuiButton(1, 10, height / 2 - 40, I18n.format("menu.singleplayer", new Object[0])));
		this.buttonList.add(new GuiButton(2, 10, height / 2 - 15, I18n.format("menu.multiplayer", new Object[0])));
		this.buttonList.add(new GuiButton(3, 10, height / 2 + 10, I18n.format("menu.options", new Object[0])));
		this.buttonList.add(new GuiButton(4, 10, height / 2 + 35, I18n.format("menu.quit", new Object[0])));

		
		FontRenderer fr = mc.fontRendererObj;
		mc.getTextureManager().bindTexture(buttonTexture);
		
		
		super.initGui();
	}
	
	public void drawlogo() {
		ResourceLocation logo = new ResourceLocation("WizClient/gui/clear.png");
        this.mc.getTextureManager().bindTexture(logo);
        
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer Wr = tess.getWorldRenderer();
        
        Wr.begin(7, DefaultVertexFormats.POSITION_TEX);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        
        Wr.pos((double)110 - 120,(double)-20, (double)this.zLevel).tex((double)0, (double)0).endVertex();
        Wr.pos((double)110 - 120,(double)this.height / 2 -20, (double)this.zLevel).tex((double)0, (double)1).endVertex();
        Wr.pos((double)110 + 120,(double)this.height / 2 -20, (double)this.zLevel).tex((double)1, (double)1).endVertex();
        Wr.pos((double)110 + 120,(double)-20, (double)this.zLevel).tex((double)1, (double)0).endVertex();
        tess.draw();
    }
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 1:
			mc.displayGuiScreen(new GuiSelectWorld(this));
			break;
		case 2:
			mc.displayGuiScreen(new GuiMultiplayer(this));
			break;
		case 3:
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
			break;
		case 4:
			mc.shutdown();
			break;
			

		default:
			break;
		}
		
		super.actionPerformed(button);
	}
}

