package com.ims.CorefrenceResolution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.PART;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 * Weka train class : Create model and writes it on disk.
 * @author Naveen
 *
 */
public class WekaTraining {
	
	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
 

 
	public static void main(String[] args) throws Exception {
		BufferedReader datafile = readDataFile(ApplicationDetails.TRAIN_DATA_FEATURES);
 
		Instances data = new Instances(datafile);
		data.setClassIndex(data.numAttributes() - 1);
  
		// Use a set of classifiers
		Classifier models = new J48();// a decision tree
		//Classifier models = new NaiveBayes();// a naive bayes
		
		// Run for each model
		models.buildClassifier(data);
		
		System.out.println(models);
		
		SerializationHelper.write(ApplicationDetails.MODEL, models);
	 
	}
}
