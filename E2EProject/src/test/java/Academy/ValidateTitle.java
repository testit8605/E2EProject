package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import pageObjects.LandingPage;

public class ValidateTitle extends Base 
{
	public static Logger log = LogManager.getLogger(Base.class.getName());
	LandingPage lp;
	//public WebDriver driver;
	
//	@BeforeTest
//	public void initializeBrowser() throws IOException
//	{
//		driver = initializeDriver();
//	}
	
	@Test(priority = 1)
	public void basePageNavigation() throws IOException
	{
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
		
		lp = new LandingPage(driver);
		Assert.assertEquals(lp.getTitle().getText(), "An Academy to Learn Earn & Shine  in your QA Career");
		Assert.assertTrue(lp.getTitle().getText().contains("An Academy to Learn Earn & Shine  in your QA Career"));
		log.info("Successfully validated Text message");
	}
	
	@Test(priority = 2)
	public void isNavigationBasDisplays() throws IOException
	{		
		Assert.assertTrue(lp.getNavigationBar().isDisplayed());
		log.info("Menu bar is displayed");
	}
	
	@Test(priority = 3)
	public void verifyViewAllTestBoxAvailable() throws IOException
	{
		getScrollToHieght(2300, driver);
		String text = lp.getTextOfViewAllCourseButton().getText();
		Assert.assertEquals(text, "VIEW ALL COURSES");
	}
	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.close();
//	}
}
