package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
                 glue = "stepsdef",
                 tags = "",
                 plugin = { "pretty",
				"html:target/cucumber-reports/CucumberTestReport.html",
				"json:target/cucumber-reports/CucumberTestReport.json",
				"junit:target/cucumber-reports/CucumberTestReport.xml" })
public class Runner {
}
