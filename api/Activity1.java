package activities;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Activity1 {
	
	final static String ROOT_URI = "https://petstore.swagger.io/v2/pet";
	
  @Test (priority=1)
  public void test1() {
	  
	  String reqBody = "{\"id\": 77232, \"name\": \"Riley\", \"status\": \"alive\"}";
		 
		    Response postResponse = 
		        given().contentType(ContentType.JSON) // Set headers
		        .body(reqBody).when().post(ROOT_URI); // Send POST request
		 
		    // Print response of POST request
		    String body = postResponse.getBody().asPrettyString();
		    System.out.println(body);
		    
  }
  
  @Test (priority=2)
  public void test2() {
	  
	  Response getResponse = 
	            given().contentType(ContentType.JSON) // Set headers
	            .when().pathParam("petId", "77232") // Set path parameter
	            .get(ROOT_URI + "/{petId}");
		 
		    // Print response
		    System.out.println(getResponse.asPrettyString());
		    // Assertions
		    getResponse.then().body("id", equalTo(77232));
		    getResponse.then().body("name", equalTo("Riley"));
		    getResponse.then().body("status", equalTo("alive"));
		
		    
  }
  
  @Test(priority=3)
  public void test3() {
      Response delResponse = 
          given().contentType(ContentType.JSON) // Set headers
          .when().pathParam("petId", "77232") // Set path parameter
          .delete(ROOT_URI + "/{petId}"); // Send DELETE request

      // Assertion
      System.out.println(delResponse.asPrettyString());
      delResponse.then().body("code", equalTo(200));
      delResponse.then().body("message", equalTo("77232"));
  }

}
