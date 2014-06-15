package eu.modscraft.mods.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import eu.modscraft.mods.items.ItemInfo;


public class ConfigHandler 
{
	public static void init(File file)
	{
		Configuration config = new Configuration(file);
		config.load();
		ItemInfo.testItemID=config.get(ItemInfo.testItemCategory, ItemInfo.testItemKey, ItemInfo.testItemDefaultID).getInt()-256;
		
		/*
        BlockInfo.machineID=config.getBlock(BlockInfo.machineKey, BlockInfo.machineDefault).getInt();
		 */
		config.save();
	}
}
