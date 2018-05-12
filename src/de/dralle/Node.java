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

	public int calculateCharacterWidth() {
		if(getDecidingFeature()!=null) {
			String name=getDecidingFeature().getName();
			int len = name.length()+2; //add space left an right
			int branchWidth=0;
			for (IEdge iEdge : branches) {
				branchWidth+=iEdge.calculateCharacterWidth();
			}
			int fullWidth = Math.max(len, branchWidth);
			return fullWidth;
		}
		return 2;
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
	
}
