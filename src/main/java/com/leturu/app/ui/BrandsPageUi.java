package com.leturu.app.ui;

import com.leturu.app.ui.objects.SearchResultForm;
import com.vastenly.taf.core.ui.Element;
import com.vastenly.taf.core.ui.controls.Input;


public class BrandsPageUi {

	//public final Element brands = new Element("//a[@class='main-menu-item-link'][contains(.,'Бренды')]");
	public final Element allBrands = new Element("css=.brands-list-full-header-title");

	public final Element homePageElement = new Element("css=.logo");
	public final Input searchInput = new Input("//div[@class='brands-list-full-search']/input");
	public final Element result = new Element("css=.brands-list-full-content-list a");

	public final Element chosenResultsRowPrototype = new Element("//ul[@class='chosen-results']//li[contains(text(),\"%s\")]");
	public final Element chosenResultsList = new Element("//ul[@class='chosen-results']//li[contains(@class,'active-result')]");

	
	public SearchResultForm searchResultForm() {
		return new SearchResultForm();
	}
	
	public enum Brand {
		
		ALL_BRANDS ("Все бренды"),
		DIOR ("DIOR"),
		LETUAL ("Л'Этуаль"),
		BALDESSARINI ("BALDESSARINI"),
		LETUAL_SELECTION ("Л'Этуаль selection"),
		LOREAL_PARIS ("L'OREAL PARIS"), //L`OREAL PARIS
		LOREAL_PROF ("L'OREAL PROFESSIONNEL");
	
		private String brand;
		
		private Brand(String brand) {
			this.brand = brand;
		}
		
		public String getName() {
			return brand;
		}
		
		public static Brand getRandom() {
		        return values()[(int) (Math.random() * values().length)];
		}
	}
}
