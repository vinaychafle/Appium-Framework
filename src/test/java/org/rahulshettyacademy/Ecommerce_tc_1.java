package org.rahulshettyacademy;

import java.io.IOException;

import org.openqa.selenium.By;
import org.rahulshettyacademy.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Ecommerce_tc_1 extends BaseTest {
	AndroidDriver driver;
	@BeforeMethod
	public void preSetup() throws IOException
	{
		driver=configureAppium();
//		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//		
//		driver.startActivity(activity);
	}
	@Test
	public void FillForm() throws InterruptedException
	{
Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		
		//((AndroidDriver)driver).startActivity(activity);
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Isabell Clark");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Argentina\").instance(0))"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String text=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(text, "Please enter your name");
		
	}
	
	@Test
	public void FillFormwithNoError() throws InterruptedException
	{
		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		
		driver.startActivity(activity);
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Isabell Clark");
		driver.hideKeyboard();
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Argentina\").instance(0))"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		//String text=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		//Assert.assertEquals(text, "Please enter your name");
		
	}

}
