package de.dralle;

import java.util.List;

public class ContinuousFeature<T extends Number> extends AbstractFeature<T> {
	
	

	public ContinuousFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContinuousFeature(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FeatureType getFeatureType() {		
		return FeatureType.Continuous;
	}

}
