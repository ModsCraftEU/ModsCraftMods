package eu.modscraft.mods.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityRenderWindmillFloor extends TileEntitySpecialRenderer{

	private final ResourceLocation textureWindmillFloor=new ResourceLocation(eu.modscraft.mods.ModsCraft_ModInformation.ID,"textures/model/windmillFloor.png");
	private int textureWidth = 32;
	private int textureHeight = 32;
	private float pixel=1F/16F; // Size of ONE Pixel, for e.g. on a Dirt Block you can count the pixels
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double distX, double distY, double distZ, float f) {
		// For OpenGL-Comments see TileEntityRenderWindmill.java
		GL11.glPushMatrix();
			GL11.glTranslatef((float)distX, (float)distY, (float)distZ);
			Tessellator tesselator=Tessellator.instance;
			this.bindTexture(textureWindmillFloor);
			tesselator.startDrawingQuads(); 	//Start the tesselator
			{
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==5){
					tesselator.addVertexWithUV(0, pixel*5, 1, 1/textureWidth*(8+16),1/textureHeight*(8+16));
					tesselator.addVertexWithUV(1, pixel*5, 1, 1/textureWidth*(8+16),1/textureHeight*8);
					tesselator.addVertexWithUV(1, pixel*5, 0, 1/textureWidth*8,1/textureHeight*8);
					tesselator.addVertexWithUV(0, pixel*5, 0, 1/textureWidth*8,1/textureHeight*(8+16));
				}
			}
			tesselator.draw();
		GL11.glPopMatrix();
	}

}
