package de.dralle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFeature<T> implements IFeature<T> {
	private List<IValue<T>> values;
	public List<IValue<T>> getValues() {
		return values;
	}
	public void addValue(IValue<T> value) {
		values.add(value);
	}
	private String name;
	public AbstractFeature() {
		super();
	}
	public AbstractFeature(String name) {
		super();
		values=new ArrayList<IValue<T>>();
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

	

}
