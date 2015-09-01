package net.shadowfacts.tek.api.energy;

import net.minecraft.item.ItemStack;

/**
 * @author shadowfacts
 */
public interface EnergyItem {

	int acceptEnergy(ItemStack stack, int max, boolean simulate);

	default int acceptEnergy(ItemStack stack, int max) {
		return acceptEnergy(stack, max, false);
	}

	int sendEnergy(ItemStack stack, int max, boolean simulate);

	default int sendEnergy(ItemStack stack, int max) {
		return sendEnergy(stack, max, false);
	}

	int getStoredEnergy(ItemStack stack);

	int getMaxStoredEnergy(ItemStack stack);

}
