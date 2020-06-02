package com.leturu.app.steps;

import com.leturu.app.ApplicationConstants;
import com.leturu.app.ui.BrandsPageUi;
import com.vastenly.taf.core.ui.Element;
import com.vastenly.taf.core.ui.Page;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BrandsPageSteps {

	private final BrandsPageUi ui = new BrandsPageUi();

	public BrandsPageSteps() {
		//ui.allBrands.waitForToBeVisible();
	}

	public void openDropListFromSearchField() {
		ui.searchInput.waitForToBeVisible();
		ui.searchInput.click();
	}

	public void goToHomePage() {
		ui.homePageElement.waitForToBeVisible();
		if (!ui.homePageElement.isVisible())
			ui.homePageElement.click();
		ui.homePageElement.waitForToBeVisible();
	}
	
	public void selectOptionFromSearchDropList(String brand) {
	    openDropListFromSearchField();
    	    //Element searchResult = ui.chosenResultsRowPrototype.setLocatorStringVariable(brand);
		ui.searchInput.setValue(brand);

		ui.result.waitForToBeVisible();
		ui.result.click();
	}
	
	public List<String> getOptionsListFromSearchDropList() {
		openDropListFromSearchField();
    	    List<WebElement> chosenResultsList = ui.chosenResultsList.getElements();
    	    List <String> options = new ArrayList<String>();
    	    for (WebElement element : chosenResultsList ){
    		String e = element.getText();
    		if (e == null)
    		    e = "empty";
    		options.add(e);
    	    }
    	    return options;
	}
	
	public boolean verifyRelevantSearchResultPageIsOpened(String brand) {
	    return ui.searchResultForm().contentPrototype.setLocatorStringVariable(brand).waitForToBeDisplayed();
	}
	
	public String getSearchResultCount() {
	    return ui.searchResultForm().searchResultsCount.getText();   	
	}
}
