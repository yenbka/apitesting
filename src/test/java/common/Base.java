package common;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Base {
	Response response;
	Token token = new Token();

	public Response postObject(String uri, String path, String body) {
		return response = given()
				.baseUri(uri)
				.basePath(path)
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json").body(body).post();
	}
	
	public Response putObject(String uri, String id, String body) {
		return response = given().log().all()
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json")
				.body(body)
				.put(uri+id+".json");
	}
	
	public Response deleteObject(String uri, String id) {
		return response = given().log().all()
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json")
				.delete(uri+id+".json");
	}

	public Response getListObject(String url) {
		return response = given()
				.header(token.getKey(), token.getToken())
				.get(url);
	}

	public Response getObjectById(String uri, String id) {
		return response = given()
				.baseUri(uri)
				.header(token.getKey(), token.getToken())
				.get("/"+id+".json");
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public String getBodyResponse() {
		return response.body().asPrettyString();
	}

	public String getValueWithKey(String key) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		return jsonPathEvaluator.get(key);
	}
}
