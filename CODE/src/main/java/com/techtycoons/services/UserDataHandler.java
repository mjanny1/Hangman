package com.techtycoons.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class UserDataHandler {
	static JSONObject userObject;
	
	/*
	 * Constructor
	 */
	UserDataHandler()
	{
		userObject = null;
	}
	
	/*
	 * Method: getUserRecord
	 * Used by the controller to get the User's all-time score record
	 */
	public String getUserRecord(String username) {
		String userRecord = null;
		try {
			userObject = null;
			JSONParser parser = new JSONParser();
			FileReader file = new FileReader("../../../../resources/config/user_records.json");
			Object obj = parser.parse(file);
			JSONArray userData = (JSONArray) obj;
			userData.forEach( user -> findUsername(username, (JSONObject) user) );
			
			if (userObject == null) {
				userRecord = "Could not locate User's Record!";
			}
			else
			{
				int wins = (int) userObject.get("wins");
				int losses = (int) userObject.get("losses");
				userRecord = "Record: " + Integer.toString(wins) + "-" + Integer.toString(losses);
			}
		}
		catch (Exception ex) {
			System.out.println("An exception occurred in UserDataHandler: getUserRecord Method! " + ex);
		}
		return userRecord;
	}
	
	/*
	 * Method: findUsername
	 * Locates the record for the user in the file
	 */
	private void findUsername(String username, JSONObject obj) {
		String objectUsername = (String) obj.get("username");
		if (objectUsername == username) {
			userObject = obj;
		}
		else {
			userObject = null;
		}
		
	}
}
