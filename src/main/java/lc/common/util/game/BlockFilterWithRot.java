package lc.common.util.game;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFilterWithRot extends BlockFilter{
	private ForgeDirection targetrotation;
	public BlockFilterWithRot(Block block, int metadata, ForgeDirection rotation) {
		super(block, metadata);
		targetrotation = rotation;
		// TODO Auto-generated constructor stub
	}
	public BlockFilterWithRot(Block block, int metadata) {
		super(block, metadata);
		targetrotation = ForgeDirection.NORTH;
		// TODO Auto-generated constructor stub
	}
	public BlockFilterWithRot(Block block) {
		super(block, -1);
		targetrotation = ForgeDirection.NORTH;
		// TODO Auto-generated constructor stub
	}
	public ForgeDirection getRotation() {
		return targetrotation;
	}
}
