package horticulture.tractors;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelTractor extends ModelBase{
	
	final ModelRenderer[] mr = new ModelRenderer[16];

	public ModelTractor(){
		textureWidth = 128;
		textureHeight = 128;
		mr[0] = new ModelRenderer(this, 0, 102);
		mr[1] = new ModelRenderer(this, 0, 26);
		mr[2] = new ModelRenderer(this, 68, 48);
		mr[3] = new ModelRenderer(this, 55, 0);
		mr[4] = new ModelRenderer(this, 22, 72);
		mr[5] = new ModelRenderer(this, 22, 72);
		mr[6] = new ModelRenderer(this, 0, 80);
		mr[7] = new ModelRenderer(this, 0, 80);
		mr[8] = new ModelRenderer(this, 0, 46);
		mr[9] = new ModelRenderer(this, 0, 46);
		mr[10] = new ModelRenderer(this, 0, 66);
		mr[11] = new ModelRenderer(this, 0, 70);
		mr[12] = new ModelRenderer(this, 0, 66);
		mr[13] = new ModelRenderer(this, 58, 29);
		mr[14] = new ModelRenderer(this, 98, 0);
		mr[15] = new ModelRenderer(this, 0, 0);
/*		mr[0].addBox(	-7,		-6,		-5,		12,16,10);	//body
		mr[1].addBox(	 5,		 2,		-6,		16, 8,12);	//body 2
		mr[2].addBox(	16,		-5,		-6,		18, 7,12);	//body 3
		mr[3].addBox(	-8,		 6.5F,	-6,		 3, 4,12);	//lights
		mr[4].addBox(	-3,		-5,		-6,		 2, 2,12);	//front axle
		mr[5].addBox(	26,		-1,		-8,		 2, 2,16);	//back axle
		mr[6].addBox(	-6,		-8,		-9,		 8, 8, 3);	//front right wheel
		mr[7].addBox(	-6,		-8,		 6,		 8, 8, 3);	//front left wheel
		mr[8].addBox(	19,		-8,	   -12,		16,16, 5);	//back right wheel
		mr[9].addBox(	19,		-8,		 8,		16,16, 5);	//back left wheel
		mr[10].addBox(	 1,		10,		 3,		 1, 3, 1);	//smokestack bottom
		mr[11].addBox(	 0.5F,	13,		 2.5F,	 2, 5, 2);	//smokestack middle
		mr[12].addBox(	 1,		18,		 3,		 1, 3, 1);	//smokestack top
		mr[13].addBox(	 5,		-4,		-5,		10, 6,10);	//motor
		mr[14].addBox(	33,		 2,		-6,		 1,15,12);	//chair
		mr[15].addBox(	34,		-1,		-2,		 4, 3, 4);	//hitch*/
		mr[0].addBox(	-5,		-6,		-7,		10,16,12);	//body
		mr[1].addBox(	-6,		 2,		 5,		12, 8,16);	//body 2
		mr[2].addBox(	-6,		-5,		16,		12, 7,18);	//body 3
		mr[3].addBox(	-6,		 6.5F,	-8,		12, 4, 3);	//lights
		mr[4].addBox(	-6,		-5,		-3,		12, 2, 2);	//front axle
		mr[5].addBox(	-8,		-1,		26,		16, 2, 2);	//back axle
		mr[6].addBox(	-9,		-8,		-6,		 3, 8, 8);	//front right wheel
		mr[7].addBox(	 6,		-8,		-6,		 3, 8, 8);	//front left wheel
		mr[8].addBox(  -12,		-8,		19,		 5,16,16);	//back right wheel
		mr[9].addBox(	 8,		-8,		19,		 5,16,16);	//back left wheel
		mr[10].addBox(	 3,		10,		 1,		 1, 3, 1);	//smokestack bottom
		mr[11].addBox(	 2.5F,	13,		 0.5F,	 2, 5, 2);	//smokestack middle
		mr[12].addBox(	 3,		18,		 1,		 1, 3, 1);	//smokestack top
		mr[13].addBox(	-5,		-4,		 5,		10, 6,10);	//motor
		mr[14].addBox(	-6,		 2,		33,		12,15, 1);	//chair
		mr[15].addBox(	-2,		-1,		34,		 4, 3, 4);	//hitch
		for(ModelRenderer i:mr){
			i.setRotationPoint(0, 0, 0);
			i.setTextureSize(textureWidth, textureHeight);
			i.mirror = true;
		}
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5){
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		for(int i=0;i<16;++i){
			mr[i].render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}
