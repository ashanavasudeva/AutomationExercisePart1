package basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;



public class AutomationExercisePart1 {
	
	private static final Logger logger = Logger.getLogger(AutomationExercisePart1.class);
	WebDriver driver;
	String URL = "http://store.demoqa.com";
	
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	/*Author: Ashana Vasudeva
	 * Created Date: 4/30/2016
	 * Function:Search for Apple iphone 4S Sim free black color, Add to cart and purchase.Verification is done that the total
	 * cost is the sum of the calculated shipping cost for USA and item cost
	 * Assumptions: Country selected for shipping is United States. 
	 * Assertions: Verify phone added in cart,quantity is 1,verify item cost, shipping cost and total price, transaction is completed message
	 */
   @Test
  public void submitIphoneOrder(){
		
		logger.info(" Step 1: Search for Apple iphone 4s 16 GB");
		//driver.findElement(By.cssSelector(".search")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".search")).sendKeys("Apple iphone 4s");
		driver.findElement(By.cssSelector(".search")).submit();
		
		logger.info("Step 2: Click on the Apple iphone 4S 16GB Sim-Free - Black");
		driver.findElement(By.cssSelector(".prodtitle>a[title='Apple iPhone 4S 16GB SIM-Free – Black']")).click();
		
		logger.info("Step 3: Click Add to Cart and in the popup click Go to checkout");
		driver.findElement(By.cssSelector(".wpsc_buy_button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".go_to_checkout")).click();
		
		logger.info("Step 4: Verify that phone is added and quantity is 1");
		String iphoneName = driver.findElement(By.cssSelector(".wpsc_product_name.wpsc_product_name_0>a")).getText();
		Assert.assertEquals("Apple iPhone 4S 16GB SIM-Free - Black", iphoneName);
		String qty = driver.findElement(By.cssSelector(".adjustform.qty>input[name='quantity']")).getAttribute("value");
		AssertJUnit.assertTrue(qty.equals("1"));;
		driver.findElement(By.cssSelector(".step2>span")).click();
		
		logger.info("Step 5: Calculate shipping costs");
		driver.findElement(By.cssSelector("#current_country option[value='US']")).click();
		driver.findElement(By.cssSelector("#change_country>input[type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		logger.info("Step 6: Verify the item cost is $270, Shipping is $12 and Total Price is $282");
		String itemCost= driver.findElement(By.cssSelector(".total_item .checkout-shipping .pricedisplay")).getText();
		Assert.assertEquals("$270.00", itemCost);
		String itemShipping = driver.findElement(By.cssSelector(".total_shipping .checkout-shipping .pricedisplay")).getText();
		Assert.assertEquals("$12.00", itemShipping);
		
		String itemCostNumber = itemCost.substring(1, 7);
		String itemShippingNumber = itemShipping.substring(1,6);
		
		float itemCostFinal = Float.parseFloat(itemCostNumber);
		float itemShippingFinal = Float.parseFloat(itemShippingNumber);
		String itemTotal = driver.findElement(By.cssSelector("#checkout_total .pricedisplay")).getText();
		String itemTotalNumber = itemTotal.substring(1, 7);
		Float itemTotalFinal = Float.parseFloat(itemTotalNumber);
		Assert.assertTrue(itemCostFinal + itemShippingFinal == itemTotalFinal);
		//System.out.println("total" + itemTotalFinal);
		
		
		logger.info("Step 7: Fill in the billing details and click Purchase");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_9")).sendKeys("testuser@gmail.com");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_2")).sendKeys("Andy");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_3")).sendKeys("Lee");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_4")).sendKeys("1234 W Washington Ave");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_5")).sendKeys("Chicago");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_6")).sendKeys("IL");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_7 option[value='US']")).click();
		driver.findElement(By.cssSelector("#wpsc_checkout_form_8")).sendKeys("60603");
		driver.findElement(By.cssSelector("#wpsc_checkout_form_18")).sendKeys("98765432");
		driver.findElement(By.id("shippingSameBilling")).click();
		driver.findElement(By.cssSelector(".make_purchase.wpsc_buy_button")).click();
		
		logger.info("Step 8: Verify that the transaction is done");
		String result =driver.findElement(By.cssSelector(".wpsc-transaction-results-wrap")).getText();
		Assert.assertTrue(result.contains("Thank you, your purchase is pending. You will be sent an email once the order clears."));
		driver.close();
	}
   
   /*Author: Ashana Vasudeva
	 * Created Date: 4/30/2016
	 * Function:Login into My account (username-ashana and password-hello), edit profile: Color = Light, first name=ash, 
	 * last name = va, description = I am smart
	 * Logout and login again and verify the updated profile
	 * Assumptions: -
	 * Assertions: Logged in success, message for profile updation,logout success,the profile is updated (color is light,first name
	 * is ash, last name is va, description is I am smart
	 */
  
  @Test
  public void updateUserProfile(){
		 
	    Actions action = new Actions(driver);
	    String firstName = "ash";
	    String lastName = "va";
	    String description = "I am smart";
	  
	    logger.info("Step 1: Click My Account link and login into my account and verify that login is successful");
	    driver.findElement(By.cssSelector(".account_icon")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.id("log")).sendKeys("ashana");
	    driver.findElement(By.id("pwd")).sendKeys("hello");
	    driver.findElement(By.id("login")).click();
	    Assert.assertTrue(driver.findElement(By.id("wp-admin-bar-my-account")).getText().contains("ashana"));
	    
	    logger.info("Step 2: Click Edit Profile");
	    action.moveToElement(driver.findElement(By.id("wp-admin-bar-my-account"))).build().perform();
	    driver.findElement(By.id("wp-admin-bar-edit-profile")).click();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    logger.info("Step 3: Change the color theme to light,first name = ash, last name=va, description=I am smart.Assert that profile gets updated ");
	    driver.findElement(By.id("admin_color_light")).click();
	    driver.findElement(By.id("first_name")).clear();
	    driver.findElement(By.id("last_name")).clear();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("first_name")).sendKeys(firstName);
	    driver.findElement(By.id("last_name")).sendKeys(lastName);
	    driver.findElement(By.id("description")).sendKeys(description);
	    driver.findElement(By.id("submit")).click();
	    Assert.assertTrue(driver.findElement(By.cssSelector("#message>p>strong")).getText().contains("Profile updated."));
	    
	    logger.info("Step 4: Logout and login again and verify the updations made in step 3");
	    action.moveToElement(driver.findElement(By.id("wp-admin-bar-my-account"))).build().perform();
	    driver.findElement(By.id("wp-admin-bar-logout")).click();
	    Assert.assertTrue(driver.findElement(By.cssSelector(".message")).getText().contains("You are now logged out."));
	    driver.findElement(By.id("user_login")).sendKeys("ashana");
	    driver.findElement(By.id("user_pass")).sendKeys("hello");
	    driver.findElement(By.id("wp-submit")).click();
	    
	   Assert.assertFalse(( driver.findElement(By.id("admin_color_light")).getAttribute("checked").isEmpty()));
	   Assert.assertTrue(driver.findElement(By.id("first_name")).getAttribute("value").equals(firstName));
	   Assert.assertTrue(driver.findElement(By.id("last_name")).getAttribute("value").equals(lastName));
	   Assert.assertTrue(driver.findElement(By.id("description")).getAttribute("value").equals(description));
	   driver.close();
}
  
  /*Author: Ashana Vasudeva
	 * Created Date: 4/30/2016
	 * Function: Choose Accessories from Product Categroy and click Magic Mouse to add to cart, click checkout and from cart 
	 * Remove Magic Mouse and verify empty cart message
	 * Assumptions: -
	 * Assertions: Magic Cart is added to Cart, Empty cart message appears 
	 */
  @Test
  public void verifyEmptyCart(){
		 
		 Actions action = new Actions(driver);
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 
		 logger.info("Step 1: Add Magic Mouse to the cart by clicking Product Category -> Accessories");
		 action.moveToElement(driver.findElement(By.cssSelector("a[href*='product-category']"))).build().perform();
		 driver.findElement(By.cssSelector("a[href*='accessories'")).click();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector(".product_view_40 .wpsc_buy_button")).click(); // Clicking the Add to Cart for the first item in Accessories
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector(".go_to_checkout")).click();
		 //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".entry-title")));
		 
		 logger.info("Step 2: Verify that there are 2 items in the cart by checking that there are 2 products");
		 String item1 = driver.findElement(By.cssSelector(".wpsc_product_name.wpsc_product_name_0")).getText();
		 Assert.assertTrue(item1.contains("Magic Mouse")); // asserting that the product added in cart is Magic mouse
		 driver.findElement(By.cssSelector(".adjustform.remove>input[type='submit']")).click();
		 String message = driver.findElement(By.cssSelector(".entry-content")).getText();
		 Assert.assertEquals(message, "Oops, there is nothing in your cart.", "Empty cart message does not appear as expected");
		 
	}
  
  @AfterClass
	public void tearDown() {
		driver.quit();
	}
  
}
