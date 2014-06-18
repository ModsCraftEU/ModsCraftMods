package eu.modscraft.mods.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import eu.modscraft.mods.GuiManaBar;
import eu.modscraft.mods.GuiThirstBar;
import eu.modscraft.mods.ModsCraftMods;
import eu.modscraft.mods.blocks.TileEntityWindmill;
import eu.modscraft.mods.blocks.TileEntityWindmillFloor;
import eu.modscraft.mods.network.ClientPacketHandler;
import eu.modscraft.mods.renderer.TileEntityRenderWindmill;
import eu.modscraft.mods.renderer.TileEntityRenderWindmillFloor;

public class ClientProxy extends CommonProxy{
	
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillFloor.class,new TileEntityRenderWindmillFloor());
		MinecraftForge.EVENT_BUS.register(new GuiManaBar(Minecraft.getMinecraft()));
		MinecraftForge.EVENT_BUS.register(new GuiThirstBar(Minecraft.getMinecraft()));
	}
	public void init(){
		ModsCraftMods.channel=NetworkRegistry.INSTANCE.newEventDrivenChannel(ModsCraftMods.networkChannelName);
		ModsCraftMods.channel.register(new ClientPacketHandler());
	}
}
