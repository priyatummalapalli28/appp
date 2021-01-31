package appiumexamples;

import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
public class TestGoogle {

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u =new URL("http://0.0.0.0:4723/wd/hub");
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
	    dc.setCapability("platformVersion","8.1.0");
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
	    	
	     
	        //stop appium server
	    	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	    	Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	    	

	}

}
