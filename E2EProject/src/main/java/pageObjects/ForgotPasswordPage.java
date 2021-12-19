package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage 
{
	
	public WebDriver driver;
	
	public ForgotPasswordPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By email = By.id("email");
	private By next = By.name("commit");
	private By checkYourEmailText = By.cssSelector("[class*='heading']");
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public void clickNextButton()
	{
		driver.findElement(next).sendKeys(Keys.RETURN);
	}
	
	public WebElement checkYourEmailText()
	{
		webDriverWait(checkYourEmailText);
		return driver.findElement(checkYourEmailText);
	}
	
	public void webDriverWait(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	}
	
	

}
