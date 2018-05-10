package de.dralle;

public abstract class AbstractFeature<T> implements IFeature<T> {
	private String name;
	public AbstractFeature() {
		super();
	}
	public AbstractFeature(String name) {
		super();
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
