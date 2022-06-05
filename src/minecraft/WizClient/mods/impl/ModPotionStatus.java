package WizClient.mods.impl;

import java.util.Collection;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class ModPotionStatus
extends ModDraggable {
    private ScreenPosition pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
    protected FontRenderer fontRendererObj;
    protected float zLevelFloat;

    @Override
    public int getWidth() {
        return 101;
    }

    @Override
    public int getHeight() {
        return 154;
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x + 0, y + height, this.zLevelFloat).tex((float)(textureX + 0) * f, (float)(textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, this.zLevelFloat).tex((float)(textureX + width) * f, (float)(textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + 0, this.zLevelFloat).tex((float)(textureX + width) * f, (float)(textureY + 0) * f1).endVertex();
        worldrenderer.pos(x + 0, y + 0, this.zLevelFloat).tex((float)(textureX + 0) * f, (float)(textureY + 0) * f1).endVertex();
        tessellator.draw();
    }

    @Override
    public void render(ScreenPosition pos) {
        
    	int offsetX = 21;
        int offsetY = 14;
        int i = 80;
        int i2 = 16;
        Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();
        if (!collection.isEmpty()) {
        	GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableLighting();
            int l = 33;
            if (collection.size() > 5) {
            	l = 132 / (collection.size() - 1);
            }
            for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects()) {
            	Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
            	GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            	if (potion.hasStatusIcon()) {
            		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
				  	int i1 = potion.getStatusIconIndex();
				    this.drawTexturedModalRect(pos.getAbsoluteX() + offsetX - 20, pos.getAbsoluteY() + i2 - offsetY, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
				}
                String s1 = I18n.format(potion.getName(), new Object[0]);
                if (potioneffect.getAmplifier() == 1) {
                    s1 = String.valueOf(s1) + " " + I18n.format("enchantment.level.2", new Object[0]);
                } else if (potioneffect.getAmplifier() == 2) {
                    s1 = String.valueOf(s1) + " " + I18n.format("enchantment.level.3", new Object[0]);
                } else if (potioneffect.getAmplifier() == 3) {
                	s1 = String.valueOf(s1) + " " + I18n.format("enchantment.level.4", new Object[0]);
                }
                font.drawString(s1, pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + i2 - offsetY, 0xFFFFFF, true);
                String s = Potion.getDurationString(potioneffect);
                font.drawString(s, pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + i2 + 10 - offsetY, 0x7F7F7F, true);
            	i2 += l;
        	}
    	}
    }
    
}
