package horticulture;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemChisel extends ItemNH{
	
	ItemChisel(int id){
		super(id);
		this.setTabToDisplayOn(CreativeTabs.tabTools);
	}
	
	@Override
	public String getName() {
		return "Chisel";
	}
	
	@Override
    public boolean tryPlaceIntoWorld(ItemStack stack, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		if((w.getBlockId(x, y, z) == Block.stoneBrick.blockID) && (w.getBlockMetadata(x, y, z) == 0)){
			w.setBlockMetadataWithNotify(x, y, z, 3);
			return true;
		}else{
			return false;
		}
	}
}
