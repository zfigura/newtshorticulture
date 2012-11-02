package horticulture.fruit;

import horticulture.modnh;

import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenFruitTrees{
	
	public WorldGenFruitTrees(){
	}

	public boolean generate(World w, Random r, int x, int y, int z, boolean isPomegranate){
		if(!modnh.blockFruitSapling2.canGrowTree(w,x,y,z,7,true) && !isPomegranate){
			return false;
		}else{
			int sap = modnh.blockFruitSapling.blockID;
			int sap2 = modnh.blockFruitSapling2.blockID;
			switch(w.getBiomeGenForCoords(x,z).biomeID){
			case 16: //beach
				//generate coconut, date, fig
				switch(r.nextInt(64)){
				case 1:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 15);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
					break;
				case 2:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 7);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
					break;
				case 0:
					//TODO clean up the spilled Coke
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 8);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				}
				return true;
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
				switch(r.nextInt(3)){
				case 0:
					w.setBlockAndMetadataWithNotify(x, y, z, sap, 14);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				case 1:
					w.setBlockAndMetadataWithNotify(x, y, z, sap2, 0);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				case 2:
					w.setBlockAndMetadataWithNotify(x, y, z, sap2, 2);
					modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				}
				return true;
			case 8:
				System.out.println("Attempting to generate a tree at "+x+","+y+","+z);
				w.setBlockAndMetadataWithNotify(x, y, z, sap, 9);
				modnh.blockFruitSapling.attemptGrowTree(w, x, y, z, r, true);
				return true;
			default:
				return false;
			}
		}
	}
}
