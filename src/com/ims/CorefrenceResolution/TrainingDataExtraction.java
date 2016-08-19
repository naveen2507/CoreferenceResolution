package com.ims.CorefrenceResolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Extract training/test data instances from markable files
 * @author Naveen
 *
 */
public class TrainingDataExtraction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<CoreferenceVO> listCoreferenceVO = new ArrayList<CoreferenceVO>();
		
		TrainingDataExtraction obj = new TrainingDataExtraction();
		obj.getMarkablesByLineNUmber("D:\\Uni-MS\\Coref-Resolution\\programming-exercise\\data\\markable-doc1-train-sample-tags.tsv", listCoreferenceVO);
	}
	
	
	/**
	 * Extracts markable pairs for training or testing data as positive or negative example in the process 
	 * mention in SOA paper.
	 * @param fileName
	 * @param listCOreferenceVO
	 * @return
	 * @throws IOException
	 */
	public Map<Integer,NounPhraseVO> getMarkablesByLineNUmber(String fileName,List<CoreferenceVO>  listCOreferenceVO) throws IOException{
		
		//markablesMap stores noun phrase value object with line number as their key
		Map<Integer,NounPhraseVO> markablesMap = new HashMap<Integer,NounPhraseVO>();
		Map<String,Integer> coreferenceMap = new HashMap<String,Integer>();
		BufferedReader br  ;
		br = new BufferedReader(new FileReader(fileName));
		String line ;
		int lineNumber = 0;
		while ((line = br.readLine()) != null) {
			lineNumber++;
			NounPhraseVO np = new NounPhraseVO();
			String elem[] = line.split(ApplicationDetails.TAB_SEP);
			np.setDocId(elem[0]);
			np.setPartNumber(Integer.parseInt(elem[1]));
			np.setSentenceIndex(Integer.parseInt(elem[2]));
			np.setTags(elem[5]);
			np.setPhrase(elem[6]);
			np.setCoreferenceNumber(elem[7]);
			
			markablesMap.put(lineNumber, np);
			if(!(elem[7].equalsIgnoreCase("null"))){
				if(coreferenceMap.containsKey(elem[7])){
					NounPhraseVO prevNounPhrase = markablesMap.get(coreferenceMap.get(elem[7]));
					if(prevNounPhrase.getDocId().equalsIgnoreCase(elem[0]) && prevNounPhrase.getPartNumber()== np.getPartNumber()){
						//Get positive or negative training examples
						getCoreferentsTrainingExample(listCOreferenceVO, lineNumber, coreferenceMap.get(elem[7]));
						coreferenceMap.remove(elem[7]);
					}
				}else{
					coreferenceMap.put(elem[7], lineNumber);	
				}				
			}
			
			
		}
		
		return markablesMap;
		
	}
	/**
	 * Get positive or negative training example on the same algorithm as presented in the paper
	 * @param listCOreferenceVO
	 * @param lineNumber
	 * @param prevLineNUmber
	 */
	public void getCoreferentsTrainingExample(List<CoreferenceVO> listCOreferenceVO, int lineNumber , int prevLineNUmber){
		CoreferenceVO trueCorefent = new CoreferenceVO();
		trueCorefent.setCoreferent(true);
		trueCorefent.setFirstLineNumber(prevLineNUmber);
		trueCorefent.setSecondLineNumber(lineNumber);
		listCOreferenceVO.add(trueCorefent);
		for(int i= prevLineNUmber+1 ; i<lineNumber ; i++){
			CoreferenceVO falseCorefent = new CoreferenceVO();
			falseCorefent.setCoreferent(false);
			falseCorefent.setFirstLineNumber(i);
			falseCorefent.setSecondLineNumber(lineNumber);
			listCOreferenceVO.add(falseCorefent);
		}
		
	}
	
	
	
	public Map<String,Integer> getMarkables(String fileName) throws IOException{
		Map<String,Integer> markablesMap = new HashMap<String,Integer>();
		BufferedReader br  ;
		br = new BufferedReader(new FileReader(fileName));
		String line ;
		int lineNumber = 0;
		while ((line = br.readLine()) != null) {
			lineNumber++;
			//NounPhraseVO np = new NounPhraseVO();
			String elem[] = line.split(ApplicationDetails.TAB_SEP);
			/*np.setDocId(elem[0]);
			np.setPartNumber(Integer.parseInt(elem[1]));
			np.setSentenceIndex(Integer.parseInt(elem[2]));
			np.setTags(elem[5]);
			np.setPhrase(elem[6]);
			np.setCoreferenceNumber(elem[7]);*/
			if(!(elem[7].equalsIgnoreCase("null"))){
				if(markablesMap.containsKey(elem[7])){
					
				}else{
					markablesMap.put(elem[7], lineNumber);	
				}
				
			}
			
		}
		
		return markablesMap;
		
	}
	
	
}
