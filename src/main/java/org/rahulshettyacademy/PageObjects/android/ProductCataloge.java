package org.rahulshettyacademy.PageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCataloge extends AndroidActions{
	AndroidDriver driver;
	//driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	public List<WebElement> addToCart;
	
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cart;
	
	public ProductCataloge(AndroidDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public void addItemTOCartByIndex(int index)
	{
		addToCart.get(index).click();
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		cart.click();
		Thread.sleep(2000);
		return new CartPage(driver);
	}
}
