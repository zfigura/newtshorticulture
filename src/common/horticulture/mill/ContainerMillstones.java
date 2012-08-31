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
		for(int i=0;i<4;++i){
			for(int j=0;j<9;++j){
				this.addSlotToContainer(new Slot(inv,j+(i*9),(j*18)+8,(i*18)+84));
			}
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer p){
		return this.entity.isUseableByPlayer(p);
	}
}
