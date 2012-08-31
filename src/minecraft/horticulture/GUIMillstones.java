package horticulture;

import org.lwjgl.opengl.GL11;

import universalelectricity.basiccomponents.BasicComponents;
import horticulture.mill.ContainerMillstones;
import horticulture.mill.TileEntityMillstones;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;

public class GUIMillstones extends GuiContainer{

	public final TileEntityMillstones entity;
	public static final String texfile = "/horticulture/textures/guimill.png";

	public GUIMillstones(InventoryPlayer inv, TileEntityMillstones entity){
		super(new ContainerMillstones(inv,entity));
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
		int var4 = this.mc.renderEngine.getTexture(texfile);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int containerWidth = (this.width - this.xSize) / 2;
		int containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(containerWidth, containerHeight, 0, 0, this.xSize, this.ySize);
	}
}
