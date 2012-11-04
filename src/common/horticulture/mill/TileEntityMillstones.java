package horticulture.mill;

import horticulture.modnh;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import universalelectricity.prefab.network.IPacketReceiver;

import com.google.common.io.ByteArrayDataInput;

public class TileEntityMillstones extends TileEntity implements IPacketReceiver,IInventory{

	public static final int grindingTicksNeeded = 180;
	public int grindingTicks = 0;
	private boolean sendPacketToClients = false;
	private ItemStack[] inv = new ItemStack[2];

	@Override
	public void handlePacketData(INetworkManager network, int type, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream){
		int id = dataStream.readInt();
		switch(id){
		case -1:
			this.sendPacketToClients = dataStream.readBoolean();
			break;
		case 1:
			this.grindingTicks = dataStream.readInt();
		}
	}

	@Override
	public int getSizeInventory(){
		return this.inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int pos){
		return this.inv[pos];
	}

	@Override
	public ItemStack decrStackSize(int pos, int amt){
		if (this.inv[pos] != null){
			ItemStack stack;
			if (this.inv[pos].stackSize <= amt){
				stack = this.inv[pos];
				this.inv[pos] = null;
				return stack;
			}else{
				stack = this.inv[pos].splitStack(amt);

				if (this.inv[pos].stackSize == 0){
					this.inv[pos] = null;
				}
				return stack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int pos){
		if(this.inv[pos] != null){
			ItemStack stack = this.inv[pos];
			this.inv[pos] = null;
			return stack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int pos, ItemStack stack){
		this.inv[pos] = stack;
		if((stack != null) && (stack.stackSize > this.getInventoryStackLimit())){
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName(){
		return "Millstones";
	}

	@Override
	public int getInventoryStackLimit(){
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p){
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest(){
	}

	@Override
	public void closeChest(){
	}
	
	@Override
	public void readFromNBT(NBTTagCompound ntc){
		super.readFromNBT(ntc);
		this.grindingTicks = ntc.getInteger("grindingTicks");
		NBTTagList list = ntc.getTagList("Items");
		this.inv = new ItemStack[this.getSizeInventory()];
		for(int i=0;i<list.tagCount();++i){
			NBTTagCompound ntc2 = (NBTTagCompound) list.tagAt(i);
			byte b = ntc2.getByte("Slot");
			if((b >= 0)&&(b < this.inv.length))	{
				this.inv[b] = ItemStack.loadItemStackFromNBT(ntc2);
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound ntc){
		super.writeToNBT(ntc);
		ntc.setInteger("grindingTicks", this.grindingTicks);
		NBTTagList list = new NBTTagList();
		for(int i=0;i<inv.length;++i){
			if(this.inv[i] != null){
				NBTTagCompound ntc2 = new NBTTagCompound();
				ntc2.setByte("Slot", (byte)i);
				this.inv[i].writeToNBT(ntc2);
				list.appendTag(ntc2);
			}
		}
		ntc.setTag("Items", list);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.grindingTicks > 0){ //We have work. This should NOT be 180.
			++this.grindingTicks;
			if(this.grindingTicks == grindingTicksNeeded){
				if(this.getStackInSlot(1) == null){
					this.setInventorySlotContents(1, new ItemStack(modnh.itemFlour,3));
				}else{
					this.getStackInSlot(1).stackSize += 3;
				}
				this.grindingTicks = 0;
			}
		}
		if(this.grindingTicks == 0){ //We aren't working, or we just finished
			ItemStack stack = this.getStackInSlot(0);
			if(stack == null) return;
			if(stack.itemID == Item.wheat.shiftedIndex){
				ItemStack out = this.getStackInSlot(1);
				if(out != null){
					if(out.stackSize+3 > 64) return;
				}
				this.decrStackSize(0, 1);
				this.grindingTicks = 1;
			}
		}
	}
}