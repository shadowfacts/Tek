package net.shadowfacts.tek.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Used for TileEntities (and other interfaces) that connect to other energy devices
 *
 * @author shadowfacts
 */
public interface EnergyConnector {

	boolean canEnergyConnect(ForgeDirection side);

}
