package org.rahulshettyacademy.PageObjects.android;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	public FormPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Isabell Clark");
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOptions;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countrySelection;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	
	
	public void SetNameField(String name)
	{
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("female"))
		{
			femaleOption.click();
		}
		else maleOptions.click();
	}
	
	public void setCountryName(String CountryName)
	{
		countrySelection.click();
		scrollTOtext(CountryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+CountryName+"']")).click();
	}
	
	public void SetActivity()
	{
			
			Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
			
			driver.startActivity(activity);
		
	}


	public void submitForm()
	{
		shopButton.click();
	}

	
	
}
