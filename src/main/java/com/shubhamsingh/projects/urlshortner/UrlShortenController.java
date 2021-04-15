package com.shubhamsingh.projects.urlshortner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//This is Controller Request Mapping which redirect to index.html
@Controller
public class UrlShortenController {
	
	//I'm request mapping which redirect to index.html file
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String loadIndex() {
		return "index"; 
	}

}
