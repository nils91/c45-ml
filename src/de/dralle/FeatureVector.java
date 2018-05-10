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
}
