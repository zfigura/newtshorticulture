package horticulture;

import horticulture.mill.BlockMillstones;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class NHCraftingHandler implements ICraftingHandler{

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix){
		if(item.itemID == modnh.blockMillstones.blockID){
			player.inventory.addItemStackToInventory(new ItemStack(modnh.itemChisel));
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item){
		
	}
}
