package org.rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulshettyacademy.PageObjects.android.CartPage;
import org.rahulshettyacademy.PageObjects.android.FormPage;
import org.rahulshettyacademy.PageObjects.android.ProductCataloge;
import org.rahulshettyacademy.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Ecommerce_tc_4_Hybrid extends BaseTest {
	@Test(dataProvider="GetData",groups={"smoke"})
	public void FillForm(HashMap<String,String> input) throws InterruptedException {

		formPage.SetNameField(input.get("name"));
		formPage.setGender(input.get("Gender"));
		formPage.setCountryName(input.get("Country"));
		formPage.submitForm();

		ProductCataloge productCatlouge = new ProductCataloge(driver);
		productCatlouge.addItemTOCartByIndex(0);
		productCatlouge.addItemTOCartByIndex(0);
		CartPage cartPage = productCatlouge.goToCartPage();
		
		double totalSum = cartPage.getProductSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTremsAndConditions();
		cartPage.submitOrder();

	}
	@BeforeMethod(alwaysRun=true)
	public void preSetup()
	{
		formPage.SetActivity();
	}
	
	@DataProvider
	public Object[][] GetData() throws IOException
	{
		List<HashMap<String, String>> data=getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\rahulshettyacademy\\TestData\\eCommerse.json");
		//return new Object[] [] {{"Vinay Chafle","female","Argentina"},{"Jenny","Female","Argentina"}};
		return new Object[] [] {{data.get(0)},{data.get(1)}};
	}

}
