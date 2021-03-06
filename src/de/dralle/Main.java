package de.dralle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		Logger.getGlobal().setLevel(Level.ALL);
		ConsoleHandler logHandler = new ConsoleHandler();
		logHandler.setLevel(Level.ALL);
		Logger.getGlobal().addHandler(logHandler);
		File f = new File(".\\testdata\\C45_Beispieldaten.csv");
		if (f.exists()) {
			LOGGER.log(Level.CONFIG, "File exists");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				LOGGER.log(Level.SEVERE, "File not loaded");
			}
			if (br != null) {
				LOGGER.log(Level.CONFIG, "File loaded");
			}
			String csvDelimiter = ",";
			String line = null;
			try {
				line = br.readLine();
			} catch (IOException e) {
				LOGGER.log(Level.WARNING, "Line failed to read (1)");
			}
			String[] features = line.split(csvDelimiter);
			List<String[]> featureVectors = new ArrayList<>();
			while (line != null) {

				try {
					line = br.readLine();

				} catch (IOException e) {
					line = null;

					LOGGER.log(Level.WARNING, "Line failed to read (2)");
				}
				if (line != null) {
					featureVectors.add(line.split(csvDelimiter));
				}
			}
			try {
				br.close();
			} catch (IOException e) {

			}
			LOGGER.log(Level.CONFIG, features.length + " features");
			LOGGER.log(Level.CONFIG, featureVectors.size() + " feature vectors");
			DataSet trainDataSet=new DataSet();
			trainDataSet.setSize(features.length);
			DiscrceteResultFeature<Boolean> resultFeature=new DiscrceteResultFeature<>();
			resultFeature.setName(features[features.length - 1]);
			trainDataSet.setFeature(resultFeature, features.length-1);
			for (int i = 0; i < features.length-1; i++) {
				AbstractFeature<?> feature=null;
				switch(i) {
				case 3:
					feature=new DiscreteFeature<Boolean>(features[i]);
					break;
				case 1:
				case 2:
					feature=new ContinuousFeature<Integer>(features[i]);
					break;
				case 0:
				default:
					feature=new DiscreteFeature<String>(features[i]);
					break;
				}
				feature.setName(features[i]);
				trainDataSet.setFeature(feature, i);
			}
			for (int i = 0; i < featureVectors.size(); i++) {
				String[] values = featureVectors.get(i);
				FeatureVector vector=new FeatureVector();
				vector.setSize(values.length);
				for (int j = 0; j < values.length; j++) {
					Value<?> value=null;
					switch(j) {
					case 3:
					case 4:
						value=new Value<Boolean>(Boolean.parseBoolean(values[j]));
						break;
					case 1:
					case 2:
						value=new Value<Integer>(Integer.parseInt(values[j]));
						break;
					case 0:
					default:
						value=new Value<String>(values[j]);
						break;
					}
					value.setFeature(trainDataSet.getFeature(j));
					trainDataSet.getFeature(j).addValue(value);
					vector.setFeature(trainDataSet.getFeature(j), j);
					vector.setValue(value, j);
				}
				trainDataSet.addFeatureVector(vector);
			}
			LOGGER.log(Level.CONFIG, "Result feature set to " + resultFeature.getName());
			
			ID3Node node=new ID3Node();
			node.learn(trainDataSet);
			
			LOGGER.log(Level.INFO, "ID3 tree learned");
			
			TreePrinter tp=new TreePrinter(System.out);
			tp.print(node);

		}
	}
}