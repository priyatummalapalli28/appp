package appiumexamples;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test1Calculator 
{
	public static void main(String[] args) throws Exception
	{
		// start appium server
		//Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u = new URL("http://0.0.0.0:4788/wd/hub");
		// define desired capabilities related to device and VODQA app
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		AndroidDriver driver;
		while (2 > 1) 
		{
			try 
			{
				driver = new AndroidDriver(u, dc);
				break;
			} 
			catch (Exception ex) 
			{
			}
		}

		// test automation
		Thread.sleep(5000);
		//driver.closeApp();
		Thread.sleep(5000);
		// stop appium server
		//Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		//Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

	}

}
