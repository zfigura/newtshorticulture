package horticulture.trees;

import horticulture.BlockNH;
import horticulture.modnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.IShearable;

public class BlockFruitLeaves extends BlockNH implements IShearable{

	public int displacement = 0;
	
	public int[] adjacentBlocks;

	public BlockFruitLeaves(int id){
		super(id, Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(.2f);
		this.setLightOpacity(1);
		this.setRequiresSelfNotify();
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
	}

	@Override
	public String getName(){
		return "FruitLeaves";
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata){
		return this.blockIndexInTexture+metadata+displacement;
	}
	
	@Override
	protected int damageDropped(int metadata){
		return metadata;
	}
	
	@Override
	public boolean isWood(World w, int x, int y, int z){
		return true;
	}
	
	@Override
	public void getSubBlocks(int id, CreativeTabs tabs, List list){
		int q = ((displacement == 16) ? modnh.treeFruits.length : 16);
		for(int i=displacement;i<q;++i){
			list.add(new ItemStack(id,1,i-displacement));
		}
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public ArrayList<ItemStack> getBlockDropped(World w, int x, int y, int z, int metadata, int fortune){
		//TODO: Why is metadata 0?
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		if(w.rand.nextInt(20) < 2+fortune){
			q.add(new ItemStack(((displacement == 0) ? modnh.blockFruitSapling : modnh.blockFruitSapling2),1,this.damageDropped(metadata)));
		}
		if(w.rand.nextInt(20) < 5+fortune){
			q.add(new ItemStack(modnh.itemFruit,1,metadata+displacement));
		}
		return q;
	}
	
	@Override
	public boolean isShearable(ItemStack i, World w, int x, int y, int z){
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		q.add(new ItemStack(this,1,world.getBlockMetadata(x, y, z)));
		return q;
	}

	public void setDisplaced(){
		this.displacement = 16;
	}
	
	//Difficult leaf block code... bleh.
	@Override
	public void updateTick(World w, int x, int y, int z, Random r){
		super.updateTick(w, x, y, z, r);
		int radius = 4;
		int dimsize = 32;
		int ds2 = dimsize*dimsize;
		int halfdim = dimsize/2;
		this.adjacentBlocks = new int[dimsize*dimsize*dimsize];
		boolean haswood = false;
		for(int X=-radius;X<=radius;++X){
			for(int Y=-radius;Y<=radius;++Y){
				for(int Z=-radius;Z<=radius;++Z){
					int id = w.getBlockId(x+X, y+Y, z+Z);
					this.adjacentBlocks[(X+halfdim)*ds2+(Y+halfdim)*dimsize+Z+halfdim] = id;
					if(id == modnh.blockFruitLog.blockID){
						haswood = true;
					}
				}
			}
		}
		if(!haswood){
			this.dropBlockAsItem(w, x, y, z, w.getBlockMetadata(x, y, z), 0);
			w.setBlockWithNotify(x, y, z, 0);
		}
	}
}
