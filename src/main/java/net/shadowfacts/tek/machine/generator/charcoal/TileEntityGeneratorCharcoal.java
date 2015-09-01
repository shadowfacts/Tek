package net.shadowfacts.tek.machine.generator.charcoal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.tek.Tek;
import net.shadowfacts.tek.api.energy.EnergyHandler;
import net.shadowfacts.tek.api.energy.reference.BasicBattery;
import net.shadowfacts.tek.tileentity.TekTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityGeneratorCharcoal extends TekTileEntity implements EnergyHandler, ISidedInventory {

	private static final int GENERATION_PER_TICK = 4;
	private static final int TICKS_PER_CHARCOAL = 2000;
	private static final int COOLDOWN_TICKS = 200;

	private BasicBattery battery;
	private ItemStack fuelStack;

	private boolean isGenerating = false;
	private int ticks = 0;

	public TileEntityGeneratorCharcoal() {
		battery = new BasicBattery(16000, 16);
	}

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote &&isGenerating || canGenerate()) {
			generate();
		}
	}

	private boolean canGenerate() {
//		Is there room in the battery to add energy
//		Is there charcoal
		return battery.getStoredEnergy() + GENERATION_PER_TICK <= battery.getMaxStoredEnergy() &&
				fuelStack != null && fuelStack.getItem() == Items.coal && fuelStack.getItemDamage() == 1 && fuelStack.stackSize >= 1;
	}

	private void generate() {
		if (isGenerating) {
			if (ticks < TICKS_PER_CHARCOAL) {
				ticks++;
				battery.acceptEnergy(GENERATION_PER_TICK);
			} else if (ticks <= TICKS_PER_CHARCOAL + COOLDOWN_TICKS) {
				ticks++;
			} else {
				isGenerating = false;
			}
		} else {
			fuelStack.stackSize--;
			if (fuelStack.stackSize <= 0) {
				fuelStack = null;
			}
			isGenerating = true;
			ticks = 0;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		battery.writeToNBT(tag);
		if (fuelStack != null) {
			NBTTagCompound stack = new NBTTagCompound();
			fuelStack.writeToNBT(stack);
			tag.setTag("FuelStack", stack);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		battery.readFromNBT(tag);
		if (tag.hasKey("FuelStack")) {
			NBTTagCompound stack = tag.getCompoundTag("FuelStack");
			fuelStack = ItemStack.loadItemStackFromNBT(stack);
		}
	}

	//	EnergyConnector
	@Override
	public boolean canEnergyConnect(ForgeDirection side) {
		return true;
	}

//	EnergyHandler
	@Override
	public int sendEnergy(ForgeDirection side, int max, boolean simulate) {
		return battery.sendEnergy(max, simulate);
	}

	@Override
	public int acceptEnergy(ForgeDirection from, int max, boolean simulate) {
		return 0;
	}

	@Override
	public int getStoredEnergy() {
		return battery.getStoredEnergy();
	}

	@Override
	public int getMaxStoredEnergy() {
		return battery.getMaxStoredEnergy();
	}

//	IInventory
	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return fuelStack;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (fuelStack != null) {
			ItemStack stack;

			if (fuelStack.stackSize <= amount) {
				stack = fuelStack;
				setInventorySlotContents(slot, null);
				markDirty();
			} else {
				stack = fuelStack.splitStack(amount);

				if (fuelStack.stackSize <= 0) setInventorySlotContents(slot, null);

				markDirty();
			}

			return stack;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (slot == 0) return fuelStack;
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		fuelStack = stack;

		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}

		markDirty();
	}

	@Override
	public String getInventoryName() {
		return Tek.blocks.generatorCharcoal.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {

	}

	@Override
	public void closeInventory() {

	}

	@Override
	public boolean isItemValidForSlot(int side, ItemStack stack) {
		return stack.getItem() != null && stack.getItem() == Items.coal && stack.getItemDamage() == 1;
	}

//	ISidedInventory
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{0};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return true;
	}
}
