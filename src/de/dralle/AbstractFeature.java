package de.dralle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFeature<T> implements IFeature<T> {
	private List<Value<?>> values;
	public List<Value<?>> getValues() {
		return values;
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

	

}
