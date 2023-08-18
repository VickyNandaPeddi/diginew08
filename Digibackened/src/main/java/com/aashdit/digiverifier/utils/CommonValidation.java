package com.aashdit.digiverifier.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CommonValidation {
	
	public Boolean validationEmail(String emailfield) {
    	Boolean result=false;
    	try {
			String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";  
			Pattern pattern = Pattern.compile(regex);  
			Matcher matcher = pattern.matcher(emailfield);  
			result=matcher.matches(); 
    	}catch(Exception e) {
    		log.error("Exception occured in validationEmail method in CommonValidation-->"+e);
    	}
		return result;
    }
    
    public Boolean validateMobileNumber(String mobileNumber) {
    	Boolean result=false;
    	try {
    		Pattern  regexPattern = Pattern.compile("\\d{10}");
        	Matcher regMatcher   = regexPattern.matcher(mobileNumber);
			result=regMatcher.matches(); 
    	}catch(Exception e) {
    		log.error("Exception occured in validateMobileNumber method in CommonValidation-->"+e);
    	}
		return result;
    }
    

}
