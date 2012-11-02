package horticulture.fruit.flower;

import horticulture.BlockNH;
import horticulture.modnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFruitFlower extends BlockNH {

	public BlockFruitFlower(int id){
		super(id, Material.plants,"blockFruitFlower");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta){
		return 32+meta;
	}
	
	@Override
	public boolean canPlaceBlockAt(World w, int x, int y, int z){
		return this.canGrowHere(w,x,y,z);
	}
	
	public boolean canGrowHere(World w, int x, int y, int z){
		int id = w.getBlockId(x, y-1, z);
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.sand.blockID;
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
		for(int i=0;i<modnh.flowerFruits.length;i++){
			list.add(new ItemStack(id,1,i));
		}
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World w, int x, int y, int z, int meta, int fortune){
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		q.add(new ItemStack(modnh.blockFruitFlower,1,meta));
		return q;
	}
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random r){
		if(!canRemainHere(w,x,y,z)){
			this.dropBlockAsItem(w, x, y, z, w.getBlockMetadata(x, y, z), 0);
			w.setBlockWithNotify(x, y, z, 0);
		}else if(r.nextInt(10) == 0){
			this.dropFruit(w,x,y,z,r);
		}
	}
	
	public boolean canRemainHere(World w, int x, int y, int z){
		if(w.getBlockLightValue(x, y, z) < 9) return false;
		int meta = w.getBlockMetadata(x,y,z);
		int biome = w.getBiomeGenForCoords(x, z).biomeID;
		switch(meta){
		case 0:
		case 2:
		case 3:
		case 4:
			return (biome == 4) || (biome == 18);
		case 1:
			return (biome == 6);
		case 5:
			return (biome == 21) || (biome == 22);
		}
		return false;
	}
	
	public void dropFruit(World w, int x, int y, int z, Random r){
		w.spawnEntityInWorld(new EntityItem(w, x, y, z, new ItemStack(w.getBlockId(x, y, z),1,w.getBlockMetadata(x,y,z))));
	}
}
