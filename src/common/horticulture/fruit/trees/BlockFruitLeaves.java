package horticulture.fruit.trees;

import horticulture.BlockDisplaced;
import horticulture.modnh;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IShearable;

public class BlockFruitLeaves extends BlockDisplaced{

	public int[] adjacentBlocks;

	public BlockFruitLeaves(int id){
		super(id, Material.wood,"FruitLeaves");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(.2f);
		this.setLightOpacity(1);
		this.setRequiresSelfNotify();
		this.setStepSound(Block.soundGrassFootstep);
		this.setTickRandomly(true);
		Block.setBurnProperties(id, 30, 60);
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
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		if(w.rand.nextInt(20) < 2+fortune){
			q.add(new ItemStack(((displacement == 0) ? modnh.blockFruitSapling : modnh.blockFruitSapling2),1,this.damageDropped(metadata)));
		}
		if(w.rand.nextInt(20) < 5+fortune){
			q.add(new ItemStack(modnh.itemFruit,1,metadata+displacement));
		}
		return q;
	}

	//Difficult leaf block code... bleh.
	@Override
	public void updateTick(World w, int x, int y, int z, Random r){
		super.updateTick(w, x, y, z, r);
		if(!haswood(w,x,y,z)){
			this.dropBlockAsItem(w, x, y, z, w.getBlockMetadata(x, y, z), 0);
			w.setBlockWithNotify(x, y, z, 0);
		}else if((w.getBlockId(x, y-1, z) == 0)&&(r.nextInt(16) == 0)){
			w.setBlockAndMetadataWithNotify(x, y-1, z, isDisplaced() ? modnh.blockFruitHanging2.blockID : modnh.blockFruitHanging.blockID, w.getBlockMetadata(x, y, z));
		}
	}
	
	public boolean haswood(World w, int x, int y, int z){
		int radius = 4;
		int dimsize = 32;
		int ds2 = dimsize*dimsize;
		int halfdim = dimsize/2;
		this.adjacentBlocks = new int[dimsize*dimsize*dimsize];
		for(int X=-radius;X<=radius;++X){
			for(int Y=-radius;Y<=radius;++Y){
				for(int Z=-radius;Z<=radius;++Z){
					int id = w.getBlockId(x+X, y+Y, z+Z);
					this.adjacentBlocks[(X+halfdim)*ds2+(Y+halfdim)*dimsize+Z+halfdim] = id;
					if(id == modnh.blockFruitLog.blockID){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side){
		return world.getBlockMetadata(x, y, z) == 9;
	}
	
	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face){
		return (world.getBlockMetadata(x, y, z) == 9) ? 0 : 30;
	}
}