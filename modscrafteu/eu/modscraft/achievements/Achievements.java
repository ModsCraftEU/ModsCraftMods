package eu.modscraft.achievements;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;


public class Achievements {
	public static Achievement achievementBonemeal;
	public static Achievement achievementSmoothStone;

	public static void init(){
		
	}

	public static void load(){
		//Achievement Name, x,y = Position in AchievementTab, Items.dye,15 means
		//The dye with Meta-Data 15, which is Bone Meal, (Achievement)null is no prerequisite
		achievementBonemeal=new Achievement("achievement.bonemeal","bonemeal",0,0,new ItemStack(Items.dye,15),(Achievement)null).registerStat().setSpecial().initIndependentStat();
		achievementSmoothStone=new Achievement("achievement.smoothstone","smoothstone",2,0,Blocks.stone,achievementBonemeal).registerStat();
				
		AchievementPage.registerAchievementPage(new AchievementPage("ModsCraft.eu Achievements",new Achievement[]{achievementBonemeal,achievementSmoothStone}));
	}

}