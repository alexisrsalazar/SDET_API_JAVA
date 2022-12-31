package com.unosquare;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

public class FinalTest {
	ApiCore apiCore;
		//4 POST
	  @Test
	  public void PostLogin() throws IOException, ParseException {
		  
		  Response response = apiCore.PostLogin(".\\Json\\Register.json", "/login");
		  
		  Assert.assertEquals(response.statusCode(), 200);
		  Reporter.log(response.body().asString());
	  }
	  
	  @Test
	  public void PostCreate() throws IOException, ParseException {
		  Response response = apiCore.PostLogin(".\\Json\\creater.json", "/users");
		  
		  Assert.assertEquals(response.statusCode(), (201) );
		  Reporter.log(response.body().asString());
	  }
	  
	  @Test
	  public void PostRegister() throws IOException, ParseException {
		  Response response = apiCore.PostLogin(".\\Json\\Register.json", "/register");
		  
		  Assert.assertEquals(response.statusCode(), 200);
		  Reporter.log(response.body().asString());
	  }
	  
	  @Test
	  public void PostRegisterUnsuccessful() throws IOException, ParseException {
		  Response response = apiCore.PostLogin(".\\Json\\IncompleteRegister.json", "/register");
		  
		  Assert.assertEquals(response.statusCode(), 400);
		  Reporter.log(response.body().asString());
	  }
	  
	  //4 GET
	  
	  @Test
	  public void getUser2() {
		  
		  Response response = apiCore.GetUser("/users/2");
		  Assert.assertEquals(response.statusCode(), 200);
			Reporter.log("Sucess 200 validation");

			response.then().body("data.first_name", equalTo("Janet"));
			response.then().body("data.last_name", equalTo("Weaver"));
			response.then().body("data.email", equalTo("janet.weaver@reqres.in"));
			response.then().body("data.id", equalTo(2));
			Reporter.log(response.body().asString());
		  
	  }
	  @Test
	  public void getListUsers() {
		  
		  Response response = apiCore.GetUser("/users?page=2");
		  Assert.assertEquals(response.statusCode(), 200);
			Reporter.log("Sucess 200 validation");

			response.then().body("page", equalTo(2));
			response.then().body("per_page", equalTo(6));
			response.then().body("data[0].first_name", equalTo("Michael"));
			response.then().body("total_pages", equalTo(2));
			Reporter.log(response.body().asString());
		  
	  }
	  @Test
	  public void getListUnknown() {
		  
		  Response response = apiCore.GetUser("/unknown/2");
		  Assert.assertEquals(response.statusCode(), 200);
			Reporter.log("Sucess 200 validation");

			response.then().body("data.name", equalTo("fuchsia rose"));
			response.then().body("data.year", equalTo(2001));
			response.then().body("data.color", equalTo("#C74375"));
			response.then().body("data.pantone_value", equalTo("17-2031"));
			Reporter.log(response.body().asString());
		  
	  }
	  
	  @Test
	  public void getResource() {
		  
		  Response response = apiCore.GetUser("/unknown");
		  Assert.assertEquals(response.statusCode(), 200);
			Reporter.log("Sucess 200 validation");

			response.then().body("page", equalTo(1));
			response.then().body("total", equalTo(12));
			response.then().body("data[0].name", equalTo("cerulean"));
			response.then().body("total_pages", equalTo(2));
			Reporter.log(response.body().asString());
		  
	  }
	  
	  @Test
	  public void PutUser() throws IOException, ParseException {
		  Response response = apiCore.PostLogin(".\\Json\\putUSer.json", "/users/2");
		  
		  Assert.assertEquals(response.statusCode(), 201);
		  Reporter.log(response.body().asString());
	  }
	
	
	
	
	
	  @BeforeSuite
	  public void beforeSuite() {
		  
		  apiCore = new ApiCore();
		  
	  }
	  
}