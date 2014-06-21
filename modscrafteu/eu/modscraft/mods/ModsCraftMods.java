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
import cpw.mods.fml.common.network.FMLEventChannel;
import eu.modscraft.achievements.Achievements;
import eu.modscraft.mods.blocks.Blocks;
import eu.modscraft.mods.config.ConfigHandler;
import eu.modscraft.mods.events.ModsCraftBonemealEvent;
import eu.modscraft.mods.events.ModsCraftBucketFilledEvent;
import eu.modscraft.mods.events.ModsCraftItemSmeltedEvent;
import eu.modscraft.mods.events.ModsCraftThirstEvent;
import eu.modscraft.mods.events.testingEventHandler;
import eu.modscraft.mods.fluids.FluidHandler;
import eu.modscraft.mods.items.Items;
import eu.modscraft.mods.proxy.CommonProxy;

@Mod(modid = ModsCraft_ModInformation.ID, name = ModsCraft_ModInformation.name, version = ModsCraft_ModInformation.version)
public class ModsCraftMods {
	
	//Our instance.
	@Instance(ModsCraft_ModInformation.ID)
    public static ModsCraftMods instance; 	
	
	@SidedProxy(clientSide="eu.modscraft.mods.proxy.ClientProxy",serverSide="eu.modscraft.mods.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	
	//Used for Packet Stuff
	public static final String networkChannelName="ModsCraftMods";
	public static FMLEventChannel channel;
	//Packet Type enumeration
	public final static int PACKET_TYPE_ENTITY_SYNC=1;
	public final static int PACKET_TYPE_C2S_TEST = 1;
	public final static int PACKET_TYPE_UPDATE_MANA=2;
	public final static int PACKET_TYPE_UPDATE_THIRST=3;
	public final static int PACKET_TYPE_DRINK = 4;
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Items.init();
		Blocks.preInit();
		Achievements.init();
		FluidHandler.preInit();
    }
	@EventHandler
    public void load(FMLInitializationEvent event)
    {
    	Items.addNames();
    	Items.registerRecipes();
    	Achievements.load();
    	
    	//String channel = null;
    	//EnumMap<Side, FMLEmbeddedChannel> channels = NetworkRegistry.INSTANCE.newChannel(channel, new ChannelHandler());
    	proxy.init();

    	MinecraftForge.EVENT_BUS.register(new ModsCraftBonemealEvent());
    	MinecraftForge.EVENT_BUS.register(new ModsCraftBucketFilledEvent());
    	MinecraftForge.EVENT_BUS.register(new ModsCraftThirstEvent());
    	MinecraftForge.EVENT_BUS.register(new testingEventHandler());
    	FMLCommonHandler.instance().bus().register(new ModsCraftItemSmeltedEvent());
    	
    	Blocks.init();
    	FluidHandler.init();
    	proxy.registerProxies();
    	proxy.registerRenderers();
    }
    @EventHandler
    public void modsLoaded(FMLPostInitializationEvent event)
    {
    	FluidHandler.postInit();
    }
}