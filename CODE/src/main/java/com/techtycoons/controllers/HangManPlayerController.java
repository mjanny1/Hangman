package com.techtycoons.controllers;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import com.techtycoons.model.UserWord;
import com.techtycoons.model.UserWordAndBlank;
import com.techtycoons.services.HangManPlayerService;
import com.techtycoons.services.HangmanFigure;
import com.techtycoons.services.UserDataHandler;
 
@Controller
public class HangManPlayerController {
 
	@Autowired
	HangManPlayerService hmpService;
	
	@Autowired
	GameManager gm;
	
	@Autowired
	UserDataHandler dataHandler;
	
    @RequestMapping(value="/firstPage", method = RequestMethod.GET)
    public String viewHome(){
        return "WelcomePage";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }
    
    @RequestMapping(value="/submitForm", method = RequestMethod.GET)
    public String viewHomeFirst(Model m,@RequestParam("username") String username){
    	m.addAttribute("username",username);
        return "WelcomePage";
    }
    

    @RequestMapping(value="/statusWin", method = RequestMethod.GET)
    public String winStatusPage(){    	
   	System.out.println("Inside status");
        return "statusWin";
    }
    
    @RequestMapping(value="/statusLost", method = RequestMethod.GET)
    public String lostStatusPage(){    	
   	System.out.println("Inside status");
        return "statusLost";
    }
    
    
    /*Method for bringing the single user game play webpage
    and giving the required blank space for the word to be guessed  */
   @RequestMapping(value="/singleUser", method = RequestMethod.GET)
    public String singleModeHome(Model m) throws IOException{
   	    Thread newGame = new Thread((Runnable) gm);
	    newGame.start();
	    //Wait one second for the new game process and populate word
	    try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Error Occured in HanManPlayerController while Waiting!");
		}
	    String received = gm.getWord();
	    System.out.println("Word we are playing with: " + received);
	    
	    //Update the blanked out word we are trying to guess
	    String blankSpace=  gm.getWordWithGuesses();
	    System.out.println("Blanks we are playing with: " + blankSpace);
	    m.addAttribute("word", blankSpace);
	    
	    //Update Number of Guesses Remaining
	  	int guessesLeft = gm.getNumberOfGuessesRemaining();
	  	m.addAttribute("numberOfGuessesLeft", guessesLeft);
	  	
	  	//Update the scoreboard with the User's all time record
	  //	UserDataHandler dataHandler = new UserDataHandler();
	  	String scoreboard = dataHandler.getUserRecord("user1"); //using "user1" right now as an example. should be populated with actual username
	  	System.out.println("Score is::"+scoreboard);
	  	m.addAttribute("scoreboard", scoreboard);
	  	
	  	//Update Hangman Figure
	  	HangmanFigure.populateFigure(gm.getNumberOfIncorrectGuesses());
        return "singleUser";
    }

@RequestMapping(value="/guessLetterOrWord", method = RequestMethod.POST)
   public String guessLetterOrWord(RedirectAttributes redirectAttributes, Model t,@RequestParam("letterOrWord") String letterOrWord,@RequestParam("modified1") String modified1,Model m) throws IOException{
	  	System.out.println("HangManPlayerController Guessed Letter: " + letterOrWord);
	  	gm.newGuess(letterOrWord);
	  //	System.out.println("current score is::"+currentScore);
	  	String scoreboard = dataHandler.getUserRecord("user1");
	  	m.addAttribute("scoreboard", scoreboard);
	    //Wait one second for the new game process and populate word
	    try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("Error Occured in HanManPlayerController while Waiting!");
		}
	    
	    //Update Blanked Word
	    String blankspace = gm.getWordWithGuesses();
	    System.out.println("HangManPlayerController: Updated Blanked Word: " + blankspace);
	  	m.addAttribute("word", blankspace);
	  	
	  	//Update Number of Guesses Remaining
	  	int guessesLeft = gm.getNumberOfGuessesRemaining();
	  	m.addAttribute("numberOfGuessesLeft", guessesLeft);
	  	
	  	//Get all the guesses made by user
	  	String allGuess=gm.getGuesses();
	  	System.out.println("All Guess::"+allGuess);
	  	m.addAttribute("allGuess",allGuess);
	  	
	  	//show status
	  	String status=gm.getStatus();
	  	m.addAttribute("status",status);
	  	
	  	//fetch the final value of status and redirected to win page or lost page
	  	if(status.equalsIgnoreCase("You Win!!"))
	  	{
	  				redirectAttributes.addFlashAttribute("currentScore",scoreboard);
	  				return "redirect:statusWin";
	  	}
	  	if(status.equalsIgnoreCase("Sorry! You Lost!"))
	  	{
	  					  				
	  				return "redirect:statusLost";
	  	}
	  	
	  	
	  	
	    //Update Hangman Figure
//	  	List lst=HangmanFigure.populateFigureTest(gm.getNumberOfIncorrectGuesses());
//	  	
//	  	m.addAttribute("figure",lst);
	  	  
		 return "singleUser";
   }
	  	 
	  	   
}	   
	    
   
    
 
