package horticulture.nutrition;

import java.util.HashMap;

import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Item;

public final class NutritionLib{
	
	private static final HashMap<String,INutrient> nutrients = new HashMap<String,INutrient>();
	
	private NutritionLib(){}
	
	public static void registerNutrient(INutrient n){
		nutrients.put(n.getID(), n);
	}
	
	public static void applyNutrients(EntityPlayerMP player, INutrientProvider obj){
		if(obj instanceof Item){
			obj.provideNutrients(player, player.getCurrentEquippedItem());
		}
	}
	
	public static void decreaseAllNutritionLevels(EntityPlayerMP player){
		//TODO
		Nutrients ns = getNutrientLevels(player);
	}

	public static Nutrients getNutrientLevels(EntityPlayerMP player){
		//TODO
		return null;
	}
	
	public static void createAndRegisterClassA(String id, float dv){
		registerNutrient(new NutrientClassA(id,dv));
	}
}
