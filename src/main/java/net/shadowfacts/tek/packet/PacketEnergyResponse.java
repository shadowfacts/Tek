package net.shadowfacts.tek.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * @author shadowfacts
 */
public class PacketEnergyResponse implements IMessage {

	private int energy;

	public PacketEnergyResponse() {}

	public PacketEnergyResponse(int energy) {
		this.energy = energy;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		energy = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(energy);
	}

	public static class Handler implements IMessageHandler<PacketEnergyResponse, IMessage> {
		@Override
		public IMessage onMessage(PacketEnergyResponse msg, MessageContext ctx) {
			String s = "";
			s += "Energy stored: ";
			s += EnumChatFormatting.GREEN;
			s += msg.energy;
			Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(s));

			return null;
		}
	}
}
