package common;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Base {
	Response response;
	Token token = new Token();

	public Response postObject(String domain, String cate, String body) {
		return response = given()
				.baseUri(domain)
				.basePath(cate + ".json")
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json")
				.body(body)
				.post();
	}

	public Response putObject(String domain, String cate, String id, String body) {
		return response = given().log().all()
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json")
				.body(body)
				.put(domain + cate + "/" + id + ".json");
	}

	public Response deleteObject(String domain, String cate, String id) {
		return response = given().log().all()
				.header(token.getKey(), token.getToken())
				.header("Content-Type", "application/json")
				.delete(domain + cate + "/" + id + ".json");
	}

	public Response getListObject(String domain, String cate) {
		return response = given()
				.header(token.getKey(), token.getToken())
				.get(domain + cate + ".json");
	}

	public Response getObjectById(String domain, String cate, String id) {
		return response = given()
				.baseUri(domain)
				.basePath(cate)
				.header(token.getKey(), token.getToken())
				.get("/" + id + ".json");
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public String getBodyResponse() {
		return response.body().asPrettyString();
	}

	public String getValueWithKey(String key) {
		JsonPath js = new JsonPath(response.asString());
		return js.getString(key);
	}
}
