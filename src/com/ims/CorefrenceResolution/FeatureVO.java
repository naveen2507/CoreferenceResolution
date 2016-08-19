package com.ims.CorefrenceResolution;

/**
 * Feature Vector value object class
 * @author Naveen
 *
 */
public class FeatureVO {

	
	private NounPhraseVO firstNP;
	private NounPhraseVO secondNP;
	private boolean isCoreferent;
	private Integer distanceFeature;
	private Integer iPronoun;
	private Integer jPronoun;
	private boolean isStrMatch;
	private boolean defNP;
	private boolean demNP;
	public NounPhraseVO getFirstNP() {
		return firstNP;
	}
	public void setFirstNP(NounPhraseVO firstNP) {
		this.firstNP = firstNP;
	}
	public NounPhraseVO getSecondNP() {
		return secondNP;
	}
	public void setSecondNP(NounPhraseVO secondNP) {
		this.secondNP = secondNP;
	}
	public boolean isCoreferent() {
		return isCoreferent;
	}
	public void setCoreferent(boolean isCoreferent) {
		this.isCoreferent = isCoreferent;
	}
	public Integer getDistanceFeature() {
		return distanceFeature;
	}
	public void setDistanceFeature(Integer distanceFeature) {
		this.distanceFeature = distanceFeature;
	}
	public Integer getiPronoun() {
		return iPronoun;
	}
	public void setiPronoun(Integer iPronoun) {
		this.iPronoun = iPronoun;
	}
	public Integer getjPronoun() {
		return jPronoun;
	}
	public void setjPronoun(Integer jPronoun) {
		this.jPronoun = jPronoun;
	}
	public boolean isStrMatch() {
		return isStrMatch;
	}
	public void setStrMatch(boolean isStrMatch) {
		this.isStrMatch = isStrMatch;
	}
	public boolean isDefNP() {
		return defNP;
	}
	public void setDefNP(boolean defNP) {
		this.defNP = defNP;
	}
	public boolean isDemNP() {
		return demNP;
	}
	public void setDemNP(boolean demNP) {
		this.demNP = demNP;
	}
	
	
	
	
}
