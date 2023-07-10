package org.rahulshettyacademy.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.rahulshettyacademy.PageObjects.android.FormPage;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils{

	public FormPage formPage;
	public AndroidDriver driver;
	AppiumDriverLocalService service;
	@BeforeClass(alwaysRun=true)
	public AndroidDriver configureAppium() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\rahulshettyacademy\\resources\\data.properties");
		prop.load(fs);
		String IpAddress=prop.getProperty("IpAddress");
		String Port=prop.getProperty("Port");
		service=StartAppiumServer(IpAddress,Integer.parseInt(Port));
		
		UiAutomator2Options options=new UiAutomator2Options();
		
		
		options.setDeviceName("Nexus 5X API 30");
		options.setChromedriverExecutable("C:\\Users\\vchafle\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//options.setApp("C:\\Users\\vchafle\\eclipse-workspace\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\org\\ragulshettyacademy\\resources\\General-Store.apk");
	    driver=new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage=new FormPage(driver);
			return driver;
	}
	
	public void LongPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
	}
	
	public AppiumDriverLocalService StartAppiumServer(String IpAddress,int Port)
	{
		service =new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\vchafle\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(IpAddress).usingPort(Port).build();
		service.start();
		return service;
	}
	
	
	
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}
}
