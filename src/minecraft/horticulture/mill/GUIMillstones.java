package horticulture.mill;

import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

import org.lwjgl.opengl.GL11;

public class GUIMillstones extends GuiContainer{

	public final TileEntityMillstones entity;
	public static final String texfile = "/horticulture/textures/guimill.png";

	public GUIMillstones(InventoryPlayer inv, TileEntityMillstones entity){
		super(new ContainerMillstones(inv,entity));
		this.entity = entity;
	}
	
	protected void drawGuiContainerForegroundLayer(){
		this.fontRenderer.drawString("Millstones", 60, 6, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
		int tex = this.mc.renderEngine.getTexture(texfile);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(tex);
		int containerWidth = (this.width - this.xSize) / 2;
		int containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
		if(this.entity.grindingTicks > 0){
			int scale = (int)(((double)this.entity.grindingTicks / TileEntityMillstones.grindingTicksNeeded) * 23);
			this.drawTexturedModalRect(containerWidth+77, containerHeight+24, 176, 0, scale, 20);
		}
	}
}
