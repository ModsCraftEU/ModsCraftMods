package eu.modscraft.mods.fluids;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHandler {
	public static Fluid saltWater;
	public static Block saltWaterBlock;
	
	public static void preInit()
	{
		//saltWater=new Fluid("salt_water");
		saltWater=new Fluid("salt_water").setDensity(500).setViscosity(1000).setGaseous(false).setUnlocalizedName("salt_water");
		FluidRegistry.registerFluid(saltWater);
		saltWaterBlock=new SaltWaterBlock(saltWater,Material.water).setBlockName("salt_water");
		GameRegistry.registerBlock(saltWaterBlock, eu.modscraft.mods.ModsCraft_ModInformation.ID+"_"+saltWaterBlock.getUnlocalizedName());
	}
	public static void init()
	{
		
	}
	public static void postInit()
	{
		
	}
}
