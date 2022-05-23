package WizClient.mods.impl;

import java.awt.Color;
import java.util.*;

import org.lwjgl.opengl.GL11;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ModItemCounter extends ModDraggable {
	
	private List<Item> list = new ArrayList<Item>();
	
	
	@Override
	public int getWidth() {
		return 40;
	}

	@Override
	public int getHeight() {
		return 25;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(this.getRemainingItems(Items.arrow) < 1) {
			font.drawString(this.getRemainingItems(Items.arrow) + "", pos.getAbsoluteX() + 30, pos.getAbsoluteY() + 15, Color.RED.getRGB());
		} else {
			font.drawString(this.getRemainingItems(Items.arrow) + "", pos.getAbsoluteX() + 30, pos.getAbsoluteY() + 15, Color.WHITE.getRGB());
		}
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), pos.getAbsoluteX() + 25, pos.getAbsoluteY() - 1);
        
        
        if(this.getRemainingItems(Items.golden_apple) < 1) {
			font.drawString(this.getRemainingItems(Items.golden_apple) + "", pos.getAbsoluteX() + 8, pos.getAbsoluteY() + 15, Color.RED.getRGB());
		} else {
			font.drawString(this.getRemainingItems(Items.golden_apple) + "", pos.getAbsoluteX() + 8, pos.getAbsoluteY() + 15, Color.WHITE.getRGB());
		}
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.golden_apple), pos.getAbsoluteX() + 3, pos.getAbsoluteY() - 1);

	}
	
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if(this.getRemainingItems(Items.arrow) < 1) {
			font.drawString(this.getRemainingItems(Items.arrow) + "", pos.getAbsoluteX() + 30, pos.getAbsoluteY() + 15, Color.RED.getRGB());
		} else {
			font.drawString(this.getRemainingItems(Items.arrow) + "", pos.getAbsoluteX() + 30, pos.getAbsoluteY() + 15, Color.WHITE.getRGB());
		}
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.arrow), pos.getAbsoluteX() + 25, pos.getAbsoluteY() - 1);
        
        
        if(this.getRemainingItems(Items.golden_apple) < 1) {
			font.drawString(this.getRemainingItems(Items.golden_apple) + "", pos.getAbsoluteX() + 8, pos.getAbsoluteY() + 15, Color.RED.getRGB());
		} else {
			font.drawString(this.getRemainingItems(Items.golden_apple) + "", pos.getAbsoluteX() + 8, pos.getAbsoluteY() + 15, Color.WHITE.getRGB());
		}
        this.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.golden_apple), pos.getAbsoluteX() + 3, pos.getAbsoluteY() - 1);

	}
	
	   private int getRemainingItems(Item item) {
		      int i = 0;

		      for(ItemStack itemstack : this.mc.thePlayer.inventory.mainInventory) {
		         if(itemstack != null && itemstack.getItem().equals(item)) {
		            i += itemstack.stackSize;
		         }
		      }

		      return i;
		   }
	
}