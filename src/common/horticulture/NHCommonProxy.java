package horticulture;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import horticulture.mill.ContainerMillstones;
import horticulture.mill.TileEntityMillstones;
import universalelectricity.extend.CommonProxy;
import cpw.mods.fml.common.registry.GameRegistry;

public class NHCommonProxy extends CommonProxy{

	@Override
	public void init(){
		GameRegistry.registerTileEntity(TileEntityMillstones.class, "TileEntityMillstones");
		System.out.println("NH Common Proxy loading");
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer p, World w, int x, int y, int z){
		TileEntity entity = w.getBlockTileEntity(x, y, z);
		if(entity != null){
			switch(id){
			case 0:
				return new ContainerMillstones(p.inventory,(TileEntityMillstones) entity);
			}
		}
		return null;
	}
}
