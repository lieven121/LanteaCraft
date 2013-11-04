//------------------------------------------------------------------------------------------------
//
//   SG Craft - Naquadah alloy block
//
//------------------------------------------------------------------------------------------------

package gcewing.sg.blocks;

import net.minecraft.block.BlockOreStorage;

public class NaquadahBlock extends BlockOreStorage {

	// static int texture = 0x43;

	public NaquadahBlock(int id) {
		super(id);
		// setTextureFile(SGCraft.textureFile);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundMetalFootstep);
	}

}