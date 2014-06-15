package eu.modscraft.mods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockWindmillGround extends Block {

	public BlockWindmillGround(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
		this.setBlockBounds(0,0,0,1,0.3f,1);
	}
	
	//Den schwarzen Schatten in der mitte entfernen! Dafür diese beiden folgenden.
	public boolean renderAsNormalBlock(){
		return false;
	}
	public boolean isOpaqueCube(){
		return false;
	}

}
