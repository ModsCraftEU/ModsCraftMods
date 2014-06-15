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
		//item = new item(blah)
		testItem=new Item().setUnlocalizedName(ItemInfo.testItemUnlocalizedName).setCreativeTab(modscraftTab).setTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + ItemInfo.testItemTexture);
		GameRegistry.registerItem(testItem, "testItem");
	}	
	
	public static void addNames()
	{
		/*
		for(int i=0;i<ItemInfo.cardNames.length;i++)
		{
			LanguageRegistry.addName(new ItemStack(card,1,i), ItemInfo.cardNames[i]);
		}
		 */
	}
	public static void registerRecipes()
	{
		
	}
}
