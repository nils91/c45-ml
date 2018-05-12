package de.dralle;

public class PlaceholderNode extends Node {
private int parentWidth;
	public PlaceholderNode(int parentWidth) {super();
	this.parentWidth=parentWidth;
	}
	@Override
	public int calculateCharacterWidth(int parentWidth) {
		// TODO Auto-generated method stub
		return parentWidth;
	}
	@Override
	public int calculateCharacterWidth() {
		// TODO Auto-generated method stub
		return parentWidth;
	}

}
