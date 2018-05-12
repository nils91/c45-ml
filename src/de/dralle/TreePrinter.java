package de.dralle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TreePrinter {
	private PrintStream printTo;
	public TreePrinter(PrintStream out) {
		printTo=out;
	}

	public void print(Node node) {
		printTo.println(writeAll(node));
		printTo.flush();
		
	}
	private String writeAll(Node node) {
		String text="";
		text+=writeSingleNode(node);
		text+=System.lineSeparator();
		text+=writeEdges(node.getBranches());
		List<Node> subTrees = node.getSubTrees();
		while(subTrees.size()>0) {
			text+=writeNodes(subTrees);
			text+=System.lineSeparator();
			text+=writeEdges(getAllSubtreeEdgesUnified(subTrees));
			subTrees=getAllSubtreeSubtreesUnified(subTrees);
		}
		return text;
	}
	private List<Node> getAllSubtreeSubtreesUnified(List<Node> subTrees) {
		List<Node> allSubtrees=new ArrayList<>();
		for (Node t : subTrees) {
			allSubtrees.addAll(t.getSubTrees());
		}
		return allSubtrees;
	}

	private IEdge[] getAllSubtreeEdgesUnified(List<Node> subTrees) {
		List<IEdge> allEdges=new ArrayList<>();
		for (Node t : subTrees) {
			for (int i = 0; i < t.getBranches().length; i++) {
					allEdges.add(t.getBranches()[i]);
			}
		}
		return allEdges.toArray(new IEdge[allEdges.size()]);
	}

	private String writeSingleNode(Node node) {
		String line="";
		int treeWidthInCharacters=node.calculateCharacterWidth();
		String text = "";
		if(node.getDecidingFeature()!=null) {
			text=node.getDecidingFeature().getName();
		}
		for (int i = 0; i < treeWidthInCharacters/2-text.length()/2; i++) {
			line+=" ";
		}
		line+=text;
		for (int i = 0; i < treeWidthInCharacters/2-text.length()/2; i++) {
			line+=" ";
		}
		return line;
	}

	private String writeNodes(List<Node> nodes) {
		String line="";
		for (Node node : nodes) {
			line+=writeSingleNode(node);
		}
		return line;
	}
	private String writeEdges(IEdge[] iEdges) {
		String line="";
		for (IEdge iEdge : iEdges) {			
			int individualEdgeTreeWidth=iEdge.calculateCharacterWidth();
			for (int i = 0; i < individualEdgeTreeWidth/2; i++) {
				line+=" ";
			}
			line+="|";
			for (int i = 0; i < individualEdgeTreeWidth/2; i++) {
				line+=" ";
			}
		}
		line+=System.lineSeparator();
		for (IEdge iEdge : iEdges) {
			String text = "";
			if(iEdge.getValue()!=null) {
				text=iEdge.getValue().toString();
			}
			int individualEdgeTreeWidth=iEdge.calculateCharacterWidth();			
			for (int i = 0; i < individualEdgeTreeWidth/2-text.length()/2; i++) {
				line+=" ";
			}
			line+=text;
			for (int i = 0; i < individualEdgeTreeWidth/2-text.length()/2; i++) {
				line+=" ";
			}
		}
		line+=System.lineSeparator();
		for (IEdge iEdge : iEdges) {			
			int individualEdgeTreeWidth=iEdge.calculateCharacterWidth();
			for (int i = 0; i < individualEdgeTreeWidth/2; i++) {
				line+=" ";
			}
			line+="|";
			for (int i = 0; i < individualEdgeTreeWidth/2; i++) {
				line+=" ";
			}
		}
		line+=System.lineSeparator();
		return line;
	}

}
