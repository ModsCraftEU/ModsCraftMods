package eu.modscraft.mods.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import eu.modscraft.mods.blocks.Blocks;

public class ItemWindmill extends Item {
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float x2, float y2, float z2)
    {
		if(!world.isRemote)
		{
			if(side==1 && world.getBlock(x, y, z).equals(eu.modscraft.mods.blocks.Blocks.blockWindmillGround) && world.getBlockMetadata(x, y, z)==5){
				//This is used to check the space above the windmill floor for enough space to actually place the windmill
				//because it will, of course, need a lot of space. 1x7x1, 7 beeing the height. This above will check if the
				//floor block is really the middle - which is what it has to be. :)
				boolean notEnoughSpace=false;
				for(int x1=-1;x1<1;x1++){
					for(int z1=-1;z1<1;z1++){
						for(int y1=0;y1<7;y1++){
							if(!world.isAirBlock(x+x1,y+y1+1,z+z1))notEnoughSpace=true;
						}
					}
				}
				if(!notEnoughSpace){
					
					for(int y1=0;y1<7;y1++){
						//We set up the windmill parts to 7 height and set their height as meta data. It is, for e.g. used when breaking one of them to get the others and kill those
						//world.setBlock(x, y+y1+1, z, Blocks.blockWindmill);
						//world.setBlockMetadataWithNotify(x, y+y1+1, z,y1+1,2);
						world.setBlock(x, y+y1+1, z, Blocks.blockWindmill,y1+1,2);
					}
				}
				return true;
			}
		}
        return false;
    }
	
}
