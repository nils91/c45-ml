package de.dralle;

import java.util.ArrayList;
import java.util.List;

import de.dralle.util.math.MathExtensions;

public class Node implements INode{
	public Node() {
		super();
		branches=new ArrayList<>();
	}
	protected boolean leafNode;
	private Value<?> result;
	@Override
	public boolean isLeafNode() {
		return leafNode;
	}
	private AbstractFeature<?> decidingFeature;
	private List<IEdge> branches;
	@Override
	public AbstractFeature<?> getDecidingFeature() {
		return decidingFeature;
	}

	@Override
	public AbstractFeature<?> setDecidingFeature(AbstractFeature<?> f) {
		AbstractFeature<?> prevDecidingFeature = decidingFeature;
		decidingFeature=f;
		return prevDecidingFeature;
	}

	@Override
	@Deprecated
	public int addBranch(Value<?> value) {
		branches.add(new Edge(value));
		return branches.size();
	}

	@Override
	public IEdge[] getBranches() {
		return branches.toArray(new IEdge[branches.size()]);
	}

	@Override
	public int addBranch(IEdge branch) {
		branches.add(branch);
		return branches.size();
	}

	@Override
	public int calculateWidth() {
		int total=1;
		for (IEdge iEdge : branches) {
			total+=iEdge.calculateSubtreeWidth();
		}
		return total;
	}

	@Override
	public int calculateDepth() {
		int total=1;
		int[] branchesDepth=new int[branches.size()];
		for (int i = 0; i < branches.size(); i++) {
			branchesDepth[i]=branches.get(i).calculateSubtreeDepth();
		}
		int max = MathExtensions.max(branchesDepth);
		total+=max;
		return total;
	}

	public int calculateCharacterWidth(int parentWidth) {
		if(getDecidingFeature()!=null) {
			String name=getDecidingFeature().getName();
			int len = name.length()+2; //add space left an right
			int branchWidth=0;
			for (IEdge iEdge : branches) {
				branchWidth+=iEdge.calculateCharacterWidth(parentWidth/branches.size());
			}
			int fullWidth = Math.max(len, branchWidth);
			return fullWidth;
		}else {
			String value=getValue().toString();
			int len = value.length()+2; //add space left an right
			return Math.max(parentWidth, len);
		}
	}
	public int calculateCharacterWidth() {
		if(getDecidingFeature()!=null) {
			String name=getDecidingFeature().getName();
			int len = name.length(); //add space left an right
			int branchWidth=0;
			for (IEdge iEdge : branches) {
				branchWidth+=iEdge.calculateCharacterWidth();
			}
			int fullWidth = Math.max(len, branchWidth);
			return fullWidth;
		}else if(isLeafNode()){
			String value=getValue().toString();
			int len = value.length(); //add space left an right
			return len;
		}
		return 0;
	}
/**
 * Gets this Nodes childnodes while ignoring edges
 * @return
 */
	public List<Node> getSubTrees() {
		List<Node> childNodes=new ArrayList<>();
		for (IEdge edge : branches) {
			childNodes.add(edge.getSubTree());			
		}
		return childNodes;
	}

@Override
public Value<?> getValue() {	
	return result;
}

@Override
public Value<?> setValue(Value<?> v) {
	Value<?> prevV = result;
	result=v;
	return prevV;
}
	
}
