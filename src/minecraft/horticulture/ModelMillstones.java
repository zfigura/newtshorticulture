package horticulture;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMillstones extends ModelBase{

	protected final ModelRenderer[] mr;
	
	protected float tickNumber = 0;
	
	public ModelMillstones(){
		this.textureHeight = 32;
		this.textureWidth = 64;
		mr = new ModelRenderer[8];
		for(int i=0;i<8;++i){
			mr[i] = new ModelRenderer(this,0,0);
			mr[i].setRotationPoint(0, 16, 0);
			mr[i].setTextureSize(this.textureWidth, this.textureHeight);
			mr[i].mirror = true;
		}
		mr[0].addBox(-8, -8, 3, 16, 8, 5);
		mr[1].addBox(-8, -8, -8, 16, 8, 5);
		mr[2].addBox(-8, -8, -3, 5, 8, 6);
		mr[3].addBox(3, -8, -3, 5, 8, 6);
		mr[4].addBox(-8, 0, 3, 16, 8, 5);
		mr[5].addBox(-8, 0, -8, 16, 8, 5);
		mr[6].addBox(-8, 0, -3, 5, 8, 6);
		mr[7].addBox(3, 0, -3, 5, 8, 6);
	}
	
	@Override
	public void render(Entity e, float u, float v, float w, float x, float y, float z){
		super.render(e, u, v, w, x, y, z);
		for(int i=0;i<8;i++){
			mr[i].render(.0625f);
			if(i > 3){
				mr[i].rotateAngleY = tickNumber;
			}
		}
		tickNumber += 0.02f;
	}
}
