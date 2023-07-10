package org.rahulshettyacademy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumUtils {
	AppiumDriverLocalService service;
	
	public double getFormattedString(String amountString)
	{
		double price=Double.parseDouble(amountString.substring(1));
		return price;
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException
	{
		//System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\TestData\\eCommerse.json"
		String jsonContent= FileUtils.readFileToString(new File (jsonFilePath),StandardCharsets.UTF_8);
		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		
		
	}
	public void waitForElementToAppear(AppiumDriver driver)
	{
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.attributeContains(
	driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
	}
	
	public String getScreenshotPath(String testCaseName,AppiumDriver driver) throws IOException
	{
		File source=driver.getScreenshotAs(OutputType.FILE);
		String destinationFile=System.getProperty("user.dir")+"\\reports"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}
}
