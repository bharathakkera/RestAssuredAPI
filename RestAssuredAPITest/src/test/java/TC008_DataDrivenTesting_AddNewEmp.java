import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utils.XLUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC008_DataDrivenTesting_AddNewEmp {
	
	@DataProvider(name="empDataProvider")
	String[][] getEmpData() throws IOException
	{
		String path = "C:/Selenium/RestAssuredTest.xlsx";
		
		int rowNum = XLUtils.getRowCount(path, "Sheet1");
		int colCount = XLUtils.getCellCount(path, "Sheet1", 1);
		String empData[][] = new String[rowNum][colCount];
		
			
		for(int i=1; i<= rowNum; i++)
		{
			for(int j=0; j<colCount; j++) {
				empData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				
				}
		}
		
		//String empData[][]= {{"Mamatha", "40000", "30"}, {"Devansh", "80000", "10"},{"Mamatha1", "30000", "40"}};
		return empData;
	}

	@Test(dataProvider = "empDataProvider")
	void addNeEmployee(String empName, String empSalary, String empAge) {
		//Specify base URI
			RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
				
		//Request Obj
			RequestSpecification httpRequest = RestAssured.given();
			
		//Request paylaod sending along with post request
			JSONObject requestParams=new JSONObject();
					
			  requestParams.put("name", empName); 
			  requestParams.put("salary",empSalary); 
			  requestParams.put("age", empAge);
			  
			  //adding heading and stating that the request body is a json
			  httpRequest.header("Content-Type","application/json");
			  
			  //Add json to the body of the requestBhareath
			  httpRequest.body(requestParams.toJSONString());
			  
			  //Post Request 
			  Response response = httpRequest.request(Method.POST,"/create");
			  
			  //status code validation 
			  int statusCode=response.getStatusCode();
			  System.out.println("Status code is: "+statusCode);
			  Assert.assertEquals(statusCode, 200);
			  
			  //Capture Response Body to perform validations 
			  String responseBody=response.getBody().asString();
			  
			  Assert.assertEquals(responseBody.contains(empName), true);
			  Assert.assertEquals(responseBody.contains(empSalary), true);
			  Assert.assertEquals(responseBody.contains(empAge), true);
			  
			  System.out.println("Response Body is: "+responseBody);
			  
				/*
				 * //Total no of employees Response response1 = httpRequest.request(Method.GET,
				 * "/employees"); String responseBody1=response1.getBody().asString();
				 * System.out.println("No of Employees: "+responseBody1);
				 */
			 
	}
	
}
