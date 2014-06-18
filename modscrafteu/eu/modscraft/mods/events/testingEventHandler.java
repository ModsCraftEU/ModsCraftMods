package eu.modscraft.mods.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import eu.modscraft.mods.ExtendedPlayer;

public class testingEventHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		//To check that the entity is a player and that the player hasn't been registered before
		if(event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity)==null)
		{
			//We register the properties to our player
			ExtendedPlayer.register((EntityPlayer)event.entity);
			//the init method is unused because this code above will call the init method AND the constructor at once
		}
	}
	//Time to use the Mana to prevent falling damage..as long as there's enough mana, that is
	@SubscribeEvent
	public void onLivingFallEvent(LivingFallEvent event)
	{
		if(event.entity instanceof EntityPlayerMP)
		{
			//Its a player, duh
			ExtendedPlayer props=ExtendedPlayer.get((EntityPlayer)event.entity);
			if(event.distance > 3.0F && props.getCurrentMana()>0)
			{
				System.out.println("EVENT: Fall distance: "+event.distance);
				System.out.println("EVENT: Current Mana: "+props.getCurrentMana());
				float amountReduce=0;
				if(props.getCurrentMana()>=event.distance*2)
				{
					amountReduce=props.getCurrentMana()<(event.distance - 3.0F)?props.getCurrentMana():(event.distance-3.0F);
					event.distance-=amountReduce;
					
				}else{
					amountReduce=(event.distance*2)-props.getCurrentMana();
					event.distance-=amountReduce;
				}
				props.consumeMana((int)amountReduce*2);
				System.out.println("EVENT: Mana after reducing:"+props.getCurrentMana());
				System.out.println("EVENT: Fall Distance after mitigation: "+event.distance);
			}
		}
	}
}
