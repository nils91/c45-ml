package de.dralle;

import java.util.List;

public class ContinuousFeature<T extends Number> extends AbstractFeature<T> {
	
	

	@Override
	public FeatureType getFeatureType() {		
		return FeatureType.Continuous;
	}

}
