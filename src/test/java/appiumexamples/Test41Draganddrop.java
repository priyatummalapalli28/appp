package appiumexamples;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;

import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test41Draganddrop 
{

	public static void main(String[] args) throws Exception
	{
		//start appium server
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device and VODQA app
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion", "8.1.0");
		dc.setCapability("appPackage","com.vodqareactnative");
		dc.setCapability("appActivity","com.vodqareactnative.MainActivity");
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
        // drag and drop
		try
		{
			
		WebDriverWait w=new WebDriverWait(driver,40);
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']"))).click();
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("dragAndDrop"))).click();
		w.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("dragMe")));
		MobileElement dragMe=(MobileElement) driver.findElementByAccessibilityId("dragMe");
		Point source=dragMe.getCenter();
		MobileElement dropzone=(MobileElement) driver.findElementByAccessibilityId("dropzone");
		Point target=dropzone.getCenter();
		//logic
		
		PointerInput finger=new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence s=new Sequence(finger,1);
		s.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),source.x,source.y));
		s.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		s.addAction(new Pause(finger,Duration.ofMillis(600)));
		s.addAction(finger.createPointerMove(Duration.ofMillis(600),PointerInput.Origin.viewport(),target.x,target.y));
		s.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
		driver.perform(Arrays.asList(s));
		try
		{
			w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Circle dropped']")));
			System.out.println("Drag and drop test Passed");
		}
		catch(Exception ex)
		{
			System.out.println("Drag and drop Test Failed");
			File src=driver.getScreenshotAs(OutputType.FILE);
			File dest=new File("dragdropdefectsscreen.png");
			FileHandler.copy(src,dest);
		}
				
	}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}

		//close app
		driver.closeApp();

		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
		
		}
		
				
	}

