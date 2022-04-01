package WizClient.ui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;

public class WizClientMainMenu extends GuiScreen{
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("WizClient/main_menu.jpg"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(1, 10, height / 2 - 40, "SinglePlayer"));
		this.buttonList.add(new GuiButton(2, 10, height / 2 - 20, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, 10, height / 2, "Settings"));
		this.buttonList.add(new GuiButton(4, 10, height / 2 + 20, "Leave"));

		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
		case 1:
			mc.displayGuiScreen(new GuiSelectWorld(this));
			break;
		case 2:
			mc.displayGuiScreen(new GuiMultiplayer(this));
			break;
		case 3:
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
			break;
		case 4:
			mc.shutdown();
			break;
			

		default:
			break;
		}
		
		super.actionPerformed(button);
	}
}

