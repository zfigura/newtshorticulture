package horticulture.tractors;

import java.util.Iterator;
import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class EntityTractor extends Entity{

	/**Maximum speed in meters per second. TODO TODO TODO TODO: what is a realistic number?*/
	public static final int maxspeed = 1;
	public static final int terminalVelocity = -1;
	public static final double airDrag = .95;


	public EntityTractor(World w){
		super(w);
		this.preventEntitySpawning = false;
	}

	public EntityTractor(World w, double x, double y, double z){
		this(w);
		this.setPosition(x, y, z);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity e){
		return e.boundingBox;
	}

	@Override
	public AxisAlignedBB getBoundingBox(){
		return this.boundingBox;
	}

	@Override
	public boolean canBePushed(){
		return true;
	}

	@Override
	public double getMountedYOffset(){
		//TODO adjust
		return 1;
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, int q){
		super.attackEntityFrom(ds, q);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void performHurtAnimation(){
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
	}

	@Override
	public boolean canBeCollidedWith(){
		return !this.isDead;
	}

	@Override
	public void setDead(){
		super.setDead();
	}

	@Override
	public void onUpdate(){
		if(this.getTimeSinceHit() > 0){
			this.setTimeSinceHit(this.getTimeSinceHit()-1);
		}
		if(this.posY < -64){
			this.kill();
		}
		this.worldObj.spawnParticle("largesmoke", this.posX+1, this.posY+21, this.posZ+3, 0, 1, 0);
		if(this.worldObj.isRemote){
			//TODO set position and rotation
		}else{
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
			this.motionY -= .04;
			int floorX = MathHelper.floor_double(this.posX);
			int floorY = MathHelper.floor_double(this.posY);
			int floorZ = MathHelper.floor_double(this.posZ);
			this.motionX = MathHelper.clamp_float((float) motionX, -maxspeed, maxspeed);
			this.motionZ = MathHelper.clamp_float((float) motionZ, -maxspeed, maxspeed);
			double moveY = motionY;
			if(this.terminalVelocity > 0 && motionY > this.terminalVelocity){
				moveY = terminalVelocity;
				if(Math.abs(motionX) < .3 && Math.abs(motionZ) < .3){
					moveY = .15;
					motionY = moveY;
				}
			}
			if(this.onGround){
				motionX *= .5;
				motionY *= .5;
				motionZ *= .5;
			}
			moveEntity(motionX,moveY,motionZ);
			if(!this.onGround){
				motionX *= airDrag;
				motionY *= airDrag;
				motionZ *= airDrag;
			}
			this.rotationPitch = 0;
			//TODO add jiggle here. Maybe check for the "view footsteps" option?
			this.setRotation(this.rotationYaw, this.rotationPitch);
			
			List entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(.2, 0, .2));
			if(entities != null && !entities.isEmpty()){
				Iterator it = entities.iterator();
				while(it.hasNext()){
					Entity i = (Entity)it.next();
					if(i != this.riddenByEntity && i.canBePushed() && i instanceof EntityTractor){
						i.applyEntityCollision(this);
					}
				}
			}
			if(this.riddenByEntity != null && this.riddenByEntity.isDead){
				if(this.riddenByEntity.ridingEntity == this){
					this.riddenByEntity.ridingEntity = null;
				}
				this.riddenByEntity = null;
			}
		}
	}
	
	@Override
	public boolean interact(EntityPlayer p){
		if(canBeRidden()){
			if(this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != p){
				return true;
			}
			if(!this.worldObj.isRemote){
				p.mountEntity(this);
			}
		}
		return true;
	}
	
	/**Keep this in case we need to implement IInventory.*/
	public boolean isUseableByPlayer(EntityPlayer p){
		return this.isDead ? false: p.getDistanceSqToEntity(this) <= 64;
	}
	
	public boolean canBeRidden(){
		return true;
	}

	public void setForwardDirection(int dir){
		this.dataWatcher.updateObject(18, dir);
	}

	public void setTimeSinceHit(int time){
		this.dataWatcher.updateObject(17, time);
	}

	public int getForwardDirection(){
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	public int getTimeSinceHit(){
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	@Override
	protected void entityInit(){
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound ntc){

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound ntc){

	}
}
