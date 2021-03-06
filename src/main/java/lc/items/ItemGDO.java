package lc.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import lc.LCRuntime;
import lc.LanteaCraft;
import lc.api.components.ComponentType;
import lc.api.defs.Definition;
import lc.common.base.LCItem;
import lc.common.configuration.xml.ComponentConfig;
import lc.common.resource.ResourceAccess;

@Definition(name = "gdo", type = ComponentType.STARGATE, itemClass = ItemGDO.class)
public class ItemGDO extends LCItem {

	/** Display icon */
	public IIcon icon;

	public ItemGDO() {
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
		if (world.isRemote) {
			player.openGui(LanteaCraft.instance, LCRuntime.runtime.interfaces().gdoUI.getGUIID(), world, x, y, z);
		}
		return true;
	}

	@Override
	public void registerIcons(IIconRegister ir) {
		icon = ir.registerIcon(ResourceAccess.formatResourceName("${ASSET_KEY}:gdo_iris_controller_${TEX_QUALITY}"));
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
