package de.dralle;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ID3Node extends Node{
	private final static Logger LOGGER=Logger.getLogger(ID3Node.class.getName());
	public ID3Node() {
		super();
	}
	public void learn(DataSet d) {
		LOGGER.log(Level.FINE, "Builduing ID3 desision tree for " + d.getSize() + " Features "
				+ d.getData().size() + " feature vectors");
		setValue(d.getMostCommonResult());
		double entropy = d.getEntropy();
		int fIndex = d.getMostDecidingFeatureIndex();
		if (fIndex != -1) { //or entropy>0
			LOGGER.log(Level.FINE, "Most deciding feature set to " + d.getFeature(fIndex).getName());
			setDecidingFeature(d.getFeature(fIndex));
			Set<Value<?>> distinctValues = getDecidingFeature().getDistinctValues();
			for (Value<?> value : distinctValues) {
				LOGGER.log(Level.FINE, "Adding subtree for value " + value + " (Feature " + getDecidingFeature().getName() + ")");
				IEdge newEdge=new Edge(value);
				addBranch(newEdge);
				ID3Node node=new ID3Node();
				newEdge.setSubTree(node);
				DataSet subset = d.splitDiscrete(fIndex, value)[0].removeFeature(fIndex);
				node.learn(subset);				
			}
			leafNode=false;
		} else {
			LOGGER.log(Level.FINE, "No most deciding feature. Entropy is " + entropy);
			leafNode=true;
		}
	}

}
