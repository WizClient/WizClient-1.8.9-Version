package WizClient.mods.impl;

import java.awt.Color;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class ModFPS extends ModDraggable {

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return 50;
		
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		//font.drawString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, Color.WHITE.getRGB());
		font.drawString("FPS: " + mc.getDebugFPS(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, Color.HSBtoRGB((float)(System.currentTimeMillis() % 2500L) / 2500F, 0.8F, 0.8F));
		
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
