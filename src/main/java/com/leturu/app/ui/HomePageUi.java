package com.leturu.app.ui;

import com.leturu.app.ui.objects.SearchResultForm;
import com.vastenly.taf.core.ui.Element;
import com.vastenly.taf.core.ui.controls.Input;
import com.vastenly.taf.core.ui.controls.Link;


public class HomePageUi {

	public final Element brands = new Element("//a[@class='main-menu-item-link'][contains(.,'Бренды')]");
	public final Element location = new Element("css=.guess-city-popup");
	public final Element locationOkButton = new Element("click: closePopup");
	public final Element homePageElement = new Element("css=.logo");
	public final Element homePageLogin = new Element("css=.user-menu_login-link");


	public SearchResultForm searchResultForm() {
		return new SearchResultForm();
	}
}
