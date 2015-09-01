package net.shadowfacts.tek.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.tek.item.base.TekItem;

/**
 * Debug item
 *
 * @author shadowfacts
 */
public class ItemDebug extends TekItem {

	public ItemDebug() {
		super("debug");
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

		Block block = world.getBlock(x, y, z);
		TileEntity te = world.getTileEntity(x, y, z);

		return true;
	}
}
