package de.dralle;

public class Edge implements IEdge {

	private Value<?> value;
	private Node subTree;

	public Edge() {
		super();
	}
	public Edge(Value<?> value) {
		super();
		this.value=value;
	}

	@Override
	public Value<?> getValue() {
		return value;
	}

	@Override
	public Value<?> setValue(Value<?> value) {
		Value<?> prevValue = this.value;
		this.value=value;
		return prevValue;
	}

	@Override
	public Node getSubTree() {
		return subTree;
	}

	@Override
	public Node setSubTree(Node subTree) {
		Node prevTree = this.subTree;
		this.subTree=subTree;
		return prevTree;
	}
	@Override
	public int calculateSubtreeWidth() {
		if(subTree!=null) {
			return subTree.calculateWidth();
		}
		return 0;
	}
	@Override
	public int calculateSubtreeDepth() {
		if(subTree!=null) {
			return subTree.calculateDepth();
		}
		return 0;
	}
	@Override
	public int calculateCharacterWidth() {
		int len=0;
		if(value!=null) {
			len=value.toString().length()+2;//spaces left right
			int fullWidth=len;
			if(subTree!=null) {
				fullWidth=Math.max(len, subTree.calculateCharacterWidth());
			}
			len=fullWidth;
		}
		return len;
	}

}
