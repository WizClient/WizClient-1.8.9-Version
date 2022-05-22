package WizClient.mods.impl.ModDirection;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import WizClient.mods.ModInstances;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class ModDirection extends ModDraggable {
	
	ScreenPosition pos;
	
    protected static ScaledResolution scaledResolution;
    public static String markerColor = "c";
    public static int compassIndex = 0;

	@Override
	public int getWidth() {
		return 102;
	}

	@Override
	public int getHeight() {
		return 15;
	}

	@Override
	public void render(ScreenPosition pos) {
		if(ModInstances.getModDirection().isEnabled()) {
        int direction = MathHelper.floor_double((double) (mc.thePlayer.rotationYaw * 256.0F / 360.0F) + 0.5D) & 255;
        int yBase = getY(1, 12);
        int xBase = getX(65);
        
        mc.getTextureManager().bindTexture(new ResourceLocation("WizClient/Direction.png"));
        if (direction < 128) {
        	Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction, this.compassIndex * 24, 100, 13);
        } else {
            Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction - 128, this.compassIndex * 24 + 12, 100, 13);
        }

        mc.fontRendererObj.drawString("§" + this.markerColor.toLowerCase() + "|§r", pos.getAbsoluteX() + xBase - 16, pos.getAbsoluteY() + yBase - 7, 16777215);
        }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if(ModInstances.getModDirection().isEnabled()) {
        int direction = MathHelper.floor_double((double) (mc.thePlayer.rotationYaw * 256.0F / 360.0F) + 0.5D) & 255;
        int yBase = getY(1, 12);
        int xBase = getX(65);
        
        mc.getTextureManager().bindTexture(new ResourceLocation("BlackClient/Modlar/Pusula.png"));
        if (direction < 128) {
        	Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction, this.compassIndex * 24, 100, 13);
        } else {
            Gui.drawTexturedModalRect(pos.getAbsoluteX() + xBase - 64, pos.getAbsoluteY() + yBase - 10, direction - 128, this.compassIndex * 24 + 12, 100, 13);
        }

        mc.fontRendererObj.drawString("§" + this.markerColor.toLowerCase() + "|§r", pos.getAbsoluteX() + xBase - 16, pos.getAbsoluteY() + yBase - 7, 16777215);
        }
	}
	
    private static int getX(int width) {
		return width;
    }

    private static int getY(int rowCount, int height) {
        return height;
    }
    
    public static enum Direction {
        S,
        SW,
        W,
        NW,
        N,
        NE,
        E,
        SE;
     }

}
