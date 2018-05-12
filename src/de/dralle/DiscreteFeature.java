package de.dralle;

import java.util.Set;

public class DiscreteFeature<T> extends AbstractFeature<T> {
	@Override
	public FeatureType getFeatureType() {
		return FeatureType.Discrete;
	}

	public DiscreteFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscreteFeature(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractFeature<?> copy() {
		return new DiscreteFeature<>(getName());
	}

}
