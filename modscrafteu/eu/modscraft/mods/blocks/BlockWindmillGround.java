package eu.modscraft.mods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWindmillGround extends BlockContainer{

	public BlockWindmillGround(Material material) {
		super(material);
		// TODO Auto-generated constructor stub
		//To set this as slab
		this.setBlockBounds(0,0,0,1,(1F/16F)*14F,1);
	}
	
	//Den schwarzen Schatten in der mitte entfernen! Dafür diese beiden folgenden.
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	//Called every time the ground gets an update - or better its neighbor blocks. For Multiblockstructures good
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
		//System.out.println("BlockChange!");
		updateMultiBlockStructure(world,x,y,z);
	}
	@Override
	public void onBlockAdded(World world, int x,int y,int z){
		updateMultiBlockStructure(world,x,y,z);
	}
	public void updateMultiBlockStructure(World world, int x,int y,int z){
		//Non-Override!
		isMultiBlockStructure(world,x,y,z);
	}
	public int getRenderType(){
		return -1;
	}
	/*
	//This function was used to modify the size of blocks to see if they recognize themselfes as multiblocks or not
	public MovingObjectPosition collisionRayTrace(World world, int x, int y,int z, Vec3 startVec, Vec3 endVec){
		float metadata=world.getBlockMetadata(x,y,z);
		this.setBlockBounds(0, 0, 0, 1, 2*metadata, 1); //Weeeeeird behaviour! Dont enable..i warned you.
		return super.collisionRayTrace(world, x, y, z, startVec, endVec);
	}
	*/
	public boolean isMultiBlockStructure(World world, int x1,int y1,int z1){
		if(!world.isRemote)
		{
			boolean mIsStructure=false;
			boolean currentCheck=true;
			for(int x2=0;x2<3;x2++){
				for(int z2=0;z2<3;z2++){
					if(!mIsStructure){
						currentCheck=true;
						for(int x3=0;x3<3;x3++){
							for(int z3=0;z3<3;z3++){
								if(currentCheck&&!world.getBlock(x1+x2-x3, y1, z1+z2-z3).equals(eu.modscraft.mods.blocks.Blocks.blockWindmillGround)){
									currentCheck=false;
								}
							}
						}
						if(currentCheck){
							for(int x3=0;x3<3;x3++){
								for(int z3=0;z3<3;z3++){
									world.setBlockMetadataWithNotify(x1+x2-x3, y1, z1+z2-z3, x3*3+z3+1, 2);
								}
							}
						}
					}
					mIsStructure=currentCheck;
				}
			}
			if(mIsStructure)
			{
				//The Base has been completed.
				//System.out.println("True Multiblock Structure");
				return true;
			}else{
				if(world.getBlockMetadata(x1, y1, z1)>0)world.setBlockMetadataWithNotify(x1, y1, z1, 0, 3); //Argument 3 = Update other blocks in close proxmiity
				return false;
			}
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		// TODO Auto-generated method stub
		return new TileEntityWindmillFloor();
	}
}
