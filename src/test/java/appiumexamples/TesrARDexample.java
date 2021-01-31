package appiumexamples;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class TesrARDexample
{

	public static void main(String[] args) throws Exception
	{

		    //start appium server
			Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
			URL u= new URL("http://0.0.0.0:4723/wd/hub");
			//define desired capabilities related to device and app				
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME,"chrome");
			dc.setCapability("deviceName","9888e3374932353545");
			dc.setCapability("platformName","android");
			dc.setCapability("platformVersion","9");
			
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
			Thread.sleep(10000);
			driver.get("https://www.google.com");
			Thread.sleep(10000);
			driver.closeApp();
			Runtime.getRuntime().exec("taskkill /F /IM node.exe");
			Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	  
		       
			}
	
}