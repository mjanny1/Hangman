package com.techtycoons.controllers;

import com.techtycoons.controllers.GameplayFunctions;
import com.techtycoons.services.HangManPlayerServiceImpl;
import com.techtycoons.services.UserDataHandler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

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
	static String all_guesses;
	static String lastGuess;
	static String gameStatus;
	
	
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
        GameManager.all_guesses = "";
        GameManager.lastGuess = null;
        GameManager.gameStatus = null;
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
	 * Method: getNumberOfIncorrectGuesses
	 * Used mainly for the controller to input incorrect guesses into HangmanFigure Updater
	 */
	int getNumberOfIncorrectGuesses() {
		return incorrect_guesses;
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
		lastGuess = guess;
		all_guesses = all_guesses + guess + ",";
	}
	
	/* Method: getGuesses
	 * Used for the controller to get the list of guesses already used
	 */
	String getGuesses() {
		return all_guesses;
	}
	
	/* Method: getStatus
	 * Used for the controller to get the latest game status message
	 */
	String getStatus() {
		return gameStatus;
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
			blanks.append("-");
		}
		return blanks.toString();
	}
	 
	 
	 //for entering letter or word from UI
	 
	 public String guessLetterOrWord(String letter,String Word,String blank)
		{
			
			StringBuilder sb=null;
			
			char c = letter.charAt(0);
			sb=new StringBuilder(blank);
			if(Word.contains(letter))
			{
				if(letter.length()==1)
				{
					if (!sb.toString().equals(Word)) {
			
						for (int i = 0; i < Word.length(); i++)
						{
	               
		                if (Word.charAt(i) == c)
		                	{
		                	sb.setCharAt(i, c);
		                	
		                	}
		               	          		
						}
					
						}
			
			
					
				}
		}
			else
			{
				sb=new StringBuilder(letter);
				sb.append(letter+",");
						//sb=sb.toString();
				System.out.println("Wrong Guess:"+sb);
			}
			return sb.toString();
		}
	 
	 
	 public String enterLetterOrWord(String letter,String Word,String blank)
	 {
		 System.out.println("inside letteror word method"+letter+"::"+Word+"::"+blank);
		 String answer = Word;
	        StringBuilder userAnswer = new StringBuilder(blank);
	        String guess=letter;
	        

	       while (!userAnswer.toString().equals(answer)) {

	            //display blanks so user knows what they are looking for.
	         //   System.out.println(userAnswer);

	            //prompt user for a guess
	           // System.out.print("Guess a letter: ");

	            //store user response into variable "guess"
	          //  guess = scanner.nextLine();

	            //convert guess to char
	            char c = guess.charAt(0);

	            //loop through each letter in answer and search for the char from user's guess
	            for (int i = 0; i < answer.length(); i++) {

	                //if the letter at position i matches the user's guess then replace the dash
	                if (answer.charAt(i) == c) {
	                    userAnswer.setCharAt(i, c);
	                }//end if statement
	            }//end for loop
	            System.out.println("NO NO NO!"+userAnswer.toString());
	        }//end while loop

	        System.out.println("Congrats, you won!"+userAnswer.toString());
			return userAnswer.toString();

	    }//end main
	 
	 
	 
	 
	 
	 
	/* public String addChar(String str, char ch, int position) {
		    int len = str.length();
		    char[] updatedArr = new char[len + 1];
		    str.getChars(0, position, updatedArr, 0);
		    updatedArr[position] = ch;
		    str.getChars(position, len, updatedArr, position + 1);
		    return new String(updatedArr);
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
	 * Method: getWord
	 * Used by the controller to get the word we are playing with
	 */
	String getWord() {
		return word;
	}
	
	/*
	 * Method: newGame()
	 * Used to reset game statistics like previous guesses
	 */
	void newGame() {
		all_guesses = "";
		incorrect_guesses = 0;
		gameStatus = "New Game! Welcome!";
	}
	
	/*
	 * Run
	 * Game Manager Run function
	 */
    public void run() {
    	try {
    		newGame();
	    	HangManPlayerServiceImpl service = new HangManPlayerServiceImpl();
			word = service.fetchEasyWords();
			System.out.println("Device-Manager: Word: " + word);
			word_with_guesses = makeBlankWord(word);
			GameplayFunctions wordActions = new GameplayFunctions (word);
			UserDataHandler jsonWriter = new UserDataHandler();
			
			Boolean still_playing = true;
			Boolean did_you_win = false;
			
			while (still_playing) {
			    try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("Error Occured in GameManager while Waiting!");
				}
				if (guessQueue.size() > 0) {
					String guess = guessQueue.poll();
					System.out.println("GameManager: Received Guess " + guess);
					
					//word guess
					if (guess.length() > 1) {
						did_you_win = wordActions.guessWord(guess);
						System.out.println("Did you win?" + did_you_win);
						if (did_you_win == false) {
							incorrect_guesses += 1;
							gameStatus = lastGuess + " is Incorrect!";
						}
					}
					//letter guess
					else if (guess.length() == 1) {
						System.out.println("GameManager: Letter Guess!");
						char[] letter = new char[guess.length()];
						letter[0] = guess.charAt(0);
						String s = String.valueOf(letter[0]);
						System.out.println("Letter Getting Guessed: " + s);
						List<Integer> places = wordActions.guessLetter(letter[0]);
						System.out.println(Arrays.toString(places.toArray()));
						
						//Wrong Guess
						if (places.size() < 1) {
							System.out.println("GameManager: Incorrect Guess!");
							incorrect_guesses += 1;
							gameStatus = lastGuess + " is Incorrect!";
						}
						else { //Correct Guess!
							System.out.println("GameManager: Correct Guess!");
							word_with_guesses = fillInTheBlanks(word_with_guesses, word, places);
							gameStatus = lastGuess + " is Correct!";
							if (word_with_guesses.indexOf('-') == -1) {
								did_you_win = true;
							}
						}
						//Reset List after every guess
						places.clear();
					}
					else {
						still_playing = false; //should never happen
					}
					
					//See if you won
					if (incorrect_guesses >= 6) {
						did_you_win = false;
						still_playing = false;
						System.out.println("GameManager: You Lost!");
						gameStatus = "Sorry! You Lost!";
					}
					
					if (did_you_win == true) {
						still_playing = false;
						System.out.println("GameManager: You Won!");
						gameStatus = "You Win!!";
						//removed the following line becuase it's not working yet
						//jsonWriter.recordWin();
					}
				}
			}
			System.out.println("Game Over!");
    	}
    	catch (IOException ex){
    		System.out.println("Error Occurred");
    	}
	}

}
