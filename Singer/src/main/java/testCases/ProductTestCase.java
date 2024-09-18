package testCases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClasses.BaseTestClass;


import pageClasses.LandingPage;

import utilities.ConstantValue;
import utilities.TestDataProvider;




public class ProductTestCase extends BaseTestClass {
	
	
	
		@Test(dataProvider = "searchproductt")
		public void selectproduct(Hashtable<String, String> testData) {
			logger = report.createTest("Search product");
			
			invokeBrowser("Chrome");
			LandingPage landingPage =openApplication();
			 landingPage.Login(ConstantValue.userName, ConstantValue.password);
			waitForPageLoad();
			landingPage.searchproduct(testData.get("Product"));
			// Step 4: Instantiate FilterPage after searching for the product
		    
			
			
		}
		
		
		


		@DataProvider
		public Object[][] searchproductt(){
			return TestDataProvider.getTestData("Singer.xlsx", "Sheet1", "ProductSearch");
		}
		
	
		
		
	}


