package horticulture.trees;

import horticulture.ItemNH;
import horticulture.modnh;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class ItemFruit extends ItemFood{
	
	public static final String texfile = "/horticulture/textures/items32.png";

	public ItemFruit(int id){
		super(id,4,false);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setTabToDisplayOn(CreativeTabs.tabFood);
		this.setItemName("itemFruit");
	}

	@Override
	public String getItemNameIS(ItemStack stack){
		return super.getItemName()+"."+modnh.allFruits[stack.getItemDamage()];
	}
	
	@Override
	public int getIconFromDamage(int damage){
		if(damage == 0){
			return 32;
		}
		return damage-1;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tabs, List list){
		for(int i=0;i<modnh.allFruits.length;i++){
			list.add(new ItemStack(id,1,i));
		}
	}
	
	@Override
	public String getTextureFile(){
		return texfile;
	}
}
