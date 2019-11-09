package com.techtycoons.controllers;

import com.techtycoons.controllers.GameplayFunctions;

/*
 * GameManager Class
 * This class will be used to handle all game related details.
 * Will be used for both single player and multiplayer.
 */
public class GameManager {
	int incorrect_guesses;
	int correct_guesses;
	String letters_guessed;
	String[] words_guessed;
	
	
	/*
	 * Constructor: GameManager
	 */
	GameManager() {
        this.incorrect_guesses = 0;
        this.correct_guesses   = 0;
        this.letters_guessed   = null;
        this.words_guessed = new String[6]; //Max amount of word guesses can only be 6
	}
	
	/*
	 * Method: getNumberOfGuessesRemaining
	 * Used for the controller to display the number of guesses the user has left
	 */
	int getNumberOfGuessesRemaining() {
		int guessesRemaining;
		guessesRemaining = 6 - incorrect_guesses;
		return guessesRemaining;
	}
	
	/*
	 * Method: getLettersGuessed
	 * Used for the controller to display the list of letters already guessed
	 */
	String getLettersGuessed() {
		return letters_guessed;
	}
	
	/*
	 * Method: pickWord()
	 * PLACEHOLDER FOR METHOD TO PICK OUR WORD RANDOMLY FROM THE LIST
	 */
	
	
	/*
	 * Main
	 * Game Manager Main function
	 */
	public static void main(String[] args) {
		//String word = pickWord();
		String word = "hello"; //for test purposes now.
		
	}

}
