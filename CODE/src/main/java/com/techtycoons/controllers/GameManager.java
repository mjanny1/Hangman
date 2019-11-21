package com.techtycoons.controllers;

import com.techtycoons.controllers.GameplayFunctions;
import com.techtycoons.services.HangManPlayerServiceImpl;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.StringBuilder;

/*
 * GameManager Class
 * This class will be used to handle all game related details.
 * Will be used for both single player and multiplayer.
 */
@Component
public class GameManager implements Runnable {
	static int incorrect_guesses;
	static String letters_guessed;
	static String[] words_guessed;
	static Queue<String> guessQueue;
	static String word_with_guesses;
	static String word;
	
	
	/*
	 * Constructor: GameManager
	 */
	GameManager() {
        GameManager.incorrect_guesses = 0;
        GameManager.letters_guessed   = null;
        GameManager.words_guessed = new String[6]; //Max amount of word guesses can only be 6
        GameManager.guessQueue = new LinkedList<>();
        GameManager.word_with_guesses = null;
        GameManager.word = null;
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
	 public static String makeBlankWord(String word) {
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
	static String fillInTheBlanks(String blankedWord, String word, List<Integer> spots) {
		StringBuilder filledInWord = new StringBuilder(blankedWord);
		for (int i=0; i<spots.size(); i++) {
				int numberPlace = spots.get(i);
				char letter = word.charAt(numberPlace);
				filledInWord.setCharAt(numberPlace, letter);
		}
		String outString=filledInWord.toString();
		return outString;
	}
	
	/*
	 * Method: getWordWithGuesses
	 * Used by the controller to get the filled out word with guesses
	 */
	String getWordWithGuesses() {
		return word_with_guesses;
	}
	
	/*
	 * Method: getWordWithGuesses
	 * Used by the controller to get the filled out word with guesses
	 */
	String getWord() {
		return word;
	}
	
	
	/*
	 * Run
	 * Game Manager Run function
	 */
    public void run() {
    	try {
	    	HangManPlayerServiceImpl service = new HangManPlayerServiceImpl();
			word = service.fetchEasyWords();
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
						if (did_you_win == false) {
							incorrect_guesses += 1;
						}
					}
					//letter guess
					else if (guess.length() == 1) {
						char[] letter = new char[guess.length()];
						letter[0] = guess.charAt(0);
						List<Integer> places = wordActions.guessLetter(letter[0]);
						
						//Did you guess correctly?
						if (places.size() < 1) { //Wrong Guess!
							incorrect_guesses += 1;
						}
						else { //Correct Guess!
							word_with_guesses = fillInTheBlanks(word_with_guesses, word, places);
							if (word_with_guesses.indexOf('_') == -1) {
								did_you_win = true;
							}
						}
						
						if (incorrect_guesses >= 6) {
							did_you_win = false;
							still_playing = false;
						}
						
						if (did_you_win == true) {
							still_playing = false;
						}
					}
					else {
						still_playing = false; //should never happen
					}
				}
			}
    	}
    	catch (IOException ex){
    		System.out.println("Error Occurred");
    	}
	}

}
