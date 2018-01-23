package lc.tiles;

import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import lc.LCRuntime;
import lc.api.rendering.ITileRenderInfo;
import lc.client.render.gfx.particle.GFXDust;
import lc.client.render.gfx.particle.GFXFlame;
import lc.client.render.gfx.particle.GFXFluidDroplet;
import lc.common.base.LCTile;
import lc.common.configuration.xml.ComponentConfig;
import lc.common.network.LCNetworkException;
import lc.common.network.LCPacket;

public class TileConfigurator extends LCTile {

	public TileConfigurator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ITileRenderInfo renderInfoTile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configure(ComponentConfig c) {
		// TODO Auto-generated method stub

	}

	@Override
	public IInventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void thinkClient() {
		// TODO Auto-generated method stub
	}

	@Override
	public void thinkServer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void thinkPacket(LCPacket packet, EntityPlayer player) throws LCNetworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean shouldRender() {
		return true;
	}

	@Override
	public void save(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(NBTTagCompound compound) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] debug(Side side) {
		// TODO Auto-generated method stub
		return null;
	}

}
