package baseClasses;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import utilities.DateUtil;

public class PageBaseClass extends BaseTestClass {

	public ExtentTest logger;

	public PageBaseClass(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
	}

	/************************* Open Application ************************/
//	public LandingPage OpenApplication() {
//		logger.log(Status.INFO, "Opening the website");
//		driver.get("https://vetpulz.digitalpulzhealth.com/vetpulz/");
//		logger.log(Status.PASS, "Successfully Opened the https://vetpulz-test.digitalpulzhealth.com/vetpulz/");
//		LandingPage landingPage = new LandingPage(driver, logger);
//		PageFactory.initElements(driver, landingPage);
//		return landingPage;
//	}

	/****************** Get Page Title ***********************/
	public void getTitle(String expectedTitle) {
		try {
			Assert.assertEquals(driver.getTitle(), expectedTitle);
			reportPass("Actual Title : " + driver.getTitle() + " - equals to Expected Title : " + expectedTitle);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	/****************** Accept Java Script Alert ***********************/
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logger.log(Status.PASS, "Page Alert Accepted");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Cancel Java Script Alert ***********************/
	public void cancelAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			;
			logger.log(Status.PASS, "Page Alert Rejected");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Select value From DropDown ***********************/
	public void selectDropDownValue(WebElement webElement, String value) {
		try {
			Select select = new Select(webElement);
			select.selectByVisibleText(value);
			logger.log(Status.PASS, "Selectd the Value : " + value);
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void autoSuggest(WebElement webElement, String value) {
		try {
			webElement.sendKeys(value);
			List<WebElement> autoSuggest = driver.findElements(By.xpath("//*[@role='tree']/li"));
			waitLoad(5);
			autoSuggest.get(1).click(); // Use index 1 to select the second element
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

//	/****************** Get All Elements of DropDown ***********************/
//	public List getAllElementsOfDropDown(WebElement webElement){
//		try {
//			Select select = new Select(webElement);
//			List<WebElement> allElements = select.getOptions();
//			return allElements;
//		} catch (Exception e) {
//			reportFail(e.getMessage());
//		}
//		 return null;
//	}

	/****************** Verify Element is Present ***********************/
	public void veriyElementIsDisplayed(WebElement webElement) {
		try {
			if (webElement.isDisplayed()) {
				reportPass("Password Box is Displayed");

			} else {
				reportFail("Password box is not appeared");
			}

		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}

	/****************** Reporting Functions ***********************/
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	/****************** Capture Screen Shot ***********************/
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		try {
			FileUtils.copyFile(sourceFile, destFile);
			logger.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void handleWindows() {
		Set<String> windowId = driver.getWindowHandles();
		Iterator<String> itr = windowId.iterator();
		String mainpageId = itr.next();
		String currentpageId = itr.next();

		// swithcing to new tab/windows
		driver.switchTo().window(currentpageId);
		String currentwindow = driver.getWindowHandle();
		waitLoad(4);
		driver.close();
		waitLoad(4);
		driver.switchTo().window(mainpageId);
		waitLoad(4);
	}

	public boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed() && element.isEnabled();
		} catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e) {
			return false;
		}
	}

	public List getAllElementsOfDropDown(WebElement webElement) {
		try {
			Select select = new Select(webElement);
			List<WebElement> allElements = select.getOptions();
			return allElements;
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
		return null;
	}

}
