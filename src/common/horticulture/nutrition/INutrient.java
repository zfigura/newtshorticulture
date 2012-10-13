package horticulture.nutrition;

import net.minecraft.src.EntityPlayerMP;

/**Represents a type of nutrient. As far as class
 * behavior goes, this acts like a Block/Item.
 */
public abstract interface INutrient{
	
	/**
	 * This function is ALWAYS called when the nutrition
	 * levels are updated. Ensure that you check whether
	 * deficiency/saturation effects apply!
	 * @param value - The amount of this nutrient in milligrams.
	 * @param player - The player to which to apply these effects.
	 */
	void onNutritionLevelUpdate(float value, EntityPlayerMP player);
	
	/**
	 * @param currentvalue - The current amount of this nutrient in the player.
	 * @param player - The player to affect.
	 * @return The amount in milligrams of this nutrient to decrease.
	 */
	float getAmountToDecrease(float currentvalue, EntityPlayerMP player);

	/**
	 * Returns the unique nutrient ID. All UTF-8 characters are acceptable (for now.)
	 */
	String getID();
}
