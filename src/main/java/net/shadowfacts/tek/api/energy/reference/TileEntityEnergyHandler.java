package net.shadowfacts.tek.api.energy.reference;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.tek.api.energy.EnergyHandler;

/**
 * @author shadowfacts
 */
public class TileEntityEnergyHandler extends TileEntity implements EnergyHandler {

	protected BasicBattery battery = new BasicBattery(16000, 256);

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		battery.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		battery.readFromNBT(tag);
	}

	@Override
	public boolean canEnergyConnect(ForgeDirection side) {
		return true;
	}

	@Override
	public int acceptEnergy(ForgeDirection from, int max, boolean simulate) {
		return battery.acceptEnergy(max, simulate);
	}

	@Override
	public int sendEnergy(ForgeDirection side, int max, boolean simulate) {
		return battery.sendEnergy(max, simulate);
	}

	@Override
	public int getStoredEnergy() {
		return battery.getStoredEnergy();
	}

	@Override
	public int getMaxStoredEnergy() {
		return battery.getMaxStoredEnergy();
	}
}
