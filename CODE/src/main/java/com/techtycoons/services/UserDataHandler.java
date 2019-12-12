package com.techtycoons.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

@Component
public class UserDataHandler {
	static JSONObject userObject;
	private FileWriter file;
	static Object obj;
	
//	/*
//	 * Constructor
//	 */
//	public UserDataHandler()
//	{
//		userObject = null;
//	}
	
	/*
	 * Method: getUserRecord
	 * Used by the controller to get the User's all-time score record
	 */
	public String getUserRecord(String username) {
		String userRecord = null;
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
				userRecord = "Could not locate User's Record!";
				System.out.println("UserDataHandler: getUserRecord: Could not locate User's Record!");
			}
			else
			{
				long wins = (long) userObject.get("wins");
				long losses = (long) userObject.get("losses");
				userRecord = Long.toString(wins) + "-" + Long.toString(losses);
				System.out.println(userRecord);
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
	
	/*
	 * Method: recordWin
	 * Updates the user's record after a win
	 */
	public void recordWin() {
		long wins = (long) userObject.get("wins");
		wins++;
		userObject.put("wins", wins);
		
		try {
			String filePath = ResourceUtils.getFile("classpath:config/user_records.json").getAbsolutePath();
			file = new FileWriter(filePath);
		    file.write(userObject.toJSONString());
			System.out.println("Successfully Copied JSON Object to File... " + filePath);
			System.out.println("\nJSON Object: " + userObject);
			file.close();
		}
		catch (Exception e) {
			System.out.println("Exception Occurred while recording win: " + e);
		}
	}
}
