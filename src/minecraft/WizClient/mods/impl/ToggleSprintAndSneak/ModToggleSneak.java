package WizClient.mods.impl.ToggleSprintAndSneak;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class ModToggleSneak extends ModDraggable{

	ScreenPosition pos;
	
	public boolean flyBoost = true;
	public float flyBoostFactor = 4;
	public int keyHoldTicks = 15;
	
	private String textToRender = "";
	
	@Override
	public int getWidth() {
		return 104;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		textToRender = mc.thePlayer.movementInput.getDisplayTextSneak();
		font.drawString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[Sneaking (Toggled)]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
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
