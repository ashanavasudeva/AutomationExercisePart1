# AutomationExercisePart1

 Pre-requisites to run the code: Eclipse with Selenium Jars and TestNG plugin installed

-	Java class “AutomationExercisePart1.java”. All the 3 tests are in this file.
-	Details of what each test is doing is given below and also in the comments in the code

Test 1:submitIphoneOrder
Function:Search for Apple iphone 4S Sim free black color, Add to cart and purchase.Verification is done that the total
	 * cost is the sum of the calculated shipping cost for USA and item cost
	 * Assumptions: Country selected for shipping is United States. 
	 * Assertions: Verify phone added in cart,quantity is 1,verify item cost, shipping cost and total price, transaction is completed message
	 * 
	 
Test 2:updateUserProfile
 * Function:Login into My account (username-ashana and password-hello), edit profile: Color = Light, first name=ash, 
	 * last name = va, description = I am smart
	 * Logout and login again and verify the updated profile
	 * Assumptions: -
	 * Assertions: Logged in success, message for profile updation,logout success,the profile is updated (color is light,first name
	 * is ash, last name is va, description is I am smart
	 * 
	 
Test 3:verifyEmptyCart
* Function: Choose Accessories from Product Categroy and click Magic Mouse to add to cart, click checkout and from cart 
	 * Remove Magic Mouse and verify empty cart message
	 * Assumptions: -
	 * Assertions: Magic Cart is added to Cart, Empty cart message appears 
