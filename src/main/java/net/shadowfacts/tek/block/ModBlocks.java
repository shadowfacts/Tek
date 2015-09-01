package net.shadowfacts.tek.block;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.shadowmc.util.FMLLifecycleEventHandler;
import net.shadowfacts.tek.block.base.TekBlock;
import net.shadowfacts.tek.block.tray.BlockTray;
import net.shadowfacts.tek.item.tray.ItemBlockTray;
import net.shadowfacts.tek.machine.furnace.BlockEnergyFurnace;
import net.shadowfacts.tek.machine.generator.charcoal.BlockGeneratorCharcoal;

/**
 * @author shadowfacts
 */
public class ModBlocks implements FMLLifecycleEventHandler {

//	Machines
	public BlockGeneratorCharcoal generatorCharcoal;
	public BlockEnergyFurnace energyFurnace;

//	Miscellaneous
	public BlockTray tray;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		createBlocks();
		registerBlocks();
	}

	private void createBlocks() {
		generatorCharcoal = new BlockGeneratorCharcoal();
		energyFurnace = new BlockEnergyFurnace();
		tray = new BlockTray();
	}

	private void registerBlocks() {
		register(generatorCharcoal);
		register(energyFurnace);
		GameRegistry.registerBlock(tray, ItemBlockTray.class, tray.getName());
	}

	private void register(TekBlock block) {
		GameRegistry.registerBlock(block, block.getName());
	}

}
