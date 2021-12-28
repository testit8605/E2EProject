package Resources;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Base 
{
	public WebDriver driver;
	public Properties prop;
	public static Logger log;
	
	@Parameters({"browser","urlToBeTested"})
	@BeforeTest
	public void initializeDriver(String browserName, String urlToBeTested) throws IOException
	{
		System.out.println(browserName);
		System.out.println(urlToBeTested);
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		
		//***************************getting data from properties file***********************
		//String browseName= prop.getProperty("browser");
		//***************************writing data to properties file*************************
//		prop.setProperty("Ab", "CD");
//		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\data.properties");
//		prop.store(fos, null);
		
		
		//String browseName = System.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				option.setHeadless(true);
			}
			driver = new ChromeDriver(option);
		}
		else if(browserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("We do not support this driver");
		}
		driver.manage().window().maximize();
		driver.get(urlToBeTested);
		//qdriver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		log = LogManager.getLogger(Base.class.getName());
		log.info("Navigated to HomePage");
	}
	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destPath = System.getProperty("user.dir")+"\\Screenshot\\"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destPath));
		return destPath;
	}
	
	public static void DrawBorder(WebElement element, WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.border= '3px solid red'", element);
	}
	
	public static void getScrollToHieght(int height, WebDriver driver) 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,"+height+")");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
