package features;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.vastenly.taf.core.logging.ThreadLogger;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {"@example_scenarios", "@test"}, glue={ "features.stepdefinitions" }, monochrome = true, format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class RunCucumberActiveTests {
	
	@BeforeClass
	public static void initLogger() {
		ThreadLogger.initLogger();
	}

	@AfterClass
	public static void saveStatistics() {

	}
}

