package com.ims.CorefrenceResolution;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Extract Markables
 * @author Naveen
 *
 */
public class MarkableExtraction {

	 private static final char L_PAREN    = '(';
	 private static final char R_PAREN    = ')';
	 private static final char NP    = 'N';
	   
	
	public void getMarkables(SentenceVO sentenceVO,FileWriter writer) throws IOException{
		List<LineVO> listLineVO = sentenceVO.getListLineVO();
		Stack<Character> stack = new Stack<Character>();
		Stack<NounPhraseVO> stackNP = new Stack<NounPhraseVO>();
		
		List<Integer> npIndexPara = new ArrayList<Integer>();
		List<NounPhraseVO> lstNP = new ArrayList<NounPhraseVO>();
		int lineNum=0;
		for(LineVO lineInstance : listLineVO){
			lineNum++;
			String parseStr = lineInstance.getParsePos();
			if(!stackNP.isEmpty()){
				for(NounPhraseVO np : stackNP){
					int index = stackNP.indexOf(np);
					String phrase= np.getPhrase();
					np.setPhrase(phrase+" "+lineInstance.getWord());
					np.setTags(np.getTags()+" "+lineInstance.getPos());
					stackNP.set(index, np);
				}
			 }
			
			 for (int i = 0; i < parseStr.length(); i++) {
				 
				 if(parseStr.charAt(i) == L_PAREN){
					 stack.push(L_PAREN);
				 }
				 else if(parseStr.charAt(i) == NP && parseStr.charAt(i-1)==L_PAREN ){
					 
					 NounPhraseVO np = new NounPhraseVO();
					 np.setStartIndex(lineNum-1);
					 np.setDocId(sentenceVO.getDocIndex());
					 np.setPhrase(lineInstance.getWord());
					 np.setTags(lineInstance.getPos());
					 np.setSentenceIndex(sentenceVO.getSentIndex());
					 np.setPartNumber(lineInstance.getPartNumber());
					 if(lineInstance.getCoreferent()!=null){
						 np.setCorefernceAnnot(true);
						 //if(lineInstance.getCoreferent().size()>1)
							 np.setCoreferenceNumberList(lineInstance.getCoreferent()); 
						 //else
							 //np.setCoreferenceNumber(lineInstance.getCoreferent().get(0));
					 }
					 stackNP.push(np);
					 
					 npIndexPara.add(stack.size());
					 
				 }
				 
				 
				 else if(parseStr.charAt(i)==R_PAREN){
					 if(npIndexPara.contains(stack.size())){
						 npIndexPara.remove(Integer.valueOf(stack.size()));
						 
						 NounPhraseVO np = stackNP.pop();
						 if(lineInstance.getCoreferent()!=null && np.isCorefernceAnnot()){
							 List<String> coreferentList = lineInstance.getCoreferent();
							 for(String elem: coreferentList){
								 if(np.getCoreferenceNumberList().contains(elem)){
									 np.setCoreferenceNumber(elem);
								 }
							 }
						 }
						 np.setEndIndex(lineNum-1);
						 lstNP.add(np);
						 stack.pop();
					 }else{
						 stack.pop();
					 }
					 
				
					 
				 }
				 
				 
			 }
			
			
			
			
		}
		
		
		writeNounPhrases(writer, lstNP);
	}
	
	
	public void writeNounPhrases(FileWriter writer , List<NounPhraseVO> listNP) throws IOException{
	String sep=ApplicationDetails.TAB_SEP;
		for(NounPhraseVO np:listNP){
			writer.append(np.getDocId());
			writer.append(sep);
			writer.append(np.getPartNumber()+"");
			writer.append(sep);
			writer.append(""+np.getSentenceIndex());
			writer.append(sep);
			writer.append(""+np.getStartIndex());
			writer.append(sep);
			writer.append(""+np.getEndIndex());
			writer.append(sep);
			writer.append(np.getTags());
			writer.append(sep);
			writer.append(np.getPhrase());
			writer.append(sep);
			writer.append(np.getCoreferenceNumber());
			writer.append("\n");
			
		}
		
		
		
	}
	
}
