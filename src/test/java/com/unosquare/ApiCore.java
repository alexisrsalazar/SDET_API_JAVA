package com.unosquare;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiCore {
	  public Response PostLogin(String filePath, String url) throws IOException, ParseException {
		  JSONParser json = new JSONParser();
		  FileReader reader = null;
		  reader = new FileReader(filePath);
		  Object obj = json.parse(reader);


			 RestAssured.baseURI = "https://reqres.in/api"; 
			 RequestSpecification httpRequest = RestAssured.given(); 
			 httpRequest.headers("Content-Type", "application/json");
			 httpRequest.body(obj.toString());
			 return httpRequest.post(url);
	  }
	  
	  public Response GetUser(String url) {
		  RestAssured.baseURI = "https://reqres.in/api/";
			RequestSpecification httpRequest = RestAssured.given();
			return httpRequest.get(url);
			
	  }
	  
	  public Response Put(String filePath, String url) throws IOException, ParseException {
		  JSONParser json = new JSONParser();
		  FileReader reader = null;
		  reader = new FileReader(filePath);
		  Object obj = json.parse(reader);


			 RestAssured.baseURI = "https://reqres.in/api"; 
			 RequestSpecification httpRequest = RestAssured.given(); 
			 httpRequest.headers("Content-Type", "application/json");
			 httpRequest.body(obj.toString());
			 return httpRequest.put(url);
	  }

}
