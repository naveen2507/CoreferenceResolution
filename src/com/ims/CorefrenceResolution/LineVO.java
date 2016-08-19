package com.ims.CorefrenceResolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Line value object class which read data from raw data file.
 * @author Naveen
 *
 */
public class LineVO {

	private String docID;
	private int partNumber ;
	private int	wordNumber ;
	private String word;
	private String pos;
	private String parsePos;
	private String lemma;
	private List<String> coreferent;
	public String getDocID() {
		return docID;
	}
	public void setDocID(String docID) {
		this.docID = docID;
	}
	public int getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	public int getWordNumber() {
		return wordNumber;
	}
	public void setWordNumber(int wordNumber) {
		this.wordNumber = wordNumber;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getParsePos() {
		return parsePos;
	}
	public void setParsePos(String parsePos) {
		this.parsePos = parsePos;
	}
	public String getLemma() {
		return lemma;
	}
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}
	public List<String> getCoreferent() {
		return coreferent;
	}
	public void setCoreferent(List<String> coreferent) {
		this.coreferent = coreferent;
	}
	
	
	public LineVO getLine(String elem[]){
		LineVO lineInstance = this;
		
		lineInstance.setDocID(elem[0]);
		lineInstance.setPartNumber(Integer.parseInt(elem[1]));
		lineInstance.setWordNumber(Integer.parseInt(elem[2]));
		lineInstance.setWord(elem[3]);
		lineInstance.setPos(elem[4]);
		lineInstance.setParsePos(elem[5]);
		lineInstance.setLemma(elem[6]);
		if(!(elem[elem.length-1].equalsIgnoreCase("-"))){
			String coreferent = elem[elem.length-1];
			coreferent = coreferent.replaceAll("\\(","");
			coreferent = coreferent.replaceAll("\\)","");
			List<String> coreferenceList = new ArrayList<String>();
			String ar[] = coreferent.split("\\|");
			for(String e : ar){
				coreferenceList.add(e);
			}
			lineInstance.setCoreferent(coreferenceList);	
		}
		else{
			lineInstance.setCoreferent(null);
		}
			
			
		
		return lineInstance;
		
	}
	
	
}
