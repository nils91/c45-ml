package de.dralle;

public interface IValue<T> {
	T getValue();
	AbstractFeature<?> getFeature();
}
