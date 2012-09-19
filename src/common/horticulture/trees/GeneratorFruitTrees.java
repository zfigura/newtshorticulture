package horticulture.trees;

import java.util.Random;

import net.minecraft.src.ChunkProviderGenerate;
import net.minecraft.src.ChunkProviderHell;
import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
import cpw.mods.fml.common.IWorldGenerator;

public class GeneratorFruitTrees implements IWorldGenerator{
	@Override
	public void generate(Random r, int cx, int cz, World w, IChunkProvider cg, IChunkProvider cp){
		cx <<= 4;
		cz <<= 4;
		if(cg instanceof ChunkProviderGenerate){
			WorldGenFruitTrees wgft = new WorldGenFruitTrees();
			for(int i:new int[]{0,1,2,3}){
				int x = cx+r.nextInt(16);
				int z = cz+r.nextInt(16);
				wgft.generate(w, r, x, w.getTopSolidOrLiquidBlock(x, z), z, false);
			}
		}else if(cg instanceof ChunkProviderHell){
			WorldGenFruitTrees wgft = new WorldGenFruitTrees();
			int x = cx+r.nextInt(16);
			int z = cz+r.nextInt(16);
			wgft.generate(w, r, x, r.nextInt(w.getActualHeight()-10), z, true);
		}
	}
}