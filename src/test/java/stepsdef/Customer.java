package stepsdef;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;

import Support.RadomNumberPhone;
import TestRunner.Runner;
import common.Base;
import common.URL;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

public class Customer {
	Base base = new Base();
	URL url = new URL();
	String customer_id;
	String a;

	@When("I post a customer")
	public void i_post_a_customer(DataTable dataTable) throws Throwable {
		RadomNumberPhone phone = new RadomNumberPhone();
		List<List<String>> data = dataTable.cells();
		JSONArray addresses = new JSONArray();
		JSONObject address = new JSONObject();
		address.put("province_code", data.get(1).get(5));
		address.put("district_code", data.get(1).get(6));
		address.put("country_code", data.get(1).get(7));
		address.put("address", data.get(1).get(8));
		addresses.add(address);
		JSONObject customer = new JSONObject();
		customer.put("first_name", data.get(1).get(0));
		customer.put("last_name", data.get(1).get(1));
		customer.put("phone", phone.radomNumberPhoneValid());
		customer.put("email", data.get(1).get(2));
		customer.put("birthday", data.get(1).get(3));
		customer.put("sex", data.get(1).get(4));
		customer.put("note", data.get(1).get(9));
		customer.put("state", data.get(1).get(10));
		customer.put("addresses", addresses);
		JSONObject root = new JSONObject();
		root.put("customer", customer);
		base.postObject(url.getURI(), url.getEdp_customer(), root.toJSONString());
	}

	@Then("I verify post customer success")
	public void i_verify_post_customer_success() throws Throwable {
		Assert.assertEquals(200, base.getStatusCode());
		System.out.println("Response body: " + base.getBodyResponse());
		customer_id = base.getValueWithKey("customer.client_id");
		System.out.println("id: " + customer_id);
	}

	@When("I get list customer")
	public void i_get_list_customer() throws Throwable {
		// base.getListObject("https://fnb.mysapo.vn/admin/customers.json");
		// System.out.println("List customer: "+ base.getBodyResponse());

		/*
		 * Response res = given().header("X-Fnb-Token",
		 * "7ace097759cc4e4081f3538a8685b813")
		 * .get("https://fnb.mysapo.vn/admin/customers.json");
		 */

		// String a = base.getListObject("https://fnb.mysapo.vn/admin/customers.json");
		// System.out.println("List customer:
		// "+base.getListObject("https://fnb.mysapo.vn/admin/customers.json").body().asString());
		// JSONObject jsonOb = new JSONObject();
		// JSONArray JSONResponseBody = new JSONArray();
		// JSONResponseBody.add(base.getListObject("https://fnb.mysapo.vn/admin/customers.json").body().asString());
		// JSONResponseBody.add(res.body().asString());
		// System.out.println("First customer: " + JSONResponseBody);
		// System.out.println("ID: "+base.getValueWithKey("customers.client_id"));
		// JsonPath js = res.jsonPath();
		// String a = js.get("customers[0].client_id");
		// System.out.print(a);
		/*
		 * base.getListObject("https://fnb.mysapo.vn/admin/customers.json"); a =
		 * base.getValueWithKey("customers[0].client_id"); System.out.print(a); //
		 * base.getObjectById("https://fnb.mysapo.vn/admin/customers",a);
		 * System.out.print(base.getObjectById("https://fnb.mysapo.vn/admin/customers",
		 * a).body().asString());
		 */
	}

	@When("I put customer")
	public void i_put_customer(io.cucumber.datatable.DataTable dataTable) throws Throwable {
		RadomNumberPhone phone = new RadomNumberPhone();
		List<List<String>> data = dataTable.cells();
		JSONArray addresses = new JSONArray();
		JSONObject address = new JSONObject();
		address.put("province_code", data.get(1).get(5));
		address.put("district_code", data.get(1).get(6));
		address.put("country_code", data.get(1).get(7));
		address.put("address", data.get(1).get(8));
		addresses.add(address);
		JSONObject customer = new JSONObject();
		customer.put("first_name", data.get(1).get(0));
		System.out.print("first_name: " + data.get(1).get(0));
		customer.put("last_name", data.get(1).get(1));
		customer.put("phone", phone.radomNumberPhoneValid());
		customer.put("email", data.get(1).get(2));
		customer.put("birthday", data.get(1).get(3));
		customer.put("sex", data.get(1).get(4));
		customer.put("note", data.get(1).get(9));
		customer.put("state", data.get(1).get(10));
		customer.put("addresses", addresses);
		JSONObject root = new JSONObject();
		root.put("customer", customer);
		base.putObject("https://fnb.mysapo.vn/admin/customers/", a, root.toJSONString());
		System.out.println("Response body: " + base.getBodyResponse());
	}

	@When("I get customer")
	public void i_get_customer() throws Throwable {
		base.getListObject("https://fnb.mysapo.vn/admin/customers.json");
		a = base.getValueWithKey("customers[0].client_id");
		System.out.print(a);
		// base.getObjectById("https://fnb.mysapo.vn/admin/customers",a);
		System.out.print(base.getObjectById("https://fnb.mysapo.vn/admin/customers", a).body().asString());
	}

	@When("I delete customer")
	public void i_delete_customer() throws Throwable {
		base.deleteObject("https://fnb.mysapo.vn/admin/customers/", a);
		System.out.print(base.getStatusCode());
		System.out.print("Response body: " + base.getBodyResponse());
	}

}
