package de.dralle;

import java.util.List;
import java.util.Set;

public class ContinuousFeature<T extends Number> extends AbstractFeature<T> {
	
	
	public ValueRange<T> getValueRange(){
		T min=null;
		T max=null;		
		Set<Value<?>> distinstValues = getDistinctValues();
		for (Value<?> value : distinstValues) {
			if(min==null) {
				Object valueObj = value.getValue();
				if(valueObj instanceof Number) {
					Number valueNumber=(Number)valueObj;
					min=uncheckedCast(valueNumber);
				}
			}
			if(max==null) {
				Object valueObj = value.getValue();
				if(valueObj instanceof Number) {
					Number valueNumber=(Number)valueObj;
					max=uncheckedCast(valueNumber);
				}
			}
			Object valueObj = value.getValue();
			if(valueObj instanceof Number) {
				Number valueNumber=(Number)valueObj;
				if(valueNumber.doubleValue()>max.doubleValue()) {
					max=uncheckedCast(valueNumber);
				}
				if(valueNumber.doubleValue()<min.doubleValue()) {
					min=uncheckedCast(valueNumber);
				}
			}
		}
		return new ValueRange<T>(min, max);
	}
	private T uncheckedCast(Number valueNumber) {
		return (T)valueNumber;
	}
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
