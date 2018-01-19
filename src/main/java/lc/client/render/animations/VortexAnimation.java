package lc.client.render.animations;

import lc.client.animation.Animation;
import lc.common.base.LCTile;

public class VortexAnimation extends Animation {

	public VortexAnimation(Double duration,double start,  double end) {
		super(duration, false, null, null);

		addProperty("vortex-progress", start, end, InterpolationMode.SMOOTHSTEP);
		//addProperty("woosh-progress", end, start-0.1, InterpolationMode.SMOOTHSTEP);
	}

}
