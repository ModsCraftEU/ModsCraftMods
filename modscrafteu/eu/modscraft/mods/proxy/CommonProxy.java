package eu.modscraft.mods.proxy;

import cpw.mods.fml.common.network.NetworkRegistry;
import eu.modscraft.mods.ModsCraftMods;
import eu.modscraft.mods.network.ServerPacketHandler;

public class CommonProxy {

	public void registerProxies() {
		// TODO Auto-generated method stub
		
	}

	public void registerRenderers() {
		// TODO Auto-generated method stub
		
	}
	public void init(){
		ModsCraftMods.channel=NetworkRegistry.INSTANCE.newEventDrivenChannel(ModsCraftMods.networkChannelName);
		ModsCraftMods.channel.register(new ServerPacketHandler());
	}
	
}
