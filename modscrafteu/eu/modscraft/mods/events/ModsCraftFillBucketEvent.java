package eu.modscraft.mods.events;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModsCraftFillBucketEvent {

	@SubscribeEvent
	public void whenITryToFillMyBucket(FillBucketEvent e){
		//Called 2 times: For Client and Server. Check for Server World!
		//Also its called at emptying buckets
	}
	
	private ItemStack getLiquid(World world, MovingObjectPosition pos){
		
		return null;
	}
	
}
