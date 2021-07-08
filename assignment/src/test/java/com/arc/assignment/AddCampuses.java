package com.arc.assignment;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arc.assignment.Helper.ExplicitWait;
import com.arc.assignment.PageObjects.ARCFacilityHomePage;
import com.arc.assignment.PageObjects.CampusesPage;

public class AddCampuses extends BaseTest{
	public static WebDriver driver;
	ARCFacilityHomePage arcFacilityHomePage;
	CampusesPage campusesPage;
	ExplicitWait explicitWait;
	String expectedValue;
	String actualString;
	@BeforeClass
	public void setup()
	{
		driver = initializeDriver();
		arcFacilityHomePage = new ARCFacilityHomePage(driver);
		campusesPage = new CampusesPage(driver);
		explicitWait = new ExplicitWait(driver);
        
	}
	@Test(dataProvider = "getData")
	public void performLogin(HashMap<String,String> loginDetails)
	{
		arcFacilityHomePage.goTo();
		arcFacilityHomePage.performLogin().enterUserName(loginDetails.get("username"));
		arcFacilityHomePage.performLogin().enterPassword(loginDetails.get("password"));
		arcFacilityHomePage.performLogin().clickOnLoginButton();
	}
	@Test(dependsOnMethods = "performLogin",description = "Add Campus and verify the name")
	public void addCampuses() {
		campusesPage.clickOnCampus().clickOnCampusesButton();
		campusesPage.clickOnCampus().clcikOnAddCampus();
		expectedValue = campusesPage.clickOnCampus().addCampusName();
		actualString = campusesPage.clickOnCampus().verifyCampusName();
		verification();
	}
	@DataProvider
	public Object[][] getData()
	{
		HashMap<String,String> loginDetails = new <String,String> HashMap();
		loginDetails.put("username", "test.qa");
		loginDetails.put("password", "123456");
		return new Object[][] {
			{loginDetails}
		};
	}
	public void verification() {
		Assert.assertEquals(expectedValue, actualString);
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
