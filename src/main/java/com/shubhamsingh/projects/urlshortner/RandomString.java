package com.shubhamsingh.projects.urlshortner;

public class RandomString<T> {
	static String randomStr = "";
	static String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	static String getRandomChars() {
	
		for (int i = 0; i < 5; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomStr;
	}

	@Override
	public String toString() {
		return randomStr;
	}
}
