import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_AuthenticationAPI {
	
	@Test
	public void AutherizationTest() {
		
		//Specify base URI
		  RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
		  
		//Basic authentication
		  PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		  authscheme.setUserName("ToolsQA");
		  authscheme.setPassword("TestPassword");
		  
		  RestAssured.authentication = authscheme;
		  
		//Request Obj
		  RequestSpecification httpRequest = RestAssured.given();
		
		//Response Obj
		  Response response = httpRequest.request(Method.GET, "/");
		
		//status code validation
		  int statusCode=response.getStatusCode();
		  System.out.println("Status code is: "+statusCode);
		  Assert.assertEquals(statusCode, 200);
		
		//Print Response in console window
		  String responseBody = response.getBody().asString();
		  System.out.println("Response Body is:" +responseBody);
		
	}

}
