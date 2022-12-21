package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	TestDataBuild data = new TestDataBuild();
	RequestSpecification res;
	Response response;
	static String place_id;

	 @Given("^Add Place Payload with (.+) (.+) (.+)$")
	    public void add_place_payload_with(String name, String language, String address) throws IOException {
	
				  res=given().spec(requestSpecification())
				 .body(data.addPlacePayload(name,language,address));
	    }
	 

		    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
		    public void user_calls_something_with_something_http_request(String resource, String method) {
	       
	    	APIResources resourceAPI = APIResources.valueOf(resource);
	    	if(method.equalsIgnoreCase("POST")) {
	    	response = res.when().post(resourceAPI.getResource()).
					then().extract().response();
	    	}
	    	else if(method.equalsIgnoreCase("GET")){
	    		response = res.when().get(resourceAPI.getResource()).
						then().extract().response();
	    	}
	    }
	    

	    @Then("^the API call got success with status code 200$")
	    public void the_api_call_got_success_with_status_code_200(){
	        
	    	assertEquals(response.statusCode(),200);
	    }
	    

	    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	    public void something_in_response_body_is_something(String key, String value) {
	        
	    	
	    	assertEquals(getJsonPath(response,key),value);
	    }
	    
	    @And("^Verify place_Id created in maps with (.+) using \"([^\"]*)\"$")
	    public void verify_placeid_created_in_maps_with_using_something(String expectedName, String resource) throws IOException {
	        
	    	 place_id = getJsonPath(response, "place_id");
	    	 res=given().spec(requestSpecification())
					 .queryParam("place_id", place_id);
	    	 user_calls_something_with_something_http_request(resource,"GET");
	    	 
	    	 String actualName = getJsonPath(response,"name");
	    	 assertEquals(actualName,expectedName);
	    	 
	    }
	    
	    @Given("^Delete Place Payload$")
	    public void delete_place_payload() throws IOException {
	        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    }

}