package eu.modscraft.mods.items;

import eu.modscraft.mods.blocks.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWindmill extends Item {
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float x2, float y2, float z2)
    {
		if(!world.isRemote)
		{
			/*
			if(side==3)world.setBlock(x, y, z+1, Blocks.blockWindmill);
			if(side==4)world.setBlock(x-1, y, z, Blocks.blockWindmill);
			if(side==5)world.setBlock(x+1, y, z, Blocks.blockWindmill);
			if(side==2)world.setBlock(x, y, z-1, Blocks.blockWindmill);
			*/
			if(side==1)world.setBlock(x, y+1, z, Blocks.blockWindmill);
			return true;
		}
        return false;
    }
	
}
