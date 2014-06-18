package eu.modscraft.mods.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModsCraftThirstEvent {
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event)
	{
		
	}
	@SubscribeEvent
	public void onPlayerHurt(LivingHurtEvent event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			//So that only the player gets the update
			
		}
	}
	public void updatePlayerThirst(int value)
	{
		//Here we can add or remove thirst
	}
}
