package horticulture.nutrition;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;

/**
 * Implement this if your item provides nutrients.
 */
public interface INutrientProvider{
	/**
	 * This function should be called when this item
	 * is consumed.
	 * @param player - The player that consumed this
	 * item
	 * @param stack - The stack that was consumed
	 * (this should called _before_ onFoodEaten so
	 * that this arg is not null. Besides, in some
	 * cases one might wish to keep the item for
	 * further use.)
	 * @return - The nutrients to add to the player.
	 */
	Nutrients provideNutrients(EntityPlayerMP player, ItemStack stack);
}
