package eu.modscraft.mods.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.relauncher.Side;
import eu.modscraft.mods.ModsCraftMods;

public class ProcessPacketServerSide {
	public ProcessPacketServerSide()
	{
	}
	public static void processPacketOnServer(ByteBuf parBB, Side parSide, EntityPlayerMP parPlayer) throws IOException
	{
		if(parSide==Side.SERVER) //We want this to only be handled on the server, right?
		{
			if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("MODSCRAFT: Received Packet on Server Side from Player = "+parPlayer.getEntityId());
			ByteBufInputStream bbis=new ByteBufInputStream(parBB);
			int packetTypeID=bbis.readInt();
			if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Paccket Type ID="+packetTypeID);
			switch(packetTypeID)
			{
				case ModsCraftMods.PACKET_TYPE_C2S_TEST:
				{
					if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Test packet received");
					int testVal=bbis.readInt();
					if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Test payload value="+testVal);
					break;
				}
				
			}
			bbis.close();
			
		}
	}
}
