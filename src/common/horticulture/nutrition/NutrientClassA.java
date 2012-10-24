package horticulture.nutrition;

import net.minecraft.src.EntityPlayerMP;

/**
 * Represents a "class A" nutrient. These nutrients
 * have a minimum value but no maximum, and are keyed
 * to the deficiency disease of the same ID.
 */
public class NutrientClassA implements INutrient{
	
	public final String ID;
	public final float DV;
	
	public NutrientClassA(String id, float dv){
		this.ID = id;
		this.DV = dv;
	}

	@Override
	public void onNutritionLevelUpdate(float value, EntityPlayerMP player){
		if(value<0){
			//trigger deficiency symptoms
		}
	}

	@Override
	public float getAmountToDecrease(float currentvalue, EntityPlayerMP player){
		return (float)DV/(float)24000;
	}

	@Override
	public String getID(){
		return ID;
	}
}
