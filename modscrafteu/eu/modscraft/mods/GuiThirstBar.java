package eu.modscraft.mods;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiThirstBar extends Gui{
	private Minecraft mc;
	private static final ResourceLocation texture_thirstBar=new ResourceLocation(eu.modscraft.mods.ModsCraft_ModInformation.ID,"textures/gui/thirstBar.png");
	private static final int renderPositionX=224;
	private static final int renderPositionY=190;
	private static final int waterBucketOffsetY=1;
	
	public GuiThirstBar(Minecraft mc)
	{
		super();
		this.mc=mc;
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event)
	{
		if(event.isCancelable()||event.type!=ElementType.EXPERIENCE)
		{
			return;
		}
		ExtendedPlayer props=ExtendedPlayer.get(this.mc.thePlayer);
		int currentThirst=props.getThirst();
		int waterBucketsToDraw=0, halfBucketsToDraw=0;

		if(currentThirst>0)
		{
			if(currentThirst%2==0)
			{
				//Is an Even number
				waterBucketsToDraw=currentThirst/2;
			}else{
				if(currentThirst>2)
				{
					waterBucketsToDraw=(currentThirst-1)/2;
					halfBucketsToDraw=1;
				}else halfBucketsToDraw=1;
			}
		}
		/*
		 * Information about the default thirst bar:
		 * The bar itself starts at 0,0 (top-left-corner) and the bottom-right-corner is at 99,9
		 * The Water Bucket as Thirst Symbol is located at 0,10 and its bottom-right-corner is at 6,16
		 * it has to be rendered 10 times for no thirst.
		 * the half bucket is located at:0,17 and goes to 3,23
		 * The size of one full water bucket is: 7x7, the size of a half bucket is: 4x7
		 */
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//i read at the tutorial that there's a lighting but described in the vanilla code which is the reason to disale GL_LIGHTING
		GL11.glDisable(GL11.GL_LIGHTING);
		//We will first draw the empty thirst bar.. i always call it hunger bar but its thirst ._.
		//We use a modal rect for that 
		this.mc.getTextureManager().bindTexture(texture_thirstBar);
		/*
		 * this.drawTexturedModalRect(xPos, yPos, 0, 0, 51, 4);
		int manaBarWidth=(int)(((float)props.getCurrentMana()/props.getMaxMana())*49);
		this.drawTexturedModalRect(xPos, yPos+1, 0, 4, manaBarWidth, 2);
		 */
		
		//TODO: There's some problematics with switching to maximized and small window mode, causing the positions of our graphics to be incorrect.
		//We need to use relative screen coordinates instead of absolute ones.
		if(!this.mc.isFullScreen())
		{
			this.drawTexturedModalRect(renderPositionX, renderPositionY, 0, 0, 99, 9);
			if(waterBucketsToDraw>0)
			{
				for(int i=0;i<waterBucketsToDraw;i++)
				{
					this.drawTexturedModalRect(renderPositionX+i*7, renderPositionY+waterBucketOffsetY, 0, 10, 6, 8);
				}
			}
			if(halfBucketsToDraw==1)
			{
				this.drawTexturedModalRect((renderPositionX+7*waterBucketsToDraw)+1,renderPositionY+waterBucketOffsetY,0,17,3,23);
			}
		}
		//and from here on we will draw the full bar!
	}
}
