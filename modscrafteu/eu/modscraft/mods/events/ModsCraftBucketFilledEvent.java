/*
 * The following code is partially from:
 * http://www.minecraftforge.net/wiki/Create_a_Fluid#Creating_the_fluid_block
 * and is credited to BuildCraft. It is apparently their approach, therefore this code file shall be under the same license!
 * 
 */
package eu.modscraft.mods.events;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModsCraftBucketFilledEvent {
	public static ModsCraftBucketFilledEvent INSTANCE=new ModsCraftBucketFilledEvent();
	public Map<Block, Item> buckets=new HashMap<Block,Item>();
	
	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		ItemStack result=fillCustomBucket(event.world, event.target);
		if(result==null)
		{
			System.out.println("NULL");
			return;
		}
		event.result=result;
		event.setResult(Result.ALLOW);
	}
	private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
	{
		Block block=world.getBlock(pos.blockX, pos.blockY, pos.blockZ);
		
		//Item bucket=null;
		if(block.equals(eu.modscraft.mods.fluids.FluidHandler.saltWaterBlock))
		{
			System.out.println("IS SALTWATER!");
			Item bucket=eu.modscraft.mods.fluids.FluidHandler.saltWaterBucket;
			world.setBlockToAir(pos.blockX,pos.blockY,pos.blockZ);
			return new ItemStack(bucket);
		}else 
		{
			Item bucket=buckets.get(block);
	    
			if(bucket!=null&&world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ)==0)
			{
				world.setBlockToAir(pos.blockX,pos.blockY,pos.blockZ);
				return new ItemStack(bucket);
			}else{
				return null;
			}
		}
	}
}
