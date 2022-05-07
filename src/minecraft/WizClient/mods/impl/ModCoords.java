package WizClient.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import WizClient.gui.hud.ScreenPosition;
import WizClient.mods.ModDraggable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

public class ModCoords extends ModDraggable {

	private ScreenPosition pos;
	
	@Override
	public int getWidth() {
		return 60;
	}

	@Override
	public int getHeight() {
		return 35;
	}

	@Override
	public void render(ScreenPosition pos) {
		font.drawString("X: " + (int)mc.getRenderViewEntity().posX, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
		font.drawString("Y: " + (int)mc.getRenderViewEntity().getEntityBoundingBox().minY, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + font.FONT_HEIGHT, -1);
		font.drawString("Z: " + (int)mc.getRenderViewEntity().posZ, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + font.FONT_HEIGHT*2, -1);
			
		Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ));
		String biome = chunk.getBiome(new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ), this.mc.theWorld.getWorldChunkManager()).biomeName;
		font.drawString("Biome: " + biome, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1 + font.FONT_HEIGHT*3, -1);
	}

	private String getCoordsString() {
		return String.format(
				"X: %.3f\nY: %.3f\nZ: %.3f",
				mc.getRenderViewEntity().posX,
				mc.getRenderViewEntity().getEntityBoundingBox().minY,
				mc.getRenderViewEntity().posZ
				
			);
		
	}

	@Override
	public void save(ScreenPosition pos) {
		this.pos = pos;
		
	}

	@Override
	public ScreenPosition load() {
		return pos;
	}

}
