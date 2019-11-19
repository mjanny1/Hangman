package com.techtycoons.controllers;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techtycoons.model.UserWord;
import com.techtycoons.services.HangManPlayerService;
 
@Controller
public class HangManPlayerController {
 
	@Autowired
	HangManPlayerService hmpService;
	
	@Autowired
	GameManager gm;
	
	@Autowired
	GameplayFunctions gf;
	
	
	
    @RequestMapping(value="/firstPage", method = RequestMethod.GET)
    public String viewHome(){
        return "WelcomePage";
    }
    
    /*Method for bringing the single user game play webpage
    and giving the required blank space for the word to be guessed  */
   @RequestMapping(value="/singleUser", method = RequestMethod.GET)
    public String singleModeHome(Model m) throws IOException{
	    String received=hmpService.fetchEasyWords();	    
	    String blankSpace=  gm.makeBlankWord(received);
	    m.addAttribute("word", blankSpace);
	    UserWord userWord = new UserWord("joyatee", received);
	    List<UserWord> list = new ArrayList<UserWord>();
	    list.add(userWord);
	    ObjectMapper Obj = new ObjectMapper(); 
	    String jsonStr = Obj.writeValueAsString(list); 
	    
        // Displaying JSON String 
        System.out.println(jsonStr); 
	    
	    File file = new File("E:\\userswords.json");
	    
	    FileOutputStream fout = new FileOutputStream(file, true);
	    fout.write(jsonStr.getBytes());
	    fout.close();
        return "singleUser";
    }
   
   @RequestMapping(value="/guessLetterOrWord", method = RequestMethod.POST)
   public void guessLetterOrWord(@RequestParam("letterOrWord") String letterOrWord,Model m) throws IOException{
	  	   System.out.println("Guessed Letter is::"+letterOrWord);
	  	 //   gf.guessLetterOrWord(letterOrWord, received, blankSpace);
	  	   
	  	   
	    
   }
    
 
}