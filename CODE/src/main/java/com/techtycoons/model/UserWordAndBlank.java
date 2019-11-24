package com.techtycoons.model;

public class UserWordAndBlank {
	
	private String blanksDisplayed;
	private String currentWord;
	public UserWordAndBlank(String received, String blankSpace) {
		// TODO Auto-generated constructor stub
		this.blanksDisplayed=blankSpace;
		this.currentWord=received;
	}
	public String getBlanksDisplayed() {
		return blanksDisplayed;
	}
	public void setBlanksDisplayed(String blanksDisplayed) {
		this.blanksDisplayed = blanksDisplayed;
	}
	public String getCurrentWord() {
		return currentWord;
	}
	public void setCurrentWord(String currentWord) {
		this.currentWord = currentWord;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("blanksDisplayed=");
		builder.append(blanksDisplayed);
		builder.append(", currentWord=");
		builder.append(currentWord);
		//builder.append("]");
		return builder.toString();
	}

}
