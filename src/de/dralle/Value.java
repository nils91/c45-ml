package de.dralle;

public class Value<T> implements IValue<T> {
	private AbstractFeature<?> feature;
	
	public void setFeature(AbstractFeature<?> feature) {
		this.feature = feature;
	}

	public void setValue(T value) {
		this.value = value;
	}

	private T value;
	public Value() {
		super();
	}

	public Value(AbstractFeature<T> feature, T value) {
		super();
		this.feature = feature;
		this.value = value;
	}

	public Value(T value) {
		super();
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public AbstractFeature<?> getFeature() {
		return feature;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equal=false;
		if(obj instanceof Value<?>) {
			equal=value.equals(((Value<?>)obj).getValue());
		}
		return super.equals(obj)||value.equals(obj)||equal;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
