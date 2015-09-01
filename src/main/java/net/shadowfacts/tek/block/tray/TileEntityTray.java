package net.shadowfacts.tek.block.tray;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.shadowfacts.tek.Tek;

/**
 * @author shadowfacts
 */
public class TileEntityTray extends TileEntity {

	private ItemStack stack;

	boolean handleRightClick(EntityPlayer player) {
		ItemStack heldStack = player.inventory.getCurrentItem();
		if (this.stack == null && heldStack != null) {
			if (heldStack.stackSize == 1) {
				this.stack = heldStack.copy();
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
				return true;
			} else if (heldStack.stackSize >= 1) {
				this.stack = heldStack.splitStack(1);
				return true;
			}
		} else if (player.isSneaking() && player.getHeldItem() == null && this.stack != null &&
				!worldObj.isRemote) {
			EntityItem item = new EntityItem(worldObj, xCoord, yCoord, zCoord, this.stack.copy());
			worldObj.spawnEntityInWorld(item);
			stack = null;
		}

		return false;
	}

	void handleLeftClick(EntityPlayer player) {
		if (player.isSneaking() && !worldObj.isRemote) {
			ItemStack dropStack = new ItemStack(Tek.blocks.tray);
			NBTTagCompound stackTag = new NBTTagCompound();
			if (stack != null) stack.writeToNBT(stackTag);

			if (dropStack.stackTagCompound == null) dropStack.stackTagCompound = new NBTTagCompound();
			dropStack.stackTagCompound.setTag("Stack", stackTag);

			EntityItem drop = new EntityItem(worldObj, xCoord, yCoord, zCoord, dropStack);
			worldObj.spawnEntityInWorld(drop);

			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		NBTTagCompound stackTag = new NBTTagCompound();
		if (stack != null) stack.writeToNBT(stackTag);

		tag.setTag("Stack", stackTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		readFromNBT(tag, true);
	}

	public void readFromNBT(NBTTagCompound tag, boolean setCoords) {
		if (setCoords) super.readFromNBT(tag);

		if (tag != null && tag.hasKey("Stack")) {
			stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Stack"));
		}
	}
}
