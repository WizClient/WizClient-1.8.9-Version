package WizClient.gui.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ScreenPosition {
	
	public static final Minecraft mc = Minecraft.getMinecraft();
	
	private double x, y;
	
	public ScreenPosition(double x, double y) {
		setRelative(x, y);
	}
	
	public ScreenPosition(int x, int y) {
		setAbsolute(x, y);
	}
	
	public static ScreenPosition fromRelativePosition(double x, double y) {
		return new ScreenPosition(x, y);
	}
	
	public static ScreenPosition fromAbsolute(int x, int y) {
		return new ScreenPosition(x, y);
	}
	
	public int getAbsoluteX() {
		ScaledResolution res = new ScaledResolution(mc);
		
		return (int)(x * res.getScaledWidth());
	}
	
	public int getAbsoluteY() {
		ScaledResolution res = new ScaledResolution(mc);
		
		return (int)(y * res.getScaledHeight());
	}
	
	public double getRelativeX() {
		return y;
	}
	
	public double getRelativeY() {
		return x;
	}
	
	public void setAbsolute(double x, double y) {
		ScaledResolution res = new ScaledResolution(mc);
		
		this.x = (double) x / res.getScaledWidth();
		this.y = (double) y / res.getScaledHeight();
	}
	
	public void setRelative(double x, double y) {
		this.x = x;
		this.y = y;
		
	}
}
