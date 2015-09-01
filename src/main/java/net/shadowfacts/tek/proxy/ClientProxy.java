package net.shadowfacts.tek.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.shadowfacts.tek.block.tray.TileEntityTray;
import net.shadowfacts.tek.client.render.tray.TrayISBRH;
import net.shadowfacts.tek.client.render.tray.TrayTESR;

/**
 * @author shadowfacts
 */
public class ClientProxy extends CommonProxy {

	@Override
	void registerRenderers() {
		registerTESRs();
		registerISBRHs();
	}

	private void registerTESRs() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTray.class, new TrayTESR());
	}

	private void registerISBRHs() {
		RenderingRegistry.registerBlockHandler(TrayISBRH.RENDER_ID, new TrayISBRH());
	}

}
