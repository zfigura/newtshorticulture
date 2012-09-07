package horticulture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFruitHanging extends BlockDisplaced{

	protected BlockFruitHanging(int id){
		super(id,Material.plants);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabDeco);
	}

	@Override
	public String getName(){
		return "FruitHanging";
	}
	
	@Override
	public String getTextureFile(){
		return "/terrain.png";
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta){
		return this.blockIndexInTexture+meta;
	}
	
	@Override
	public boolean canPlaceBlockAt(World w, int x, int y, int z){
		return super.canPlaceBlockAt(w, x, y, z) && canGrowHere(w,x,y,z);
	}
	
	protected boolean canGrowHere(World w, int x, int y, int z){
		return (w.getBlockId(x, y+1, z) == (isDisplaced() ? modnh.blockFruitLeaves2.blockID : modnh.blockFruitLeaves.blockID)) &&
		(w.getBlockMetadata(x, y+1, z) == w.getBlockMetadata(x, y, z));
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z){
		return null;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@Override
	public int getRenderType(){
		return 1;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List list){
		int q = (isDisplaced() ? modnh.treeFruits.length : 16);
		for(int i=displacement;i<q;++i){
			list.add(new ItemStack(id,1,i-displacement));
		}
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World w, int x, int y, int z, int meta, int fortune){
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		q.add(new ItemStack(modnh.itemFruit,1,displacement+meta));
		return q;
	}
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random r){
		if(!this.canGrowHere(w, x, y, z)){
			this.dropBlockAsItem(w, x, y, z, w.getBlockMetadata(x, y, z), 0);
			w.setBlockWithNotify(x, y, z, 0);
		}
	}
	
	@Override
	public void onNeighborBlockChange(World w, int x, int y, int z, int id){
		this.updateTick(w, x, y, z, w.rand);
	}
}
