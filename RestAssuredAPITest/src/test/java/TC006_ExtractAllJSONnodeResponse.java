import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_ExtractAllJSONnodeResponse {
	@Test
	 void googleMapPrintJSONResponse()
	 {
	  
	  //Specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
		RequestSpecification httpRequest=RestAssured.given();
	  
	  //Response object
		Response response=httpRequest.request(Method.GET,"/Delhi");
	  
	  //print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
	  
		Assert.assertEquals(responseBody.contains("Delhi"), true);
	  
		JsonPath jsonpath = response.jsonPath();
		
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity")); 
		System.out.println(jsonpath.get("WeatherDescription"));
		System.out.println(jsonpath.get("WindSpeed"));
		System.out.println(jsonpath.get("WindDirectionDegree"));
		
		Assert.assertEquals(jsonpath.get("Temperature"), "33.22 Degree celsius");
	  }
	  

}
