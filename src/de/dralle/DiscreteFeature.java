package de.dralle;

public class DiscreteFeature<T> extends AbstractFeature<T> {
	
	@Override
	public FeatureType getFeatureType() {
		return FeatureType.Discrete;
	}

}
