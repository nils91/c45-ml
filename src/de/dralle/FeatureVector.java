package de.dralle;

public class FeatureVector {
	private AbstractFeature<?>[] features;
	private Value<?>[] values;
	public void setSize(int newSize) {
		features=new AbstractFeature[newSize];
		values=new Value[newSize];		
	}
	public void setFeature(AbstractFeature<?> f, int index) {
		features[index]=f;
	}
	public void setValue(Value<?> value, int index) {
		values[index]=value;
	}
	public Value<?> getValue(int index) {
		return values[index];
	}
	public FeatureVector removeFeature(int index) {
		FeatureVector newVector=new FeatureVector();
		newVector.setSize(getSize()-1);
		for (int i = 0; i < features.length-1; i++) {
			if(i<index) {
				newVector.setFeature(features[i], i);
				
				newVector.setValue(values[i], i);
			}
			if(i>=index) {
				newVector.setFeature(features[i+1], i);				

				newVector.setValue(values[i+1], i);
			}
		}
		return newVector;
	}
	public int getSize() {
		return features.length;
	}
	@Deprecated
	public void copyFeaturesFromDataSet(DataSet newSet) {
		setSize(newSet.getSize());
		for (int i = 0; i < features.length; i++) {
			setFeature(newSet.getFeature(i), i);
		}
		
	}
	@Deprecated
	public void copyValues(FeatureVector vec,DataSet newSet) {
		setSize(vec.getSize());
		for (int i = 0; i < vec.getSize(); i++) {
			Value<?> value = vec.getValue(i);
			Value<?> newValue = new Value<>(value.getValue());
			newValue.setFeature(newSet.getFeature(i));
			setValue(newValue, i);
		}
		
	}
	public FeatureVector copyToDataSet(DataSet target) {
		FeatureVector newVector=new FeatureVector();
		newVector.setSize(getSize());
		for (int i = 0; i < values.length; i++) {
			Value<?> thisValue = values[i];
			Value<?> newValue=new Value(thisValue.getValue());
			AbstractFeature<?> targetFeature = target.getFeature(i);
			newValue.setFeature(targetFeature);
			targetFeature.addValue(newValue);
			newVector.setValue(newValue, i);
			newVector.setFeature(targetFeature, i);
		}
		return newVector;
	}
	public void updateFeatures(AbstractFeature<?>[] updatedFeatures) {
		for (int i = 0; i < values.length; i++) {
			values[i].setFeature(updatedFeatures[i]);
		}
		features=updatedFeatures;
		
	}
}
