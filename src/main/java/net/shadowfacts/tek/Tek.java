package net.shadowfacts.tek;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.shadowfacts.shadowmc.util.FMLLifecycleEventHandler;
import net.shadowfacts.tek.block.ModBlocks;
import net.shadowfacts.tek.item.ModItems;
import net.shadowfacts.tek.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
@Mod(modid = Tek.modId, name = Tek.name, version = Tek.version)
public class Tek {

	public static final String modId = "Tek";
	public static final String name = "Tek";
	public static final String version = "1.0.0";
	private static final String proxyPrefix = "net.shadowfacts.tek.proxy.";

	public static Logger log = LogManager.getLogger(modId);

	@Mod.Instance(modId)
	public static Tek instance;

	@SidedProxy(serverSide = proxyPrefix + "CommonProxy", clientSide = proxyPrefix + "ClientProxy")
	public static CommonProxy proxy;

	private static List<FMLLifecycleEventHandler> handlers;

//	Content
	public static ModBlocks blocks = new ModBlocks();
	public static ModItems items = new ModItems();

	public static SimpleNetworkWrapper network;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network = NetworkRegistry.INSTANCE.newSimpleChannel(modId);

		handlers = Arrays.asList(blocks, items, proxy);

		handlers.stream().forEach(handler -> handler.preInit(event));
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		handlers.stream().forEach(handler -> handler.init(event));
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		handlers.stream().forEach(handler -> handler.postInit(event));
	}

}
