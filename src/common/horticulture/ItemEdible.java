package horticulture;

import horticulture.nutrition.INutrientProvider;
import horticulture.nutrition.INutrient;
import horticulture.nutrition.Nutrients;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;

public abstract class ItemEdible extends ItemNH implements INutrientProvider{
	
	public final Nutrients nutrients = new Nutrients();

	protected ItemEdible(int id,String name){
		super(id,name);
	}
	
	public Nutrients provideNutrients(EntityPlayerMP player, ItemStack stack){
		return nutrients;
	}
}
