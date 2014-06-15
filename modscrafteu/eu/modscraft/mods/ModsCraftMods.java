package eu.modscraft.mods;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import eu.modscraft.achievements.Achievements;
import eu.modscraft.mods.events.ModsCraftBonemealEvent;
import eu.modscraft.mods.events.ModsCraftFillBucketEvent;
import eu.modscraft.mods.events.ModsCraftItemSmeltedEvent;
import eu.modscraft.mods.items.Items;

@Mod(modid = ModsCraft_ModInformation.ID, name = ModsCraft_ModInformation.name, version = ModsCraft_ModInformation.version)
public class ModsCraftMods {
	
	//Our instance.
	@Instance(ModsCraft_ModInformation.ID)
    public static ModsCraftMods instance; 	

	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		//modscraft_mods.items.Items.init
		Items.init();
		Achievements.init();
    }
	@EventHandler
    public void load(FMLInitializationEvent event)
    {
    	Items.addNames();
    	Items.registerRecipes();
    	Achievements.load();
    	//No clue why 2 different buses have to be used..its just like that and i dont know why
    	MinecraftForge.EVENT_BUS.register(new ModsCraftBonemealEvent());
    	MinecraftForge.EVENT_BUS.register(new ModsCraftFillBucketEvent());
    	FMLCommonHandler.instance().bus().register(new ModsCraftItemSmeltedEvent());
    }
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event)
    {
    }
}