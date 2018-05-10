package de.dralle;

public class ContinuousFeature<T extends Number> extends AbstractFeature<T> {

	

	@Override
	public FeatureType getFeatureType() {		
		return FeatureType.Continuous;
	}

}
