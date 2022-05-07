package WizClient.mods.impl.ToggleSprintAndSneak;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class ModToggleSneak extends ModDraggable{

	ScreenPosition pos;
	
	public boolean flyBoost = true;
	public float flyBoostFactor = 4;
	public int keyHoldTicks = 7;
	
	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		// TODO Auto-generated method stub
		
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
