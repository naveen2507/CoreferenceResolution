-------------------------------------------------------------------------------------------------------
Co-reference Project
-------------------------------------------------------------------------------------------------------

The documentation explains the project work, which is focussed on 'A Machine Learning Approach to Coreference Resolution of Noun Phrases' by Soon.

Dependencies (Has to be in classpath):
1.apache-commons-lang.jar
2.weka.jar

Process to run the code :
1. All the path and configuration settings(if its training procee or testing) is in ApplicationDetails.java. So for path setups, 	    modification in ApplicationDetails.java is required. All the paths ,throughout the project are read from this class.

2. As explained in the process/methodology section of the documentation , first read the data file and extract markables.
   Run MarkableDataExtraction.java and get markables file.

3. Now, we have to get train/test instances and feature vector file.Input of markable file is required.
   Run FeatureExtraction.java and fet feature vector file in .arff format.

4. Training Process : Now for training the model , run WekaTraining.java for training and getting model file.

5. Testing Process : Now for testing the model , run WekaTest.java for testing and getting evaluation metrics and detailed accuracy measures expalined in the documentation.
