Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
Given Add Place Payload with <name> <language> <address>
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_Id created in maps with <name> using "getPlaceAPI"

Examples:
		| name		 |	language	|address    |
		|GaleriaMall |	English		|WallStreet |
#		|BBHouse	 |  French      |CrossStreet|

		
@DeletePlace
Scenario: Verify if Delete Place functinality is working
Given Delete Place Payload
When user calls "deletePlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"