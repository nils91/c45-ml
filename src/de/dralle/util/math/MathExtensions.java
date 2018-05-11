package de.dralle.util.math;

public class MathExtensions {
	public static double logToBase(double x, double base) {
		return Math.log(x)/Math.log(base);
	}
	public static double log2(double x) {
		return logToBase(x, 2);
	}
}
