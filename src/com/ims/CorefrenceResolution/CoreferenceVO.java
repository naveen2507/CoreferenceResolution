package com.ims.CorefrenceResolution;

/**
 * 
 * @author Naveen
 * Description : This value object class refers to coreferent markables with their line numbers
 */
public class CoreferenceVO {

	private boolean coreferent;
	private int firstLineNumber ;
	private int secondLineNumber;
	
	
	public boolean isCoreferent() {
		return coreferent;
	}
	public void setCoreferent(boolean coreferent) {
		this.coreferent = coreferent;
	}
	public int getFirstLineNumber() {
		return firstLineNumber;
	}
	public void setFirstLineNumber(int firstLineNumber) {
		this.firstLineNumber = firstLineNumber;
	}
	public int getSecondLineNumber() {
		return secondLineNumber;
	}
	public void setSecondLineNumber(int secondLineNumber) {
		this.secondLineNumber = secondLineNumber;
	}
	
	
	
}
