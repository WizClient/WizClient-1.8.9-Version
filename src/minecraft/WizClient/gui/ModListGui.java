package WizClient.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import WizClient.event.impl.RenderEvent;
import WizClient.util.RoundedButton;
import net.minecraft.client.gui.GuiScreen;

public class ModListGui extends GuiScreen {

	private int fadeIn;
	
	
	@Override
	public void initGui() {
		this.fadeIn = 0;
		
		
		this.buttonList.add(new RoundedButton(5, 10 + fadeIn, height / 2 + 70, 10, "ALT"));
		super.initGui();
	}
	
	@Override
	public void updateScreen() {
		if(fadeIn < 150) fadeIn+=2;
		
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		new RenderEvent().call();
		super.drawScreen(mouseX, mouseY, partialTicks);
		//new RenderEvent().call();
		drawDefaultBackground();
		updateScreen();
		this.drawRect(0, 0, 0 + fadeIn, this.height, new Color(40, 40, 40).getRGB());
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
}
