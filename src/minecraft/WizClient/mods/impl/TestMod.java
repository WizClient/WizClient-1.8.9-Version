package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class TestMod extends ModDraggable {

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("Ceci est un test");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("Ceci est un test", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, Color.CYAN.getRGB());
		
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("Ceci est un test", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, Color.WHITE.getRGB());
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
}
