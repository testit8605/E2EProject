package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage 
{
public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	
	private By email = By.id("email");
	private By password = By.id("password");
	private By login = By.xpath("//input[@data-testid='login-button']");
	private By forgotPassword = By.xpath("//a[normalize-space()='Forgot Password']");
	private By invaidCredentials = By.cssSelector("[class='bodySmall m-b-3-xs text-center-xs auth-flash-error']");
	private By signUp = By.cssSelector("[href*='sign_up']");
	
	public WebElement forgotPassword()
	{
		return driver.findElement(forgotPassword);
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public void getLogin()
	{
		driver.findElement(login).click();
	}
	
	public void signUp()
	{
		driver.findElement(signUp).sendKeys(Keys.RETURN);
	}
	
	public WebElement invaidCredentials()
	{
		webDriverWait(invaidCredentials);
		return driver.findElement(invaidCredentials);
	}
	
	
	
	public void webDriverWait(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	}
	
	
	

}
