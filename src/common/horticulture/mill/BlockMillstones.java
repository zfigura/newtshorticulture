package horticulture.mill;

import horticulture.modnh;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockMillstones extends BlockContainer{

	public BlockMillstones(int id){
		super(id, Material.rock);
		this.setBlockName("Millstones");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}


	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileEntityMillstones();
	}

	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public int getRenderType(){
		return -1;
	}

	@Override
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e){
		if(e instanceof EntityItem){
			EntityItem ei = (EntityItem)e;
			if(ei.item.itemID == Item.wheat.shiftedIndex){
				int i = ei.item.stackSize;
				e.setDead();
				w.spawnEntityInWorld(new EntityItem(w,(double)x,(double)(y-1),(double)z, new ItemStack(modnh.itemFlour,i)));
			}
		}
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		float size = 0.0625F;
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)x + size), (double)y, (double)((float)z + size), (double)((float)(x + 1) - size), (double)((float)(y + 1) - size), (double)((float)(z + 1) - size));
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World w, int x, int y, int z){
		float size = 0.0625F;
		return AxisAlignedBB.getAABBPool().addOrModifyAABBInPool((double)((float)x + size), (double)y, (double)((float)z + size), (double)((float)(x + 1) - size), (double)(y + 1), (double)((float)(z + 1) - size));
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int side, float q, float r, float s){
		p.openGui(modnh.getInstance(), 0, w, x, y, z);
		return true;
	}
}
