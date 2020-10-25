package com.example.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.Step;

public class Page {

	private static Logger logger = LoggerFactory.getLogger(Page.class);
	private WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[1]/div/img")
	private WebElement startButton;

	@FindBy(css = ".fWUGoP")
	private WebElement carousel;

	public Page(WebDriver theDriver) {
		logger.debug("Instantiating BotanicPage");
		this.driver = theDriver;
		wait = new WebDriverWait(driver, 15);
	}

	@Step("Open bot dialog")
	public void openBotDialog() {
		logger.info("Starting bot...");
		wait.until(ExpectedConditions.elementToBeClickable(startButton));
		startButton.click();
	}

	public void waitForText(String text) {
		new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElement(startButton, text));

	}

	@Step("Get the first image of the carousel")
	public String getFirstImageOfCarousel() {
		wait.until(ExpectedConditions.elementToBeClickable(carousel));
		return carousel.getAttribute("src");
	}

	public String getLink() {
		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		return allLinks.get(0).getAttribute("href");

	}

}
