package de.dralle;

import java.util.Set;

public class DiscreteFeature<T> extends AbstractFeature<T> {
	@Override
	public FeatureType getFeatureType() {
		return FeatureType.Discrete;
	}

}
