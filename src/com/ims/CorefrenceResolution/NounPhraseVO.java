package com.ims.CorefrenceResolution;

import java.util.List;
import java.util.Map;
/**
 * Noun Phrase value object class
 * @author Naveen
 *
 */
public class NounPhraseVO {
	
	private int startIndex;
	private int endIndex;
	private boolean isCorefernceAnnot;
	public String coreferenceNumber;
	private String phrase;
	private String tags;
	private Map<String,String> wordTags ;
	private String docId;
	private int partNumber;
	private int sentenceIndex;
	public List<String> coreferenceNumberList;
	
	
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<String> getCoreferenceNumberList() {
		return coreferenceNumberList;
	}
	public void setCoreferenceNumberList(List<String> coreferenceNumberList) {
		this.coreferenceNumberList = coreferenceNumberList;
	}
	public int getSentenceIndex() {
		return sentenceIndex;
	}
	public void setSentenceIndex(int sentenceIndex) {
		this.sentenceIndex = sentenceIndex;
	}
	public Map<String, String> getWordTags() {
		return wordTags;
	}
	public void setWordTags(Map<String, String> wordTags) {
		this.wordTags = wordTags;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public int getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public boolean isCorefernceAnnot() {
		return isCorefernceAnnot;
	}
	public void setCorefernceAnnot(boolean isCorefernceAnnot) {
		this.isCorefernceAnnot = isCorefernceAnnot;
	}
	public String getCoreferenceNumber() {
		return coreferenceNumber;
	}
	public void setCoreferenceNumber(String coreferenceNumber) {
		this.coreferenceNumber = coreferenceNumber;
	}
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	

}
