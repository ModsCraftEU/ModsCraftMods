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
		if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Received ProcessPacketClientSide on Client Side");
		World world=Minecraft.getMinecraft().theWorld;
		ByteBufInputStream bbis=new ByteBufInputStream(parBB);
		int packetTypeID=bbis.readInt();
		switch(packetTypeID)
		{
			case ModsCraftMods.PACKET_TYPE_ENTITY_SYNC:
			{
				boolean isAnimal=bbis.readBoolean();
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Entity is animal: "+isAnimal);
				break;
			}
			case ModsCraftMods.PACKET_TYPE_UPDATE_MANA:
			{
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Mana Update Packet Received!");
				int newManaAmount=bbis.readInt();
				ExtendedPlayer props=ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
				props.setCurrentMana(newManaAmount);
				break;
			}
			case ModsCraftMods.PACKET_TYPE_UPDATE_THIRST:
			{
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Thirst Update Packet Received!");
				int newThirstAmount=bbis.readInt();
				boolean updateDirection=bbis.readBoolean();	//This is false for a decreasing effect and true for increasing
				ExtendedPlayer props=ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
				props.handleReceivedThirstUpdate(newThirstAmount, updateDirection);
				break;
			}
			case ModsCraftMods.PACKET_TYPE_DRINK:
			{
				ExtendedPlayer props=ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Drink Update Packet Received!");
				int replenish=bbis.readInt();
				System.out.println("############");
				System.out.println("old Thirst="+props.getThirst());
				System.out.println("replenish="+replenish);
				int newThirstAmount=props.getThirst()+replenish;
				System.out.println("New thirst="+newThirstAmount);
				if(newThirstAmount>20)newThirstAmount=20;
				props.setCurrentThirst(newThirstAmount);
				System.out.println("############");
				break;
			}
		}
		bbis.close();
	}
}
