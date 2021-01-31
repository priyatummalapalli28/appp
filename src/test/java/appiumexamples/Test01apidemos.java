package appiumexamples;

import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test01apidemos
{

	public static void main(String[] args)throws Exception 
	{
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and VODQA app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName","9888e3374932353545");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion", "9");
		dc.setCapability("app","D:\\apidemos\\ApiDemos.apk");

	}

}
