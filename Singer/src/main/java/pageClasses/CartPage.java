package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClass;

public class CartPage extends PageBaseClass {

    @FindBy(xpath="//*[@id=\"place-order\"]")
    public WebElement order_button;

    public CartPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage placeorderrr() {
        try {
            // Wait for the button to be enabled
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(order_button));

            if (order_button.isEnabled()) {
                order_button.click();
                logger.log(Status.PASS, "Clicked the place order button");
                waitForPageLoad();
            } else {
                logger.log(Status.FAIL, "Order button is disabled");
                reportFail("Order button is disabled");
            }
        } catch (Exception e) {
            reportFail(e.getMessage());
        }

        CheckoutPage checkPage = new CheckoutPage(driver, logger);
        PageFactory.initElements(driver, checkPage);
        return checkPage;
    }
}


