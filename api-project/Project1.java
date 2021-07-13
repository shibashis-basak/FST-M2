package projects;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;

public class Project1 {
   
	RequestSpecification requestSpec;
	int id;
  
  @BeforeClass
  public void setUp() {
	  
	  requestSpec = new RequestSpecBuilder()
			// Set content type
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "Token")
			// Set base URL
			.setBaseUri("https://api.github.com")
			// Build request specification
			.build();
	  
  }
  
  @Test(priority=1)
  public void postRequest() {
		  
		  String postRequestBody = "{\"title\": \"TestAPIKey\", \"key\": \"ssh-rsa AAAAB3NzaC1yc2EABCDDAQABAAABAQCBCtSOpzk+rkTzRCZV+sFnZxxjFUy8J8v1GCB8RvjmN57JuVQ+aRmkgvxQRt+EvMZequcfMuu2BjIeL8JZ0RYkJu4U9mDJeYKue9QxgGgv94MQKCNwx/PrL4Tp6tiqqPujzfJ+auDFgqzS3L4CXSJSvK3bcTUX/TDle8gsWcE3nabGj21/h73Phv8pDpZP0rcT9eJVGWNoElj7pffTUGBlvKZSaTrLXaFTwUupaafOkijf0zmSUew5vDD+znm9p7EBy9gAPDio4wSAgIVjFRq2JUmAqQ1lX+d5ohQh7AtfmzrnwcMVZ+IiY/067M9oGS1\"}";
		  
		  Response postResponse = given().spec(requestSpec) // Use requestSpec
		      .body(postRequestBody) // Set request body
		      .when().post("/user/keys"); // Send POST request
		  
		  System.out.println(postResponse.asPrettyString());
		  
		  id = postResponse.then().extract().path("id");
		  
		  postResponse.then().statusCode(201);
		  
  }
  
  
  @Test(priority=2)
  public void getRequest() {
	  
	  Response getResponse = given().spec(requestSpec) // Use requestSpec
		      .when().get("/user/keys"); // Send GET request
	  
	  System.out.println(getResponse.asPrettyString());
	  
	  getResponse.then().log().all();
	  
	  getResponse.then().statusCode(200);
	  
  }
  
  
  @Test(priority=3)
  public void deleteRequest() {
	  
	  
	  Response deleteResponse = given().spec(requestSpec) // Set headers			  
		      .when().pathParam("keyId", id) // using path parameter
		      .delete("/user/keys/{keyId}"); // Send DELETE request
	  
	  System.out.println(deleteResponse.asPrettyString());
	  
	  deleteResponse.then().log().all();
	  
	  deleteResponse.then().statusCode(204);
	  
  }
  
  

  
}
