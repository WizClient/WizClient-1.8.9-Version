package WizClient.mods;

import java.io.File;

import WizClient.FileManager;
import WizClient.gui.hud.IRenderer;
import WizClient.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
	
	protected ScreenPosition pos;
	
	public ModDraggable() {
		pos = loadPositionFromFile();
	}
	

	@Override
	public ScreenPosition load() {
		return pos;
	}
	
	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		savePositionToFile();
		
	}
	
	private File getFolders() {
		File folder = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
		folder.mkdir();
		return folder;
	}
	
	private void savePositionToFile() {
		FileManager.writeJsonToFile(new File(getFolders(), "pos.json"), pos);
		
	}


	private ScreenPosition loadPositionFromFile() {
		ScreenPosition loaded = FileManager.readFromJson(new File(getFolders(), "pos.json"), ScreenPosition.class);
		
		if(loaded == null) {
			loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
			this.pos = loaded;
			savePositionToFile();
		}
		
		return loaded;
	}
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}
	
}
