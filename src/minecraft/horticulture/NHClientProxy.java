package horticulture;

import horticulture.mill.TileEntityMillstones;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;

public class NHClientProxy extends NHCommonProxy{ //hopefully this is okay
	
	@Override
	public void init(){
		MinecraftForgeClient.preloadTexture(ItemNH.texfile);
		MinecraftForgeClient.preloadTexture(BlockNH.texfile);
		ClientRegistry.registerTileEntity(TileEntityMillstones.class, "TileEntityMillstones", new RenderMillstones());
		System.out.println("NH Client Proxy loading");
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer p, World w, int x, int y, int z){
		TileEntity entity = w.getBlockTileEntity(x, y, z);
		if(entity != null){
			switch(id){
			case 0:
				return new GUIMillstones(p.inventory,(TileEntityMillstones) entity);
			}
		}
		return null;
	}
}
