package eu.modscraft.mods;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import eu.modscraft.achievements.Achievements;
import eu.modscraft.mods.blocks.Blocks;
import eu.modscraft.mods.config.ConfigHandler;
import eu.modscraft.mods.events.ModsCraftBonemealEvent;
import eu.modscraft.mods.events.ModsCraftFillBucketEvent;
import eu.modscraft.mods.events.ModsCraftItemSmeltedEvent;
import eu.modscraft.mods.items.Items;
import eu.modscraft.mods.proxy.CommonProxy;

@Mod(modid = ModsCraft_ModInformation.ID, name = ModsCraft_ModInformation.name, version = ModsCraft_ModInformation.version)
public class ModsCraftMods {
	
	//Our instance.
	@Instance(ModsCraft_ModInformation.ID)
    public static ModsCraftMods instance; 	
	
	@SidedProxy(clientSide="eu.modscraft.mods.proxy.ClientProxy",serverSide="eu.modscraft.mods.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Items.init();
		Blocks.preInit();
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
    	
    	Blocks.init();
    	proxy.registerProxies();
    }
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event)
    {
    }
}