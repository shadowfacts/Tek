package net.shadowfacts.tek.machine.generator.charcoal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.tek.machine.TekMachine;

/**
 * @author shadowfacts
 */
public class BlockGeneratorCharcoal extends TekMachine implements ITileEntityProvider {

	public BlockGeneratorCharcoal() {
		super(Material.rock, "generator.charcoal");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem();
		if (stack != null && stack.getItem() != null && stack.getItem() == Items.coal && stack.getItemDamage() == 1) {

		}
		return false;
	}

	//	ITileEntityProvider
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityGeneratorCharcoal();
	}
}
