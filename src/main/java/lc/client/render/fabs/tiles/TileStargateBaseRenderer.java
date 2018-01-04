package lc.client.render.fabs.tiles;

import lc.api.stargate.StargateType;
import lc.client.animation.Animation;
import lc.client.models.ModelStargate;
import lc.client.render.animations.VortexAnimation;
import lc.common.LCLog;
import lc.common.base.LCTile;
import lc.common.base.LCTileRenderer;
import lc.common.base.multiblock.MultiblockState;
import lc.common.base.pipeline.LCTileRenderPipeline;
import lc.common.configuration.xml.ComponentConfig;
import lc.common.resource.ResourceAccess;
import lc.common.resource.ResourceMap;
import lc.common.util.data.StateMap;
import lc.common.util.math.Vector3;
import lc.tiles.TileStargateBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * Stargate base tile renderer.
 *
 * @author AfterLifeLochie
 *
 */
public class TileStargateBaseRenderer extends LCTileRenderer {

	/** The texture resource map */
	public final ResourceMap resources = new ResourceMap();

	public final ResourceLocation fxHorizon = ResourceAccess.getNamedResource(ResourceAccess
			.formatResourceName("textures/fx/eventhorizon_${TEX_QUALITY}.png"));
	public final ResourceLocation fxIrisMech = ResourceAccess.getNamedResource(ResourceAccess
			.formatResourceName("textures/fx/iris_${TEX_QUALITY}.png"));
	public final ResourceLocation fxIrisEnergy = ResourceAccess.getNamedResource(ResourceAccess
			.formatResourceName("textures/fx/energy_iris_${TEX_QUALITY}.png"));

	public final static int ehGridRadialSize = 100;
	public final static int irBladeSize = 20;
	public final static int ehGridPolarSize = ModelStargate.numRingSegments;
	public final static double ehBandWidth = ModelStargate.ringInnerRadius / ehGridRadialSize;

	/** The Stargate model */
	public final ModelStargate model;

	/** Default constructor */
	public TileStargateBaseRenderer() {
		for (StargateType type : StargateType.values()) {
			ResourceLocation fr, gl, glo;
			String suffix = (type.getSuffix() != null && type.getSuffix().length() > 0) ? "_" + type.getSuffix()
					+ "_${TEX_QUALITY}.png" : "_${TEX_QUALITY}.png";
			ResourceMap map = new ResourceMap();
			fr = ResourceAccess.getNamedResource(ResourceAccess.formatResourceName("textures/tileentity/stargate"
					+ suffix));
			gl = ResourceAccess.getNamedResource(ResourceAccess
					.formatResourceName("textures/tileentity/stargate_glyphs" + suffix));
			map.add("frame", fr).add("glyphs", gl);
			
			if (! type.equals(StargateType.STANDARD) & ! type.equals(StargateType.NOX)) {
			String suffixGlow = (type.getSuffix() != null && type.getSuffix().length() > 0) ? "_" + type.getSuffix()
				+ "_glow_${TEX_QUALITY}.png" : "_${TEX_QUALITY}.png";
			glo = ResourceAccess.getNamedResource(ResourceAccess
					.formatResourceName("textures/tileentity/stargate_glyphs" + suffixGlow));
				map.add("glow", glo);
			}				
			
				
			
			resources.add(type.getName(), map);
		}

		model = new ModelStargate();
		model.init();
	}

	@Override
	public void configure(ComponentConfig c) {
		// TODO Auto-generated method stub

	}

	@Override
	public LCTileRenderer getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean renderTileEntityAt(LCTile tile, LCTileRenderPipeline renderer, double x, double y, double z,
			float partialTickTime) {
		if (tile instanceof TileStargateBase) {
			TileStargateBase base = (TileStargateBase) tile;
			if (base.getState() == MultiblockState.FORMED) {
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_NORMALIZE);
				GL11.glTranslated(x + 0.5d, y + 3.5d, z + 0.5d);
				StateMap state = base.renderInfoTile().tileRenderState();
				Animation animation = (Animation) base.renderInfoTile().tileAnimation();
				if (animation != null) {
					Double frame = base.renderInfoTile().tileAnimationProgress() + (double) partialTickTime;
					if (!animation.finished(frame))
						animation.sampleProperties(state, frame);
				}
				model.render(this, renderer, base, state);
				//GL11.glPushMatrix();
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_NORMALIZE);
				//GL11.glTranslated(x + 0.5d, y + 3.5d, z + 0.5d);
				
				if (state.get("iris", false)) {
					if (state.get("iris-type", "").equals("mechanical")) {
						renderMechIrisImmediate(base, renderer, state, x, y, z, partialTickTime);
					
					} else if (state.get("iris-type", "energy").equals("energy")) {
						renderEnergyIrisImmediate(base, renderer, state, x, y, z, partialTickTime);
					
					} else {
						renderGhostIrisImmediate(base, renderer, state, x, y, z, partialTickTime);
					}
				}
				GL11.glPopMatrix();

				
				//GL11.glPopMatrix();
				
				if (state.get("event-horizon", false))
					renderGfxHorizonImmediate(base, renderer, state, x, y, z);
				if (state.get("iris", false) & state.get("iris-progress", 0.0d) != 0.0) state.set("vortex-progress", 0.0d);
				if (state.get("vortex-progress", 0.0d) > 0.0d)
					renderGfxVortex(base, renderer, state, x, y, z);
				//else 
				//	 LCLog.debug("lieven121 state : " + state.get("vortex-progress", 0.0d) + " " + state.get("event-vortex", false));
				GL11.glPopMatrix();
				
				
			}
		}
		return true;
	}
 
	private void renderMechIrisImmediate(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z, float t) {
		double progress = 1.0d - state.get("iris-progress", 0.0d);
		pipeline.bind(fxIrisMech);
		pipeline.bind(ResourceAccess.getNamedResource(ResourceAccess
				.formatResourceName("textures/fx/iris_test_${TEX_QUALITY}.png")));
	/*	 for (int i = 0; i < 8; i+=2) { // */ for (int i = 0; i < irBladeSize; i++) {
			
			GL11.glPushMatrix();
			
			GL11.glRotated(360.0 / irBladeSize * i , 0.0, 0.0, 1.0);
			double tiltAngle = progress * 60;
			double radius = ModelStargate.ringMidRadius;
			double longW = ModelStargate.ringMidRadius, shortW = ModelStargate.ringInnerRadius;
			double heightZ = 0.2, heightQ = 1.00;
			double length = heightQ - (heightQ - heightZ) * progress * progress;
			double u0 = shortW / longW, v0 = length / heightQ;
			double v1 = heightZ / heightQ;
			double depth0 = 0.05, depth1 = 0.01;
			
			

			GL11.glTranslated(radius-0.1, 0, 0);
			GL11.glRotated(-tiltAngle, 0, 0, 1);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glTexCoord2d(1,1);
			GL11.glVertex3d(-longW-1*state.get("iris-progress", 0.0d), 0, depth0+0.2*state.get("iris-progress", 0.0d));
			GL11.glTexCoord2d(1, 0);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex3d(0, 0, depth0 + depth1);
			//GL11.glVertex3d(0, heightZ, depth0 + depth1);
			GL11.glTexCoord2d(0,1);
			GL11.glVertex3d(-longW + shortW, length, depth0);
			//GL11.glTexCoord2d(1, 0);
			//GL11.glVertex3d(-longW, length, depth0);
			GL11.glEnd();

				GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex3d(-longW-1*state.get("iris-progress", 0.0d), 0, depth0+0.2*state.get("iris-progress", 0.0d));
			GL11.glTexCoord2d(0, 1);
			GL11.glVertex3d(-longW + shortW, length, depth0+ depth1);
			GL11.glTexCoord2d(1, 1);
			GL11.glVertex3d(0, 0, depth0 + depth1);
			GL11.glEnd();
			GL11.glPopMatrix();
			
		}

	}
	
	//TODO propper  animation
/*	private void renderMechIrisImmediate(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z, float t) {
		double progress = 1.0d - state.get("iris-progress", 0.0d);
		pipeline.bind(fxIrisMech);
		pipeline.bind(ResourceAccess.getNamedResource(ResourceAccess
				.formatResourceName("textures/fx/iris_test_${TEX_QUALITY}.png")));
		/*for (int i = 0; i < 1; i++) {*//* for (int i = 0; i < irBladeSize; i++) {
			
			GL11.glPushMatrix();
			
			GL11.glRotated(360.0 / irBladeSize * i , 0.0, 0.0, 1.0);
			double tiltAngle = progress * 60;
			double radius = ModelStargate.ringMidRadius;
			double longW = ModelStargate.ringMidRadius, shortW = ModelStargate.ringInnerRadius;
			double heightZ = 0.2, heightQ = 1.00;
			double length = heightQ - (heightQ - heightZ) * progress * progress;
			double u0 = shortW / longW, v0 = length / heightQ;
			double v1 = heightZ / heightQ;
			double depth0 = 0.05, depth1 = 0.01;
			
			

			GL11.glTranslated(radius-0.1, 0, 0);
			GL11.glRotated(-tiltAngle, 0, 0, 1);
			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glTexCoord2d(0.2, 0.2);
			GL11.glVertex3d(-longW, 0, depth0);
			GL11.glTexCoord2d(0.8, 0.2);
			GL11.glVertex3d(0, 0, depth0 + depth1);
			GL11.glTexCoord2d(0.8, v1);
			GL11.glVertex3d(0, heightZ, depth0 + depth1);
			GL11.glTexCoord2d(u0, v0);
			GL11.glVertex3d(-longW + shortW, length, depth0);
			GL11.glTexCoord2d(0, v0);
			GL11.glVertex3d(-longW, length, depth0);
			GL11.glEnd();

			GL11.glBegin(GL11.GL_TRIANGLE_FAN);
			GL11.glTexCoord2d(0, 0);
			GL11.glVertex3d(-longW, 0, depth0);
			GL11.glTexCoord2d(0, v0);
			GL11.glVertex3d(-longW, length, depth0);
			GL11.glTexCoord2d(u0, v0);
			GL11.glVertex3d(-longW + shortW, length, depth0);
			GL11.glTexCoord2d(1, v1);
			GL11.glVertex3d(0, heightZ, depth0 - depth1);
			GL11.glTexCoord2d(1, 0);
			GL11.glVertex3d(0, 0, depth0 - depth1);
			GL11.glEnd();
			GL11.glPopMatrix();
			
		}

	}*/
	
	
	

	

	
	//TODO clean up
	
	private void renderEnergyIrisImmediate(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z, float t) { //TODO maybe replace with particle to make see trough
		double progress = 1.0d - state.get("iris-progress", 0.0d);

		Minecraft.getMinecraft().getTextureManager().bindTexture(fxIrisEnergy);
		Tessellator test = Tessellator.instance;
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glDepthMask(false);
		double grid[][] = tile.getGfxGrid()[0];
		for (double i = ehGridRadialSize-ehGridRadialSize*(1-progress); i < ehGridRadialSize; i++) {
			test.startDrawing(GL11.GL_QUAD_STRIP);
			GL11.glBegin(5);
			if (state.get("event-horizon", false)) 
				test.setColorOpaque(255, 255, 255);
			else
				test.setColorOpaque(100, 100, 100);
			
			test.setBrightness(240);
			//test.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1F);
			for (int j = 0; j <= ehGridPolarSize; j++) {
				renderEnergyIris(state,test, grid, i, j);
				renderEnergyIris(state,test, grid, i + 1, j);
			}
			GL11.glEnd();
			test.draw();
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
	}
	
	private void renderEnergyIris(StateMap state, Tessellator tessel, double[][] grid, double i, int j) {
		double r = i/ehGridRadialSize*10 * 2.75d / 10.0d;
		double x = r * ModelStargate.cos[j];
		double y = r * ModelStargate.sin[j];

		double u = (x + 3) / 6;
		double v = (y + 3) / 6;
		tessel.setTextureUV(u, v);
		//tessel.addVertex(x, y, 0.09);
		//if (state.get("event-horizon", false)) {
			tessel.addVertex(x,  y, 0.08);
	//	} else {
		//	tessel.addVertex(x, y, 0.08);
		//}
	}

	private void renderGhostIrisImmediate(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z, float t) {

	}
		
	private void renderGfxHorizonImmediate(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z) {
		pipeline.bind(fxHorizon);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glNormal3d(0, 0, 1);
		double grid[][] = tile.getGfxGrid()[0];
		for (int i = 1; i < ehGridRadialSize/ehGridRadialSize*10; i++) {
			GL11.glBegin(GL11.GL_QUAD_STRIP);
			for (int j = 0; j <= ehGridPolarSize; j++) {
				eventHVertex(state, grid, i, j);
				eventHVertex(state, grid, i + 1, j);
			}
			GL11.glEnd();
		}

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glTexCoord2d(0.5, 0.5);
		GL11.glVertex3d(0, 0, grid[1][0]);
		for (int j = 0; j <= ehGridPolarSize; j++)
			eventHVertex(state, grid, 1, j);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_CULL_FACE);

	}

	private void eventHVertex(StateMap state, double[][] grid, int i, int j) {
		double r = i * 2.75d / 10.0d;
		double x = r * ModelStargate.cos[j];
		double y = r * ModelStargate.sin[j];

		double u = (x + 3) / 6;
		double v = (y + 3) / 6;
		GL11.glTexCoord2d(u, v);
		if (state.get("iris", false)) {
			GL11.glVertex3d(x, y, Math.min(grid[j + 1][i], 0.035));
		} else
			GL11.glVertex3d(x, y, grid[j + 1][i]);
	}
	
	
	
	
	
	
	//TODO make better animation
	
	private void renderGfxVortex(TileStargateBase tile, LCTileRenderPipeline pipeline, StateMap state,
			double x, double y, double z) {
		double progress =  state.get("vortex-progress", 0.0d);
		//progress = 1;
		
		if (progress > 1.0d) progress = 2.1-progress;
		

		//progress = 0.3;
		
		pipeline.bind(fxHorizon);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glNormal3d(0, 0, 1);
		double grid[][] = tile.getGfxGrid()[0];
		for (double i = 0; i < ehGridRadialSize/ehGridRadialSize*4; i++) {
			GL11.glBegin(GL11.GL_QUAD_STRIP);
			for (int j = 0; j <= ehGridPolarSize; j++) {
				eventVortex(state, grid, i*progress, j);
				eventVortex(state, grid, (i+1)*progress, j);
			}
			GL11.glEnd();
		}

		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		//GL11.glTexCoord2d(0.5, 0.5);
		//GL11.glVertex3d(0, 0, grid[1][0]);
		GL11.glNormal3d(0, 0, 1);
		for (int j = 0; j <= ehGridPolarSize; j++)
			eventVortex(state, grid,  (4*(progress)), j);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_CULL_FACE);
		if (state.get("vortex-progress", 0.0d) >= 2.0) {
			//state.set("event-vortex", false);
			 state.set("vortex-progress", 0.0d);
		}

	}
	

	private void eventVortex(StateMap state, double[][] grid, double id, int j) {
		//double progress = state.get("vortex-progress", 0.0d);
		//if (progress > 1.0d) progress = 2.1-progress; 
		//progress = 1;
		int i = (int) id;
		//progress = 0;
		double r = Math.pow(6-id, 2)/6 * 4.0 / 10.0d;
		double x = r * ModelStargate.cos[j];
		double y = r * ModelStargate.sin[j];
		
		//if ( i > 2 ) {
		double u = (x + 3) / 6;
		double v = (y + 3) / 6;
		//Tessellator tessel = Tessellator.instance;
		GL11.glTexCoord2d(u, v);
		GL11.glVertex3d(x+Math.min(grid[j + 1][i], 0.04),  y+Math.min(grid[j + 1][i], 0.04), id);


		}

	/*			tessel.startDrawing(5);
			for (double i=0; i <= 2*Math.PI+0.14; i+= Math.PI/16) {
				tessel.setColorRGBA_F(1f, 1f, 1f, 1.0f);
				tessel.addVertexWithUV(Math.cos(i)*2, Math.sin(i)*2, 0, 0.2,0.2 );
				tessel.addVertexWithUV(Math.cos(i)*2, Math.sin(i)*2, 3*progress, 0.8, 0.8);
			}
			tessel.draw();*/
	
	
	
	
	
	
}
