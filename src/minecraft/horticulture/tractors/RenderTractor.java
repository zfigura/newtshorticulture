package horticulture.tractors;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Entity;
import net.minecraft.src.Render;
import net.minecraft.src.RenderManager;

public class RenderTractor extends Render{
	
	protected ModelTractor model = new ModelTractor();
	public static final String texfile = "/horticulture/textures/tractor.png";
	
	public RenderTractor(){
		super();
		this.setRenderManager(RenderManager.instance);
	}

	@Override
	public void doRender(Entity e, double x, double y, double z, float yaw, float pitch){
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180-yaw, 0, 1, 0);
		this.loadTexture(texfile);
		this.model.render(e, 0, 0, 0, 0, 0, .0625f);
		GL11.glPopMatrix();
	}
}
