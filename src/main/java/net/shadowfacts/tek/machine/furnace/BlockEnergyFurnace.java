package net.shadowfacts.tek.machine.furnace;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.shadowfacts.tek.block.base.TekBlock;

/**
 * @author shadowfacts
 */
public class BlockEnergyFurnace extends TekBlock implements ITileEntityProvider {

	public BlockEnergyFurnace() {
		super(Material.rock, "energyfurnace");
	}

//	ITileEntityProvider
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEnergyFurnace();
	}
}
