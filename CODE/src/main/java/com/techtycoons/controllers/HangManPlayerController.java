package com.techtycoons.controllers;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class HangManPlayerController {
 
    @RequestMapping(value="/firstPage", method = RequestMethod.GET)
    public String viewHome(){
        return "WelcomePage";
    }
    
    @RequestMapping(value="/singleUser", method = RequestMethod.GET)
    public String singleModeHome(){
        return "singleUser";
    }
}