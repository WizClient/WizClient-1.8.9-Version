package WizClient.mods.impl.ToggleSprintAndSneak;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;

public class ModToggleSprint extends ModDraggable{

	ScreenPosition pos;
	
	public boolean flyBoost = true;
	public float flyBoostFactor = 4;
	public int keyHoldTicks = 7;
	
	private String textToRender = "";
	
	@Override
	public int getWidth() {
		//return font.getStringWidth(mc.thePlayer.movementInput.getDisplayText());
		return 40;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		
		
		//textToRender = mc.thePlayer.movementInput.getDisplayText();
		System.out.println(mc.thePlayer.movementInput);
		//font.drawString(textToRender, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
		
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString("[Sprinting (Toggled)]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
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
