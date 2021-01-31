package appiumexamples;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;

public class angrybirds
{
	public static void main(String[] args) throws Exception
	{
		Runtime.getRuntime().exec("cmd.exe /c start cmd.exe /k \"appium\"");
		URL u= new URL("http://0.0.0.0:4723/wd/hub");
		//define desired capabilities related to device
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME,"");
		dc.setCapability("deviceName","emulator-5554");
		dc.setCapability("platformName","android");
		dc.setCapability("platformVersion","8.1.0");
		dc.setCapability("appPackage","com.rovio.angrybirds");
		dc.setCapability("appActivity","com.rovio.fusion.App");
		dc.setCapability("orientation","LANDSCAPE");
		//LAUNCH app in device through appium server
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
		try
		{
			Thread.sleep(12000);
			findImageWithOptimization(driver,"checkmark.png").click();
			Thread.sleep(3000);
			WebElement birdE1=findImageWithOptimization(driver,"redsling.png");
			shootBird(driver,birdE1,-280,140);
			Thread.sleep(10000);
			findImageWithOptimization(driver,"levelcleared.png");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		//close app
		driver.quit();
		//stop appium server
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
	}
	
	private static WebElement findImageWithOptimization(AndroidDriver driver, String imageName) throws Exception
	{
		File f=new File(imageName);
		Path refImgPath=f.toPath();
		byte[] b=Files.readAllBytes(refImgPath);
		String imageData=Base64.getEncoder().encodeToString(b);
		WebElement el=null;
		double max=1.0;
		double min=0.0;
		double stopSearch=0.05;
		double check=0;
		NotFoundException notfound=null;
		while(Math.abs(max-min)>stopSearch)
		{
			check=(max+min)/2;
			driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD,check);
			try
			{
				el=driver.findElementByImage(imageData);
				min=check;
			}
			catch(NotFoundException err)
			{
				max=check;
				notfound=err;
			}
		}
		
		if(el!=null)
		{
			System.out.println(imageName+"was found at the highest threshold of:"+ check);
			return(el);	
		}
			
		  System.out.println(imageName+"could not be found even at a low threshold:"+ check);
		  throw(notfound);
	}
	
	private static void shootBird(AndroidDriver driver,WebElement birdE1,int xOffset,int yOffset)
	{
		Rectangle rect=birdE1.getRect();
		Point start=new Point(rect.x+rect.width/2,rect.y+rect.height/2);
		Point end=start.moveBy(xOffset,yOffset);
		Duration dragDuration=Duration.ofMillis(750);
		
		PointerInput finger=new PointerInput(Kind.TOUCH,"finger");
		Sequence shoot=new Sequence(finger,0);
		shoot.addAction(finger.createPointerMove(Duration.ofMillis(0),Origin.viewport(),start.x,start.y));
		shoot.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));
		shoot.addAction(finger.createPointerMove(dragDuration, Origin.viewport(),end.x,end.y));
		shoot.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(shoot));
	}
}
