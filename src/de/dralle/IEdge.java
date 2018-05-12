package de.dralle;

public interface IEdge {
	Value<?> getValue();
	Value<?> setValue(Value<?> value);
	Node getSubTree();
	Node setSubTree(Node subTree);
	int calculateSubtreeWidth();
	int calculateSubtreeDepth();
	int calculateCharacterWidth();
	
}
