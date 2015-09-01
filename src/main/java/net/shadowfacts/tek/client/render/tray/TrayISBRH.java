package net.shadowfacts.tek.client.render.tray;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import net.shadowfacts.tek.block.tray.TileEntityTray;

/**
 * @author shadowfacts
 */
public class TrayISBRH implements ISimpleBlockRenderingHandler {

	public static final int RENDER_ID = RenderingRegistry.getNextAvailableRenderId();

	private static final TileEntityTray te = new TileEntityTray();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0, 0, 0, 0);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return RENDER_ID;
	}
}
