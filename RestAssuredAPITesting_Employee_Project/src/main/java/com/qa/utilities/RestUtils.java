package com.qa.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
	
	public String getEmpName(){
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("Agasthya"+generatedString);	
	}
	
	public String getSalary(){
		return RandomStringUtils.randomNumeric(6);
	}
	
	public String getAge(){
		return RandomStringUtils.randomNumeric(2);
	}

}

