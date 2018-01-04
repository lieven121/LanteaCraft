package lc.structure;

import lc.LCRuntime;
import lc.common.LCLog;
import lc.common.base.LCBlock;
import lc.common.base.LCTile;
import lc.common.util.game.BlockFilter;
import lc.common.util.game.BlockFilterWithRot;
import lc.common.util.math.Vector3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureDefault {
	private int[][][] structure; //or private
	private BlockFilterWithRot[] blocks; //or private
	private World world;
	private int worldX , worldY , worldZ;
	private ForgeDirection rotation;
	
	public StructureDefault(int[][][] structure, BlockFilterWithRot[] blocks){
		this.structure = structure;
		this.blocks = blocks;
	}
	
	
	private Block getBlock(){
		return (Block) LCRuntime.runtime.blocks().stargateBaseBlock;
	}
	private BlockFilter getFilter(int x, int y, int z){
		return blocks[structure[y][z][x]];
	}
	
	//public boolean placeStructure() {
	//rotation standard south
	public boolean placeStructure(World world, int worldX ,int worldY , int worldZ , ForgeDirection rotation) {	
		//LCLog.debug("null");
		if (structure != null && blocks != null) {
			for (int y = 0; y < structure.length; y++) {
				for (int z = 0; z < structure[y].length; z++) {
					for (int x = 0; x < structure[y][z].length; x++) {

						
					//	centerX-structure.length/2+structure.length
						if (structure[y][z][x] == -1) continue;
						BlockFilter filter = blocks[structure[y][z][x]];
						if (filter == null) continue;
						Block block = filter.getBlock();
						int metadata = Math.max(0, filter.getMetadata());
						//LCLog.debug(x);
						int xRot = x,yRot = y,zRot = z;
						if (rotation == ForgeDirection.SOUTH) zRot = -z;
						if (rotation == ForgeDirection.EAST) { zRot=xRot; xRot=-z; }
						if (rotation == ForgeDirection.WEST) { zRot=xRot; xRot=z; }
						//TODO rotate around same axys
						if( block != null) {
							world.setBlock(worldX+xRot, worldY+yRot, worldZ+zRot, block, metadata,0);
			
							if (block instanceof LCBlock) {
								
								LCTile te = (LCTile) world.getTileEntity(worldX+xRot, worldY+yRot, worldZ+zRot);
								if (te != null)
								te.setRotation(rotation);
							}
							/*World w, StructureBoundingBox bb, Vector3 v0, Vector3 v1, Block b0, int m0, Block b1, int m1,
							boolean rep) {*/
						//fillWithMetadataBlocks(world, bb, v0.fx(), v0.fy(), v0.fz(), v1.fx(), v1.fy(), v1.fz(), b0,
							//	m0, b1, m1, rep);
							//TileEntity te = world.getTileEntity(xRot,yRot,z);
							//te.ro
							/*if (te != null) {
							LCTile teLC = (LCTile) te;
								teLC.setRotation(ForgeDirection.EAST);
							}*/
						}
					}
				}
			}
			return true;
		}
		return false;
	}
	
	
}
