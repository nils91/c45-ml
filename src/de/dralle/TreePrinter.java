package de.dralle;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TreePrinter {
	private PrintStream printTo;

	public TreePrinter(PrintStream out) {
		printTo = out;
	}

	public void print(Node node) {
		printTo.println(writeAll(node));
		printTo.flush();

	}

	private String writeAll(Node node) {
		int width=node.calculateCharacterWidth();
		String text = "";
		text += writeSingleNode(width,node);
		text += System.lineSeparator();
		text += writeEdges(node.getBranches());
		List<Node> subTrees = node.getSubTrees();
		while (subTrees.size() > 0) {
			text += writeNodes(node.calculateCharacterWidth(), subTrees);
			text += System.lineSeparator();
			text += writeEdges(getAllSubtreeEdgesUnified(width,subTrees));
			subTrees = getAllSubtreeSubtreesUnified(width,subTrees);
		}
		return text;
	}

	private String writeEdges(IEdge[] branches) {
		String line = "";		
		for (IEdge iEdge : branches) {
			Value<?> val = iEdge.getValue();
			int individualEdgeTreeWidth = iEdge.calculateCharacterWidth();
			for (int i = 0; i < individualEdgeTreeWidth / 2 - 1; i++) {
				line += " ";
			}
			if (val != null) {
				line += "|";
			} else {
				line += " ";
			}
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		for (IEdge iEdge : branches) {
			String text = "";
			int individualEdgeTreeWidth = iEdge.calculateCharacterWidth()-2;
			if (iEdge.getValue() != null) {
				text = iEdge.getValue().toString();
				text = "|" + text + "|";
			}else {
				for (int i = 0; i < individualEdgeTreeWidth; i++) {
					text+=" ";
				}
			}
			
			for (int i = 0; i < individualEdgeTreeWidth / 2 - text.length() / 2; i++) {
				line += " ";
			}
			line += text;
			for (int i = 0; i < individualEdgeTreeWidth / 2 - text.length() / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		for (IEdge iEdge : branches) {
			Value<?> val = iEdge.getValue();
			int individualEdgeTreeWidth = iEdge.calculateCharacterWidth();
			for (int i = 0; i < individualEdgeTreeWidth / 2-1; i++) {
				line += " ";
			}
			if (val != null) {
				line += "|";
			} else {
				line += " ";
			}
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		return line;
	}

	private List<Node> getAllSubtreeSubtreesUnified(int parentWidth,List<Node> subTrees) {
		
		boolean terminate=true;
		for (Node t : subTrees) {
			if(t.getBranches().length>0) {
				terminate=false;
			}
		}
		List<Node> allSubtrees = new ArrayList<>();
		if(!terminate) {
			for (Node t : subTrees) {
				IEdge[] edges = t.getBranches();
				if(edges.length==0) {
					allSubtrees.add(new PlaceholderNode(parentWidth/subTrees.size()));//Placeholder
				}
				allSubtrees.addAll(t.getSubTrees());
			}
		}		
		return allSubtrees;
	}

	private IEdge[] getAllSubtreeEdgesUnified(int parentWidth,List<Node> subTrees) {
		List<IEdge> allEdges = new ArrayList<>();
		for (Node t : subTrees) {
			if (t.getBranches().length == 0) {
				allEdges.add(new PlaceholderEdge(parentWidth/subTrees.size()));
			} else {
				for (int i = 0; i < t.getBranches().length; i++) {
					allEdges.add(t.getBranches()[i]);
				}
			}

		}
		return allEdges.toArray(new IEdge[allEdges.size()]);
	}

	private String writeSingleNode(int parentCharacterWidth,Node node) {
		String line = "";
		int treeWidthInCharacters = Math.max(node.calculateCharacterWidth(),parentCharacterWidth);
		String text = "";
		if (node.getDecidingFeature() != null) {
			text = node.getDecidingFeature().getName();
		}else if(node.isLeafNode()){
			text=node.getValue().toString();
		}
		if(!text.equals("")) {
			text="|"+text+"|";
		}
		
		for (int i = 0; i < treeWidthInCharacters / 2 - text.length() / 2; i++) {
			line += " ";
		}
		line += text;
		for (int i = 0; i < treeWidthInCharacters / 2 - text.length() / 2; i++) {
			line += " ";
		}
		return line;
	}

	private String writeNodes(int parentCharacterWidth, List<Node> nodes) {
		String line = "";
		String[] parts = new String[nodes.size()];
		for (int i = 0; i < parts.length; i++) {
			parts[i] = writeSingleNode(parentCharacterWidth/parts.length,nodes.get(i));
		}
		int widthForOnePart = parentCharacterWidth;
		if (nodes.size() > 0) {
			widthForOnePart = parentCharacterWidth / nodes.size();
		}
		for (int i = 0; i < parts.length; i++) {
			line += String.format("%1$" + widthForOnePart + "s", parts[i]);
		}
		return line;
	}

	private String writeEdges(int parentCharacterWidth, IEdge[] iEdges) {
		String line = "";
		int treeWidthPerBranch = parentCharacterWidth;
		if (iEdges.length > 0) {
			treeWidthPerBranch /= iEdges.length;
		}
		for (IEdge iEdge : iEdges) {
			Value<?> val = iEdge.getValue();
			int individualEdgeTreeWidth = Math.max(treeWidthPerBranch, iEdge.calculateCharacterWidth(parentCharacterWidth)/iEdges.length);
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
			if (val != null) {
				line += "|";
			} else {
				line += " ";
			}
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		for (IEdge iEdge : iEdges) {
			String text = "";
			if (iEdge.getValue() != null) {
				text = iEdge.getValue().toString();
			}
			int individualEdgeTreeWidth = Math.max(treeWidthPerBranch, iEdge.calculateCharacterWidth(parentCharacterWidth)/iEdges.length);
			for (int i = 0; i < individualEdgeTreeWidth / 2 - text.length() / 2; i++) {
				line += " ";
			}
			line += text;
			for (int i = 0; i < individualEdgeTreeWidth / 2 - text.length() / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		for (IEdge iEdge : iEdges) {
			Value<?> val = iEdge.getValue();
			int individualEdgeTreeWidth = Math.max(treeWidthPerBranch, iEdge.calculateCharacterWidth(parentCharacterWidth)/iEdges.length);
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
			if (val != null) {
				line += "|";
			} else {
				line += " ";
			}
			for (int i = 0; i < individualEdgeTreeWidth / 2; i++) {
				line += " ";
			}
		}
		line += System.lineSeparator();
		return line;
	}

}
