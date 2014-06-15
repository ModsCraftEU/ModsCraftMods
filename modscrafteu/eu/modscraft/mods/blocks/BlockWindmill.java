package eu.modscraft.mods.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWindmill extends BlockContainer{
	
	public BlockWindmill(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
	}
	
	//Prevent render from this Block, we want to use a custom render method.
	public int getRenderType(){
		return -1;
	}
	
	//Solid Block? Nope.
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	//@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityWindmill();
	}

}
