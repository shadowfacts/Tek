package net.shadowfacts.tek.machine.furnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.shadowfacts.tek.Tek;
import net.shadowfacts.tek.api.energy.EnergyHandler;
import net.shadowfacts.tek.api.energy.reference.BasicBattery;
import net.shadowfacts.tek.tileentity.TekTileEntity;

/**
 * @author shadowfacts
 */
public class TileEntityEnergyFurnace extends TekTileEntity implements ISidedInventory, EnergyHandler {

	private BasicBattery battery;

	private ItemStack[] stacks = new ItemStack[2];

	public TileEntityEnergyFurnace() {
		battery = new BasicBattery(4000, 10);
	}


	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		battery.writeToNBT(tag);

		NBTTagList stackList = new NBTTagList();
		for (int i = 0; i < stacks.length; i++) {
			ItemStack stack = stacks[i];
			if (stack != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stack.writeToNBT(stackTag);
				tag.setInteger("Index", i);
				stackList.appendTag(stackTag);
			}
		}
		tag.setTag("Stacks", stackList);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		battery.readFromNBT(tag);

		stacks = new ItemStack[2];
		NBTTagList stackList = tag.getTagList("Stacks", 10);

		for (int i = 0; i < stackList.tagCount(); i++) {
			NBTTagCompound stackTag = stackList.getCompoundTagAt(i);
			int index = stackTag.getInteger("Index");
			if (index >= 0 && index < stacks.length) {
				stacks[index] = ItemStack.loadItemStackFromNBT(stackTag);
			}
		}
	}

	//	EnergyHandler
	@Override
	public int acceptEnergy(ForgeDirection from, int max, boolean simulate) {
		return battery.acceptEnergy(max, simulate);
	}

	@Override
	public int sendEnergy(ForgeDirection side, int max, boolean simulate) {
		return battery.acceptEnergy(max, simulate);
	}

	@Override
	public int getStoredEnergy() {
		return battery.getStoredEnergy();
	}

	@Override
	public int getMaxStoredEnergy() {
		return battery.getMaxStoredEnergy();
	}

	@Override
	public boolean canEnergyConnect(ForgeDirection side) {
		return true;
	}

//	ISidedInventory
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		switch (ForgeDirection.getOrientation(side)) {
			case DOWN:
				return new int[]{1};
			default:
				return new int[]{0};
		}
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return slot == 0;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return slot == 1;
	}

	@Override
	public int getSizeInventory() {
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return stacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if (stacks[slot] != null) {
			ItemStack stack;

			if (stacks[slot].stackSize <= amount) {
				stack = stacks[slot];
				setInventorySlotContents(slot, null);
			} else {
				stack = stacks[slot].splitStack(amount);

				if (stacks[slot].stackSize <= 0) setInventorySlotContents(slot, null);
			}

			markDirty();
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (stacks[slot] != null) {
			ItemStack stack = stacks[slot];
			stacks[slot] = null;
			return stack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		stacks[slot] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return Tek.blocks.energyFurnace.getName();
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 && FurnaceRecipes.smelting().getSmeltingResult(stack) != null;
	}
}
