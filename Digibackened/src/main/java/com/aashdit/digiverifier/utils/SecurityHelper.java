package com.aashdit.digiverifier.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aashdit.digiverifier.config.admin.model.User;
import com.aashdit.digiverifier.login.model.LoggedInUser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class SecurityHelper {
	public static User getCurrentUser() {
	    try {
	      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      LoggedInUser currentUser = (LoggedInUser)auth.getPrincipal();
	      return currentUser.getDbUser();
	    } catch (Exception e) {
	      return null;
	    } 
	  }
	
	 public static String getUserName(String token) {
		    String[] split_string = token.split("\\.");
		    String base64EncodedBody = split_string[1];
		    Base64 base64Url = new Base64(true);
		    String body = new String(base64Url.decode(base64EncodedBody));
		    JsonObject jsonObject = (new JsonParser()).parse(body).getAsJsonObject();
		    String userName = jsonObject.get("sub").getAsString();
		    return userName;
		  }
}
