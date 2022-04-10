package WizClient.ui;

import java.awt.Color;

import java.io.IOException;
import java.util.logging.Logger;

import com.mojang.authlib.GameProfile;
import com.mojang.realmsclient.dto.PlayerInfo;

import WizClient.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;

public class WizClientMainMenu extends GuiScreen{
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("WizClient/main_menu.jpg"));
		
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		
		GlStateManager.pushMatrix();
		GlStateManager.scale(1.2, 1.2, 1.2);
		mc.fontRendererObj.drawStringWithShadow(mc.getSession().getUsername(), this.width / 2 - mc.fontRendererObj.getStringWidth(""), this.height / 2 - mc.fontRendererObj.FONT_HEIGHT, -1);
		GlStateManager.popMatrix();
		
		Gui.drawRect(0, 0, 220, this.height, new Color(0, 0, 0, 170).getRGB());
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		Client.getInstance().getDiscordRP().update("Idle", "Main menu");
		this.buttonList.add(new GuiButton(1, 10, height / 2 - 40, I18n.format("menu.singleplayer", new Object[0])));
		this.buttonList.add(new GuiButton(2, 10, height / 2 - 15, I18n.format("menu.multiplayer", new Object[0])));
		this.buttonList.add(new GuiButton(3, 10, height / 2 + 10, I18n.format("menu.options", new Object[0])));
		this.buttonList.add(new GuiButton(4, 10, height / 2 + 35, I18n.format("menu.quit", new Object[0])));

		System.out.println(new Color(77, 93, 239).getRGB());
		
		super.initGui();
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

