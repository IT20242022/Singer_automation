package baseClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import pageClasses.LandingPage;
import utilities.ExtentReportManager;

public class BaseTestClass {

	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	

	/************************ Invoke Browser ****************/
	
	public void invokeBrowser(String browserName) {
		try {

			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
				
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("FireFox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "\\Drivers\\operadriver.exe");
				driver = new FirefoxDriver();
			} else {
				driver = new EdgeDriver();

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void flushReport() {

		report.flush();

		//driver.quit(); // Use quit() instead of close() for closing the browser and ending the
						// WebDriver session

	}

	/***************** Select Date From Calendar *****************/
	public void selectDateIncalendar(String date, WebElement dateTextBox) {

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date expectedDate = dateFormat.parse(date);

			String day = new SimpleDateFormat("d").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;

			while (true) {
				String displayDate = driver.findElement(By.className("datepicker-switch")).getText();

				if (expectedMonthYear.equals(displayDate)) {

					driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr/td[text()='"+day+"']")).click();

					break;
				} else if (expectedDate.compareTo(currentDate) > 0) {
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[3]")).click();
				} else {
					driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[2]/th[1]")).click();
				}

			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/***************** Wait Functions in Framework *****************/
	/****** PageLoad wait *****/
	public void waitForPageLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		while (i != 180) {
			String pageState = (String) js.executeScript("return document.readyState;");
			if (pageState.equals("complete")) {
				break;
			} else {
				waitLoad(1);
			}
		}

		waitLoad(2);

		i = 0;
		while (i != 180) {
			Boolean jsState = (Boolean) js.executeScript("return window.jQuery != undefined && jQuery.active == 0;");
			if (jsState) {
				break;
			} else {
				waitLoad(1);
			}
		}
	}

	/****** Thread sleep *****/
	public void waitLoad(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LandingPage openApplication() {
        logger = report.createTest("Open Application Test");
        logger.log(Status.INFO, "Opening the Website");
        driver.get(" https://www.singersl.com/");
        logger.log(Status.PASS, "Successfully opened https://www.singersl.com/");
        return new LandingPage(driver, logger);
    }
}
