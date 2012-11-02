package horticulture;

import net.minecraft.src.Material;

public abstract class BlockDisplaced extends BlockNH implements IDisplaceable{
	
	protected int displacement;

	protected BlockDisplaced(int id, Material material, String name){
		super(id, material,name);
	}
	
	@Override
	public void setDisplaced(){
		this.displacement = 16;
	}

	@Override
	public void setDisplaced(int displacement){
		this.displacement = displacement;
	}

	@Override
	public int getDisplacement(){
		return displacement;
	}

	@Override
	public boolean isDisplaced(){
		return displacement == 16;
	}

}
