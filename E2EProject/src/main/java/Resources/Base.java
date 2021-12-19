package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
	public WebDriver driver;
	public Properties prop;
	
	//@Parameters("browseName")
	@BeforeTest
	public WebDriver initializeDriver() throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		
		//***************************getting data from properties file***********************
		String browseName= prop.getProperty("browser");
		//***************************writing data to properties file*************************
//		prop.setProperty("Ab", "CD");
//		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data.properties");
//		prop.store(fos, null);
		
		
		//String browseName = System.getProperty("browser");
		
		if(browseName.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			if(browseName.contains("headless"))
			{
				option.setHeadless(true);
			}
			driver = new ChromeDriver(option);
		}
		else if(browseName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browseName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("We do not support this driver");
		}
		
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\Screenshot\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destPath));
		return destPath;
	}
	
	public static void getScrollToHieght(int height, WebDriver driver) 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+height+")");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	
	
}
