package com.ims.CorefrenceResolution;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
/**
 * Weka Test class
 * @author Naveen
 *
 */
public class WekaTest {
	
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
		BufferedReader testdatafile = readDataFile(ApplicationDetails.TEST_DATA_FEATURES);
		BufferedReader traindatafile = readDataFile(ApplicationDetails.TRAIN_DATA_FEATURES);
		Instances traindata = new Instances(traindatafile); 
		Instances testdata = new Instances(testdatafile);
		testdata.setClassIndex(testdata.numAttributes() - 1);
		traindata.setClassIndex(traindata.numAttributes() - 1);
		  
		// Use a set of classifiers
		Classifier model = (J48)SerializationHelper.read(ApplicationDetails.MODEL);// a decision tree
		
		//to classify first instance
		//double actualvalue = testdata.instance(0).classValue();
		//Instance newInst = testdata.instance(0);
		//double pred = model.classifyInstance(newInst);
		
		Evaluation eval = new Evaluation(traindata);
		eval.evaluateModel(model, testdata);
		
		
		
		System.out.println(eval.toSummaryString());
		
		double cm[][] = eval.confusionMatrix();
		
		
		System.out.println("Precision for 0 : "+eval.precision(0));
		System.out.println("Precision for 1 : "+eval.precision(1));
		System.out.println("Recall for 0 : "+eval.recall(0));
		System.out.println("Recall for 1 : "+eval.recall(1));
		
		System.out.println("FMeasure 0 : "+eval.fMeasure(0));
		System.out.println("FMeasure 1 : "+eval.fMeasure(1));
		
		
		 System.out.println(eval.toClassDetailsString());
		 System.out.println(eval.toMatrixString());

		 
		 for(Instance a : testdata){
			// System.out.println(eval.evaluateModelOnce(model, a));
		 }
	}
}
