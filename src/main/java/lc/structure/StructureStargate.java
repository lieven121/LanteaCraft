package lc.structure;

import lc.LCRuntime;
import lc.common.LCLog;
import lc.common.base.LCTile;
import lc.common.util.game.BlockFilter;
import lc.common.util.game.BlockFilterWithRot;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureStargate {
	//yzx
	static int[][][] structure = {{
	//LAYER 0
	{1,2,1,3,1,2,1}  //z 
	},{
	{2,0,0,0,0,0,2}  //z 
	},{
	{1,0,0,0,0,0,1} //z 
	},{
	{1,0,0,0,0,0,1} //z 
	},{
	{2,0,0,0,0,0,2}  //z 
	},{
	{1,0,0,0,0,0,1} //z 
	},{
	{1,2,1,2,1,2,1} //z 
	}};
	
	 static int height = structure.length;
	 static int width = 1;
	 static int length = 7; //TODO 
	
	 static BlockFilterWithRot[] blocks = { new BlockFilterWithRot(Blocks.air) , new BlockFilterWithRot(LCRuntime.runtime.blocks().stargateRingBlock.getBlock(), 0),
				new BlockFilterWithRot(LCRuntime.runtime.blocks().stargateRingBlock.getBlock(), 1),
				new BlockFilterWithRot(LCRuntime.runtime.blocks().stargateBaseBlock.getBlock()) };

	 public static int getHeigth() { //y
		 return height;
	 }
	 public static int getWidth() { //z
		 return width;
	 }
	 public static int getLength() { //x
		 return length;
	 }

	public static boolean generateStructure(World world, int worldX,int worldY, int worldZ, ForgeDirection rotation) {
		StructureDefault struct = new StructureDefault(structure, blocks);
		struct.placeStructure(world, worldX , worldY, worldZ, rotation);
		return true;			
	}
	
	
	 	public static boolean generateStructureCenter(World world, int worldX,int worldY, int worldZ, ForgeDirection rotation) {
		StructureDefault struct = new StructureDefault(structure, blocks);
		
		if (!rotation.equals(ForgeDirection.EAST) & !rotation.equals(ForgeDirection.WEST) ) 
			struct.placeStructure(world, worldX - length/2 , worldY, worldZ - width/2, rotation);
		else 
			struct.placeStructure(world, worldX - width/2 , worldY, worldZ - length/2, rotation);
		
		//add paiting
		return true;			
	}
	 
	
}
