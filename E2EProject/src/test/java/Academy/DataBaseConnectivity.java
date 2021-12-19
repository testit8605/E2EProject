package Academy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataBaseConnectivity 
{
	public static void main(String args[]) throws SQLException
	{
	//**********Creating table in DataBase************
//	create database Qadbt;
//	use Qadbt;
//	create table EmployeeInfo(name varchar(20), id int(20), location varchar(20), age int(20));
//	describ EmployeeInfo;
	
	//**********Add data into the table************
//	insert into EmployeeInfo values('sam', 1,'jamod',21);
//	insert into EmployeeInfo values('jay', 1,'jamod',21);
//	insert into EmployeeInfo values('sam', 1,'jamod',21);
	
//	select * from EmployeeInfo;
	
	//******************Download jar- mysql-connector-java-5.1.21------>  MySQL Connector/J » 5.1.21**********
	
	String host= "localhost";
	String port = "3306";
	String dataBaseName= "Demo";
	Connection con = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dataBaseName+"", "Root", "root");
	Statement s = con.createStatement();
	String query = "Select * from demo where scenario = 'zeroBalance'";
	ResultSet rs = s.executeQuery(query);
	while(rs.next())
	{
		System.out.println(rs.getString("username"));
		System.out.println(rs.getString("password"));
	}
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.get("");
	driver.findElement(By.xpath("")).sendKeys(rs.getString("username"));
	driver.findElement(By.xpath("")).sendKeys(rs.getString("password"));
	
	}
}
