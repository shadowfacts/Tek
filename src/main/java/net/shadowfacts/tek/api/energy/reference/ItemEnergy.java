package net.shadowfacts.tek.api.energy.reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.shadowfacts.tek.api.energy.EnergyItem;

/**
 * Reference implementation of {@link net.shadowfacts.tek.api.energy.EnergyItem}
 *
 * @author shadowfacts
 */
public class ItemEnergy extends Item implements EnergyItem {

	protected int capacity;
	protected int maxAccept;
	protected int maxSend;

	public ItemEnergy(int capacity, int maxAccept, int maxSend) {
		this.capacity = capacity;
		this.maxAccept = maxAccept;
		this.maxSend = maxSend;
	}

	public ItemEnergy(int capacity, int maxTransfer) {
		this(capacity, maxTransfer, maxTransfer);
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setMaxTransfer(int maxTransfer) {
		setMaxAccept(maxTransfer);
		setMaxSend(maxTransfer);
	}

	public void setMaxAccept(int maxAccept) {
		this.maxAccept = maxAccept;
	}

	public void setMaxSend(int maxSend) {
		this.maxSend = maxSend;
	}

	@Override
	public int acceptEnergy(ItemStack stack, int max, boolean simulate) {
		if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound();

		int energy = stack.stackTagCompound.getInteger("Energy");
		int accepted = Math.min(capacity - energy, Math.min(maxAccept, max));

		if (!simulate) {
			energy += accepted;
			stack.stackTagCompound.setInteger("Energy", energy);
		}

		return accepted;
	}

	@Override
	public int sendEnergy(ItemStack stack, int max, boolean simulate) {
		if (stack.stackTagCompound == null) stack.stackTagCompound = new NBTTagCompound();

		int energy = stack.stackTagCompound.getInteger("Energy");
		int sent = Math.min(energy, Math.min(maxSend, max));

		if (!simulate) {
			energy -= sent;
			stack.stackTagCompound.setInteger("Energy", energy);
		}

		return sent;
	}

	@Override
	public int getStoredEnergy(ItemStack stack) {
		if (stack.stackTagCompound == null || !stack.stackTagCompound.hasKey("Energy")) return 0;
		return stack.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxStoredEnergy(ItemStack stack) {
		return capacity;
	}

}
