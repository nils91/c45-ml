package de.dralle;

import java.io.PrintStream;

public class TreePrinter {
	private PrintStream printTo;
	public TreePrinter(PrintStream out) {
		printTo=out;
	}

	public void print(Node node) {
		printTo.println(write(node));
		printTo.flush();
		
	}
	private String write(Node node) {
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
		line+=System.lineSeparator();
		line+=writeEdges(node.getBranches());
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
		for (IEdge iEdge : iEdges) {
			Node subTree = iEdge.getSubTree();
			if(subTree!=null) {
				line+=write(subTree);
			}
		}
		line+=System.lineSeparator();
		return line;
	}

}
