package com.techtycoons.controllers;

import com.techtycoons.controllers.GameplayFunctions;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import java.lang.StringBuilder;

/*
 * GameManager Class
 * This class will be used to handle all game related details.
 * Will be used for both single player and multiplayer.
 */
@Component
public class GameManager {
	int incorrect_guesses;
	int correct_guesses;
	String letters_guessed;
	String[] words_guessed;
	static Queue<String> guessQueue;
	static String word_with_guesses;
	
	
	/*
	 * Constructor: GameManager
	 */
	GameManager() {
        this.incorrect_guesses = 0;
        this.correct_guesses   = 0;
        this.letters_guessed   = null;
        this.words_guessed = new String[6]; //Max amount of word guesses can only be 6
        this.guessQueue = new LinkedList<>();
        this.word_with_guesses = null;
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
	 * Method: newGuess
	 * Used for the controller to send all guesses to the guess queue
	 */
	void newGuess(String guess) {
		guessQueue.add(guess);
	}
	
	/*
	 * Method: makeBlankWord
	 * Used to create a Blank word matching the length of the original word
	 * 
	 * Parameters: String: Word we are playing with
	 */
	 public String makeBlankWord(String word) {
		StringBuilder blanks = new StringBuilder();
		for (int i =0 ; i < word.length(); i++) {
			blanks.append("_ ");
		}
		return blanks.toString();
	}
	 
	 
	
	/*
	 * Method: fillInTheBlanks
	 * Used to fill in the blanks when a letter is correctly guessed
	 * 
	 * Parameters: List<String>: list of integers of places to fill
	 */
//	static String fillInTheBlanks(String blankedWord, List<Integer> spots) {
//		
//	}
	
	/*
	 * Method: getWordWithGuesses
	 * Used by the controller to get the filled out word with guesses
	 */
	String getWordWithGuesses() {
		return word_with_guesses;
	}
	
	/*
	 * Method: pickWord()
	 * PLACEHOLDER FOR METHOD TO PICK OUR WORD RANDOMLY FROM OUR TEXT FILE LIST
	 */
	
	
	/*
	 * Main
	 * Game Manager Main function
	 */
	/*public static void main(String[] args) {
		//String word = pickWord();
		String word = "hello"; //for test purposes now.
		word_with_guesses = makeBlankWord(word);
		GameplayFunctions wordActions = new GameplayFunctions (word);
		
		Boolean still_playing = true;
		Boolean did_you_win = false;
		
		while (still_playing) {
			if (guessQueue.size() > 0) {
				String guess = guessQueue.poll();
				
				//word guess
				if (guess.length() > 1) {
					did_you_win = wordActions.guessWord(guess);
				}
				//letter guess
				else if (guess.length() == 1) {
					char[] letter = new char[guess.length()];
					letter[0] = guess.charAt(0);
					List<Integer> places = wordActions.guessLetter(letter[0]);
					
					
				}
				else {
					still_playing = false; //should never happen
				}
			}
		}
		
	}*/

}
