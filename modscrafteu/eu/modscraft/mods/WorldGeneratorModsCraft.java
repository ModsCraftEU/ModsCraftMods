package eu.modscraft.mods;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorModsCraft implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		switch(world.provider.dimensionId)
		{	//I read that -1 is the nether, 0 the overworld and 1 the end - i also used default because I
			//don't know if used with mods like Mystcraft where there are tons of dimenions on a server
			case -1:
			{
				break;
			}
			case 0:
			{
				generateOverworld(random,chunkX,chunkZ,world);
				break;
			}
			case 1:
			{
				break;
			}
			default:break;
		}
	}
	public void generateOverworld(Random random, int chunkX, int chunkZ, World world)
	{
		//System.out.println("DEBUG! SALTWATER FOUND");
		for(int i=50;i<70;i++)
		{
			mGenerate(world,random,chunkX*16,i,chunkZ*16);
		}
		
	}
	public boolean mGenerate(World world, Random random, int x, int y, int z)
    {
		if(world.getBlock(x, y, z)==Blocks.water)
		{
			world.setBlock(x, y, z, eu.modscraft.mods.fluids.FluidHandler.saltWaterBlock, 0, 2);
			return true;
		}
        if (world.getBlock(x, y + 1, z) != Blocks.stone&&world.getBlock(x, y + 1, z) != Blocks.dirt&&world.getBlock(x, y + 1, z) != Blocks.sand&&world.getBlock(x, y + 1, z) != Blocks.gravel)
        {
            return false;
        }
        else if (world.getBlock(x, y - 1, z) != Blocks.stone&&world.getBlock(x, y - 1, z) != Blocks.dirt&&world.getBlock(x, y - 1, z) != Blocks.sand&&world.getBlock(x, y - 1, z) != Blocks.gravel)
        {
            return false;
        }
        else if (world.getBlock(x, y, z).getMaterial() != Material.air)
        {
            return false;
        }
        else
        {
            int l = 0;

            if (world.getBlock(x - 1, y, z) == Blocks.stone||world.getBlock(x-1,y,z)==Blocks.dirt||world.getBlock(x-1,y,z)==Blocks.gravel||world.getBlock(x-1,y,z)==Blocks.sand)
            {
                ++l;
            }

            if (world.getBlock(x + 1, y, z) == Blocks.stone||world.getBlock(x+1,y,z)==Blocks.dirt||world.getBlock(x+1,y,z)==Blocks.gravel||world.getBlock(x+1,y,z)==Blocks.sand)
            {
                ++l;
            }

            if (world.getBlock(x, y, z - 1) == Blocks.stone||world.getBlock(x,y,z-1)==Blocks.dirt||world.getBlock(x,y,z-1)==Blocks.gravel||world.getBlock(x,y,z-1)==Blocks.sand)
            {
                ++l;
            }

            if (world.getBlock(x, y, z + 1) == Blocks.stone||world.getBlock(x,y,z+1)==Blocks.dirt||world.getBlock(x,y,z+1)==Blocks.gravel||world.getBlock(x,y,z+1)==Blocks.sand)
            {
                ++l;
            }

            int i1 = 0;

            if (world.isAirBlock(x - 1, y, z)||(world.getBlock(x-1,y,z)==Blocks.water))
            {
                ++i1;
            }

            if (world.isAirBlock(x + 1, y, z)||(world.getBlock(x+1,y,z)==Blocks.water))
            {
                ++i1;
            }

            if (world.isAirBlock(x, y, z - 1)||(world.getBlock(x,y,z-1)==Blocks.water))
            {
                ++i1;
            }

            if (world.isAirBlock(x, y, z + 1)||(world.getBlock(x,y,z+1)==Blocks.water))
            {
                ++i1;
            }
            
            if (l >= 3 && i1 >= 1)
            {
            	world.setBlock(x, y, z, eu.modscraft.mods.fluids.FluidHandler.saltWaterBlock, 0, 2);
            	world.scheduledUpdatesAreImmediate = true;
            	eu.modscraft.mods.fluids.FluidHandler.saltWaterBlock.updateTick(world, x, y, z, random);
                world.scheduledUpdatesAreImmediate = false;
                
            }

            return true;
        }
    }
}
