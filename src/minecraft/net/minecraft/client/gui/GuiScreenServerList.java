package net.minecraft.client.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import javax.lang.model.element.TypeElement;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import WizClient.util.ServerDataFeatured;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiScreenServerList extends GuiScreen
{
	
	private ServerSelectionList serverListSelector;
	
    private final GuiScreen field_146303_a;
    private final ServerData field_146301_f;
    private GuiTextField field_146302_g;
    
    private ServerDataFeatured server;

    public GuiScreenServerList(GuiScreen p_i1031_1_, ServerData p_i1031_2_)
    {
        this.field_146303_a = p_i1031_1_;
        this.field_146301_f = p_i1031_2_;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.field_146302_g.updateCursorCounter();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 116 + 12, I18n.format("selectServer.select", new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 140 + 12, I18n.format("gui.cancel", new Object[0])));
        this.field_146302_g = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 166, 200, 20);
        this.field_146302_g.setMaxStringLength(128);
        this.field_146302_g.setFocused(true);
        this.field_146302_g.setText(this.mc.gameSettings.smoothCamera);
        ((GuiButton)this.buttonList.get(0)).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
        this.mc.gameSettings.smoothCamera = this.field_146302_g.getText();
        this.mc.gameSettings.saveOptions();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 1) // cancel
            {
                this.field_146303_a.confirmClicked(false, 0);
            }
            else if (button.id == 0) // direct connect
            {
                this.field_146301_f.serverIP = this.field_146302_g.getText();
                this.field_146303_a.confirmClicked(true, 0);
            }
        }
    }

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    	
        if (this.field_146302_g.textboxKeyTyped(typedChar, keyCode))
        {
            ((GuiButton)this.buttonList.get(0)).enabled = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
        }
        else if (keyCode == 28 || keyCode == 156)
        {
            this.actionPerformed((GuiButton)this.buttonList.get(0));
        }
                
        server = new ServerDataFeatured("", this.field_146302_g.getText());
        
        System.out.println(server.serverMOTD);
        
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.field_146302_g.mouseClicked(mouseX, mouseY, mouseButton);
    }

    
    private static final ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
    
    
    //=======================================    The news parts of direct connect    =========================================
    
    /*
    private void drawServer() {
    	this.mc = Minecraft.getMinecraft();
        ResourceLocation serverIcon = new ResourceLocation("servers/" + this.server.serverIP + "/icon");
        DynamicTexture Icon = (DynamicTexture)this.mc.getTextureManager().getTexture(serverIcon);
        
        if (!this.server.field_78841_f)
        {
            this.server.field_78841_f = true;
            this.server.pingToServer = -2L;
            this.server.serverMOTD = "";
            this.server.populationInfo = "";
            field_148302_b.submit(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        //ServerListEntryNormal.this.owner.getOldServerPinger().ping(server);
                    	
                    }
                    catch (UnknownHostException var2)
                    {
                        server.pingToServer = -1L;
                        server.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t resolve hostname";
                    }
                    catch (Exception var3)
                    {
                        server.pingToServer = -1L;
                        server.serverMOTD = EnumChatFormatting.DARK_RED + "Can\'t connect to server.";
                    }
                }
            });
        }
    }
    */
    
    
    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    
    private void drawActualServer() {
    	this.drawRect(400, 400, 40, 400, -1);
    }
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
    	
        this.drawDefaultBackground();
        
        
        this.drawCenteredString(this.fontRendererObj, I18n.format("selectServer.direct", new Object[0]), this.width / 2, 20, 16777215);
        this.drawString(this.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), this.width / 2 - 100, 150, 10526880);
        this.field_146302_g.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
