package eu.modscraft.mods.events;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import eu.modscraft.achievements.Achievements;

public class ModsCraftItemSmeltedEvent {

	@SubscribeEvent
	public void whenISmeltAnItemOrBlock(PlayerEvent.ItemSmeltedEvent e){
		if(Block.getBlockFromItem(e.smelting.getItem()).equals(Blocks.stone)){
			e.player.addStat(Achievements.achievementSmoothStone, 1);
		}
	}
}
