package eu.modscraft.mods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class Blocks {
	public static Block blockWindmill;
	public static Block blockWindmillGround;
	
	public static void preInit(){
		
		blockWindmill=new BlockWindmill(Material.rock).setBlockName(BlockInformation_ModsCraft.windmillAddName).setCreativeTab(CreativeTabs.tabBlock);
		blockWindmillGround=new BlockWindmillGround(Material.ground).setBlockName(BlockInformation_ModsCraft.windmillGroundAddName).setCreativeTab(CreativeTabs.tabBlock).setBlockTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+":"+"blockWindmillGround");
		
		GameRegistry.registerBlock(blockWindmill, BlockInformation_ModsCraft.windmillAddName);
		GameRegistry.registerBlock(blockWindmillGround, BlockInformation_ModsCraft.windmillGroundAddName);
	}
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityWindmill.class, "windmill");
	}
}
