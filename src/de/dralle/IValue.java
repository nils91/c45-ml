package de.dralle;

public interface IValue<T> {
	T getValue();
	IFeature<T> getFeature();
}
