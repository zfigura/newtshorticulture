package horticulture.mill;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import universalelectricity.network.IPacketReceiver;

import com.google.common.io.ByteArrayDataInput;

public class TileEntityMillstones extends TileEntity implements IPacketReceiver,IInventory{

	private ItemStack[] inv = new ItemStack[2];

	@Override
	public void handlePacketData(NetworkManager network, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream){
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
}