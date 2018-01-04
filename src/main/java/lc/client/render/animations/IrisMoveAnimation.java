package lc.client.render.animations;

import lc.client.animation.Animation;
import lc.common.base.LCTile;

public class IrisMoveAnimation extends Animation {

	public IrisMoveAnimation(Double duration, double result) {
		super(duration, true, null, null);

			//mixer().replayChannel("lock");
		addProperty("iris-progress", 0.0d, result, InterpolationMode.SMOOTHSTEP);

	}

}
