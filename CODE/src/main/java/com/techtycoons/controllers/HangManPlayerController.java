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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import com.techtycoons.model.UserWord;
import com.techtycoons.model.UserWordAndBlank;
import com.techtycoons.services.HangManPlayerService;
 
@Controller
public class HangManPlayerController {
 
	@Autowired
	HangManPlayerService hmpService;
	
	@Autowired
	GameManager gm;
	
	
    @RequestMapping(value="/firstPage", method = RequestMethod.GET)
    public String viewHome(){
        return "WelcomePage";
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
	    
	    String blankSpace=  gm.getWordWithGuesses();
	    System.out.println("Blanks we are playing with: " + blankSpace);
	    m.addAttribute("word", blankSpace);
	    
	    //Update Number of Guesses Remaining
	  	int guessesLeft = gm.getNumberOfGuessesRemaining();
	  	m.addAttribute("numberOfGuessesLeft", guessesLeft);
	   
//	   UserWordAndBlank userWord = new UserWordAndBlank(received,blankSpace);
//	    List<UserWordAndBlank> list = new ArrayList<UserWordAndBlank>();
//	    
//	    list.add(userWord);
//	    
//	    JSONObject obj=new JSONObject();
//	    obj.put("received", received);
//		obj.put("blankSpace", blankSpace);
//		FileWriter file=new FileWriter("E:\\usersRandomWordsNew.json");
//		file.write(obj.toString());
//		file.flush();
//	    System.out.println("json object is::"+obj);
	    
	   // list.add(blankSpace);
	   /* ObjectMapper Obj = new ObjectMapper(); 
	    
	    String jsonStr = Obj.writeValueAsString(list); 
	    
        // Displaying JSON String 
        System.out.println(jsonStr); 
	    
	    File file = new File("E:\\usersRandomWordsq.json");
	    
	    FileOutputStream fout = new FileOutputStream(file, true);
	    fout.write(jsonStr.getBytes());
	    fout.close();*/
        return "singleUser";
    }

@RequestMapping(value="/guessLetterOrWord", method = RequestMethod.POST)
   public String guessLetterOrWord(Model t,@RequestParam("letterOrWord") String letterOrWord,@RequestParam("modified1") String modified1,Model m) throws IOException{
	  	System.out.println("HangManPlayerController Guessed Letter: " + letterOrWord);
	  	gm.newGuess(letterOrWord);
	    //Wait one second for the new game process and populate word
	    try {
			TimeUnit.SECONDS.sleep(2);
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
	  	
	  	
//	  	String wordFetched=null;
//	  	String blankSpace=null;
//	  	String word,word1,word2=null;
//
//	  	 //   gf.guessLetterOrWord(letterOrWord, received, blankSpace);
//	  	   //gm.newGuess(letterOrWord);
//	  	  // gm.getWordWithGuesses();
//	  	   JSONParser parse=new JSONParser();
//	  	   
//	  	   try {
//	  		//   parse.parse(arg0)
//	  		 Object obj=parse.parse(new FileReader("E:\\usersRandomWordsNew.json"));
//			JSONObject json= (JSONObject) obj;
//			wordFetched=(String)json.get("received");
//			blankSpace=(String)json.get("blankSpace");
//			//System.out.println("Word is *******************"+wordFetched+"::blank Space is::"+blankSpace);
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	  	  // System.out.println("modified1::"+modified1);
//	  	  //for(int i=0;i<=wordFetched.length();i++) {
//		word=gm.guessLetterOrWord(letterOrWord, wordFetched, blankSpace);
//		word1=gm.guessLetterOrWord(letterOrWord, wordFetched, modified1);
//	//	word2=gm.guessLetterOrWord(wronguess, wordFetched, modified1);
//		
//		
//		
//		
//		
//	  	  //}
//	  	  m.addAttribute("word", word);
//	  	  t.addAttribute("word", word1);
//	  	 // x.addAttribute("wrongGuess", word2);
	  	  
		 return "singleUser";
   }
	  	  //  gf.guessLetterOrWord(letterOrWord, received, blankSpace);
	  	 //  gm.fillInTheBlanks(letterOrWord, word, spots)

	  	   
	  	   
	    
   }
    
 
