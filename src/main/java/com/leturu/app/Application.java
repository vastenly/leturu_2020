package com.leturu.app;

import com.leturu.app.steps.HomePageSteps;
import com.leturu.app.steps.BrandsPageSteps;

public class Application {

	public final HomePageSteps homePage() { return new HomePageSteps(); }
	public final BrandsPageSteps brandsPage() { return new BrandsPageSteps(); }
}
