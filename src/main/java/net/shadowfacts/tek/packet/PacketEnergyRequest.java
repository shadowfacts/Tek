package net.shadowfacts.tek.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.shadowfacts.tek.api.energy.EnergyHandler;

/**
 * @author shadowfacts
 */
public class PacketEnergyRequest implements IMessage {

	private int x, y, z;

	public PacketEnergyRequest() {}

	public PacketEnergyRequest(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public static class Handler implements IMessageHandler<PacketEnergyRequest, PacketEnergyResponse> {
		@Override
		public PacketEnergyResponse onMessage(PacketEnergyRequest msg, MessageContext ctx) {
			TileEntity te = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(msg.x, msg.y, msg.z);
			if (te instanceof EnergyHandler) {
				return new PacketEnergyResponse(((EnergyHandler)te).getStoredEnergy());
			} else {
				return null;
			}
		}
	}

}
