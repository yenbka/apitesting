package stepsdef;

import java.util.ArrayList;
import java.util.List;

import TestRunner.Runner;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;

public class Customer {
	Response res;
	@When("I post a customer")
	public void i_post_a_customer(DataTable dataTable) throws Throwable {
		List<List<String>> data = dataTable.cells();
		List<String> address = new ArrayList<String>();
		address.add(data.get(1).get(6));
		address.add(data.get(1).get(7));
		address.add(data.get(1).get(8));
		address.add(data.get(1).get(9));
		Model.Customer customer = new Model.Customer();
		customer.setFirst_name(data.get(1).get(0));
		customer.setLast_name(data.get(1).get(1));
		customer.setPhone(data.get(1).get(2));
		customer.setEmail(data.get(1).get(3));
		customer.setBirthday(data.get(1).get(4));
		customer.setSex(data.get(1).get(5));
		customer.setAddresses(address);
		customer.setNote(data.get(1).get(10));
		res = given()
				.baseUri("https://fnb.mysapo.vn/admin/customers.json")
				.header("X-Fnb-Token","7ace097759cc4e4081f3538a8685b813")
				.contentType(ContentType.JSON)
				.body(customer)
				.post();
		System.out.println(res.body().asString());
	}

	@Then("I verify post customer success")
	public void i_verify_post_customer_success() throws Throwable {
		System.out.println(res.prettyPrint());
		Assert.assertEquals(200, res.getStatusCode());
	}

}
