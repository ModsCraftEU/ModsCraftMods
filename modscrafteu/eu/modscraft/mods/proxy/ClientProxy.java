package eu.modscraft.mods.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import eu.modscraft.mods.blocks.TileEntityWindmill;
import eu.modscraft.mods.blocks.TileEntityWindmillFloor;
import eu.modscraft.mods.renderer.TileEntityRenderWindmill;
import eu.modscraft.mods.renderer.TileEntityRenderWindmillFloor;

public class ClientProxy extends CommonProxy{
	
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillFloor.class,new TileEntityRenderWindmillFloor());
	}
}
