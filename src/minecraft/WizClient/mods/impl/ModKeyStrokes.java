package WizClient.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;

public class ModKeyStrokes extends ModDraggable{

	
	public static enum KeystrokesMode {
		
		ZQSD(Key.forward, Key.left, Key.back, Key.right),
		ZQSD_MOUSE(Key.forward, Key.left, Key.back, Key.right, Key.LMB, Key.RMB),
		ZQSD_SPRINT(Key.forward, Key.left, Key.back, Key.right, new Key("§m-----", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 41, 58, 18)),
		ZQSD_SPRINT_MOUSE(Key.forward, Key.left, Key.back, Key.right, Key.LMB, Key.RMB, new Key("§m-----", Minecraft.getMinecraft().gameSettings.keyBindSneak, 1, 61, 58, 18))
		;
		
		private final Key[] keys;
		private int with = 0;
		private int height = 0;
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for(Key key : keys) {
				this.with = Math.max(this.with, key.getX() +  key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWith() {
			return with;
		}
		
		public Key[] getKeys() {
			return keys;
		}
	}
	
	private static class Key {
		
		private static final Key forward = new Key("Z", Minecraft.getMinecraft().gameSettings.keyBindLeft, 21, 1, 18, 18);
		private static final Key left = new Key("Q", Minecraft.getMinecraft().gameSettings.keyBindBack, 1, 21, 18, 18);
		private static final Key back = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindRight, 21, 21, 18, 18);
		private static final Key right = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindJump, 41, 21, 18, 18);
	
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindPickBlock, 1, 41, 28, 18);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindDrop, 31, 41, 28, 18);
		
		private final String name;
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int with;
		private final int height;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int with, int height) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.with = with;
			this.height = height;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public int getHeight() {
			return height;
		}
		
		public String getName() {
			return name;
		}
		
		public int getWidth() {
			return with;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	private KeystrokesMode mod = KeystrokesMode.ZQSD_SPRINT_MOUSE;
	
	public void setMod(KeystrokesMode mod) {
		this.mod = mod;
	}
	
	@Override
	public int getWidth() {
		return mod.getWith();
	}

	@Override
	public int getHeight() {
		return mod.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {
		GL11.glPushMatrix();
		
		
		for (Key key : mod.getKeys()) {
			int textWidth = font.getStringWidth(key.getName());
			
			Gui.drawRect(
					pos.getAbsoluteX() + key.getX(),  
					pos.getAbsoluteY() + key.getY(), 
					pos.getAbsoluteX() + key.getX() + key.getWidth(), 
					pos.getAbsoluteY() + key.getY() + key.getHeight(),
					key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB()
				);
			
			font.drawString(
					key.getName(),
					pos.getAbsoluteX() + key.getX() + key.getWidth()  / 2 - textWidth / 2,
					pos.getAbsoluteY() + key.getY() + key.getHeight()  / 2 - 4,
					key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB()
				);
		}
		
		GL11.glPopMatrix();
		
	}

}
