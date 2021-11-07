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
		System.out.println("Response body: "+ base.getBodyResponse());
		customer_id = base.getValueWithKey("customer.client_id");
		System.out.println("id: "+customer_id);	
	}
	
	@When("I get list customer")
	public void i_get_list_customer() throws Throwable {
		base.getListObject("https://fnb.mysapo.vn/admin/customers.json");
		//System.out.println("List customer: "+ base.getBodyResponse());
		Response res = given().log().all()
				.header("X-Fnb-Token", "7ace097759cc4e4081f3538a8685b813")
				.get("https://fnb.mysapo.vn/admin/customers/"+customer_id+".json");
		System.out.println("List customer: "+ res.body().asPrettyString());
		System.out.println("List customer: "+ res.getStatusCode());
	}

}
