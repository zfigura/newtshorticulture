package horticulture.trees;

import horticulture.modnh;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemBlockFruitSapling extends ItemBlock{

	public ItemBlockFruitSapling(int id) {
		super(id);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
	
	@Override
	public int getIconFromDamage(int damage){
		return 15;
	}
	
	@Override
	public String getItemNameIS(ItemStack stack){
		return super.getItemName()+"."+modnh.treeFruits[stack.getItemDamage()]+stack.itemID;
	}
}
