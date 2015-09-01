package net.shadowfacts.tek.api.energy;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author shadowfacts
 */
public interface EnergyAcceptor extends EnergyConnector {

	int acceptEnergy(ForgeDirection from, int max, boolean simulate);

	int getStoredEnergy();

	int getMaxStoredEnergy();

}
