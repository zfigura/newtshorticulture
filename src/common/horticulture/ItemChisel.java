package horticulture;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeVersion;

public class ItemChisel extends ItemNH{
	
	ItemChisel(int id){
		super(id,"Chisel");
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10){
		if((w.getBlockId(x, y, z) == Block.stoneBrick.blockID) && (w.getBlockMetadata(x, y, z) == 0)){
			w.setBlockMetadataWithNotify(x, y, z, 3);
			return true;
		}else{
			return false;
		}
	}
}
