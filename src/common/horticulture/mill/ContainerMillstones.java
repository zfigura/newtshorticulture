package horticulture.mill;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotFurnace;

public class ContainerMillstones extends Container{

	public final TileEntityMillstones entity;

	public ContainerMillstones(InventoryPlayer inv, TileEntityMillstones entity){
		this.entity = entity;
		this.addSlotToContainer(new Slot(entity,0,55,25));
		this.addSlotToContainer(new SlotFurnace(inv.player, entity, 1, 108, 25));
		//		for(int i=0;i<4;++i){
		//			for(int j=0;j<9;++j){
		//				this.addSlotToContainer(new Slot(inv,j+(i*9),(j*18)+8,(i*18)+84));
		//			}
		//		}
		for (int var3 = 0; var3 < 3; ++var3)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(inv, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (int var3 = 0; var3 < 9; ++var3)
		{
			this.addSlotToContainer(new Slot(inv, var3, 8 + var3 * 18, 142));
		}

	}

	//	public ItemStack transferStackInSlot(int slot){
	//		return null;
	//	}

	@Override
	public boolean canInteractWith(EntityPlayer p){
		return this.entity.isUseableByPlayer(p);
	}

//	@Override
//	public ItemStack transferStackInSlot(int par1){
//		ItemStack var2 = null;
//		Slot var3 = (Slot)this.inventorySlots.get(par1);
//		if (var3 != null && var3.getHasStack()){
//			ItemStack var4 = var3.getStack();
//			var2 = var4.copy();
//			if(par1 == 2){
//				if(!this.mergeItemStack(var4, 3, 39, true)){
//					return null;
//				}
//				var3.onSlotChange(var4, var2);
//			}else if(par1 != 1 && par1 != 0){
//				if(var4.getItem() instanceof IItemElectric){
//					if(!this.mergeItemStack(var4, 0, 1, false)){
//						return null;
//					}
//				}else if(FurnaceRecipes.smelting().getSmeltingResult(var4) != null){
//					if(!this.mergeItemStack(var4, 1, 2, false)){
//						return null;
//					}
//				}else if(par1 >= 3 && par1 < 30){
//					if(!this.mergeItemStack(var4, 30, 39, false))	{
//						return null;
//					}
//				}else if(par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false)){
//					return null;
//				}
//			}else if(!this.mergeItemStack(var4, 3, 39, false)){
//				return null;
//			}
//			if(var4.stackSize == 0){
//				var3.putStack(null);
//			}else{
//				var3.onSlotChanged();
//			}
//			if(var4.stackSize == var2.stackSize){
//				return null;
//			}
//
//			var3.onPickupFromSlot(var4);
//		}
//
//		return var2;
//	}

}
