package eu.modscraft.mods;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import eu.modscraft.mods.items.Items;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModsCraft_ModInformation.ID, name = ModsCraft_ModInformation.name, version = ModsCraft_ModInformation.version)
public class ModsCraftMods {
	
	@Instance(ModsCraft_ModInformation.ID)
    public static ModsCraftMods instance; 	

	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		//modscraft_mods.items.Items.init
		Items.init();
    }
	@EventHandler
    public void load(FMLInitializationEvent event)
    {
		//modscraft_mods.items.Items.addNames
    	Items.addNames();
    	//modscraft_mods.items.Items.registerRecipes
    	Items.registerRecipes();
    }
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event)
    {
    }
}