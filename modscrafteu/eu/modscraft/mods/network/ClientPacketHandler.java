package eu.modscraft.mods.network;

import java.io.IOException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import eu.modscraft.mods.ModsCraftMods;

public class ClientPacketHandler extends ServerPacketHandler{
	@SubscribeEvent
	public void onClientPacket(ClientCustomPacketEvent event) throws IOException
	{
		channelName=event.packet.channel();
		if(channelName.equals(ModsCraftMods.networkChannelName))
		{
			//Our Packet.
			if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("MODSCRAFT: Client received Server packet");
			ProcessPacketClientSide.processPacketOnClient(event.packet.payload(),event.packet.getTarget());
		}
	}
}
