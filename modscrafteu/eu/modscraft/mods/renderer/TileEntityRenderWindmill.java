package eu.modscraft.mods.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileEntityRenderWindmill extends TileEntitySpecialRenderer {
	
	//The Windmill Model's texture. The texture in the beginning is not more than al ittle bit of OpenGL-Vertices
	private final ResourceLocation textureWindmill=new ResourceLocation(eu.modscraft.mods.ModsCraft_ModInformation.ID,"textures/model/windmill.png");
	//Sizes need to be grabbed automatically at some point.
	private int textureWidth = 64;
	private int textureHeight = 32;
	
	//@Override
	public void renderTileEntityAt(TileEntity tileEntity, double distX, double distY, double distZ, float f) {
		// For rendering our multiblock structure
		
		//OpenGL-Drawing, Tesselators from LWJGL (i guess)
		
		//Begin OpenGL-Operations
		GL11.glPushMatrix();
			GL11.glTranslatef((float)distX, (float)distY, (float)distZ);
			
			//Only one Tessellator at any time usable
			Tessellator tesselator=Tessellator.instance;
			this.bindTexture(textureWindmill);
			tesselator.startDrawingQuads(); 	//Start the tesselator
			{	//Brackets are theoretically obsolete but an optical help
				//Rendering in Minecraft has to begin at the bottom and then go counter-clockwise
				//: Bottom Right, top right, top left, bottom left for e.g.
				tesselator.addVertexWithUV(0, 0, 1, 1, 1); //Textureposition (1:1): Bottom right
				tesselator.addVertexWithUV(0, 1, 1, 1, 0); 
				tesselator.addVertexWithUV(0, 1, 0, 0, 0);
				tesselator.addVertexWithUV(0, 0, 0, 0, 1);
				/*
				//Zweite Würfelseite, theoretisch.
				
				tesselator.addVertexWithUV(1, 1, 1, 1, 1);
				tesselator.addVertexWithUV(0, 1, 1, 1, 0);
				tesselator.addVertexWithUV(0, 0, 1, 0, 1);
				tesselator.addVertexWithUV(1, 0, 1, 0, 0);
				*/
			}
			tesselator.draw(); 				//End of tesselator, free it again
		//End Operations
		GL11.glPopMatrix();
	}

}
