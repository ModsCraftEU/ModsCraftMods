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
				//This is to make the Blocks with 0 Meta visible again.
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==0){
					tesselator.addVertexWithUV(1, pixel*14, 1, 1F/textureWidth*(32),1F/textureHeight*(32));
					tesselator.addVertexWithUV(1, pixel*14, 0, 1F/textureWidth*(32),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 0, 1F/textureWidth*(0),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 1, 1F/textureWidth*(0),1F/textureHeight*(32));
				}
				//THe Meta Data are also based on their position in their 'matrix', they have no vanilla Meaning.
				//If you are looking north (So standing in the south, the construct north of you) these are the blocks:
				//Right - Corner - Bottom
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==1){
					tesselator.addVertexWithUV(0.5, pixel*14, 0.5, 1F/textureWidth*(32),1F/textureHeight*(32));
					tesselator.addVertexWithUV(0.5, pixel*14, 0, 1F/textureWidth*(32),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0, pixel*14, 0, 1F/textureWidth*(8+16),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0, pixel*14, 0.5, 1F/textureWidth*(8+16),1F/textureHeight*(32));
				}
				//Right - Middle
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==2){
					tesselator.addVertexWithUV(0.5, pixel*14, 1, 1F/textureWidth*(32),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0.5, pixel*14, 0, 1F/textureWidth*(32),1F/textureHeight*(8));
					tesselator.addVertexWithUV(0, pixel*14, 0, 1F/textureWidth*(8+16),1F/textureHeight*(8));
					tesselator.addVertexWithUV(0, pixel*14, 1, 1F/textureWidth*(8+16),1F/textureHeight*(8+16));
				}
				//Right - Corner - Upper
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==3){
					tesselator.addVertexWithUV(0.5, pixel*14, 1, 1F/textureWidth*(32),1F/textureHeight*(8));
					tesselator.addVertexWithUV(0.5, pixel*14, 0.5, 1F/textureWidth*(32),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 0.5, 1F/textureWidth*(8+16),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 1, 1F/textureWidth*(8+16),1F/textureHeight*(8));
				}
				//Bottom - Middle
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==4){
					tesselator.addVertexWithUV(1, pixel*14, 0.5, 1F/textureWidth*(8+16),1F/textureHeight*(32));
					tesselator.addVertexWithUV(1, pixel*14, 0, 1F/textureWidth*(8+16),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0, pixel*14, 0, 1F/textureWidth*(8),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0, pixel*14, 0.5, 1F/textureWidth*(8),1F/textureHeight*(32));
				}
				//Middle Block
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==5){
					tesselator.addVertexWithUV(1, pixel*14, 1, 1F/textureWidth*(8+16),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(1, pixel*14, 0, 1F/textureWidth*(8+16),1F/textureHeight*8);
					tesselator.addVertexWithUV(0, pixel*14, 0, 1F/textureWidth*8,1F/textureHeight*8);
					tesselator.addVertexWithUV(0, pixel*14, 1, 1F/textureWidth*8,1F/textureHeight*(8+16));
				}
				//Top - Middle
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==6){
					tesselator.addVertexWithUV(1, pixel*14, 1, 1F/textureWidth*(8+16),1F/textureHeight*(8));
					tesselator.addVertexWithUV(1, pixel*14, 0.5, 1F/textureWidth*(8+16),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 0.5, 1F/textureWidth*(8),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0, pixel*14, 1, 1F/textureWidth*(8),1F/textureHeight*(8));
				}
				//Bottom - Left
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==7){
					tesselator.addVertexWithUV(1, pixel*14, 0.5, 1F/textureWidth*(8),1F/textureHeight*(32));
					tesselator.addVertexWithUV(1, pixel*14, 0, 1F/textureWidth*(8),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0.5, pixel*14, 0, 1F/textureWidth*(0),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(0.5, pixel*14, 0.5, 1F/textureWidth*(0),1F/textureHeight*(32));
				}
				//Middle - Left
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==8){
					tesselator.addVertexWithUV(1, pixel*14, 1, 1F/textureWidth*(8),1F/textureHeight*(8+16));
					tesselator.addVertexWithUV(1, pixel*14, 0, 1F/textureWidth*(8),1F/textureHeight*(8));
					tesselator.addVertexWithUV(0.5, pixel*14, 0, 1F/textureWidth*(0),1F/textureHeight*(8));
					tesselator.addVertexWithUV(0.5, pixel*14, 1, 1F/textureWidth*(0),1F/textureHeight*(8+16));
				}
				//Top - Left
				if(tileEntity.getWorldObj().getBlockMetadata(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord)==9){
					tesselator.addVertexWithUV(1, pixel*14, 1, 1F/textureWidth*(8),1F/textureHeight*(8));
					tesselator.addVertexWithUV(1, pixel*14, 0.5, 1F/textureWidth*(8),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0.5, pixel*14, 0.5, 1F/textureWidth*(0),1F/textureHeight*(0));
					tesselator.addVertexWithUV(0.5, pixel*14, 1, 1F/textureWidth*(0),1F/textureHeight*(8));
				}
				
			}
			tesselator.draw();
		GL11.glPopMatrix();
	}

}
