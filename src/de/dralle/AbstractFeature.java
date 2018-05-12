package de.dralle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.dralle.util.math.MathExtensions;

public abstract class AbstractFeature<T> implements IFeature<T> {
	private List<Value<?>> values;
	public List<Value<?>> getValues() {
		return values;
	}
	public Set<Value<?>> getDistinctValues() {
		Set<Value<?>> hashMap = new HashSet<>();
		for (Value<?> v : values) {
			hashMap.add(v);
		}
		return hashMap;
	}
	public int getDistinctValueCount() {		
		return getDistinctValues().size();
	}
	public void addValue(Value<?> value) {
		values.add(value);
	}
	private String name;
	public AbstractFeature() {
		super();
		values=new ArrayList<Value<?>>();
	}
	public AbstractFeature(String name) {
		super();
		values=new ArrayList<Value<?>>();
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public int countValue(Value<?> value) {
		int cnt=0;
		for (Value<?> localValue : values) {
			if(localValue.equals(value)) {
				cnt++;
			}
		}
		return cnt;
	}
	public double getInformationGain(DataSet d) {
		double fullDataSetEntropy = d.getEntropy();
		double gain = fullDataSetEntropy;
		Set<Value<?>> distinctValues = getDistinctValues();
		for (Value<?> value : distinctValues) {
			int featureIndex=d.getFeatureIndex(this);
			DataSet subset=d.splitDiscrete(featureIndex, value)[0];
			gain-=(subset.getFeature(featureIndex).countValue(value)/(double)d.getData().size())*subset.getEntropy();
		}
		return gain;
	}
	public double getIntrinsicValue() {
		double intrinsicValue = 0;
		Set<Value<?>> distinctValues = getDistinctValues();
		for (Value<?> value : distinctValues) {
			intrinsicValue-=(countValue(value)/(double)values.size())*MathExtensions.log2(countValue(value)/(double)values.size());				
		}
		return intrinsicValue;
	}
	public double getInformationGainRatio(DataSet d) {
		double informationGain=getInformationGain(d);
		if(informationGain==0) {
			return 0;
		}
		return getIntrinsicValue()/getInformationGain(d);
	}

	public Value<?> getMostCommonValue(){
		Value<?> val=null;
		int valueCnt=0;
		Set<Value<?>> distinctValues = getDistinctValues();
		for (Value<?> value : distinctValues) {
			if(countValue(value)>valueCnt) {
				valueCnt=countValue(value);
				val=value;
			}
		}
		return val;
	}

	public abstract AbstractFeature<?> copy();
	

}
