package horticulture.mill;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;

public class RenderMillstones extends TileEntitySpecialRenderer{
	
	protected final ModelMillstones model = new ModelMillstones();
	
	public static final String texfile = "/horticulture/textures/millstones.png";

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8){
		this.bindTextureByName(texfile);
		GL11.glPushMatrix();
		GL11.glTranslated(x+.5f, y-.5f, z+.5f);
		model.render(null, 0, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}
}
