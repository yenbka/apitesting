package stepsdef;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Support.RadomNumberPhone;
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

import static io.restassured.RestAssured.given;

public class Customer {
	Base base = new Base();
	URL url = new URL();
	String id_cus;
	String id_address;

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
		base.postObject(url.getDomain(), url.getCate_cus(), root.toJSONString());
	}

	@Then("I verify post customer success")
	public void i_verify_post_customer_success() throws Throwable {
		Assert.assertEquals(200, base.getStatusCode());
		System.out.println("Response body: " + base.getBodyResponse());
	}

	@When("I put customer")
	public void i_put_customer(io.cucumber.datatable.DataTable dataTable) throws Throwable {
		RadomNumberPhone phone = new RadomNumberPhone();
		List<List<String>> data = dataTable.cells();
		JSONArray addresses = new JSONArray();
		JSONObject address = new JSONObject();
		address.put("client_id", id_address);
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
		base.putObject(url.getDomain(), url.getCate_cus(), id_cus, root.toJSONString());
		System.out.println("Response body: " + base.getBodyResponse());
	}

	@When("I get customer")
	public void i_get_customer() throws Throwable {
		base.getListObject(url.getDomain(), url.getCate_cus());
		id_cus = base.getValueWithKey("customers[0].client_id");
		id_address = base.getValueWithKey("customers[0].addresses[0].client_id");
		System.out.print("id customer: "+id_cus);
		System.out.print("id address:"+id_address);
		System.out.print(base.getObjectById(url.getDomain(), url.getCate_cus(), id_cus).body().asString());
	}

	@When("I delete customer")
	public void i_delete_customer() throws Throwable {
		base.deleteObject(url.getDomain(), url.getCate_cus(), id_cus);
		System.out.print(base.getStatusCode());
		System.out.print("Response body: " + base.getBodyResponse());
	}

}
