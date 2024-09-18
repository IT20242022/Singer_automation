//package testCases;
//
//import org.testng.annotations.Test;
//import java.util.Hashtable;
//
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import baseClasses.BaseTestClass;
//import pageClasses.RegisterPage;
//
//import pageClasses.LandingPage;
//import utilities.ConstantValue;
//import utilities.TestDataProvider;
//
//public class RegistrationTest extends BaseTestClass {
//
//	
//	
//	LandingPage landingpage;
//
//	@Test(dataProvider = "getCustommerFormData",priority = 1)
//	public void openRegistrationPage(Hashtable<String, String> testData) {
//		logger = report.createTest("Register form : " + testData.get("Comment"));
//
//		invokeBrowser("Chrome");
//		
//		
//		
//		
//		landingpage.fillRegisterForm(testData.get("FullName"), testData.get("Email"), testData.get("PhoneNumber"));
//		waitLoad(3);
//		 
//		//acceptAlert()
//
//	}
//
//	
//
//
//	@DataProvider
//	public Object[][] getCustommerFormData() {
//		return TestDataProvider.getTestData("Singer.xlsx", "Sheet1", "Register");
//	}
//
//	
//	
//	
//}
//
//
//
package testCases;

import org.testng.annotations.Test;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import baseClasses.BaseTestClass;
import pageClasses.LandingPage;
import utilities.TestDataProvider;

public class RegistrationTest extends BaseTestClass {

   
    LandingPage landingpage;

    @Test(dataProvider = "getCustomerFormData", priority = 1)
    public void openRegistrationPage(Hashtable<String, String> testData) {
        logger = report.createTest("Register form : " + testData.get("FullName"));

        // Step 1: Invoke the browser
        invokeBrowser("Chrome");

        // Step 2: Open the application and initialize the landing page
        landingpage = openApplication();

        

        // Step 4: Initialize the registerPage object
//        registerPage = new RegisterPage(driver, logger);

        // Step 5: Fill the registration form
        landingpage.fillRegisterForm(testData.get("FullName"), testData.get("Email"), testData.get("PhoneNumber"));

        // Step 6: Wait for the alert or next action (optional)
        waitLoad(3);

        // Step 7: Close the browser session (optional)
        driver.quit();
    }

    @DataProvider
    public Object[][] getCustomerFormData() {
        return TestDataProvider.getTestData("Singer.xlsx", "Sheet1", "Register");
    }
}
