package horticulture.tractors;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityTractor extends Entity{

	public EntityTractor(World w){
		super(w);
		this.preventEntitySpawning = false;
	}

	@Override
	protected void entityInit(){
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound ntc){
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound ntc){
		
	}
	
	@Override
	public boolean canBePushed(){
		return false;
	}
}
