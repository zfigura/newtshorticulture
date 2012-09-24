package horticulture.tractors;

import java.util.Iterator;
import java.util.List;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityBoat;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumMovingObjectType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Vec3;
import net.minecraft.src.World;
import horticulture.ItemNH;

public class ItemTractor extends ItemNH{

	public ItemTractor(int id){
		super(id,"Tractor");
		this.maxStackSize = 1;
		this.setTabToDisplayOn(CreativeTabs.tabTransport);
	}

	public ItemStack onItemRightClick(ItemStack stack, World w, EntityPlayer p){
		w.spawnEntityInWorld(new EntityTractor(w,p.posX,p.posY,p.posZ));
		return stack;
		//Copied from ItemBoat. Sorry for any messiness
		/*
		float var4 = 1.0F;
		float pitch = p.prevRotationPitch + (p.rotationPitch - p.prevRotationPitch) * var4;
		float yaw = p.prevRotationYaw + (p.rotationYaw - p.prevRotationYaw) * var4;
		double x = p.prevPosX + (p.posX - p.prevPosX) * (double)var4;
		double y = p.prevPosY + (p.posY - p.prevPosY) * (double)var4 + 1.62D - (double)p.yOffset;
		double z = p.prevPosZ + (p.posZ - p.prevPosZ) * (double)var4;
		Vec3 vector = Vec3.getVec3Pool().getVecFromPool(x, y, z);
		float var14 = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
		float var15 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
		float var16 = -MathHelper.cos(-pitch * 0.017453292F);
		float vy = MathHelper.sin(-pitch * 0.017453292F);
		float vx = var15 * var16;
		float vz = var14 * var16;
		double vscale = 5.0D;
		Vec3 newvec = vector.addVector((double)vx * vscale, (double)vy * vscale, (double)vz * vscale);
		MovingObjectPosition mop = w.rayTraceBlocks_do(vector, newvec, true);

		if (mop == null)
		{
			return stack;
		}
		else
		{
			Vec3 var25 = p.getLook(var4);
			boolean var26 = false;
			float var27 = 1.0F;
			List var28 = w.getEntitiesWithinAABBExcludingEntity(p, p.boundingBox.addCoord(var25.xCoord * vscale, var25.yCoord * vscale, var25.zCoord * vscale).expand((double)var27, (double)var27, (double)var27));
			Iterator var29 = var28.iterator();

			while (var29.hasNext())
			{
				Entity var30 = (Entity)var29.next();

				if (var30.canBeCollidedWith())
				{
					float var31 = var30.getCollisionBorderSize();
					AxisAlignedBB var32 = var30.boundingBox.expand((double)var31, (double)var31, (double)var31);

					if (var32.isVecInside(vector))
					{
						var26 = true;
					}
				}
			}

			if (var26)
			{
				return stack;
			}
			else
			{
				if (mop.typeOfHit == EnumMovingObjectType.TILE)
				{
					int var35 = mop.blockX;
					int var33 = mop.blockY;
					int var34 = mop.blockZ;

					if (!w.isRemote)
					{
						if (w.getBlockId(var35, var33, var34) == Block.snow.blockID)
						{
							--var33;
						}

						w.spawnEntityInWorld(new EntityTractor(w, (double)((float)var35 + 0.5F), (double)((float)var33 + 1.0F), (double)((float)var34 + 0.5F)));
					}

					if (!p.capabilities.isCreativeMode)
					{
						--stack.stackSize;
					}
				}

				return stack;
			}
		}
		*/
	}
}
