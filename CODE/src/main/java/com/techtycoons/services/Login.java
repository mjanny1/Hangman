package com.techtycoons.services;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.util.ResourceUtils;

import org.springframework.stereotype.Component;

@Component
public class Login {
	
	static JSONObject userObject;
	static Object obj;
	static String statusMessage;
	
	/*
	 * Constructor
	 */
	Login() {
		statusMessage = "";
		userObject = null;
		obj = null;
	}
	
	/*
	 * Method: getLoginStatusMessage
	 * Used by the controller to get the status message for the 
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	
	
	/*
	 * Method: checkCredentials
	 * Used by the UI to log in to the system
	 */
	public boolean checkCredentials(String username, String passwordGuessed) {
		boolean allowLogin = false;
		try {
			userObject = null;
			JSONParser parser = new JSONParser();
			String filePath = ResourceUtils.getFile("classpath:config/user_records.json").getAbsolutePath();
			System.out.println("Record File Path = " + filePath);
			FileReader file = new FileReader(filePath);
			obj = parser.parse(file);
			JSONArray userData = (JSONArray) obj;
			userData.forEach( user -> findUsername(username, (JSONObject) user) );
			
			if (userObject == null) {
				statusMessage = "Invalid Username!";
				System.out.println("Login.java: checkCredentials: Could not locate User's Record!");
				System.out.println("Status Message: " + statusMessage);
				allowLogin = false;
			}
			else
			{
				String password = (String) userObject.get("password");
				if (passwordGuessed.equals(password)) {
					allowLogin = true;
					System.out.println("Correct Password! Welcome " + username);
				}
				else {
					allowLogin = false;
					statusMessage =  "Incorrect Password!";
					System.out.println("Incorrect Password!");
				}
			}
		}
		catch (Exception ex) {
			System.out.println("An exception occurred in Login: checkCredentials Method! " + ex);
		}
		return allowLogin;
	}
	
	/*
	 * Method: findUsername
	 * Locates the record for the user in the file
	 */
	private void findUsername(String username, JSONObject obj) {
		System.out.println(obj.get("username"));
		String objectUsername = (String) obj.get("username");
		System.out.println("Username: " + username + "   ObjectUserName: " + objectUsername);
		if (objectUsername.contentEquals(username) ) {
			System.out.println("Found Match");
			userObject = obj;
		}
		else {
			System.out.println("Match Not Found");
			userObject = null;
		}
		
	}
}
