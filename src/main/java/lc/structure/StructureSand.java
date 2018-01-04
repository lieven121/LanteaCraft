package lc.structure;

import lc.common.LCLog;
import lc.common.util.game.BlockFilter;
import lc.common.util.game.BlockFilterWithRot;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureSand {
	//yzx
	static int[][][] structure = {{
	//LAYER 0
	{1,1,1,1,1,1,1,1,1}, /////NORTH///x
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}, // East
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}  //z 
	},{ //y
	//LAYER 1
	{1,1,1,1,1,1,1,1,1},
	{1,0,1,1,0,1,1,0,1},
	{1,1,1,1,1,1,1,1,1},
	{1,1,1,1,2,1,1,1,1},
	{1,0,1,2,2,2,1,0,1},
	{1,1,1,1,2,1,1,1,1},
	{1,1,1,1,1,1,1,1,1},
	{1,0,1,1,0,1,1,0,1},
	{1,1,1,1,1,1,1,1,1},
		},{ //y
	//LAYER 2
	{1,1,1,1,1,1,1,1,1}, ////////x
	{1,3,1,1,3,1,1,3,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,1,1,1,4,1,1,1,1}, //
	{1,3,1,4,1,4,1,3,1}, // 
	{1,1,1,1,4,1,1,1,1},
	{1,1,1,1,1,1,1,1,1},
	{1,3,1,1,3,1,1,3,1},
	{1,1,1,1,1,1,1,1,1}  //z
		},{ //y
		},{ //y
		},{ //y
		},{ //y
	{1,1,1,1,1,1,1,1,1}, /////SOUTH///x
	{1,1,1,5,5,5,1,1,1}, //
	{1,1,5,1,5,1,5,1,1}, //
	{1,5,1,1,5,1,1,5,1}, //
	{1,1,1,1,5,1,1,1,1}, // East
	{1,1,1,1,5,1,1,1,1}, //
	{1,1,1,1,5,1,1,1,1}, //
	{1,1,1,1,5,1,1,1,1}, //
	{1,1,1,1,1,1,1,1,1}  //z 
		
	}};
	
	 static BlockFilterWithRot[] blocks = { new BlockFilterWithRot(Blocks.air) , new BlockFilterWithRot(Blocks.sandstone) , new BlockFilterWithRot(Blocks.wool,5), new BlockFilterWithRot(Blocks.chest),
			 new BlockFilterWithRot(Blocks.sticky_piston,1,ForgeDirection.NORTH),new BlockFilterWithRot(Blocks.glowstone)};

	 
	 
	 static int height = structure.length;
	 static int width = 9;
	 static int length = 9; //TODO 

	public static boolean generateStructure(World world, int worldX,int worldY, int worldZ, ForgeDirection rotation) {
		StructureDefault struct = new StructureDefault(structure, blocks);
		struct.placeStructure(world, worldX , worldY, worldZ, rotation);
		return true;			
	}
	
	
	 	public static boolean generateStructureCenter(World world, int worldX,int worldY, int worldZ, ForgeDirection rotation) {
		StructureDefault struct = new StructureDefault(structure, blocks);
		struct.placeStructure(world, worldX - length/2 , worldY, worldZ + width/2, rotation);
		return true;			
	}
	 
	
}
