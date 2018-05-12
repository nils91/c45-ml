package de.dralle;

public class DiscrceteResultFeature<T> extends DiscreteFeature<T> implements IResultFeature<T> {

	public DiscrceteResultFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DiscrceteResultFeature(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractFeature<?> copy() {		
		return new DiscrceteResultFeature<>(getName());
	}

}
