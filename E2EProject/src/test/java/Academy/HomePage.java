package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.Base;
import pageObjects.ForgotPasswordPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;

public class HomePage extends Base
{
	//public static Logger log = LogManager.getLogger(Base.class.getName());
	//public WebDriver driver;
	
//	@BeforeTest
//	public void initializeBrowser() throws IOException
//	{
//		driver = initializeDriver();
//	}
	
	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("url"));
		log.info("Navigated to HomePage");
	}
	
	@Test(dataProvider="getData")
	public void verifyInvalidCredentialMessage(String userName, String pass, String text) throws IOException, InterruptedException
	{
		
		LandingPage lp = new LandingPage(driver);
		LoginPage llp = lp.getLogin();
		llp.getEmail().sendKeys(userName);
		llp.getPassword().sendKeys(pass);
		
		log.info(text);
		llp.getLogin();
		Assert.assertEquals(llp.invaidCredentials().getText(), "Your email or password is incorrect.");
		
		Thread.sleep(2000);
		
//		llp.signUp();
//		Thread.sleep(5000);

		
		llp.forgotPassword().sendKeys(Keys.RETURN);
		ForgotPasswordPage fp = new ForgotPasswordPage(driver);
		fp.getEmail().sendKeys(userName);
		fp.clickNextButton();
		Thread.sleep(2000);
		String emailText = fp.checkYourEmailText().getText();
		Assert.assertEquals(emailText, "Check your email");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] obj = new Object[2][3];
		obj[0][0]= "Akshay@Test.com";
		obj[0][1]= "123456";
		obj[0][2]= "Restricted User";
		
		obj[1][0]= "Sanjay@Test.com";
		obj[1][1]= "54647";
		obj[1][2]= "Non Restricted User";
		
		return obj;
				
	}
	
//	@AfterTest
//	public void tearDown()
//	{
//		driver.close();
//	}


}
