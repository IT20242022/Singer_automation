package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import baseClasses.PageBaseClass;

public class ProductsPage extends PageBaseClass {

	public ProductsPage(WebDriver driver, ExtentTest logger) {
		super(driver, logger);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//*[@id=\"block-categories\"]/div/ul[1]/li/label/span")
	public WebElement mobile;
	
	@FindBy(xpath="//*[@id=\"popularity\"]/div/div[1]/div[2]/a/img")
	public WebElement printer;
	
	@FindBy(xpath="//*[@id=\"sub_one\"]/li[2]/label/span")
	public WebElement subprinter;
	
}

