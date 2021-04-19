package com.shubhamsingh.projects.urlshortner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



// This is our RestController 

@RestController
public class UrlShortenRestController {
	
	//1. create a method getRandomChars() to generate random characters
//	
//	private String getRandomChars() {
//		String randomStr = "";
//		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//		for (int i = 0; i < 5; i++)
//			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
//		return randomStr;
//	}

	// 2. Create a Model or POJO to set and get the FULL URL and SHORT URL i.e. ShortenUrl.java
	
	
	private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();
	private HashSet<String> guaranteeRandomString = new HashSet<>();
	
	
	// 3.Create Rest API (/shortenUrl ) by using random characters 
	@RequestMapping(value="/shortenurl", method=RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
		guaranteeRandomString.clear();
		guaranteeRandomString.add(RandomString.getRandomChars());
		String removeSquareBraces = guaranteeRandomString.toString().replaceAll("(^\\[|\\]$)", "");
		
		String randomChar = removeSquareBraces;
		guaranteeRandomString.clear();
		setShortUrl(randomChar, shortenUrl); //create short url here
		return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
	}
	
	private void setShortUrl(String randomChar, ShortenUrl shortenUrl) throws MalformedURLException {
		 shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
		 shortenUrlList.put(randomChar, shortenUrl);
		 
	}
	
	
	//4. Create Redirect controller to get full url from HashMap -- create rest api which accept path variable with /s/randomString 
	@RequestMapping(value="/s/{randomstring}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
		response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
		shortenUrlList.clear();
		guaranteeRandomString.clear();
		
		//getting full url from HashMap using short url 
	}

	

	
		

}
