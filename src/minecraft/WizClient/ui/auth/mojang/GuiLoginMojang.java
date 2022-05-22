package WizClient.ui.auth.mojang;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import WizClient.ui.auth.SessionChanger;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

public class GuiLoginMojang extends GuiScreen {
    private GuiTextField username, password;

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            if(this.username.getText().equals("")) {
                this.mc.displayGuiScreen(new GuiLoginMojang());
            } else {
                SessionChanger.getInstance().setUser(this.username.getText(), this.password.getText());
            }

        }
    }

    @Override
    public void drawScreen(final int x2, final int y2, final float z2) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        this.username.drawTextBox();
        this.password.drawTextBox();
        Gui.drawCenteredString(this.mc.fontRendererObj, "Email & Password", (int)(this.width / 2), (int)(sr.getScaledHeight() / 2 - 65), -1);
        super.drawScreen(x2, y2, z2);
    }

    @Override
    public void initGui() {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50 - 10, this.height / 2, 120, 20, I18n.format("Login Mojang", new Object[0])));
        (this.username = new GuiTextField(100, this.fontRendererObj, this.width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 50, 120, 20)).setFocused(true);
        (this.password = new GuiTextField(100, this.fontRendererObj, this.width / 2 - 50 - 10, sr.getScaledHeight() / 2 - 25, 120, 20)).setFocused(false);
        Keyboard.enableRepeatEvents(true);
    }


    @Override
    protected void keyTyped(final char character, final int key) {
        try {
            super.keyTyped(character, key);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (character == '\t' && !this.username.isFocused()) {
            this.username.setFocused(true);
            this.password.setFocused(false);
        }
        if (character == '\t' && !this.password.isFocused()) {
            this.password.setFocused(true);
            this.username.setFocused(false);
        }
        if (character == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
        this.username.textboxKeyTyped(character, key);
        this.password.textboxKeyTyped(character, key);
    }

    @Override
    protected void mouseClicked(final int x2, final int y2, final int button) {
        try {
            super.mouseClicked(x2, y2, button);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.username.mouseClicked(x2, y2, button);
        this.password.mouseClicked(x2, y2, button);
    }

    @Override
    public void onGuiClosed() {
        mc.entityRenderer.loadEntityShader(null);
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
    }
}
