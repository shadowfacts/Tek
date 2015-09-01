package net.shadowfacts.tek.api.energy;


/**
 * @author shadowfacts
 */
public interface Battery {

	int acceptEnergy(int max, boolean simulate);

	default int acceptEnergy(int max) {
		return acceptEnergy(max, false);
	}

	int sendEnergy(int max, boolean simulate);

	default int sendEnergy(int max) {
		return sendEnergy(max, false);
	}

	int getStoredEnergy();

	int getMaxStoredEnergy();

}
