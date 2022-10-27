package com.reqres.utilities;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSONReading {
	
	public String getJSONData(String key, String JSONFilePath) {
	//parsing file "JSONExample.json"
		
		Object obj = null;
		try {
			obj = new JSONParser().parse(new FileReader(JSONFilePath));
		}catch (Exception e) {
			
		}
		//TypeCasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;
		
		//getting firstName and lastName
		String jsonData = (String) jo.get(key);
		
		return jsonData;
	}	
}