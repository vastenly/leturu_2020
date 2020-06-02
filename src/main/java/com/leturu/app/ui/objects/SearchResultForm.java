package com.leturu.app.ui.objects;

import com.vastenly.taf.core.ui.Element;


public class SearchResultForm {
	
	//public final Element contentPrototype = new Element("//div[@class='breadcrumb']//li[contains(text(),\"%s\")]");
	public final Element contentPrototype = new Element("//li[@class='active'][contains(.,\"%s\")]");

	public final Element searchResultsCount = new Element("//div[@class='atg_store_searchResultsCount']/strong");

}
