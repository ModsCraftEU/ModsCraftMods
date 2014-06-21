package eu.modscraft.mods.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import eu.modscraft.mods.items.ItemInfo;

public class FluidHandler {
	public static Fluid saltWater;
	public static Block saltWaterBlock;
	public static Item saltWaterBucket;
	
	public static void preInit()
	{
		//saltWater=new Fluid("salt_water");
		saltWater=new Fluid("salt_water").setDensity(1000).setViscosity(1000).setGaseous(false).setUnlocalizedName("salt_water");
		FluidRegistry.registerFluid(saltWater);
		saltWaterBlock=new SaltWaterBlock(saltWater,Material.water).setBlockName("salt_water");
		GameRegistry.registerBlock(saltWaterBlock, eu.modscraft.mods.ModsCraft_ModInformation.ID+"_"+saltWaterBlock.getUnlocalizedName());
		saltWaterBucket=new SaltWaterBucket(saltWaterBlock);
		saltWaterBucket.setUnlocalizedName("saltWaterBucket").setContainerItem(Items.bucket);
		saltWaterBucket.setTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + "waterBucket_saltwater");
		GameRegistry.registerItem(saltWaterBucket, "saltWaterBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("salt_water",FluidContainerRegistry.BUCKET_VOLUME),new ItemStack(saltWaterBucket),new ItemStack(Items.bucket));
		eu.modscraft.mods.events.ModsCraftBucketFilledEvent.INSTANCE.buckets.put(saltWaterBlock, saltWaterBucket);
		//saltWaterBlock.registerIcons();
		GameRegistry.addSmelting(saltWaterBucket, new ItemStack(net.minecraft.item.Item.getItemById(326)), 0.4f);
	}
	public static void init()
	{
		
	}
	public static void postInit()
	{
		
	}
}
