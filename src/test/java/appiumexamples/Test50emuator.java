package appiumexamples;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Test50emuator 

{

	public static void main(String[] args) throws Exception
	
	{
		//get list of notifications
				//start appium server
				Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
				URL u= new URL("http://0.0.0.0:4723/wd/hub");
				//define desired capabilities related to device and app
				DesiredCapabilities dc=new DesiredCapabilities();
				dc.setCapability(CapabilityType.BROWSER_NAME, "");
				dc.setCapability("deviceName","emulator-5554");
				dc.setCapability("platformName","android");
				dc.setCapability("platformVersion", "8.1.0");
				//dc.setCapability("autoGrantPermissions","true");
				//dc.setCapability("adbExecTimeout","50000");
				dc.setCapability("appPackage","com.vodqareactnative");
				dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
				try
				{
					System.out.println(driver.executeScript("mobile:getNotifications"));
					Map<String, Object> resmap=(Map<String, Object>)driver.executeScript("mobile:getNotifications");
					List<Map<String, Object>> mapslist=(List<Map<String, Object>>)resmap.get("statusBarNotifications");
					for(Map<String, Object> eachmap:mapslist)
					{
						Map<String, String> ncontent=(Map<String, String>)eachmap.get("notifications");
						//display title
						if(ncontent.get("bigTitle")!=null)
						{
							System.out.println(ncontent.get("bigTitle"));
							
						}
						else
						{
							System.out.println(ncontent.get("title"));
						}
						
						//display text
						if(ncontent.get("bigText")!=null)
						{
							System.out.println(ncontent.get("bigText"));
						}
						else
						{
							System.out.println(ncontent.get("text"));
						}
						
					}
							
					}
					catch(Exception ex)
					{
						System.out.println(ex.getMessage());
						
					}
					//close App
					driver.quit();
					//stop appium server
					Runtime.getRuntime().exec("taskkill /F /IM node.exe");
					Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
			
	}

}
