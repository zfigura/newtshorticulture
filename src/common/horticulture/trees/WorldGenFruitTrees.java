package horticulture.trees;

import horticulture.modnh;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenFruitTrees extends WorldGenerator{
	
	public WorldGenFruitTrees(){
		super(true);
	}

	@Override
	public boolean generate(World w, Random r, int x, int y, int z){
		if(!modnh.blockFruitSapling2.canGrowTree(w,x,y,z,7,true)){
			return false;
		}else{
			int sap = modnh.blockFruitSapling.blockID;
			int sap2 = modnh.blockFruitSapling2.blockID;
			switch(w.getBiomeGenForCoords(x,z).biomeID){
//			case 2: //desert
			case 16: //beach
//			case 17: //desert hills
				//generate coconut, date, fig
				switch(r.nextInt(24)){
				case 1:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 15);
					break;
				case 2:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 7);
					break;
				case 3:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 8);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
					return true;
				}
			case 4: //forest
			case 18: //foresthills
				//generate apple, pear, apricot, cherry, peach, plum, mulberry
				w.setBlockAndMetadataWithNotify(x, y, z, sap, r.nextInt(7));
				modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				return true;
			case 6: //swamp
				//generate lemon, lime, orange, olive, kumquat
				if(r.nextInt(5) == 2){
					w.setBlockAndMetadataWithNotify(x, y, z, sap2, 1);
				}else{
					w.setBlockAndMetadataWithNotify(x, y, z, sap, r.nextInt(4)+10);
				}
				modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, false);
				return true;
			case 21: //jungle
			case 22: //jungle hills
				//TODO XXX generate banana, mango, pecan
				return true;
			default:
				return false;
			}
		}
	}
}
