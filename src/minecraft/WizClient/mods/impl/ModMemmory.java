package WizClient.mods.impl;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.renderer.OpenGlHelper;

public class ModMemmory extends ModDraggable {

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return 110;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}
	
	private static long bytesToMb(long bytes)
    {
        return bytes / 1024L / 1024L;
    }

	@Override
	public void render(ScreenPosition pos) {
		long i = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long k = Runtime.getRuntime().freeMemory();
        long l = j - k;
        
        String allmemmory = String.format("Mem: % 2d%% %03d/%03dMB",new Object[]{Long.valueOf(l * 100L / i), Long.valueOf(bytesToMb(l)), Long.valueOf(bytesToMb(i))});
        String memoryPercent = String.format("Mem: % 2d%%",new Object[]{Long.valueOf(l * 100L / i)});

        
        //font.drawString(memoryPercent, pos.getAbsoluteX() + 1, pos.getAbsoluteY(),  -1);
        font.drawString(allmemmory, pos.getAbsoluteX() + 1, pos.getAbsoluteY(),  -1);
	}

}
