package de.dralle;

public class ContinuousFeature<T extends Number> implements IFeature<T> {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FeatureType getFeatureType() {		
		return FeatureType.Continuous;
	}

}
