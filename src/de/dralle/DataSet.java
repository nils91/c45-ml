package de.dralle;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	private AbstractFeature<?>[] features;
	public void setFeature(AbstractFeature<?> f, int index) {
		features[index]=f;
	}
	public AbstractFeature<?> getFeature(int index) {
		return features[index];
	}
	private List<FeatureVector> data;
	public AbstractFeature<?>[] getFeatures() {
		return features;
	}
	public List<FeatureVector> getData() {
		return data;
	}
	public int addFeatureVector(FeatureVector e) {
		data.add(e);
		return data.size();
	}
	public DataSet() {
		super();
		this.data = new ArrayList<>();
	}
	public void setSize(int newSize) {
		features=new AbstractFeature[newSize];
	}
}
