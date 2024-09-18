package testCases;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClasses.BaseTestClass;
import pageClasses.CartPage;
import pageClasses.CheckoutPage;
import pageClasses.LandingPage;
import utilities.ConstantValue;
import utilities.TestDataProvider;

public class PlaceOrderTest extends BaseTestClass {

	CartPage cartpage;
	CheckoutPage Checkoutpage;

	@Test(dataProvider = "order")
	public void order(Hashtable<String, String> testData) {
		logger = report.createTest("Search product");

		invokeBrowser("Chrome");
		LandingPage landingPage = openApplication();
		landingPage.Login(ConstantValue.userName, ConstantValue.password);
		waitForPageLoad();

		landingPage.placeorder();
		CartPage cartpage = new CartPage(driver, logger);
		Checkoutpage = cartpage.placeorderrr();
		Checkoutpage.checkout(testData.get("NIC"));

	}

	@DataProvider
	public Object[][] order() {
		return TestDataProvider.getTestData("Singer.xlsx", "Sheet1", "Checkout");
	}

}
