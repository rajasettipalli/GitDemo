package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination m = new StepDefination();
		
		if(StepDefination.place_id == null) {
		m.add_place_payload_with("Shop", "English", "Hyderabad");
		m.user_calls_something_with_something_http_request("AddPlaceAPI", "POST");
		m.verify_placeid_created_in_maps_with_using_something("Shop", "getPlaceAPI");
		
		}
	}
}
