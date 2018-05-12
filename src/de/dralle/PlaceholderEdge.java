package de.dralle;

public class PlaceholderEdge implements IEdge {
	private int parentWidth;
	public PlaceholderEdge(int parentWidth) {super();
	this.parentWidth=parentWidth;
	}

	@Override
	public Value<?> getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value<?> setValue(Value<?> value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getSubTree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node setSubTree(Node subTree) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateSubtreeWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateSubtreeDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateCharacterWidth() {
		// TODO Auto-generated method stub
		return parentWidth;
	}

	@Override
	public int calculateCharacterWidth(int parentWidth) {
		// TODO Auto-generated method stub
		return parentWidth;
	}

}
