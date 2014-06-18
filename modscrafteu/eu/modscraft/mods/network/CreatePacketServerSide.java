package eu.modscraft.mods.network;

import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import eu.modscraft.mods.ModsCraftMods;

public class CreatePacketServerSide {
	public CreatePacketServerSide(){}
	public static FMLProxyPacket createEntityPacket(Entity parEntity) throws IOException
	{
		System.out.println("MODSCRAFT: Sending Entity Sync Packet on ServerSide");
		ByteBufOutputStream bbos=new ByteBufOutputStream(Unpooled.buffer());
		
		//Creating payload:
		bbos.writeInt(ModsCraftMods.PACKET_TYPE_ENTITY_SYNC);
		bbos.writeInt(parEntity.getEntityId());
		//for the simplicity of the this test i write just a bool to see if its an enimal or not
		if(parEntity instanceof EntityAnimal)
		{
			bbos.writeBoolean(true);
		}else bbos.writeBoolean(false);
		FMLProxyPacket packet=new FMLProxyPacket(bbos.buffer(),ModsCraftMods.networkChannelName);
		bbos.close();
		return packet;
	}
	public static FMLProxyPacket createManaPacket(int manaAmount) throws IOException
	{
		System.out.println("MODSCRAFT: Creating Mana-Packet");
		ByteBufOutputStream bbos=new ByteBufOutputStream(Unpooled.buffer());
		bbos.writeInt(ModsCraftMods.PACKET_TYPE_UPDATE_MANA);
		bbos.writeInt(manaAmount);
		FMLProxyPacket packet=new FMLProxyPacket(bbos.buffer(),ModsCraftMods.networkChannelName);
		bbos.close();
		return packet;
	}
	public static void sendToAll(FMLProxyPacket parPacket)
	{
		ModsCraftMods.channel.sendToAll(parPacket);
	}
	public static void sendS2CEntitySync(Entity parEntity)
	{
		try
		{
			sendToAll(createEntityPacket(parEntity));
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void sendManaUpdate(int manaAmount, EntityPlayerMP player)
	{
		//public void sendTo(FMLProxyPacket pkt, EntityPlayerMP player)
		try
		{
			ModsCraftMods.channel.sendTo(createManaPacket(manaAmount), player);
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
