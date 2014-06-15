package eu.modscraft.mods.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import eu.modscraft.mods.blocks.TileEntityWindmill;
import eu.modscraft.mods.renderer.tileEntity.TileEntityRenderWindmill;

public class ClientProxy extends CommonProxy{
	
	public void registerProxies() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
	}
}
