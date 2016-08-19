package com.ims.CorefrenceResolution;

import java.util.List;

/**
 * Sentence value object class
 * @author Naveen
 *
 */
public class SentenceVO {

	private String docIndex;
	private int sentIndex;
	private List<LineVO> listLineVO;
	
	private List<NounPhraseVO> words;

	
	
	public List<LineVO> getListLineVO() {
		return listLineVO;
	}

	public void setListLineVO(List<LineVO> listLineVO) {
		this.listLineVO = listLineVO;
	}

	public String getDocIndex() {
		return docIndex;
	}

	public void setDocIndex(String docIndex) {
		this.docIndex = docIndex;
	}

	public int getSentIndex() {
		return sentIndex;
	}

	public void setSentIndex(int sentIndex) {
		this.sentIndex = sentIndex;
	}

	public List<NounPhraseVO> getWords() {
		return words;
	}

	public void setWords(List<NounPhraseVO> words) {
		this.words = words;
	}
	
	
	
	
	
}
