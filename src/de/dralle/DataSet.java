package de.dralle;

import java.util.ArrayList;
import java.util.List;

public class DataSet {
	private AbstractFeature<?>[] features;
	public void setFeature(AbstractFeature<?> f, int index) {
		features[index]=f;
	}
	private List<FeatureVector> data;
	public DataSet() {
		super();
		this.data = new ArrayList<>();
	}
	public void setSize(int newSize) {
		features=new AbstractFeature[newSize];
	}
}
