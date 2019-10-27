package WaferProcess;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Main.ChromeWebDriver;
import Utils.Utils;

public class CreateWafer {

	
	
	public static String baseUrl = "https://www.amazon.in";
	public ChromeWebDriver cwd = ChromeWebDriver.getInstance();
	public WebDriver driver = cwd.s;

	Utils u = new Utils();
	String[][] data;
	String[][] xpathData;
	String TestName;
	String input;
	String url;

	// Constructor
	public CreateWafer() throws FileNotFoundException, IOException {

		data = u.ReadExcel("Excel\\WebPageSearch.xlsx", "Employee Data");
		xpathData = u.ReadExcel("Excel\\Xpath\\WebPageSearch_xpath.xlsx", "Employee Data");
	}

	@BeforeTest
	public void SetBaseURL() {
		// driver = new ChromeDriver();
		// driver.get(baseUrl);
	}

	@AfterTest
	public void Close() {
		// driver.close();
	}

	@Test
	public void abc() {

		for (int row = 1; row < data.length; row++) {

			TestName = data[row][0];
			input = data[row][1];
			url = data[row][2];

			if (TestName.equals("InputSearchItem")) {
				InputSearchItem();
			}

			if (TestName.equals("SearchMobile")) {
				SearchMobile();
			}
		}
	}

	public void InputSearchItem() {
		String TestName = "InputSearchItem";
		driver.get(url);
		Assert.assertEquals(driver.getTitle(),
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		String searchText = u.GetXpath(TestName, "SearchText", xpathData);
		driver.findElement(By.xpath(searchText)).sendKeys(input);
		driver.findElement(By.xpath(u.GetXpath(TestName, "searchBtn", xpathData))).click();
	}

	public void SearchMobile() {
		String TestName = "SearchMobile";
		driver.get(url);
		driver.findElement(By.xpath(u.GetXpath(TestName, "SearchText", xpathData))).sendKeys(input);
		driver.findElement(By.xpath(u.GetXpath(TestName, "searchBtn", xpathData))).sendKeys(Keys.ENTER);
	}
	
	
}
