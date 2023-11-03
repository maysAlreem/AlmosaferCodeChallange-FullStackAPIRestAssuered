package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AlmosaferTests {

	private Response getMethodResponseBody;

	public void setGetMethodResponseBody(Response getMethodResponseBody) {
		this.getMethodResponseBody = getMethodResponseBody;
	}

	public Response getGetmethodResponse() {
		return getMethodResponseBody;
	}

	@Test
	public void TestGet() {
		String URL = "https://ae.almosafer.com/api/v3/flights/flight/search?query=RUH-JED/2023-11-20/2023-11-30/Economy/2Adult";
		Response response = RestAssured.given().header("Content-Type", "application/json").get(URL);
		setGetMethodResponseBody(response);
		JsonPath responseToJSON = response.jsonPath();
		System.out.println(response.getHeader("content-type"));
		int statusCode = response.getStatusCode();
		System.out.println(response.getStatusCode());
		System.out.println("Response Body is :\n" + response.getBody().asString());
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void TestPost() {
		String URL = "https://ae.almosafer.com/api/v3/flights/flight/async-search-result";
		if (getGetmethodResponse() != null) {
			Response response = RestAssured.given().header("Content-Type", "application/json")
					.body(getGetmethodResponse().getBody().asString()).post(URL);
			
			System.out.println(response.getHeader("content-type"));
			
			int statusCode = response.getStatusCode();
			
			System.out.println(response.getStatusCode());
			System.out.println("Response Body is :\n" + response.getBody().asString());
			
			Assert.assertEquals(statusCode, 200);
		} else {
			System.out.println("GetMethod Response should not be null");
		}

	}
}
