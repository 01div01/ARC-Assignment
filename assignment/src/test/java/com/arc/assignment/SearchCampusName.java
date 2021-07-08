package com.arc.assignment;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.arc.assignment.Helper.ExplicitWait;
import com.arc.assignment.PageObjects.ARCFacilityHomePage;
import com.arc.assignment.PageObjects.CampusesPage;

public class SearchCampusName extends BaseTest {
	public static WebDriver driver;
	ARCFacilityHomePage arcFacilityHomePage;
	CampusesPage campusesPage;
	ExplicitWait explicitWait;
	int rowValue;
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
	@Test(dependsOnMethods = "performLogin" ,description = "Seach and Verify the Created Campus")
	public void searchCampus() {
		campusesPage.searchCampus().clickOnCampusesButton();
		campusesPage.searchCampus().sendTextInSearchField();
		rowValue = campusesPage.searchCampus().getRowCount();
		verification();
	}

	private void verification() {
		Assert.assertTrue(rowValue>0);
		
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
	//@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
