package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import pageObjects.LandingPage;

public class ValidateNavigationBar extends Base 
{
	public static Logger log = LogManager.getLogger(Base.class.getName());
	//public WebDriver driver;
	
//	@BeforeTest
//	public void initializeBrowser() throws IOException
//	{
//		driver = initializeDriver();
//	}
	
	@Test
	public void isNavigationBasDisplays() throws IOException
	{		
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
		
		LandingPage lp = new LandingPage(driver);
		Assert.assertTrue(lp.getNavigationBar().isDisplayed());
		log.info("Menu bar is displayed");
	}
//	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.close();
//	}
	

}
