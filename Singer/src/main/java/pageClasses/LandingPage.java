package pageClasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import baseClasses.PageBaseClass;

public class LandingPage extends PageBaseClass {

	public LandingPage(WebDriver driver, ExtentTest logger) {
        super(driver, logger);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id='block-singer-account-menu']/ul/li[2]/a")
    public WebElement login_button;

    @FindBy(xpath = "//*[@id='first_name']")
    public WebElement full_namee;

    @FindBy(xpath = "//*[@id='email_change']")
    public WebElement Email;

    @FindBy(xpath = "//*[@id='mobile']")
    public WebElement mobile_number;

    @FindBy(xpath = "//*[@id='edit-submit-2']")
    public WebElement create_account;
    
    @FindBy(xpath="//*[@id=\"email\"]")
    public WebElement L_email;
    
    @FindBy(xpath="//*[@id=\"password\"]")
    public WebElement password;
    
    @FindBy(xpath="//*[@id=\"edit-search\"]")
    public WebElement p_search;

    @FindBy(xpath="//*[@id=\"login-submit\"]")
    public WebElement login_btn;
    
    @FindBy(xpath="//*[@id=\"block-singer-shopping-cart\"]/div/div/div/a/span[1]")
    public WebElement cart;
    
    public void fillRegisterForm(String fullname, String email, String mobilenumber) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Explicit wait

        try {
            // Wait for the login button to be visible and then click it
            wait.until(ExpectedConditions.visibilityOf(login_button));
            login_button.click();
            logger.log(Status.PASS, "Clicked the login Button");

            // Wait for the registration form elements to be visible
            wait.until(ExpectedConditions.visibilityOf(full_namee));
            full_namee.sendKeys(fullname);
            logger.log(Status.PASS, "Entered Full Name: " + fullname);

            wait.until(ExpectedConditions.visibilityOf(Email));
            Email.sendKeys(email);
            logger.log(Status.PASS, "Entered Email: " + email);

            wait.until(ExpectedConditions.visibilityOf(mobile_number));
            mobile_number.sendKeys(mobilenumber);
            logger.log(Status.PASS, "Entered Mobile Number: " + mobilenumber);

            wait.until(ExpectedConditions.elementToBeClickable(create_account));
            create_account.click();
            logger.log(Status.PASS, "Clicked the Create Account button");

        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred while filling the registration form: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void Login(String emaill,String Password) {
    	WebDriverWait wait = new WebDriverWait(driver, 10); // Explicit wait

        try {
            // Wait for the login button to be visible and then click it
            wait.until(ExpectedConditions.visibilityOf(login_button));
            login_button.click();
            logger.log(Status.PASS, "Clicked the login Button");

            // Wait for the registration form elements to be visible
            wait.until(ExpectedConditions.visibilityOf(L_email));
            L_email.sendKeys(emaill);
            logger.log(Status.PASS, "Entered email: " + emaill);

            wait.until(ExpectedConditions.visibilityOf(password));
            password.sendKeys(Password);
            logger.log(Status.PASS, "Entered password: " + Password);

           

            wait.until(ExpectedConditions.elementToBeClickable(login_btn));
            login_btn.click();
            logger.log(Status.PASS, "Clicked the Create Account button");

        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred while filling the registration form: " + e.getMessage());
            e.printStackTrace();
        }
    }
    	
    
    
    
    
    public void searchproduct(String pname) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Explicit wait

        try {
            // Wait for the login button to be visible and then click it
            wait.until(ExpectedConditions.visibilityOf(p_search));
            p_search.sendKeys(pname);
            logger.log(Status.PASS, "Clicked the login Button");
            p_search.sendKeys(Keys.ENTER);
            

        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred while filling the registration form: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public CartPage placeorder() {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Explicit wait

        try {
            // Wait for the login button to be visible and then click it
            wait.until(ExpectedConditions.visibilityOf(cart));
            cart.click();
            logger.log(Status.PASS, "Clicked the cart Button");
            
            

        } catch (Exception e) {
            logger.log(Status.FAIL, "An error occurred while filling the registration form: " + e.getMessage());
            e.printStackTrace();
        }
        CartPage cartPage = new CartPage(driver, logger);
		PageFactory.initElements(driver, cartPage);
		return cartPage;
    }
}
