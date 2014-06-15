package eu.modscraft.mods.events;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import eu.modscraft.achievements.Achievements;

public class ModsCraftBonemealEvent {

	@SubscribeEvent
	public void whenIBonemealStuff(BonemealEvent e){
		//Burn it!
		e.world.setBlock(e.x, e.y+1,e.z,Blocks.fire);
		e.entityPlayer.addStat(Achievements.achievementBonemeal, 1);
	}
}
