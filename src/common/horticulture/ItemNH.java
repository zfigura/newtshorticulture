package horticulture;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Item;
import net.minecraft.src.ModLoader;

public class ItemNH extends Item{
	
	public static final String texfile = "/horticulture/textures/items.png";

	public ItemNH(int id){
		super(id);
		this.setItemName(getName());
		ModLoader.addName(this,getName());
	}
	
	public ItemNH(int id, String name){
		super(id);
		this.setItemName(name);
		ModLoader.addName(this, name);
	}
	
	@Override
	public String getTextureFile(){
		return texfile;
	}
	
	public String getName(){
		return null;
	}
}
