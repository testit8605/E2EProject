package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage 
{
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By signIn = By.cssSelector("a[href*='sign_in']");
	private By title = By.xpath("//div[@class='item active']//h2");
	private By navigationBar = By.xpath("//div[@class='nav-outer clearfix']//ul[@class='navigation clearfix']");
	private By viewAllCourse = By.xpath("//a[contains(@class,'btn-primary')]");
	
	public LoginPage getLogin()
	{
		driver.findElement(signIn).click();
		LoginPage llp = new LoginPage(driver);
		return llp;
	}
	
	public WebElement getTitle()
	{
		return driver.findElement(title);
	}
	
	public WebElement getNavigationBar()
	{
		return driver.findElement(navigationBar);
	}
	
	public WebElement getTextOfViewAllCourseButton()
	{
		return driver.findElement(viewAllCourse);
	}

}
