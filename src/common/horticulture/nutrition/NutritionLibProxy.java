package horticulture.nutrition;

import net.minecraft.src.EntityPlayerMP;

public final class NutritionLibProxy{

	public static final Class nh = checkForNH();

	private static Class checkForNH(){
		try{
			return Class.forName("horticulture.nutrition.NutritionLib", false, null);
		}catch(ClassNotFoundException e){
			return null;
		}
	}

	public static void registerNutrient(INutrient n){
		try{
			nh.getMethod("registerNutrient", INutrient.class).invoke(null, n);
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
	
	public static void applyNutrients(EntityPlayerMP p, INutrientProvider obj){
		try{
			nh.getMethod("applyNutrients", EntityPlayerMP.class, INutrientProvider.class).invoke(null, obj);
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
}
