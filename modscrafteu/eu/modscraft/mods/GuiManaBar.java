package eu.modscraft.mods;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiManaBar extends Gui{
	private Minecraft mc;
	private static final ResourceLocation texturePath=new ResourceLocation(eu.modscraft.mods.ModsCraft_ModInformation.ID,"textures/gui/mana_bar.png");
	public GuiManaBar(Minecraft mc)
	{
		super();
		//This is to invoke the render engine
		this.mc=mc;
	}
	//
	// This event is called by GuiIngameForge during each frame by
	// GuiIngameForge.pre() and GuiIngameForce.post().
	//(meaning: it could be used as FPS killer)
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if(event.isCancelable()||event.type!=ElementType.EXPERIENCE)
		{
			return;
		}
		ExtendedPlayer props=ExtendedPlayer.get(this.mc.thePlayer);
		if(props==null||props.getMaxMana()==0)
		{
			//If player doesnt have mana, most probably meaning that the props didnt get assigned to him, why ever that would happen.
			return;
		}
		int xPos=2;
		int yPos=2;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//i read at the tutorial that there's a lighting but described in the vanilla code which is the reason to disale GL_LIGHTING
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.getTextureManager().bindTexture(texturePath);
		this.drawTexturedModalRect(xPos, yPos, 0, 0, 51, 4);
		int manaBarWidth=(int)(((float)props.getCurrentMana()/props.getMaxMana())*49);
		this.drawTexturedModalRect(xPos, yPos+1, 0, 4, manaBarWidth, 2);
	}

}
