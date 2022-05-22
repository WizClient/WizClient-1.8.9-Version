package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.EntityLivingBase;

public class ModTargetHUD extends ModDraggable {

	private EntityLivingBase target;
	private final String HEART = "\u2764";
	
	@Override
	public int getWidth() {
		return 70;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT * 2 + 3;
	}

	@Override
	public void render(ScreenPosition pos) {
		target = (EntityLivingBase) mc.pointedEntity;
	    if(target != null) {
	        font.drawString(target.getName(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, -1);
	        font.drawString(String.format("%d §4%s§f", (int) target.getHealth(), HEART), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);
	        GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + font.getStringWidth(target.getName()) + 25, pos.getAbsoluteY() + 30, 20, 50, 0, target);
	    }
		
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + getHeight(), new Color(0, 0, 0, 170).getRGB());
	    font.drawString(mc.thePlayer.getName(), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2, -1);
	    font.drawString(String.format("%d §4%s§f", (int) mc.thePlayer.getHealth(), HEART), pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1); // im not too sure why you have the extra &f
	    GuiInventory.drawEntityOnScreen(pos.getAbsoluteX() + font.getStringWidth(mc.thePlayer.getName()) + 30, pos.getAbsoluteY() + 30, 20, 50, 0, mc.thePlayer);
	}

}
