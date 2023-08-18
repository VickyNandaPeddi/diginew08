package com.aashdit.digiverifier.utils;

import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CommonUtils {
	

	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);
	
	
	public static String trimDNHEmployerName(String name){
		name = name.trim();
		return String.join(" ",Arrays.stream(name.split(" ")).limit(2).collect(Collectors.toList()));
	}
	
	public static double checkStringSimilarity(String string1,String string2) {
		string1 = string1.toLowerCase().trim();
		string2 = string2.toLowerCase().trim();
		if(string1.indexOf("wipro") > -1 && string2.indexOf("wipro") > -1) {
			return 1.0;
		} else {
			JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
			String regex = "/[^a-zA-Z0-9 ]/g";
			return jaroWinkler.apply(string1.replace(regex, ""), string2.replace(regex, ""));
		}
		
	}
	
//	public static String trimEmployerName(String name) {
//		let employer = employerName.toLowerCase().replace("technologies", "").replace("innovation", "").replace("innovations", "").replace("management", "").replace("consultant", "").replace("consultants", "").replace("informatics", "").replace("private", "").replace("global", "").replace("consultancy", "").replace("services", "").replace("pvt", "").replace("ltd", "").replace("solutions", "").replace("limited", "").replace(",", "").replace(".", "").replace(/ /gi, "").trim();
//		// return employer.split(' ').slice(0,2).join(' ');
//		let stringTrimPercentage = 100; // This will trim based on the minimum given length for the string
////		if(length > 0) {
////			return employer.substring(0,length);
////		} else {
////			return employer.substring(0, Math.floor((parseInt(employer.length) * stringTrimPercentage) / 100)).toLowerCase();
////		}
//	}
	
	

	
	
	
}
