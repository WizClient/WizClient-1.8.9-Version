package WizClient.gui.modoptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import WizClient.gui.hud.HUDManager;
import WizClient.gui.hud.IRenderer;
import WizClient.mods.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;

public class ScrollListModToggle extends GuiListExtended {

	private final List<ModEntry> entrys = new ArrayList<ModEntry>();

	public ScrollListModToggle(Minecraft mcIn, GuiModToggle inGui) {
		super(mcIn, inGui.width, inGui.height, 63, inGui.height - 32, 20);
		for(IRenderer r : HUDManager.getInstance().getRegisteredRenderers()) {
			if(r instanceof Mod) {
				Mod m = (Mod)r;
				entrys.add(new ModEntry(inGui, m));
			}
		}
		 Collections.sort(this.entrys); 
	}

	@Override
	public IGuiListEntry getListEntry(int index) {
		return entrys.get(index);
	}

	@Override
	protected int getSize() {
		return entrys.size();
	}

}
