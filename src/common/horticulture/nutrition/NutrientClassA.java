package horticulture.nutrition;

import net.minecraft.src.EntityPlayerMP;

/**
 * Represents a "class A" nutrient. These nutrients
 * have a minimum value but no maximum, and are keyed
 * to the deficiency disease of the same ID.
 */
public class NutrientClassA implements INutrient{
	
	public final String ID;
	public final float minValue;
	
	public NutrientClassA(String id, float min){
		this.ID = id;
		this.minValue = min;
	}

	@Override
	public void onNutritionLevelUpdate(float value, EntityPlayerMP player){
		
	}

	@Override
	public float getAmountToDecrease(float currentvalue, EntityPlayerMP player){
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
