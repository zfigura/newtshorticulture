package horticulture.fruit;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemExplodingLemon extends ItemFood{

	public ItemExplodingLemon(int id){
		super(id,2,false);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setItemName("Lemon");
		this.setIconCoord(7,0);
		this.setAlwaysEdible();
		LanguageRegistry.addName(this, this.getItemName());
		this.setTextureFile(ItemFruit.texfile);
	}
	
	@Override
	protected void func_77849_c(ItemStack stack, World w, EntityPlayer p){
		w.createExplosion(null, p.posX, p.posY, p.posZ, 10, false);
		p.setEntityHealth(0);
	}
}
