package net.shadowfacts.tek.block.tray;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.tek.block.base.TekBlock;
import net.shadowfacts.tek.client.render.tray.TrayISBRH;

/**
 * @author shadowfacts
 */
public class BlockTray extends TekBlock implements ITileEntityProvider {

	public BlockTray() {
		super("tray");
		setBlockBounds(0, 0, 0, 1, 2f/16, 1);
		setIsNormalBlock(false);
		setHarvestLevel("pickaxe", 1);
		setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = world.getTileEntity(x, y, z);
		return te instanceof TileEntityTray && ((TileEntityTray) te).handleRightClick(player);
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityTray) {
			((TileEntityTray)te).handleLeftClick(player);
		}
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.DOWN;
	}

	@Override
	public int getRenderType() {
		return TrayISBRH.RENDER_ID;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTray();
	}
}
