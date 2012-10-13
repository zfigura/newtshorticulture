package horticulture.nutrition;

import java.util.HashMap;

/**
 * This represents individual doses of nutrients. If
 * <code>Nutrient</code> acts like <code>Item</code>,
 * this acts like <code>ItemStack</code> EXCEPT that the
 * values of this object are not modified, allowing
 * for an item class to contain only one of these and
 * pass it every time the item is consumed.
 * @see INutrient
 */
public final class Nutrients{
	
	private HashMap<INutrient,Float> map = new HashMap<INutrient,Float>();
	
	public Nutrients(){
		
	}
	
	public Nutrients(INutrient[] n, float[] f){
		if(n.length != f.length){
			throw new RuntimeException("List length mismatch!");
		}
		for(int i=0;i<n.length;i++){
			map.put(n[i], f[i]);
		}
	}
	
	public Nutrients addNutrient(INutrient type, float amount){
		map.put(type, amount);
		return this;
	}
	
	public float getNutrientAmount(INutrient type){
		return map.get(type);
	}
}
