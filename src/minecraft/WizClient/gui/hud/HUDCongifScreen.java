package WizClient.gui.hud;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUDCongifScreen extends GuiScreen {

    private boolean dragged = false;

    protected boolean hovered;
	
	private static HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();
	
	private Optional<IRenderer> selectedRenderer = Optional.empty();
	
	private int prevX, prevY;
	
	private IRenderer draggedmodule;
	
	public HUDCongifScreen(HUDManager api) {
		Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();
		renderers.clear();
		for(IRenderer ren : registeredRenderers) {
			if(ren.isEnabled()) {
				ScreenPosition pos = ren.load();
				
				if(pos == null) {
					pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
				}
				
				
				adjustBounds(ren, pos);
				this.renderers.put(ren, pos);
			}
			
			
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		final float zBackup = this.zLevel;
		this.zLevel = 200;
		
		
		this.drawHollowRect(0, 0, this.width - 1, this.height - 1, new Color(69, 195, 218).getRGB());
		for(IRenderer renderer : renderers.keySet()) {
			
			ScreenPosition pos = renderers.get(renderer);
			renderer.renderDummy(pos);
			
			
			int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            this.hovered = mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth() && mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight();

            

            if(draggedmodule != renderer && draggedmodule != null) {
    			this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), new Color(147, 82, 244).getRGB());
            	continue;
            } else {
    			this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), new Color(147, 150, 244).getRGB());
            }
            

            if (this.hovered) {
                if (dragged) {
                    pos.setAbsolute(pos.getAbsoluteX() + mouseX - this.prevX, pos.getAbsoluteY() + mouseY - this.prevY);

                    draggedmodule = renderer;            
                    
                    adjustBounds(renderer, pos);

                    this.prevX = mouseX;
                    this.prevY = mouseY;
        			
                    continue;

                }
            }
			
			this.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), renderer.getWidth(), renderer.getHeight(), new Color(147, 82, 244).getRGB());
			
		}
        
		this.zLevel = zBackup;
		updateScreen();
	}
	
	private void drawHollowRect(int x, int y, int w, int h, int color) {
		this.drawHorizontalLine(x, x + w, y, color);
		this.drawHorizontalLine(x, x + w, y + h, color);
		
		
		this.drawVerticalLine(x, y + h, y, color);
		this.drawVerticalLine(x + w, y + h, y, color);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == Keyboard.KEY_ESCAPE || keyCode == Minecraft.getMinecraft().gameSettings.CLIENT_GUI_MOD_POS.getKeyCode()) {
			renderers.entrySet().forEach((entry) -> {
				entry.getKey().save(entry.getValue());
			});
			this.mc.displayGuiScreen(null);
		}
	}
	
	@Override
	protected void mouseClickMove(int x, int y, int button, long time) {
		if(selectedRenderer.isPresent()) {
			moveSelectedRendererBy(x - prevX, y - prevY);
		}
		updateScreen();
		this.prevX = x;
		this.prevY = y;
	}
	
	private void moveSelectedRendererBy(int offsetX, int offsetY) {
		IRenderer renderer = selectedRenderer.get();
		
		ScreenPosition pos = renderers.get(renderer);
		
		pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);
		adjustBounds(renderer, pos);

	}
	
	@Override
	public void onGuiClosed() {
		for(IRenderer renderer : renderers.keySet()) {
			renderer.save(renderers.get(renderer));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
	
	private void adjustBounds(IRenderer renderer, ScreenPosition pos) {
		
		
		ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
		
		int screenWidth = res.getScaledWidth();
		int screenHeight = res.getScaledHeight();
		
		
		
		int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
		int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));
		
		pos.setAbsolute(absoluteX, absoluteY);
		
		
		
	}
	
	@Override
	protected void mouseClicked(int x, int y, int button) throws IOException {
		this.prevX = x;
        this.prevY = y;

        dragged = true;

        loadMouseOver(x, y);
        super.mouseClicked(x, y, button);
	}
	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

        dragged = false;
        draggedmodule = null;

        super.mouseReleased(mouseX, mouseY, state);
    }
	
	private void loadMouseOver(int x, int y) {
		this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
	}
	
	private class MouseOverFinder implements Predicate<IRenderer> {

		private int mouseX, mouseY;
		
		public MouseOverFinder(int x, int y) {
			this.mouseX = x;
			this.mouseY = y;
		
		}

		@Override
		public boolean test(IRenderer renderer) {
			
		
			ScreenPosition pos = renderers.get(renderer);
			
			int absoluteX = pos.getAbsoluteX();
			int absoluteY = pos.getAbsoluteY();
			
			if(mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {
				
				if(mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {
					
					return true; 
					
				}
				
			}
			
			return false;
		}
		
	}
}
