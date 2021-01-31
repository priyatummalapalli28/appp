package appiumexamples;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Testvodapp 
{

	public static void main(String[] args) throws Exception
	
	{
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and VODQA app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName","9888e3374932353545");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion", "9");
		dc.setCapability("app","D:\\VodQA.apk");
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
		
	    Runtime.getRuntime().exec("taskkill /F /IM node.exe");
    	Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	    
	}

}
