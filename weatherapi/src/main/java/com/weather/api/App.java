package com.weather.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.net.URLConnection;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class App 
{
	public static Map<String,Object> jsonToMap(String str){
		Map<String,Object> map=new Gson().fromJson(
				str,new TypeToken<HashMap<String,Object>>(){}.getType()
				);
		return map;
	}
    	
    	public static void main(String args[]) {
    		Scanner sc=new Scanner(System.in);
    		System.out.println("ENTER CITY NAME");
  
  String city=sc.nextLine();
    		String urlString="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=91a2cd30f833c7bbffc874c84f2676fe&units=imperial";
    		try {
    			StringBuilder result=new StringBuilder();
    			URL url=new URL(urlString);
    			URLConnection conn=url.openConnection();
    			BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
    			String line;
    			while((line=rd.readLine())!=null) {
    				result.append(line);
    			}
    			rd.close();
    			Map<String,Object> respMap=jsonToMap(result.toString());
    			Map<String,Object> mainMap=jsonToMap(respMap.get("main").toString());
    			Map<String,Object> windMap=jsonToMap(respMap.get("wind").toString());
    			System.out.println("Current Temperature: "+mainMap.get("temp")+"F");
    			System.out.println("Current Humidity: "+mainMap.get("humidity"));
    			System.out.println("Wind Speeds: "+windMap.get("speed"));
    			System.out.println("Wind Angle: "+windMap.get("deg"));
    			
    		}
    		catch(IOException e) {
    			System.out.println(e.getMessage());
    		}
    	}
}
