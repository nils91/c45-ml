package de.dralle;

public class ValueRange<T extends Number> {
	private T min;
	private T max;
	public ValueRange(T min, T max) {
		super();
		this.min = min;
		this.max = max;
	}
	public T getMin() {
		return min;
	}
	public T getMax() {
		return max;
	}
	public T getAverage() {
		return (T)new Double((min.doubleValue()+max.doubleValue())/2);
	}
}
