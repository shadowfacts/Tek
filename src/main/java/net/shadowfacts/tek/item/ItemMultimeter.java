package net.shadowfacts.tek.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.shadowfacts.tek.Tek;
import net.shadowfacts.tek.api.energy.EnergyHandler;
import net.shadowfacts.tek.item.base.TekItem;
import net.shadowfacts.tek.packet.PacketEnergyRequest;

/**
 * @author shadowfacts
 */
public class ItemMultimeter extends TekItem {

	public ItemMultimeter() {
		super("multimeter");
		setTexturePath("tools/");
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.getTileEntity(x, y, z) instanceof EnergyHandler) {
			Tek.network.sendToServer(new PacketEnergyRequest(x, y, z));
			return true;
		}
		return false;
	}
}
