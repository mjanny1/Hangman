package com.techtycoons.controllers;

import java.util.List;

import org.springframework.stereotype.Component;

/*
 * Gameplay Functions Class is used for repeated game functions like:
 * guessing a word
 * guessing a letter
 */
@Component
public class GameplayFunctions {
	
	private String word;
	private String lettersUsed = null;
	
	

    public GameplayFunctions() {
	}




	//Constructor done by MAtt
	GameplayFunctions(String word)
	{
		this.word = word;
	}
	
	 
		
		
		public String guessLetterOrWord(String letter,String Word,String blank)
		{
			
			StringBuilder sb=new StringBuilder(blank);
			if(Word.contains(letter))
			{
							
				int indexOfLetter=Word.indexOf(letter);			
				sb.setCharAt(indexOfLetter+1, letter.charAt(0));
					
				
				
			}
			return sb.toString();
		}
		
	
	/*
	 * Method: guessLetter 
	 * Author:Matt
	 * Returns an Integer List of indexes in the word
	 * where a match occurred.
	 */
	List<Integer> guessLetter(char letter) {
		System.out.println("In gameplayerFunction::"+letter);
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
