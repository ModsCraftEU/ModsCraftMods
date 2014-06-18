package eu.modscraft.mods.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eu.modscraft.mods.ExtendedPlayer;
import eu.modscraft.mods.ModsCraftMods;

public class ProcessPacketClientSide {
	public ProcessPacketClientSide()
	{
		
	}
	@SideOnly(Side.CLIENT)
	public static void processPacketOnClient(ByteBuf parBB, Side parSide) throws IOException
	{
		System.out.println("Received ProcessPacketClientSide on Client Side");
		World world=Minecraft.getMinecraft().theWorld;
		ByteBufInputStream bbis=new ByteBufInputStream(parBB);
		int packetTypeID=bbis.readInt();
		switch(packetTypeID)
		{
			case ModsCraftMods.PACKET_TYPE_ENTITY_SYNC:
			{
				boolean isAnimal=bbis.readBoolean();
				System.out.println("Entity is animal: "+isAnimal);
				break;
			}
			case ModsCraftMods.PACKET_TYPE_UPDATE_MANA:
			{
				System.out.println("Mana Update Packet Received!");
				int newManaAmount=bbis.readInt();
				ExtendedPlayer props=ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
				props.setCurrentMana(newManaAmount);
				break;
			}
		}
		bbis.close();
	}
}
