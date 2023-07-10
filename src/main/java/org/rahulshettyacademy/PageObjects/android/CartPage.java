package org.rahulshettyacademy.PageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshettyacademy.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {
	AndroidDriver driver;
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//List<WebElement> ele=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
		private List<WebElement> productList;
		
		//String displayString=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
		private WebElement TotalAmount;
		
		//WebElement terms=driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
		private WebElement terms;
		
		//driver.findElement(By.id("android:id/button1")).click();
		@AndroidFindBy(id="android:id/button1")
		private WebElement acceptButton;
		
		//driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
		@AndroidFindBy(className="android.widget.CheckBox")
		private WebElement checkBox;
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
		private WebElement proceedToCheckout;
		
		public List<WebElement> getProductList()
		{
			return productList;
			
		}
		
		public double getProductSum()
		{
			int count=productList.size();
			double Totalsum=0;
			for(int i=0;i<count;i++)
			{
				String amountString=productList.get(i).getText();
				Double price=getFormattedString(amountString);
				Totalsum=price+Totalsum;
			}
			return Totalsum;
		}
		public Double getTotalAmountDisplayed()
		{
			return getFormattedString(TotalAmount.getText());
			
		}
		public void acceptTremsAndConditions()
		{
			LongPressAction(terms);
			acceptButton.click();
		}
		public Double getFormattedAmount(String amount)
		{
			Double price=Double.parseDouble(amount.substring(1));
			return price;
		}
		public void submitOrder()
		{
			checkBox.click();
			proceedToCheckout.click();
		}
}
