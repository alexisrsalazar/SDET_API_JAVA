package com.unosquare;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {

  @Test
  public void file_creater() throws IOException, ParseException {
	  JSONParser json = new JSONParser();
	  FileReader reader = null;
	  reader = new FileReader(".\\Json\\Register.json");
	  Object obj = json.parse(reader);


		 RestAssured.baseURI = "https://reqres.in/api"; 
		 RequestSpecification httpRequest = RestAssured.given(); 
		 httpRequest.headers("Content-Type", "application/json");
		 httpRequest.body(obj.toString());
		 Response response = httpRequest.post("/users");
		 int statusCode = response.getStatusCode();

			// Assert that correct status code is returned.
			Assert.assertEquals(statusCode,201);
			String strStatus = Integer.toString(statusCode);
			Reporter.log(response.body().asString());
			Reporter.log(strStatus + " Sucess Validation");
			Reporter.log(obj.toString());
			Reporter.log(RestAssured.baseURI.concat("/users"));
			
  }
  @Test
  public void ApiCore() throws IOException, ParseException {
	  JSONParser json = new JSONParser();
	  FileReader reader = null;
	  reader = new FileReader(".\\Json\\Register.json");
	  Object obj = json.parse(reader);


		 RestAssured.baseURI = "https://reqres.in/api"; 
		 RequestSpecification httpRequest = RestAssured.given(); 
		 httpRequest.headers("Content-Type", "application/json");
		 httpRequest.body(obj.toString());
		 Response response = httpRequest.post("/register");
		 int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
					Assert.assertEquals(statusCode,200);
					String strStatus = Integer.toString(statusCode);
					Reporter.log(response.body().asString());
					Reporter.log(strStatus + " Sucess Validation");
					Reporter.log(obj.toString());
					Reporter.log(RestAssured.baseURI.concat("/register"));
  }
}
