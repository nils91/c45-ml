package de.dralle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.dralle.util.math.MathExtensions;

public class DataSet {
	private AbstractFeature<?>[] features;

	public void setFeature(AbstractFeature<?> f, int index) {
		features[index] = f;
	}

	public AbstractFeature<?> getFeature(int index) {
		return features[index];
	}
	public AbstractFeature<?> getResultFeature(){
		return features[features.length-1];
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
		features = new AbstractFeature[newSize];
	}

	public DataSet removeFeature(int index) {
		DataSet newDataSet = new DataSet();
		newDataSet.setSize(getSize() - 1);
		// remove from features
		for (int i = 0; i < features.length - 1; i++) {
			if (i < index) {
				newDataSet.setFeature(features[i], i);
			}
			if (i >= index) {
				newDataSet.setFeature(features[i + 1], i);
			}
		}
		// remove from data
		for (int i = 0; i < data.size(); i++) {
			FeatureVector fVector = data.get(i);
			newDataSet.addFeatureVector(fVector.removeFeature(index));
		}
		return newDataSet;
	}

	public DataSet[] splitDiscrete(int featureIndex) {
		AbstractFeature<?> featureToSplitAt = features[featureIndex];
		Set<Value<?>> distinctFeatureValues = featureToSplitAt.getDistinctValues();
		DataSet[] newDataSets = new DataSet[featureToSplitAt.getDistinctValueCount()];
		for (int i = 0; i < newDataSets.length; i++) {
			DataSet newDataSet = new DataSet();
			newDataSet.setSize(getSize());
			// features
			for (int j = 0; j < features.length; j++) {
				newDataSet.setFeature(features[j], j);
			}
			newDataSets[i] = newDataSet;
		}
		// split data in groups for distinct values
		int curDataSetIndex = 0;
		for (Value<?> value : distinctFeatureValues) {
			DataSet curSet = newDataSets[curDataSetIndex];
			curDataSetIndex++;
			for (int i = 0; i < data.size(); i++) {
				FeatureVector vec = data.get(i);
				if (vec.getValue(featureIndex).getValue().equals(value.getValue())) {
					curSet.addFeatureVector(vec);
				}
			}
		}
		return newDataSets;
	}

	public DataSet[] splitDiscrete(int featureIndex, Object value) {
		DataSet[] newDataSets = new DataSet[2];
		for (int i = 0; i < newDataSets.length; i++) {
			DataSet newDataSet = new DataSet();
			newDataSet.setSize(getSize());
			// features
			for (int j = 0; j < features.length; j++) {
				newDataSet.setFeature(features[j], j);
			}
			newDataSets[i] = newDataSet;
		}
		// split data in groups for distinct values

		for (int j = 0; j < newDataSets.length; j++) {
			DataSet curSet = newDataSets[j];
			for (int i = 0; i < data.size(); i++) {
				FeatureVector vec = data.get(i);
				if (j == 0 && vec.getValue(featureIndex).getValue().equals(value)) {
					curSet.addFeatureVector(vec);
				}
				if (j == 1 && !vec.getValue(featureIndex).getValue().equals(value)) {
					curSet.addFeatureVector(vec);
				}
			}
		}
		return newDataSets;
	}

	/**
	 * 
	 * @param featureIndex Feature to split at
	 * @param value        Value to split at
	 * @return 2 DataSets, DataSet 0 contains all <= value, 1 all >
	 */
	public DataSet[] splitContinuous(int featureIndex, Number value) {
		AbstractFeature<?> featureToSplitAt = features[featureIndex];
		if (featureToSplitAt instanceof ContinuousFeature<?>) {
			DataSet[] newDataSets = new DataSet[2];
			for (int i = 0; i < newDataSets.length; i++) {
				DataSet newDataSet = new DataSet();
				newDataSet.setSize(getSize());
				// features
				for (int j = 0; j < features.length; j++) {
					newDataSet.setFeature(features[j], j);
				}
				newDataSets[i] = newDataSet;
			}
			// split data in groups for distinct values

			for (int j = 0; j < newDataSets.length; j++) {
				DataSet curSet = newDataSets[j];
				for (int i = 0; i < data.size(); i++) {
					FeatureVector vec = data.get(i);
					Object val = vec.getValue(featureIndex).getValue();
					if (val instanceof Number) {
						Number numVal = (Number) val;
						if (j == 0 && numVal.doubleValue() <= value.doubleValue()) {
							curSet.addFeatureVector(vec);
						}
						if (j == 1 && numVal.doubleValue() > value.doubleValue()) {
							curSet.addFeatureVector(vec);
						}
					}

				}
			}
			return newDataSets;
		}
		return null;
	}

	public int getSize() {
		return features.length;
	}
	public double getEntropy() {
		double entropy = 0;
		int cntTotal = data.size();
		//last feature is result
		AbstractFeature<?> resultFeature = features[features.length-1];
		Set<Value<?>> distinctValues = resultFeature.getDistinctValues();
		for (Value<?> value : distinctValues) {
			int cnt=resultFeature.countValue(value);
			double frac=cnt/(double)cntTotal;
			entropy+=(-frac*MathExtensions.log2(frac));
		}
		return entropy;
	}

	public int getFeatureIndex(AbstractFeature<?> abstractFeature) {
		for (int i = 0; i < features.length; i++) {
			if(features[i].equals(abstractFeature)) {
				return i;
			}
		}
		return -1;
	}
	public AbstractFeature<?> getMostDecidingFeature() {
		AbstractFeature<?> objectivlyBestFeature = null;
		double featureInformationGain = 0;
		for (int i = 0; i < features.length; i++) {
			double gain = features[i].getInformationGain(this);
			if (gain > featureInformationGain) {
				objectivlyBestFeature = features[i];
				featureInformationGain = gain;
			}
		}
		return objectivlyBestFeature;
	}
	public int getMostDecidingFeatureIndex() {
		return getFeatureIndex(getMostDecidingFeature());
	}
	public IValue<?> getMostCommonResult(){
		return getResultFeature().getMostCommonValue();
	}
//	public DataSet removeFeature(AbstractFeature<?> feature) {
//	}
//	public DataSet removeFeatureByName(String name) {
//	}
}
