package eu.modscraft.mods.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import eu.modscraft.mods.ExtendedPlayer;

public class ModsCraftThirstEvent {
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event)
	{
		if(event.entity instanceof EntityPlayerMP)
		{
			ExtendedPlayer props=ExtendedPlayer.get(Minecraft.getMinecraft().thePlayer);
			props.doThirstOperation(event.entity);
		}
	}
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		if(event.entity instanceof EntityPlayerMP )
		{
			//So that only the player gets the update
			
		}
	}
}
