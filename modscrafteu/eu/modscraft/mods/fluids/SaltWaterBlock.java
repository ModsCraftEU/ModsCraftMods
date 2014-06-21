package eu.modscraft.mods.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SaltWaterBlock extends BlockFluidClassic{

	@SideOnly(Side.CLIENT)
	protected IIcon stillIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon flowingIcon;
	
	public SaltWaterBlock(Fluid fluid, Material material) {
		super(fluid, material);
		setCreativeTab(eu.modscraft.mods.items.Items.modscraftTab);
		setLightOpacity(3);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return (side==0||side==1)?stillIcon:flowingIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		stillIcon=register.registerIcon(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + "saltWater_still");
		flowingIcon=register.registerIcon(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + "saltWater_flowing");
	}
	//The two return trues have to be replaced with false so that this one doesnt override other liquids anymore
	//TODO: Set the fluid that can be replaced specifically (water in that case) because most probably this will also
	//override lava at the moment, which isnt logical..not really.
	@Override
	public boolean canDisplace(IBlockAccess world,int x,int y,int z)
	{
		 if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
         return super.canDisplace(world, x, y, z);
	}
	
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) 
	{
        if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return true;
        return super.displaceIfPossible(world, x, y, z);
	}
}
