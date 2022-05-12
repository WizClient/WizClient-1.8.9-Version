package WizClient.mods.impl;

import java.util.Calendar;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class ModCurrentTime extends ModDraggable {

	private ScreenPosition pos;
	
	
	@Override
	public int getWidth() {
		return 27;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		
		Calendar now = Calendar.getInstance();
		String currenthour = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
		
		font.drawString(currenthour, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
		
	}
	
}
