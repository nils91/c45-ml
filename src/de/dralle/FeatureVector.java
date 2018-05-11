package de.dralle;

public class FeatureVector {
	private AbstractFeature<?>[] features;
	private IValue<?>[] values;
	public void setSize(int newSize) {
		features=new AbstractFeature[newSize];
		values=new IValue[newSize];		
	}
	public void setFeature(AbstractFeature<?> f, int index) {
		features[index]=f;
	}
	public void setValue(IValue<?> value, int index) {
		values[index]=value;
	}
	public IValue<?> getValue(int index) {
		return values[index];
	}
	public FeatureVector removeFeature(int index) {
		FeatureVector newVector=new FeatureVector();
		newVector.setSize(getSize()-1);
		for (int i = 0; i < features.length; i++) {
			if(i<0) {
				newVector.setFeature(features[i], i);
				
				newVector.setValue(values[i], i);
			}
			if(i>=0) {
				newVector.setFeature(features[i+1], i);				

				newVector.setValue(values[i+1], i);
			}
		}
		return newVector;
	}
	private int getSize() {
		return features.length;
	}
}
