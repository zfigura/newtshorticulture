package horticulture.tractors;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import horticulture.modnh;

import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityTractor extends Entity{
	private boolean field_70279_a;
	private double field_70276_b;
	private int boatPosRotationIncrements;
	private double boatX;
	private double boatY;
	private double boatZ;
	private double boatYaw;
	private double boatPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;

	public EntityTractor(World par1World){
		super(par1World);
		this.field_70279_a = true;
		this.field_70276_b = .07;
		this.preventEntitySpawning = true;
		this.setSize(1.5625F,1.5625F);
		this.yOffset = this.height / 2;
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they
	 * walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking(){
		return false;
	}

	@Override
	protected void entityInit(){
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Integer(0));
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and
	 * blocks. This enables the entity to be pushable on contact, like boats or
	 * minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity){
		return par1Entity.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	@Override
	public AxisAlignedBB getBoundingBox(){
		return this.boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed(){
		return true;
	}

	public EntityTractor(World w, double x, double y, double z){
		this(w);
		this.setPosition(x, y + this.yOffset, z);
		this.motionX = 0;
		this.motionY = 0;
		this.motionZ = 0;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding
	 * this one.
	 */
	@Override
	public double getMountedYOffset(){
		return -.3;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2){
		System.out.println("Ouch!");
		if (this.func_85032_ar()){
			return false;
		} else if (!this.worldObj.isRemote && !this.isDead){
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + par2 * 10);
			this.setBeenAttacked();
			if (par1DamageSource.getEntity() instanceof EntityPlayer
					&& ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode){
				this.setDamageTaken(100);
			}
			if (this.getDamageTaken() > 40){
				if (this.riddenByEntity != null){
					this.riddenByEntity.mountEntity(this);
				}
				this.dropItemWithOffset(modnh.itemTractor.shiftedIndex, 1, 0);
				this.setDead();
			}
			return true;
		} else {
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	public void performHurtAnimation(){
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith(){
		return !this.isDead;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9){
		if (this.field_70279_a){
			this.boatPosRotationIncrements = par9 + 5;
		} else {
			double var10 = par1 - this.posX;
			double var12 = par3 - this.posY;
			double var14 = par5 - this.posZ;
			double var16 = var10 * var10 + var12 * var12 + var14 * var14;

			if (var16 <= 1){
				return;
			}

			this.boatPosRotationIncrements = 3;
		}

		this.boatX = par1;
		this.boatY = par3;
		this.boatZ = par5;
		this.boatYaw = par7;
		this.boatPitch = par8;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	public void setVelocity(double par1, double par3, double par5){
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate(){
		super.onUpdate();

		if (this.getTimeSinceHit() > 0){
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0){
			this.setDamageTaken(this.getDamageTaken() - 1);
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		byte var1 = 5;
		double var2 = 0;

		for (int var4 = 0; var4 < var1; ++var4){
			double var5 = this.boundingBox.minY
					+ (this.boundingBox.maxY - this.boundingBox.minY)
					* (var4 + 0) / var1 - .125;
			double var7 = this.boundingBox.minY
					+ (this.boundingBox.maxY - this.boundingBox.minY)
					* (var4 + 1) / var1 - .125;
			AxisAlignedBB var9 = AxisAlignedBB.getAABBPool()
					.addOrModifyAABBInPool(this.boundingBox.minX, var5,
							this.boundingBox.minZ, this.boundingBox.maxX, var7,
							this.boundingBox.maxZ);

			if (this.worldObj.isAABBInMaterial(var9, Material.water)){
				var2 += 1 / var1;
			}
		}

		double var24 = Math.sqrt(this.motionX * this.motionX + this.motionZ
				* this.motionZ);
		double var6;
		double var8;

		if (var24 > .2625){
			var6 = Math.cos(this.rotationYaw * Math.PI / 180);
			var8 = Math.sin(this.rotationYaw * Math.PI / 180);

			for (int var10 = 0; var10 < 1 + var24 * 60; ++var10){
				double var11 = this.rand.nextFloat()*2 - 1;
				double var13 = (this.rand.nextInt(2)*2 - 1) * .7;
				double var15;
				double var17;

				if (this.rand.nextBoolean()){
					var15 = this.posX - var6 * var11 * .8 + var8 * var13;
					var17 = this.posZ - var8 * var11 * .8 - var6 * var13;
					this.worldObj.spawnParticle("splash", var15,
							this.posY - .125, var17, this.motionX,
							this.motionY, this.motionZ);
				} else {
					var15 = this.posX + var6 + var8 * var11 * .7;
					var17 = this.posZ + var8 - var6 * var11 * .7;
					this.worldObj.spawnParticle("splash", var15,
							this.posY - .125, var17, this.motionX,
							this.motionY, this.motionZ);
				}
			}
		}

		double var12;
		double var26;

		if (this.worldObj.isRemote && this.field_70279_a){
			if (this.boatPosRotationIncrements > 0){
				var6 = this.posX + (this.boatX - this.posX)
						/ this.boatPosRotationIncrements;
				var8 = this.posY + (this.boatY - this.posY)
						/ this.boatPosRotationIncrements;
				var26 = this.posZ + (this.boatZ - this.posZ)
						/ this.boatPosRotationIncrements;
				var12 = MathHelper.wrapAngleTo180_double(this.boatYaw
						- this.rotationYaw);
				this.rotationYaw = (float) (this.rotationYaw + var12
						/ this.boatPosRotationIncrements);
				this.rotationPitch = (float) (this.rotationPitch + (this.boatPitch - this.rotationPitch)
						/ this.boatPosRotationIncrements);
				--this.boatPosRotationIncrements;
				this.setPosition(var6, var8, var26);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			} else {
				var6 = this.posX + this.motionX;
				var8 = this.posY + this.motionY;
				var26 = this.posZ + this.motionZ;
				this.setPosition(var6, var8, var26);

				if (this.onGround){
					this.motionX *= .5;
					this.motionY *= .5;
					this.motionZ *= .5;
				}

				this.motionX *= .99;
				this.motionY *= .95;
				this.motionZ *= .99;
			}
		} else {
			if (var2 < 1){
				var6 = var2*2 - 1;
				this.motionY += .04*var6;
			} else {
				if (this.motionY < 0){
					this.motionY /= 2;
				}

				this.motionY += .007;
			}

			if (this.riddenByEntity != null){
				this.motionX += this.riddenByEntity.motionX
						* this.field_70276_b;
				this.motionZ += this.riddenByEntity.motionZ
						* this.field_70276_b;
			}

			var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ
					* this.motionZ);

			if (var6 > .35){
				var8 = .35 / var6;
				this.motionX *= var8;
				this.motionZ *= var8;
				var6 = .35;
			}

			if (var6 > var24 && this.field_70276_b < .35){
				this.field_70276_b += (.35 - this.field_70276_b) / 35;

				if (this.field_70276_b > .35){
					this.field_70276_b = .35;
				}
			} else {
				this.field_70276_b -= (this.field_70276_b - .07) / 35;

				if (this.field_70276_b < .07){
					this.field_70276_b = .07;
				}
			}

			if (this.onGround){
				this.motionX *= .5;
				this.motionY *= .5;
				this.motionZ *= .5;
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			if (this.isCollidedHorizontally && var24 > .2){
				if (!this.worldObj.isRemote){
					this.setDead();
					int var25;
					for (var25 = 0; var25 < 3; ++var25){
						this.dropItemWithOffset(Block.planks.blockID, 1, 0);
					}
					for (var25 = 0; var25 < 2; ++var25){
						this.dropItemWithOffset(Item.stick.shiftedIndex, 1, 0);
					}
				}
			} else {
				this.motionX *= .99;
				this.motionY *= .95;
				this.motionZ *= .99;
			}

			this.rotationPitch = 0;
			var8 = this.rotationYaw;
			var26 = this.prevPosX - this.posX;
			var12 = this.prevPosZ - this.posZ;

			if (var26 * var26 + var12 * var12 > .001){
				var8 = ((float) (Math.atan2(var12, var26) * 180 / Math.PI));
			}

			double var14 = MathHelper.wrapAngleTo180_double(var8
					- this.rotationYaw);

			if (var14 > 20){
				var14 = 20;
			}

			if (var14 < -20){
				var14 = -20;
			}

			this.rotationYaw = (float) (this.rotationYaw + var14);
			this.setRotation(this.rotationYaw, this.rotationPitch);

			if (!this.worldObj.isRemote){
				List var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(.2,0,.2));
				int var27;

				if (var16 != null && !var16.isEmpty()){
					for (var27 = 0; var27 < var16.size(); ++var27){
						Entity var18 = (Entity) var16.get(var27);

						if (var18 != this.riddenByEntity && var18.canBePushed()
								&& var18 instanceof EntityTractor){
							var18.applyEntityCollision(this);
						}
					}
				}

				for (var27 = 0; var27 < 4; ++var27){
					int var28 = MathHelper.floor_double(this.posX
							+ (var27 % 2 - .5) * .8);
					int var19 = MathHelper.floor_double(this.posZ
							+ (var27 / 2 - .5) * .8);

					for (int var20 = 0; var20 < 2; ++var20){
						int var21 = MathHelper.floor_double(this.posY) + var20;
						int var22 = this.worldObj.getBlockId(var28, var21,
								var19);
						int var23 = this.worldObj.getBlockMetadata(var28,
								var21, var19);

						if (var22 == Block.snow.blockID){
							this.worldObj.setBlockWithNotify(var28, var21,
									var19, 0);
						} else if (var22 == Block.waterlily.blockID){
							Block.waterlily.dropBlockAsItemWithChance(this.worldObj, var28, var21, var19, var23, .3f, 0);
							this.worldObj.setBlockWithNotify(var28, var21,
									var19, 0);
						}
					}
				}

				if (this.riddenByEntity != null && this.riddenByEntity.isDead){
					this.riddenByEntity = null;
				}
			}
		}
	}

	@Override
	public void updateRiderPosition(){
		if (this.riddenByEntity != null){
			double var1 = Math.cos(this.rotationYaw * Math.PI / 180) * .4;
			double var3 = Math.sin(this.rotationYaw * Math.PI / 180) * .4;
			this.riddenByEntity.setPosition(
					this.posX + var1,
					this.posY + this.getMountedYOffset()
							+ this.riddenByEntity.getYOffset(), this.posZ
							+ var3);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow,
	 * gets into the saddle on a pig.
	 */
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer){
		if (this.riddenByEntity != null
				&& this.riddenByEntity instanceof EntityPlayer
				&& this.riddenByEntity != par1EntityPlayer){
			return true;
		} else {
			if (!this.worldObj.isRemote){
				par1EntityPlayer.mountEntity(this);
			}

			return true;
		}
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(int par1){
		this.dataWatcher.updateObject(19, Integer.valueOf(par1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize(){
		return 0;
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public int getDamageTaken(){
		return this.dataWatcher.getWatchableObjectInt(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int par1){
		this.dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit(){
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public void setForwardDirection(int par1){
		this.dataWatcher.updateObject(18, Integer.valueOf(par1));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public int getForwardDirection(){
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	@SideOnly(Side.CLIENT)
	public void func_70270_d(boolean par1){
		this.field_70279_a = par1;
	}
}
