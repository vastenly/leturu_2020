package features.stepdefinitions;

import java.util.List;
import com.leturu.app.test.BaseTest;
import com.leturu.app.ui.BrandsPageUi.Brand;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.vastenly.taf.utils.EnumUtils;

public class ExampleBddSteps extends BaseTest {
	
    
    @Before
	public void onStart(Scenario scenario) {
		beforeScenario(scenario);
	}

	@After
	public void tearDown(Scenario scenario) {
		afterScenario(scenario);

	}
	
	@Given("^Open home page$")
	public void openLetuHomePage() {
		log.info("[Step] Open home page.");
		browser.openUrl(APP_URL);
		browser.switchToNewOpenedWindow();
		browser.windowMaximize();
	}

	@And("^Go to home page$")
	public void goToHomePage() {
		log.info("[Step] Go to home page");
		app.homePage().goToHomePage();
        browser.close();
	}

	@And("^Open brands page$")
	public void openBrandsPage() {
		log.info("[Step] Open brands page");
		app.homePage().openBrandsPage();
	}

	Brand value;
	
	@And("^Select \"([^\"]*)\" from search drop list$")
	public void selectItemFromSearchField(String item) {
		log.info("[Step] Select [" + item + "] from search field");

		if (item.equalsIgnoreCase("unspecified"))
		    item = Brand.getRandom().getName();
		value = EnumUtils.getValueBy(Brand.class, item);
		app.brandsPage().selectOptionFromSearchDropList(value.getName());
	}
	
	@Then("^Verify search results as expected$")
	public void verifyOpenedSearchResultsAsExpected() {
		log.info("[Step] Verify search results as expected");
		app.brandsPage().verifyRelevantSearchResultPageIsOpened(value.getName());
		//if (!value.equals("Все бренды"))
		    //log.info("Search result count: "+app.brandsPage().getSearchResultCount());
	}

	@And("^Select each item from search field and verify it's page opened$")
	public void selectEachItemFromSearchFieldAndVerifyPageOpened() {
		log.info("[Step] Select each item from search field and verify it's page opened");
		List<String> options = app.brandsPage().getOptionsListFromSearchDropList();
		log.info("Getting options list: "+options);
		for (String option : options ) {
		    log.info("Select [" + option + "] from search field");
		    app.brandsPage().selectOptionFromSearchDropList(option);
		    log.info("[Step] Verify the page opened");
		    app.brandsPage().verifyRelevantSearchResultPageIsOpened(option);
		}
	}
}