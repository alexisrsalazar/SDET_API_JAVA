package com.unosquare;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
 

public class FirstPost {
  @SuppressWarnings("unchecked")
@Test
  public void requestParams() {
	  JSONObject requestParams = new JSONObject(); 
	  requestParams.put("name","JohnAPI");
	  requestParams.put("job","QA");
	  
		 RestAssured.baseURI = "https://reqres.in/api"; 
		 RequestSpecification httpRequest = RestAssured.given(); 
		 httpRequest.headers("Content-Type", "application/json");
		 httpRequest.body(requestParams.toString());
		 Response response = httpRequest.post("/users");
		 int statusCode = response.getStatusCode();

			// Assert that correct status code is returned.
			Assert.assertEquals(statusCode,201);
			Reporter.log("Sucess 201 validation");
		 
  }
}
