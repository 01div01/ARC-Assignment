package com.arc.assignment.PageObjects;

import org.openqa.selenium.WebDriver;

import com.arc.assignment.PageComponent.AddCampuses;
import com.arc.assignment.PageComponent.SearchCampusPage;

public class CampusesPage {
	WebDriver driver;
	
	public CampusesPage(WebDriver driver) {
		this.driver = driver;
	}
	public AddCampuses clickOnCampus()
	{
		return new AddCampuses(driver);
	}
	public  SearchCampusPage searchCampus()
	{
		return new SearchCampusPage(driver);
	}

}
