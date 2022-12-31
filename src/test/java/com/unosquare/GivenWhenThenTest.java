package com.unosquare;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class GivenWhenThenTest {
  @Test
  public void f_first() {
	  RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/users/2");
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Sucess 200 validation");
		
	  given()
	  .when()
	  	.get("https://reqres.in/api/users/2")
	  		.then()
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.'first_name'", equalTo("Janet"))
	  		.assertThat().body("data.'last_name'", equalTo("Weaver"))
	  		.assertThat().body("data.'email'", equalTo("janet.weaver@reqres.in"))
	  		.assertThat().body("data.'id'", equalTo(2));
	  
	  Reporter.log(response.body().asString());
  }
  @Test
  public void f_second() {
	  RestAssured.baseURI = "https://reqres.in/api/";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/unknown/2");
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode,200);
		Reporter.log("Sucess 200 validation");
		
	  given()
	  .when()
	  	.get("https://reqres.in/api/unknown/2")
	  		.then()
	  		.assertThat().statusCode(200)
	  		.assertThat().contentType(ContentType.JSON)
	  		.assertThat().body("data.'name'", equalTo("fuchsia rose"))
	  		.assertThat().body("data.'year'", equalTo(2001))
	  		.assertThat().body("data.'color'", equalTo("#C74375"))
	  		.assertThat().body("data.'pantone_value'", equalTo("17-2031"));
	  
	  Reporter.log(response.body().asString());
  }
  @BeforeMethod
  public void beforeMethod() {
  }

}
