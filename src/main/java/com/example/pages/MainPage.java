package com.example.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * Class that encapsulates initial web and navigations
 *
 */
public class MainPage {

	private ChromeDriver driver;

	private static final String INITIAL_URL = "http://localhost:8080/";

	public MainPage(ChromeDriver thedriver) {
		driver = thedriver;
	}

	public Page goToPage() {
		driver.get(INITIAL_URL);
		return PageFactory.initElements(driver, Page.class);
	}

	


}