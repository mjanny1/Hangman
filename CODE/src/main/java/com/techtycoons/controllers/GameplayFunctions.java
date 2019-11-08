package com.techtycoons.controllers;

import java.util.List;

public class GameplayFunctions {
	
	private String word;
	private String lettersUsed = null;

    //Constructor
	GameplayFunctions(String word)
	{
		this.word = word;
	}
	
	/*
	 * Method: guessLetter
	 * Returns an Integer List of indexes in the word
	 * where a match occurred.
	 */
	List<Integer> guessLetter(char letter) {
		List<Integer> matchHere = null;
		
		//If the letter has already been used
		if (lettersUsed.indexOf(letter) >= 0) {
			System.out.println("Letter already Used!");
		    matchHere.add(-1); //Return code for letter already used
		}
		else {
			for (int n = 0; n < word.length(); n++) {
				if (word.charAt(n) == letter) {
					System.out.println("Letter Found!");
					matchHere.add(n);
				}
			}
			lettersUsed += letter;
		}
		return matchHere;
	}
	
	/*
	 * Method: guessWord
	 * Returns a Boolean as to whether or not the word
	 * guessed is correct.
	 */
	Boolean guessWord(String wordGuessed) {
		Boolean result = false;
		
		if (wordGuessed.contentEquals(word)) {
			System.out.println("You guessed Correctly!");
			result = true;
		}
		else {
			System.out.println("Your guess is incorrect!");
		}
		
		return result;
	}
	
}
