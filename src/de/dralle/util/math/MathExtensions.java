package de.dralle.util.math;

public class MathExtensions {
	public static double logToBase(double x, double base) {
		return Math.log(x)/Math.log(base);
	}
	public static double log2(double x) {
		return logToBase(x, 2);
	}
	public static <T extends Number> T max(T[] array){
		T max=null;
		for (int i = 0; i < array.length; i++) {
			if(max==null) {
				max=array[i];
			}
			if(array[i].doubleValue()>max.doubleValue()) {
				max=array[i];
			}
		}
		return max;
	}
	public static int max(int[] array){
		return max(array);
	}
	public static <T extends Number> T min(T[] array){
		T min=null;
		for (int i = 0; i < array.length; i++) {
			if(min==null) {
				min=array[i];
			}
			if(array[i].doubleValue()<min.doubleValue()) {
				min=array[i];
			}
		}
		return min;
	}
	public static int min(int[] array){
		return min(array);
	}
}
