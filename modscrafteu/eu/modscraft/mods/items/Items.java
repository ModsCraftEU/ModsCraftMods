package eu.modscraft.mods.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Items {
	/*
	 * Information about our Thirst Replenish Items:
	 * You can initialize them just like a normal item, but if you don't set their Thirst Replenish Amount
	 * they will only replenish 1 thirst (0.5 Buckets)
	 */
	public static Item testItem;
	public static Item itemWindmill;
	public static Item itemMana;
	public static Item coffee;
	public static Item brownDrink;
	public static Item greenDrink;
	public static Item waterBottle;
	public static Item strangeDrink;
	
	public static CreativeTabs modscraftTab=new CreativeTabs("modscraftTab"){
		public Item getTabIconItem(){
			return net.minecraft.init.Items.emerald;
		}
	};
	
	public static void init()
	{
		testItem=new Item().setUnlocalizedName(ItemInfo.testItemUnlocalizedName).setCreativeTab(modscraftTab).setTextureName(eu.modscraft.mods.ModsCraft_ModInformation.ID+ ":" + ItemInfo.testItemTexture);
		itemWindmill=new ItemWindmill().setUnlocalizedName(ItemInfo.windmillUnlocalizedName).setCreativeTab(modscraftTab).setTextureName("diamond");
		itemMana=new ItemUseMana().setUnlocalizedName("item_mana").setCreativeTab(modscraftTab).setTextureName("coal");
		coffee=new FoodItemsWithThirstRegeneration(4,1.0F,false,"coffee").setUnlocalizedName("item_coffee");
		brownDrink=new FoodItemsWithThirstRegeneration(4,1.0F,false,"brown_drink").setUnlocalizedName("brown_drink");
		greenDrink=new FoodItemsWithThirstRegeneration(6,2.0F,false,"green_drink").setUnlocalizedName("green_drink");
		waterBottle=new FoodItemsWithThirstRegeneration(2,0.5F,false,"water_bottle").setUnlocalizedName("water_bottle");
		strangeDrink=new FoodItemsWithThirstRegeneration(5,2.0F,false,"strange_drink").setUnlocalizedName("strange_drink");
		//This is to set the regeneration amount of thirst.
		((FoodItemsWithThirstRegeneration) coffee).setThirstReplenishAmount(5);
		((FoodItemsWithThirstRegeneration) brownDrink).setThirstReplenishAmount(4);
		((FoodItemsWithThirstRegeneration) greenDrink).setThirstReplenishAmount(8);
		((FoodItemsWithThirstRegeneration) waterBottle).setThirstReplenishAmount(2);
		((FoodItemsWithThirstRegeneration) strangeDrink).setThirstReplenishAmount(10);
		
		GameRegistry.registerItem(strangeDrink, "strange_drink");
		GameRegistry.registerItem(brownDrink, "brown_drink");
		GameRegistry.registerItem(greenDrink, "green_drink");
		GameRegistry.registerItem(waterBottle, "water_bottle");
		GameRegistry.registerItem(coffee, "item_coffee");
		GameRegistry.registerItem(itemMana, "item_mana");
		GameRegistry.registerItem(testItem, ItemInfo.testItemUnlocalizedName);
		GameRegistry.registerItem(itemWindmill, ItemInfo.windmillUnlocalizedName);
	}	
	
	public static void addNames()
	{
		
	}
	public static void registerRecipes()
	{
		//GameRegistry.addRecipe(new ItemStack(mysimpletool, 1), new Object[]{ "XXX", " # ", " # ", ('X'), Items.apple, ('#'), Items.coal});
		GameRegistry.addRecipe(new ItemStack(waterBottle,2),new Object[]{
			"XX ",
			"   ",
			"   ", ('X'), net.minecraft.item.Item.getItemById(326)
		});
		GameRegistry.addRecipe(new ItemStack(brownDrink,2),new Object[]{
			"X  ",
			"X  ",
			"   ", ('X'), net.minecraft.item.Item.getItemById(326)
		});
		GameRegistry.addRecipe(new ItemStack(greenDrink,2),new Object[]{
			"X  ",
			" X ",
			"   ", ('X'), net.minecraft.item.Item.getItemById(326)
		});
		GameRegistry.addRecipe(new ItemStack(coffee,2),new Object[]{
			"X  ",
			" X ",
			"  X", ('X'), net.minecraft.item.Item.getItemById(326)
		});
		GameRegistry.addShapelessRecipe(new ItemStack(strangeDrink,2),
				new ItemStack(waterBottle),new ItemStack(brownDrink),new ItemStack(greenDrink),
				new ItemStack(coffee));
		
	}
}
