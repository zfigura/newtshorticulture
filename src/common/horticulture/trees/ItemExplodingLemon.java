package horticulture.trees;

import horticulture.modnh;

import java.util.ArrayList;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

//TODO everything
public class ItemExplodingLemon extends ItemFood{

	public ItemExplodingLemon(int id){
		super(id,2,false);
		this.setTabToDisplayOn(CreativeTabs.tabFood);
		this.setItemName("Lemon");
		this.setIconCoord(1,0);
		this.setAlwaysEdible();
		ModLoader.addName(this, this.getItemName());
	}
	
	@Override
	protected void func_77849_c(ItemStack stack, World w, EntityPlayer p){
//		if(p instanceof EntityClientPlayerMP) return;
		w.createExplosion(null, p.posX, p.posY, p.posZ, 10);
		p.setEntityHealth(0);
//		int forests = 0,places = 0;
//		int cx = p.chunkCoordX << 4,cz = p.chunkCoordZ << 4;
//		for(int i=0;i<256;i++){
//			int x=(i&15)+cx,z=(i&240)+cz;
//			int y = w.getTopSolidOrLiquidBlock(x, z);
//			if(w.getBiomeGenForCoords(x, z).biomeID == 4){
//				++forests;
//				if(modnh.blockFruitSapling.canGrowTree(w, x, y, z, 7, true)){
//					++places;
//				}
//			}
//		}
//		System.out.println(forests+","+places);
	}
}
