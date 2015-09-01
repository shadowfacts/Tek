package net.shadowfacts.tek.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.shadowfacts.shadowmc.util.FMLLifecycleEventHandler;
import net.shadowfacts.tek.Tek;
import net.shadowfacts.tek.block.tray.TileEntityTray;
import net.shadowfacts.tek.machine.furnace.TileEntityEnergyFurnace;
import net.shadowfacts.tek.machine.generator.charcoal.TileEntityGeneratorCharcoal;
import net.shadowfacts.tek.packet.ModPackets;
import net.shadowfacts.tek.packet.PacketEnergyRequest;
import net.shadowfacts.tek.packet.PacketEnergyResponse;

/**
 * @author shadowfacts
 */
public abstract class CommonProxy implements FMLLifecycleEventHandler {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		registerTileEntities();
		registerPackets();
		registerRenderers();
	}

	private void registerTileEntities() {
//		Machines
		GameRegistry.registerTileEntity(TileEntityGeneratorCharcoal.class, Tek.blocks.generatorCharcoal.getName());
		GameRegistry.registerTileEntity(TileEntityEnergyFurnace.class, Tek.blocks.energyFurnace.getName());

//		Miscellaneous
		GameRegistry.registerTileEntity(TileEntityTray.class, Tek.blocks.tray.getName());
	}

	private void registerPackets() {
		Tek.network.registerMessage(PacketEnergyRequest.Handler.class, PacketEnergyRequest.class, ModPackets.EnergyRequest, Side.SERVER);
		Tek.network.registerMessage(PacketEnergyResponse.Handler.class, PacketEnergyResponse.class, ModPackets.EnergyResponse, Side.CLIENT);
	}

	abstract void registerRenderers();

}
