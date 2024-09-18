package testCases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClasses.BaseTestClass;
import pageClasses.LandingPage;
import utilities.TestDataProvider;

public class LoginTestCase extends BaseTestClass {
	LandingPage landingpage;

	@Test(dataProvider = "getCustomerFormData", priority = 1)
	public void openRegistrationPage(Hashtable<String, String> testData) {
		logger = report.createTest("Login form : ");

		// Step 1: Invoke the browser
		invokeBrowser("Chrome");

		// Step 2: Open the application and initialize the landing page
		landingpage = openApplication();

		landingpage.Login(testData.get("Email"), testData.get("Password"));

		// Step 6: Wait for the alert or next action (optional)
		waitLoad(3);

		// Step 7: Close the browser session (optional)
		driver.quit();
	}

	@DataProvider
	public Object[][] getCustomerFormData() {
		return TestDataProvider.getTestData("Singer.xlsx", "Sheet1", "Login");
	}
}
