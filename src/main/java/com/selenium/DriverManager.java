package com.selenium;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class that encapsulates Chrome driver. We will only have one instance of it
 * per session.
 * 
 * @author mfidalgosicilia
 *
 */

public class DriverManager {
	private static Logger logger = LoggerFactory.getLogger(DriverManager.class);

	protected static ChromeDriver driver;

	protected DriverManager() {

	}

	public static ChromeDriver createDriver() {
		boolean isActive = driverIsActive();
		logger.debug("is driver active? ---> {}", isActive);

		if (driver != null && isActive) {
			dismissDriver();
		}

		logger.info("Building Chrome driver...");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/chromedriver-86");

		ChromeOptions opts = new ChromeOptions();

		if (insideDocker()) {
			logger.info("Running inside docker. Going Headless");
			opts.addArguments("--headless");
			opts.setHeadless(true);
			System.setProperty("webdriver.chrome.whitelistedIps", "");
		}

		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
		return driver;
	}

	private static boolean insideDocker() {
		try (Stream<String> stream = Files.lines(Paths.get("/proc/1/cgroup"))) {
			return stream.anyMatch(line -> line.contains("/docker"));
		} catch (IOException e) {
			return false;
		}

	}

	public static ChromeDriver getDriver() {
		if (driver == null) {
			driver = createDriver();
		}
		return driver;
	}

	private static boolean driverIsActive() {
		if (driver == null)
			return false;
		String session = driver.getSessionId().toString();
		logger.info("session: {}", session);
		return !session.isEmpty();
	}

	public static void dismissDriver() {
		if (driver != null) {
			logger.info("Dismissing driver...");
			driver.quit();
			driver = null;
		}
	}

}