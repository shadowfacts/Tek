package net.shadowfacts.tek.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author shadowfacts
 */
public interface EnergySender extends EnergyConnector {

	int sendEnergy(ForgeDirection side, int max, boolean simulate);

	int getStoredEnergy();

	int getMaxStoredEnergy();

}
