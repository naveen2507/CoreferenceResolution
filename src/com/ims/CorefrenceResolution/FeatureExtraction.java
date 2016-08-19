package com.ims.CorefrenceResolution;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @author Naveen
 * Description : This class extract coreferent pair instances and consequently extract feature vectors 
 * 				 Loads in into arff file for further training or testing
 */
public class FeatureExtraction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FeatureExtraction featureExtObj = new FeatureExtraction();
		
		List<CoreferenceVO> listCoreferenceVO = new ArrayList<CoreferenceVO>();
		
		TrainingDataExtraction obj = new TrainingDataExtraction();
		Map<Integer,NounPhraseVO> markablesMap;
		if(ApplicationDetails.TRAIN){
			markablesMap = obj.getMarkablesByLineNUmber(ApplicationDetails.TRAIN_DATA_MARKABLES, listCoreferenceVO);
		}else{
			markablesMap = obj.getMarkablesByLineNUmber(ApplicationDetails.TEST_DATA_MARKABLES, listCoreferenceVO);
		}
		List<FeatureVO> listFeatureVO = featureExtObj.getFeatureList(markablesMap, listCoreferenceVO, featureExtObj);
		
		if(ApplicationDetails.TRAIN){
			featureExtObj.writeeArffFormat(ApplicationDetails.TRAIN_DATA_FEATURES, listFeatureVO);
		}else{
			featureExtObj.writeeArffFormat(ApplicationDetails.TEST_DATA_FEATURES, listFeatureVO);
		}
			
	}
	
	public void writeeArffFormat(String fileName,List<FeatureVO> listFeatureVO) throws IOException{
		
		FileWriter writer  = new FileWriter(fileName);
		writer.append("@RELATION  coreference");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE distance numeric");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE iPronoun numeric");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE jPronoun numeric");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE Str_Match {true, false}");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE DefiniteNP {true, false}");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE DemNP {true, false}");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@ATTRIBUTE Class_Labels {true, false}");
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append(ApplicationDetails.LINE_SEP);
		writer.append("@data");
		writer.append(ApplicationDetails.LINE_SEP);
		
		for(FeatureVO feature:listFeatureVO){
			writer.append(""+feature.getDistanceFeature());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.getiPronoun());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.getjPronoun());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isStrMatch());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isDefNP());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isDemNP());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isCoreferent());
			writer.append(ApplicationDetails.LINE_SEP);
				
		}
		writer.flush();
		writer.close();
	}
	
	
public void writeCsvFormat(String fileName,List<FeatureVO> listFeatureVO) throws IOException{
		
		FileWriter writer  = new FileWriter(fileName);

	
		writer.append("dist_num");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("iPronoun");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("jPronoun");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("Str_Match");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("DefiniteNP");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("DemNP");
		writer.append(ApplicationDetails.COMMA_SEP);
		writer.append("Class_Labels");
		writer.append(ApplicationDetails.LINE_SEP);
		
		
		for(FeatureVO feature:listFeatureVO){
			writer.append(""+feature.getDistanceFeature());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.getiPronoun());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.getjPronoun());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isStrMatch());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isDefNP());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isDemNP());
			writer.append(ApplicationDetails.COMMA_SEP);
			writer.append(""+feature.isCoreferent());
			writer.append(ApplicationDetails.LINE_SEP);
				
		}
		writer.flush();
		writer.close();
	}
	
	public List<FeatureVO> getFeatureList(Map<Integer,NounPhraseVO> markablesMap,List<CoreferenceVO>  listCoreferenceVO,FeatureExtraction featureExtObj){
		
		
		List<FeatureVO> listFeatureVO = new ArrayList<FeatureVO>();
		for(CoreferenceVO coreference : listCoreferenceVO){
			FeatureVO featureVO = new FeatureVO();
			NounPhraseVO firstNP = markablesMap.get(coreference.getFirstLineNumber());
			NounPhraseVO secondNP = markablesMap.get(coreference.getSecondLineNumber());
			
			featureVO.setCoreferent(coreference.isCoreferent());
			featureVO.setDistanceFeature(featureExtObj.getDistanceFeature(firstNP, secondNP));
			featureVO.setiPronoun(featureExtObj.checkPronoun(firstNP));
			featureVO.setjPronoun(featureExtObj.checkPronoun(secondNP));
			featureVO.setStrMatch(featureExtObj.strMatch(firstNP, secondNP));
			featureVO.setDefNP(featureExtObj.defPronumnStart(secondNP));
			featureVO.setDemNP(featureExtObj.demPronumnStart(secondNP));
			
			listFeatureVO.add(featureVO);
		}
		return listFeatureVO;
		
	}
	
	public Integer getDistanceFeature(NounPhraseVO firstNP,NounPhraseVO secondNP){
		
		if(firstNP.getSentenceIndex()==secondNP.getSentenceIndex()){
			return 0;
		}else{
			return secondNP.getSentenceIndex()-firstNP.getSentenceIndex();
		}
		
	}
	
	public Integer checkPronoun(NounPhraseVO np){
		
		String posTag = np.getTags();
		if(posTag.split(" ").length==1 && posTag.contains("PRP") ){
			return 1;
		}
		else{
			return 0;
		}
		
		
	}

	public boolean strMatch(NounPhraseVO firstNP,NounPhraseVO secondNP){
		
	
		String[] articles = ApplicationDetails.ARTICLES.split(ApplicationDetails.COMMA_SEP);
		String[] demPrp = ApplicationDetails.DEMONSTRATIVE_PRONOUN.split(ApplicationDetails.COMMA_SEP);
		String[] excludedWords = (String[]) ArrayUtils.addAll(articles, demPrp);
		
		String firstStr = firstNP.getPhrase();
		String secondStr = secondNP.getPhrase();
		
		for(String word : excludedWords){
			firstStr.replace(word, "").trim();
			secondStr.replace(word, "").trim();
		}
		
		if(firstStr.equalsIgnoreCase(secondStr))
			return true;
		else
			return false;
		
	}
	
	
	public boolean defPronumnStart(NounPhraseVO secondNP){
		
		String secondStr = secondNP.getPhrase();
		
		if(secondStr.trim().startsWith("the"))
			return true;
		else
			return false;
		
	}
	
	
	public boolean demPronumnStart(NounPhraseVO secondNP){
		
		String secondStr = secondNP.getPhrase();
		String[] demPrp = ApplicationDetails.DEMONSTRATIVE_PRONOUN.split(ApplicationDetails.COMMA_SEP);
		
		for(String word : demPrp){
			if(secondStr.trim().startsWith(word)){
				return true;
			}		
		}
		return false;
	}
	
	
}
