package com.techtycoons.model;

public class UserWord {

	private String userName;
	private String currentWord;
	
	public UserWord(String userName, String currentWord) {
		super();
		this.userName = userName;
		this.currentWord = currentWord;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		builder.append("UserWord [userName=");
		builder.append(userName);
		builder.append(", currentWord=");
		builder.append(currentWord);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
