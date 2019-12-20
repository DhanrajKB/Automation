package com.qa.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class TestUtils {
	
	
	public static String getValueOfJPath(JSONObject jsonResponse, String jPath){
		Object jObj = jsonResponse;
		
		for(String s:jPath.split("/")){
			if(!s.isEmpty()){
				System.out.println("value of s ---->>> " + s);
				if(!(s.contains("[") || s.contains("]")|| s.contains("{") || s.contains("}"))){
					jObj = ((JSONObject) jObj).get(s);
				}else if (s.contains("[") || s.contains("]")) {
					jObj = ((JSONArray) ((JSONObject) jObj).get(s.split("\\[")[0])).
							get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
				}else if (s.contains("{") || s.contains("}")) {
					System.out.println("jobj :--->>> " + jObj);
					System.out.println(((JSONObject) jObj).get(s.split("\\{")[0]));
//					System.out.println("---->>>> " + ((JSONArray)((JSONObject) jObj).get(s.split("\\{")[0])).
//							get(Integer.parseInt(s.split("\\{")[1].replace("\\}", ""))));
//					System.out.println((JSONArray)((JSONObject) jObj).get(s.split("\\{")[0])).
//						get(Integer.parseInt(s.split("\\{")[1].replace("}", "")));
					jObj = ((JSONArray) ((JSONObject) jObj).get(s.split("\\{")[0])).
							get(Integer.parseInt(s.split("\\{")[1].replace("}", "")));
				}
			}
			else
				System.out.println("jPath value is empty or not in proper format");
		}
		System.out.println("Value of the jPath :--->> " + jObj.toString());
		return jObj.toString();
	}

}
