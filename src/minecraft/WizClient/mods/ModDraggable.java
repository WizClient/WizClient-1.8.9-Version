package WizClient.mods;

import WizClient.gui.hud.IRenderer;
import WizClient.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}
	
}
