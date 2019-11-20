package com.techtycoons.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


@Service
public class HangManPlayerServiceImpl implements HangManPlayerService {
	
//	HangManPlayerDao hangManDao;
//	
//	public HangManPlayerDao getHangManDao() {
//		return hangManDao;
//	}
//
//	public void setHangManDao(HangManPlayerDao hangManDao) {
//		this.hangManDao = hangManDao;
//	}
//for fetching a single random wor from a file
	public String fetchEasyWords() throws IOException
	{
		System.out.println("In service");
		List<String> filearray = new ArrayList<String>();
		
		File file = ResourceUtils.getFile("classpath:config/list_of_words.txt");
				 
				//File is found
				System.out.println("File Found : " + file.exists());
				 
				//Read File Content
				String content = new String(Files.readAllBytes(file.toPath()));
				System.out.println("The content of the file is::"+content);
				String[] c=content.split(" ");
				for(String s:c)
				{
					filearray.add(s);
				}
				
				int size = filearray.size();
				System.out.println("size is::"+size);
				Random rn = new Random();
				
				int randomWord = rn.nextInt(size);
				//System.out.println(content);
				System.out.println("Random word is: " + filearray.get(randomWord));
				//System.out.println("ok done");
				return filearray.get(randomWord);
		
		
				
	}

}
