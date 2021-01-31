package appiumexamples;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Testbase64 
{
	public static void main(String[] args) throws Exception
	{
		File f=new File("D:\\eclipseappium\\dial.png");
		Path path=f.toPath();
		String encodedfile=Base64.getEncoder().encodeToString(Files.readAllBytes(path));
		System.out.println(encodedfile);
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u = new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to app and device
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("appPackage","com.google.android.dialer");
		dc.setCapability("appActivity","com.google.android.dialer.extensions.GoogleDialtactsActivity");
		//launch app in device through appium server
		AndroidDriver driver;
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
//test automation
		try
		{
			Thread.sleep(5000);
			if(driver.findElementByImage(encodedfile).isDisplayed())
					{
				int x=driver.findElementByImage(encodedfile).getLocation().getX();
				int y=driver.findElementByImage(encodedfile).getLocation().getY();
				int w=driver.findElementByImage(encodedfile).getSize().getWidth();
				int h=driver.findElementByImage(encodedfile).getSize().getHeight();
				System.out.println(x+" "+y);
				System.out.println(w+" "+h);
         //automate located element using "TouchAction" class methods
		}
		Thread.sleep(5000);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			
		}
		//close app
		driver.closeApp();
		Thread.sleep(5000);
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
}

	