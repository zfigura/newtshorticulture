package horticulture;

import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;

public abstract class BlockNH extends Block{

	public static final String texfile = "/horticulture/textures/blocks.png";

	protected BlockNH(int id,Material material,String name){
		super(id,material);
		this.setBlockName(name);
		ModLoader.addName(this, name);
	}
	
	@Override
	public String getTextureFile(){
		return texfile;
	}
}
