package net.shadowfacts.tek.client.render.tray;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.shadowfacts.tek.client.model.ModelTray;
import org.lwjgl.opengl.GL11;

/**
 * @author shadowfacts
 */
public class TrayTESR extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation("tek", "textures/model/tray.png");
	private ModelTray model = new ModelTray();

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float delta) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float)x + .5f, (float)y + .13f, (float)z + .5f);
		GL11.glRotatef(180, 1, 0, 0);

		bindTexture(texture);
		model.renderAll();


		GL11.glPopMatrix();
	}

}
