package eu.modscraft.mods.network;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import eu.modscraft.mods.ModsCraftMods;

public class ServerPacketHandler {
	protected String channelName;
	protected EntityPlayerMP player;
	
	@SubscribeEvent
	public void onServerPacket(ServerCustomPacketEvent event) throws IOException
	{
		channelName=event.packet.channel();
		//Credits to GoToLink, he figured out how to get the player entity
		NetHandlerPlayServer netHandlerPlayServer=(NetHandlerPlayServer)event.handler;
		player=netHandlerPlayServer.playerEntity;
		if(channelName.equals(ModsCraftMods.networkChannelName))
		{
			//Our Channel, probably our Packet!
			if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("MODSCRAFT: Received packet from player = "+player.getEntityId());
			ProcessPacketServerSide.processPacketOnServer(event.packet.payload(),event.packet.getTarget(),player);
		}
	}
}
