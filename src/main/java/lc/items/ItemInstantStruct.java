package lc.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import lc.LCRuntime;
import lc.LanteaCraft;
import lc.api.components.ComponentType;
import lc.api.defs.Definition;
import lc.common.LCLog;
import lc.common.base.LCBlock;
import lc.common.base.LCItem;
import lc.common.base.LCTile;
import lc.common.configuration.xml.ComponentConfig;
import lc.common.resource.ResourceAccess;
import lc.common.util.ScanningHelper;
import lc.structure.StructureAbydosPyramid;
import lc.structure.StructureDefault;
import lc.structure.StructureRuinToAbydos;
import lc.structure.StructureSand;
import lc.structure.StructureStargate;
import lc.tiles.TileStargateBase;
import lc.tiles.TileTransportRing;

@Definition(name = "Instant Structure", type = ComponentType.WORLDGEN, itemClass = ItemInstantStruct.class)
public class ItemInstantStruct extends LCItem {

	/** Display icon */
	public IIcon icon;

	public ItemInstantStruct() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configure(ComponentConfig c) {
		// TODO Auto-generated method stub

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_,
			float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		int[] convertVal = {3,4,2,5};
		ForgeDirection direction = ForgeDirection.values()[convertVal[MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3]];
			//StructureRuinToAbydos.generateStructureCenter(world, x, y-StructureRuinToAbydos.getHeigth()+2, z, direction);
			StructureAbydosPyramid.generateStructureCenter(world, x, y-6, z, direction);
			/*if (world.getBlock(x, y, z) instanceof LCBlock) {
				//TileStargateBase te = (TileStargateBase) world.getTileEntity(x,y,z);
				if (!world.isRemote) {
					player.addChatMessage(new ChatComponentText("Gate added, Adress: "+te.getStargateAddressString()));
				}
			}*/
		return true;
	}

	@Override
	public void registerIcons(IIconRegister ir) {
		icon = ir.registerIcon(ResourceAccess.formatResourceName("${ASSET_KEY}:"));
	}

	@Override
	public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
		return icon;
	}

	@Override
	public IIcon getIconFromDamage(int p_77617_1_) {
		return icon;
	}

}
