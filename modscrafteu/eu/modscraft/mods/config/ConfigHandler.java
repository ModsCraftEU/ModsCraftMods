package eu.modscraft.mods.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import eu.modscraft.mods.blocks.BlockInformation_ModsCraft;
import eu.modscraft.mods.items.ItemInfo;


public class ConfigHandler 
{
	public static void init(File file)
	{
		System.out.println("CONFIG PATH: "+file.getAbsolutePath());
		Configuration config = new Configuration(file);
		config.load();
		ItemInfo.testItemID=config.get(ItemInfo.testItemCategory, ItemInfo.testItemKey, ItemInfo.testItemDefaultID).getInt()-256;
		ItemInfo.windmillID=config.get(ItemInfo.windmillCategory, ItemInfo.windmillKey, ItemInfo.windmillDefaultID).getInt()-256;
		BlockInformation_ModsCraft.windmillBlockID=config.get(BlockInformation_ModsCraft.windmillBlockCategory, BlockInformation_ModsCraft.windmillBlockKey, BlockInformation_ModsCraft.windmillBlockDefaultID).getInt();
		/*
        BlockInfo.machineID=config.getBlock(BlockInfo.machineKey, BlockInfo.machineDefault).getInt();
		 */
		config.save();
	}
}
