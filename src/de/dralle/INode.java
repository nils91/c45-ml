package de.dralle;

public interface INode {
	boolean isLeafNode();
	AbstractFeature<?> getDecidingFeature();
	AbstractFeature<?> setDecidingFeature(AbstractFeature<?> f);
	@Deprecated
	int addBranch(Value<?> value);
	int addBranch(IEdge branch);
	IEdge[] getBranches();
	int calculateWidth();
	int calculateDepth();
}
