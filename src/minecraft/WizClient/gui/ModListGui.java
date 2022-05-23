package WizClient.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;

public class ModListGui extends GuiScreen {

	private int fadeIn;
	private boolean fadingOut;
	private int fadeOut;
	
	
	@Override
	public void initGui() {
		this.fadeIn = 0;
		this.fadingOut = false;
		this.fadeOut = 0;
	}
	
	@Override
	public void updateScreen() {
		if(fadeIn < 150) fadeIn++;
		
		if (fadingOut) { 
			if (fadeOut < 150) { 
				fadeOut++;
			} 
		}
		
		if(fadeOut >= 149) {
			this.mc.setIngameFocus();
			this.mc.displayGuiScreen((GuiScreen)null);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		updateScreen();
		this.drawRect(0, 0, 0 + fadeIn - fadeOut, this.height, new Color(40, 40, 40).getRGB());
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_ESCAPE) {
			fadingOut = true;
		}
	}
	
}
