package com.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.pages.MainPage;
import com.example.pages.Page;
import com.selenium.DriverManager;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Epic("Epic example")
@Feature("SmokeTest")
public class SmokeTest {

	@Test
	@Description("example text is found")
	public void exampleTextIsFound() throws Exception {
		ChromeDriver driver = DriverManager.getDriver();
		try {
			MainPage mainPage = new MainPage(driver);
			Page page = mainPage.goToPage();
			String textFound = page.getText();

			assertThat("Text sent is not found!", textFound, containsString("Example"));

		} finally {
			takeScreenshotForAllure("TextSentIsShown", driver);
		}

	}

	@AfterEach
	public void killDriver() {
		DriverManager.dismissDriver();
	}

	// Take a screenshot for allure report
	@Attachment(value = "{testName} - screenshot", type = "image/png")
	private byte[] takeScreenshotForAllure(String testName, ChromeDriver driver) {
		return (driver).getScreenshotAs(OutputType.BYTES);
	}

}
