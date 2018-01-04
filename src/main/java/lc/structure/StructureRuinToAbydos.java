package lc.structure;

import lc.LCRuntime;
import lc.common.LCLog;
import lc.common.util.game.BlockFilter;
import lc.common.util.game.BlockFilterWithRot;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class StructureRuinToAbydos {
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
	//LAYER 2
	{1,1,1,1,1,1,1,1,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,7,0,0,0,7,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,6,0,5,0,6,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,7,0,0,0,7,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,1,1,1,1,1,1,1,1},
		},{ //y
	//LAYER 2
	{1,1,1,2,1,2,1,1,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{2,0,0,0,0,0,0,0,2},
	{1,0,0,0,4,0,0,0,1},
	{2,0,0,0,0,0,0,0,2},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,1,1,2,1,2,1,1,1},
		},{ //y
	//LAYER 1
	{1,1,1,1,1,1,1,1,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,0,0,0,0,0,0,0,1},
	{1,1,1,1,1,1,1,1,1},
		},{ //y
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
	{1,1,1,1,1,1,1,1,1}, /////NORTH///x
	{1,1,3,1,3,1,3,1,1}, //
	{1,1,1,1,1,1,1,1,1}, //
	{1,3,1,1,1,1,1,3,1}, //
	{1,1,1,1,1,1,1,1,1}, // East
	{1,1,1,1,1,1,1,1,1}, //
	{1,3,1,1,1,1,1,3,1}, //
	{1,1,3,1,1,1,3,1,1}, //
	{1,1,1,1,1,1,1,1,1}  //z 
		},{
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, /////NORTH///x
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1, 8,-1,-1,-1,-1}, // East
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //
	{-1,-1,-1,-1,-1,-1,-1,-1,-1}, //z 
		
	}};
	
	 static int height = structure.length;
	 static int width = 1;
	 static int length = 9; //TODO 
	 
	 static BlockFilterWithRot[] blocks = { new BlockFilterWithRot(Blocks.air) , new BlockFilterWithRot(Blocks.sandstone,2) , new BlockFilterWithRot(Blocks.sandstone,1), new BlockFilterWithRot(Blocks.stone_slab,1),
			 new BlockFilterWithRot(Blocks.wool,1,ForgeDirection.NORTH),new BlockFilterWithRot(LCRuntime.runtime.blocks().lanteaDecorBlock.getBlock(),4),new BlockFilterWithRot(Blocks.chest),
			 new BlockFilterWithRot(LCRuntime.runtime.blocks().lanteaBrazier.getBlock()),new BlockFilterWithRot(LCRuntime.runtime.blocks().lanteaObelisk.getBlock())};

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
