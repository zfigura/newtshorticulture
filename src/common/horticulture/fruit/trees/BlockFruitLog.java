package horticulture.fruit.trees;

import horticulture.BlockNH;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFruitLog extends BlockNH{

	public BlockFruitLog(int id){
		super(id,Material.wood,"Log");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1);
	}
	
	@Override
	public int getRenderType(){
		return 31;
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side,int metadata){
		if((side == 0) || (side == 1)){
			return 21;
		}else{
			return 20;
		}
	}
	
	@Override
	public boolean isWood(World w, int x, int y, int z){
		return true;
	}
	
	@Override
	public String getTextureFile(){
		return "/terrain.png";
	}
}
