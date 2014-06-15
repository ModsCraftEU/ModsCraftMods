package eu.modscraft.mods.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {
	
	public static Item testItem;
	
	public static CreativeTabs modscraftTab=new CreativeTabs("modscraftTab"){
		public Item getTabIconItem(){
			return net.minecraft.init.Items.emerald;
		}
	};
	
	public static void init()
	{
		/*
		 * Language Files:
		 * Name Blocks:
		 * tile.blockRed.name=Red Block
		 * where blockRed is the unlocalized name
		 * item.unlocalized_testItem_name.name=ModsCraft Test Item
		 * where unlocalized_testItem_name is also the unlocalized name!
		 */
		testItem=new Item().setUnlocalizedName(ItemInfo.testItemUnlocalizedName).setCreativeTab(modscraftTab).setTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + ItemInfo.testItemTexture);
		GameRegistry.registerItem(testItem, "testItem");
	}	
	
	public static void addNames()
	{
		
	}
	public static void registerRecipes()
	{
		
	}
}
