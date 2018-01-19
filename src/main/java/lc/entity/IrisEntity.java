package lc.entity;

import lc.api.components.ComponentType;
import lc.api.defs.Definition;
import lc.tiles.TileStargateBase;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@Definition(name = "IrisEntity", type = ComponentType.STARGATE, entityClass = IrisEntity.class)
public class IrisEntity extends Entity{

	public IrisEntity(World p_i1582_1_) {
		super(p_i1582_1_);
	}
	
	public IrisEntity(TileStargateBase base) {
		super(base.getWorldObj());
		double radius=3.0;
		double xPos = base.xCoord;
		double yPos = base.yCoord+radius;
		double zPos = base.zCoord;
		double thickness = 0.4;
		ForgeDirection rotation = base.getRotation();
		/*AxisAlignedBB localBox = newAxisAlignedBB();
	/*	if (rotation == ForgeDirection.EAST || rotation == ForgeDirection.WEST)
			localBox = new AxisAlignedBB(xPos, radius, radius, radius, radius, radius);*/
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbtCompound) {
		//load coordinates
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbtCompound) {
		// TODO Auto-generated method stub
		
	}
	
}
