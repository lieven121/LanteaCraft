package lc.dimensions.chulak;

import lc.api.defs.IDefinitionReference;
import lc.api.defs.IDimensionDefinition;
import lc.common.impl.registry.DefinitionReference;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;

/**
 * Abydos dimension implementation
 *
 * @author AfterLifeLochie
 *
 */
public class ChulakDimension implements IDimensionDefinition {

	/**
	 * Default constructor
	 *
	 * @param providerId
	 *            The provider ID to use
	 * @param dimensionId
	 *            The dimension ID to use
	 */
	public ChulakDimension(final int providerId, final int dimensionId) {
		DimensionManager.registerProviderType(providerId, getWorldProviderClass(), false);
		DimensionManager.registerDimension(dimensionId, providerId);
	}

	@Override
	public Class<? extends WorldProvider> getWorldProviderClass() {
		return ChulakWorldProvider.class;
	}

	@Override
	public IDefinitionReference ref() {
		return new DefinitionReference(this);
	}

}
