package horticulture.trees;

import horticulture.BlockNH;
import horticulture.modnh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockFruitSapling extends BlockNH{

	public int displacement = 0;

	public BlockFruitSapling(int id){
		super(id,Material.plants);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabDeco);
		float size = .4f;
		this.setBlockBounds(0.5F - size, 0.0F, 0.5F - size, 0.5F + size, size * 2.0F, 0.5F + size);
	}

	@Override
	public String getName(){
		return "FruitSapling";
	}

	@Override
	public String getTextureFile(){
		return "/terrain.png";
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata){
		return 15;
	}

	@Override
	public boolean canPlaceBlockAt(World w, int x, int y, int z){
		return super.canPlaceBlockAt(w, x, y, z) && this.canGrowHere(w,x,y,z);
	}

	protected boolean canGrowHere(World w, int x, int y, int z){
		int id = w.getBlockId(x, y-1, z);
		return id == Block.grass.blockID || id == Block.dirt.blockID || id == Block.sand.blockID;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4){
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
		int q = ((displacement == 16) ? modnh.treeFruits.length : 16);
		for(int i=displacement;i<q;++i){
			list.add(new ItemStack(id,1,i-displacement));
		}
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World w, int x, int y, int z, int metadata, int fortune){
		ArrayList<ItemStack> q = new ArrayList<ItemStack>();
		q.add(new ItemStack(((displacement == 0) ? modnh.blockFruitSapling : modnh.blockFruitSapling2),1,metadata));
		return q;
	}

	@Override
	public void updateTick(World w, int x, int y, int z, Random r){
		if(!canGrowHere(w,x,y,z)){
			this.dropBlockAsItem(w, x, y, z, w.getBlockMetadata(x, y, z), 0);
			w.setBlockWithNotify(x, y, z, 0);
		}else if(r.nextInt(10) == 0){
			this.attemptGrowTree(w,x,y,z,r);
		}
	}

	public void attemptGrowTree(World w, int x, int y, int z, Random r){
		this.attemptGrowTree(w, x, y, z, r, false);
	}

	public void attemptGrowTree(World w, int x, int y, int z, Random r, boolean leavesAllowed){
		//		System.out.println("I grew a tree at "+x+","+y+","+z);
		int q = r.nextInt(10);
		int height = (q < 4) ? ((q == 1) ? 7 : 6) : 5;
		if(canGrowTree(w,x,y,z,height,true)){
			int leafid = (displacement == 0) ? modnh.blockFruitLeaves.blockID : modnh.blockFruitLeaves2.blockID;
			int meta = w.getBlockMetadata(x, y, z);
			for(int i=0;i<height-1;i++){
				w.setBlock(x, i+y, z, modnh.blockFruitLog.blockID);
			}
			//Generate lowest level
			for(int i=x-2;i<x+3;++i){
				for(int j=z-2;j<z+3;++j){
					if((i != x) || (j != z)){
						w.setBlockAndMetadataWithNotify(i, y+height-3, j, leafid, meta);
						w.setBlockAndMetadataWithNotify(i, y+height-2, j, leafid, meta);
					}
				}
			}
			q = r.nextInt(256);
			if((q & 1) == 0){
				w.setBlockWithNotify(x-2, y+height-2, z-2, 0);
			}
			if((q & 2) == 0){
				w.setBlockWithNotify(x-2, y+height-2, z+2, 0);
			}
			if((q & 4) == 0){
				w.setBlockWithNotify(x+2, y+height-2, z-2, 0);
			}
			if((q & 8) == 0){
				w.setBlockWithNotify(x+2, y+height-2, z+2, 0);
			}
			for(int i=x-1;i<x+2;++i){
				for(int j=z-1;j<z+2;++j){
					if((i != x) || (j != z)){
						w.setBlockAndMetadataWithNotify(i, y+height-1, j, leafid, meta);
						w.setBlockAndMetadataWithNotify(i, y+height, j, leafid, meta);
					}
				}
			}
			w.setBlockAndMetadataWithNotify(x, y+height, z, leafid, meta);
			if((q & 16) == 0){
				w.setBlockWithNotify(x-1, y+height, z-1, 0);
			}
			if((q & 32) == 0){
				w.setBlockWithNotify(x-1, y+height, z+1, 0);
			}
			if((q & 64) == 0){
				w.setBlockWithNotify(x+1, y+height, z-1, 0);
			}
			if((q & 128) == 0){
				w.setBlockWithNotify(x+1, y+height, z+1, 0);
			}
		}
	}

	public boolean canGrowTree(World w, int x, int y, int z, int height, boolean leavesAllowed){
		for(int X=x-2;X<x+3;X++){
			for(int Y=y;Y<y+height+2;Y++){
				for(int Z=z-2;Z<z+3;Z++){
					int id = w.getBlockId(X, Y, Z);
					if(id == 0) continue;
					if((id == 18)&&(leavesAllowed)) continue;
					if((Block.blocksList[id].blockMaterial != Material.plants)){
						return false;
					}
				}
			}
		}
		return (w.getBlockLightValue(x, y, z) > 9)&&(canGrowHere(w,x,y,z));
	}

	public boolean isBiomeCompatible(World w, int x, int y, int z){
		int meta = w.getBlockMetadata(x, y, z);
		int biome = w.getBiomeGenForCoords(x, z).biomeID;
		if(w.getBlockId(x, y, z) == modnh.blockFruitSapling.blockID){
			switch(meta){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				return (biome == 4)||(biome == 18);
			case 7:
			case 8:
			case 15:
				return (biome == 2)||(biome == 16)||(biome == 17);
			case 9:
				return true; //Pomegranates are a special case.
			case 10:
			case 11:
			case 12:
			case 13:
				return (biome == 6);
			case 14:
				return (biome == 21)||(biome == 22);
			}
		}else{
			switch(meta){
			case 0:
			case 2:
				return (biome == 21)||(biome == 22);
			case 1:
				return (biome == 6);
			}
		}
		return false;
	}

	public void setDisplaced(){
		this.displacement = 16;
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9){
		ItemStack stack = p.getCurrentEquippedItem();
		if((stack != null) && (stack.itemID == Item.dyePowder.shiftedIndex) && (stack.getItemDamage() == 15)){
			this.attemptGrowTree(w, x, y, z, w.rand);
			--stack.stackSize;
			return true;
		}else{
			return super.onBlockActivated(w, x, y, z, p, par6, par7, par8, par9);
		}
	}
}
