package appiumexamples;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import io.appium.java_client.android.AndroidDriver;



public class Test38CommonUI
{

	public static void main(String[] args) throws Exception
	{
	
		//get environment
		Scanner sc=new Scanner(System.in);
		System.out.println("enter environment like mobile/computer");
		String en=sc.nextLine();
		RemoteWebDriver driver;
		//specific c0de as per environment
		if(en.equalsIgnoreCase("mobile"))

		{
			//start appium server to use given "chromedriver" as per "chrome" in device
			
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium --chromedriver-executable D:\\appiumprojects\\chromedriver\"");
			URL u = new URL("http://0.0.0.0:4723/wd/hub");
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			dc.setCapability("deviceName","9888e3374932353545");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","9");
			//launch browser in device through appium server by creating driver object
			while(2>1)
			{
				try
				{
					driver=new AndroidDriver(u,dc);
					break;
				}
				catch(Exception ex)
				{
				}
			}
			}
		else //computer
		{
			//launch browser in computer by creating driver object
			System.setProperty("webdriver.chrome.driver","D:\\appiumprojects\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(5000);
		}
		try
		{
			Thread.sleep(10000);
			//launch site
			driver.get("http://newtours.demoaut.com");
			Thread.sleep(5000);
			//click register link
			driver.findElementByPartialLinkText("REGISTER").click();
			Thread.sleep(5000);
			//fill fields
			driver.findElementByXPath("//input[@name='firstName']").sendKeys("abdul");
			driver.findElementByCssSelector("input[name='lastName']").sendKeys("kalam");
			driver.findElementByCssSelector("input[name='phone']").sendKeys("9030957382");
			driver.findElementByCssSelector("input[name='userName']").sendKeys("apj@kalam.com");
			driver.findElementByCssSelector("input[name='address1']").sendKeys("dn-11,street");
			driver.findElementByCssSelector("input[name='address2']").sendKeys("kalam road");
			driver.findElementByCssSelector("input[name='city']").sendKeys("rameshwaramkalam");
			driver.findElementByCssSelector("input[name='state']").sendKeys("tamilnadu");
			driver.findElementByCssSelector("input[name='postalCode']").sendKeys("645782");
			Thread.sleep(5000);
			//automate dropdown
			WebElement e= driver.findElementByTagName("select");
			Select s=new Select(e);
			s.selectByVisibleText("INDIA");
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("input[name='email']")).sendKeys("apj11");
			driver.findElement(By.cssSelector("input[name='password']")).sendKeys("batch249");
			driver.findElement(By.cssSelector("input[name='confirmPassword']")).sendKeys("batch249");
			Thread.sleep(5000);
			//submit data to server
			driver.findElement(By.cssSelector("input[name='register']")).click();
			Thread.sleep(5000);	
			//close site
			driver.close();
		}
		catch(Exception ex)
		{
			SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yy-hh-mm-ss");
			Date dt=new Date();
			File src=driver.getScreenshotAs(OutputType.FILE);
			String fname=sf.format(dt)+".png";
			File dest = new File(fname);
			FileHandler.copy(src, dest);
			System.out.println(ex.getMessage());
		}
		//specific code as per mobile environment
		if(en.equalsIgnoreCase("mobile"))
		{
			//stop appium server
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		}
	}
}

