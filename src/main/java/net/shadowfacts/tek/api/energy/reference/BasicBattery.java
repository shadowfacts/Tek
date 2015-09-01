package net.shadowfacts.tek.api.energy.reference;

import net.minecraft.nbt.NBTTagCompound;
import net.shadowfacts.tek.api.energy.Battery;

/**
 * Reference implementation of {@link net.shadowfacts.tek.api.energy.Battery}
 *
 * @author shadowfacts
 */
public class BasicBattery implements Battery {

	protected int energy;
	protected int capacity;
	protected int maxAccept;
	protected int maxSend;

	public BasicBattery(int capacity, int maxAccept, int maxSend) {
		this.capacity = capacity;
		this.maxAccept = maxAccept;
		this.maxSend = maxSend;
	}

	public BasicBattery(int capacity, int transfer) {
		this(capacity, transfer, transfer);
	}

//	NBT
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		if (energy < 0) energy = 0;

		tag.setInteger("Energy", energy);

		return tag;
	}

	public BasicBattery readFromNBT(NBTTagCompound tag) {
		energy = tag.getInteger("Energy");

		if (energy > capacity) energy = capacity;

		return this;
	}

//	Getters and Setters
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getMaxAccept() {
		return maxAccept;
	}

	public void setMaxAccept(int maxAccept) {
		this.maxAccept = maxAccept;
	}

	public int getMaxSend() {
		return maxSend;
	}

	public void setMaxSend(int maxSend) {
		this.maxSend = maxSend;
	}

	//	Battery
	@Override
	public int acceptEnergy(int max, boolean simulate) {
		int accepted = Math.min(capacity - energy, Math.min(maxAccept, max));

		if (!simulate) energy += accepted;

		return accepted;
	}

	@Override
	public int sendEnergy(int max, boolean simulate) {
		int sent = Math.min(energy, Math.min(maxSend, max));

		if (!simulate) energy -= sent;

		return sent;
	}

	@Override
	public int getStoredEnergy() {
		return energy;
	}

	@Override
	public int getMaxStoredEnergy() {
		return capacity;
	}

}
