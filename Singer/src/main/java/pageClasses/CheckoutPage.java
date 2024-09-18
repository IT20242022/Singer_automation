package pageClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class CheckoutPage extends PageBaseClass {

	public CheckoutPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//*[@id=\"edit-address-information-profile-nic-number\"]")
	public WebElement Nic;
	
	@FindBy(xpath="//input[contains(@class, 'button--primary') and @value='Continue']")
	public WebElement continueee;
	
	public void checkout(String nic) {
	    try {
	        // Enter NIC number
	        Nic.sendKeys(nic);
	        logger.log(Status.PASS, "Entered the NIC");

	        // Wait for the continue button to be clickable
	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        wait.until(ExpectedConditions.elementToBeClickable(continueee));

	        // Scroll the continue button into view
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueee);

	        // Click the continue button using JavaScript to avoid click interception
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueee);

	        logger.log(Status.PASS, "Clicked the continue button");
	    } catch (Exception e) {
	        logger.log(Status.FAIL, "Checkout failed: " + e.getMessage());
	        reportFail(e.getMessage());
	    }
	}


	
}
