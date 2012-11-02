package horticulture.fruit.trees;

import horticulture.modnh;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class ItemBlockFruitLeaves extends ItemBlock{
	
	public ItemBlockFruitLeaves(int id){
		super(id);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damage){
		return damage;
	}
	
	@Override
	public String getItemNameIS(ItemStack stack){
//		return super.getItemName()+"."+stack.getItemDamage();
		return super.getItemName()+"."+modnh.treeFruits[stack.getItemDamage()]+stack.itemID;
	}
}
