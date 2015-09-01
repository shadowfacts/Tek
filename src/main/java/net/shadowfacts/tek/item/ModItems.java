package net.shadowfacts.tek.item;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.shadowfacts.shadowmc.util.FMLLifecycleEventHandler;
import net.shadowfacts.tek.item.base.TekItem;

/**
 * @author shadowfacts
 */
public class ModItems implements FMLLifecycleEventHandler {

//	Items
	public ItemMultimeter multimeter;
	public ItemDebug debugger;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		createItems();
		registerItems();
	}

	private void createItems() {
		multimeter = new ItemMultimeter();
		debugger = new ItemDebug();
	}

	private void registerItems() {
		register(multimeter);
		register(debugger);
	}

	private void register(TekItem item) {
		GameRegistry.registerItem(item, item.getName());
	}
}
