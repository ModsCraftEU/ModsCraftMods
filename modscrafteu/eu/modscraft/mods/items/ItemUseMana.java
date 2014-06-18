package eu.modscraft.mods.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import eu.modscraft.mods.ExtendedPlayer;

//This Items only purpose is to use Mana..using and replenishing it. Its solely for testing and not good for anything. Dont add it in live-code
public class ItemUseMana extends Item{
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player){
		if(!world.isRemote)
		{
			ExtendedPlayer props=ExtendedPlayer.get(player);
			if(props.consumeMana(15))
			{
				//Player has enough Mana to consume 15
				world.setBlock((int)player.posX+1, (int)player.posY, (int)player.posZ+1,Blocks.fire);
			}else{
				//Player didnt have enough Mana, so we replenish it
				if(eu.modscraft.mods.ModsCraft_ModInformation.doDebugOutput)System.out.println("Player is out of Mana!");
				props.replenishMana();
			}
		}
		return itemstack;
	}
}
