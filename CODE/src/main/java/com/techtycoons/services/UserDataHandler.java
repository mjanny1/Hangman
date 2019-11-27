package com.techtycoons.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class UserDataHandler {
	
	/*
	 * Method: getUserRecord
	 * Used by the controller to get the User's all-time record
	 */
	String getUserRecord(String username) {
		try {
			JSONParser parser = new JSONParser();
			FileReader file = new FileReader("../../../../resources/config/user_records.json");
			Object obj = parser.parse(file);
			JSONArray userData = (JSONArray) obj;
			userData.forEach( user -> findUsername(username, (JSONObject) user) );
		}
		catch (Exception ex) {
			
		}
		return "return statement";
	}
	
	/*
	 * Method: findUsername
	 * Locates the record for the user in the file
	 */
	private void findUsername(String name, JSONObject obj) {
		
	}
}
