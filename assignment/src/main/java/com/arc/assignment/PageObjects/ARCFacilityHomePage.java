package com.arc.assignment.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.arc.assignment.PageComponent.LoginPage;

public class ARCFacilityHomePage {
	WebDriver driver;

	public ARCFacilityHomePage(WebDriver driver) {
		this.driver = driver;
	}
	public void goTo()
	{
		driver.get("https://staging.arcfacilities.com/");
	}
	public LoginPage performLogin()
	{
		return new LoginPage(driver);
	}
}
