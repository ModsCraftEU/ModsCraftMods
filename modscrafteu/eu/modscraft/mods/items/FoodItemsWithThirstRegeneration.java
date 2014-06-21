package eu.modscraft.mods.items;

import eu.modscraft.mods.ExtendedPlayer;
import eu.modscraft.mods.network.CreatePacketServerSide;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FoodItemsWithThirstRegeneration extends ItemFood{

	private int thirstReplenishAmount;
	private boolean alwaysEdible;
	
	public FoodItemsWithThirstRegeneration(int hungerRegenValue,float saturation,boolean edibleByWolfes, String textureName) {
		super(hungerRegenValue,saturation,edibleByWolfes);
		this.setCreativeTab(CreativeTabs.tabFood);
		thirstReplenishAmount=1;
		this.alwaysEdible=true;
		//eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + "coffee");
		this.setTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+":"+textureName);
	}
	public void setThirstReplenishAmount(int amount)
	{
		thirstReplenishAmount=amount;
	}
	public int getThirstReplenishAmount()
	{
		return this.thirstReplenishAmount;
	}
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.canEat(this.alwaysEdible))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(player instanceof EntityPlayerMP)
			{
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("FOOD! TRHIST REPLENISH = "+thirstReplenishAmount);
				//CreatePacketServerSide.sendThirstUpdate(newThirst, direction, entity);
				CreatePacketServerSide.sendDrinkUpdate(this.thirstReplenishAmount, player);
			}
		}
	}
}
