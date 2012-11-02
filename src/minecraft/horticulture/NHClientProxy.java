package horticulture;

import horticulture.fruit.ItemFruit;
import horticulture.mill.GUIMillstones;
import horticulture.mill.RenderMillstones;
import horticulture.mill.TileEntityMillstones;
import horticulture.tractors.EntityTractor;
import horticulture.tractors.RenderTractor;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.RenderManager;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class NHClientProxy extends NHCommonProxy{
	
	@Override
	public void init(){
		MinecraftForgeClient.preloadTexture(ItemNH.texfile);
		MinecraftForgeClient.preloadTexture(BlockNH.texfile);
		MinecraftForgeClient.preloadTexture(ItemFruit.texfile);
		ClientRegistry.registerTileEntity(TileEntityMillstones.class, "TileEntityMillstones", new RenderMillstones());
		RenderingRegistry.registerEntityRenderingHandler(EntityTractor.class, new RenderTractor());
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
