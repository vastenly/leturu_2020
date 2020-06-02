package com.leturu.app.steps;

import com.leturu.app.ui.HomePageUi;


public class HomePageSteps {
	
	private final HomePageUi ui = new HomePageUi();

	public HomePageSteps () {
		ui.homePageElement.waitForToBeVisible();
		ui.homePageLogin.waitForToBeVisible();
	}

	public BrandsPageSteps openBrandsPage() {
		ui.brands.waitForToBeVisible();
		ui.brands.click();
		return new BrandsPageSteps();
	}

	public void goToHomePage() {
		ui.homePageElement.waitForToBeVisible();
		if (!ui.homePageElement.isVisible())
			ui.homePageElement.click();
		ui.homePageElement.waitForToBeVisible();
	}
}
