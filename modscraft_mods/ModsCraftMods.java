package modscraft_mods;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModsCraft_ModInformation.ID, name = ModsCraft_ModInformation.name, version = ModsCraft_ModInformation.version)
public class ModsCraftMods {
	
	@Instance(ModsCraft_ModInformation.ID)
    public static ModsCraftMods instance; 	

	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		/*
	 	ConfigHandler.init(event.getSuggestedConfigurationFile());
	 	Items.init();
        Items.init();
        Blocks.init();
        proxy.initSounds();
        proxy.initRenderers();
        */
    }
	
}
