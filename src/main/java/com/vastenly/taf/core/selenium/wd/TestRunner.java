package com.vastenly.taf.core.selenium.wd;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.vastenly.taf.core.assertions.AssertionsList;
import com.vastenly.taf.core.selenium.browser.BrowserConstants;
import com.vastenly.taf.core.selenium.browser.BrowserType;
import com.vastenly.taf.utils.random.RandomString;

public class TestRunner extends AssertionsList {
	
	protected static Set<WebDriver> driversPool = new HashSet<WebDriver>();
	
	private static ThreadLocal<WebDriver> localDriver;
	protected BrowserType browserType;
	protected WebDriver driver;
	
	public TestRunner() {
		this.driver = localDriver.get();
	}
	
	public TestRunner(BrowserType browserType) {
		
		this.browserType = browserType;
		synchronized (this) {
			
			localDriver = new ThreadLocal<WebDriver>() {
				protected WebDriver initialValue() {
					try {
						return getWebDriver();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			};
			
			this.driver = localDriver.get();
			driversPool.add(driver);
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
		}
	}
	
	private synchronized WebDriver getWebDriver() throws URISyntaxException, IOException {
		switch (browserType) {
		case CHROME_WINDOWS:
			return getChromeDriver(BrowserConstants.CHROME_WINDOWS_PATH);
		case REMOTE_CHROME_LINUX:
			try {
				ChromeOptions options = new ChromeOptions();
				options.setBinary(BrowserConstants.CHROME_LINUX_BINARY_PATH);
				options.addArguments(BrowserConstants.CHROME_IGNORE_FLAG_CMD_ARGS);
				final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				final URL url = new URL(BrowserConstants.REMOTE_DRIVER_URL);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				return new RemoteWebDriver(url,capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		case REMOTE_CHROME_MAC:
			try {
				ChromeOptions options = new ChromeOptions();
				options.setBinary(BrowserConstants.CHROME_MAC_BINARY_PATH);
				options.addArguments(BrowserConstants.CHROME_IGNORE_FLAG_CMD_ARGS);
				final DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				final URL url = new URL(BrowserConstants.REMOTE_DRIVER_URL);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				return new RemoteWebDriver(url,capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		case CHROME_LINUX:
			return getChromeDriver(BrowserConstants.CHROME_LINUX_PATH);
		case CHROME_MAC:
			return getChromeDriver(BrowserConstants.CHROME_MAC_PATH);
		case FIREFOX:
			return new FirefoxDriver();
		case SAFARI:
			return new SafariDriver();
		default:
			return new FirefoxDriver();
		}
	}

	private synchronized WebDriver getChromeDriver(String driverPath) {

		System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);

		ChromeOptions options = new ChromeOptions();
		options.addArguments(BrowserConstants.CHROME_IGNORE_FLAG_CMD_ARGS);

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		if(driverPath.equals(BrowserConstants.CHROME_WINDOWS_PATH)) {
			capabilities.setCapability(BrowserConstants.CHROME_DRIVER_BINARY_PROPERTY, driverPath);
		}  else if (driverPath.equals(BrowserConstants.CHROME_LINUX_PATH)) {
			options.setBinary(BrowserConstants.CHROME_LINUX_BINARY_PATH);
			options.addArguments(BrowserConstants.CHROME_WORK_PROFILE + RandomString.getRandomAlphabetic(12));
			options.addArguments(BrowserConstants.CHROME_PROXY_AUTO_DETECT);
		} else if (driverPath.equals(BrowserConstants.CHROME_MAC_PATH)) {
			options.setBinary(BrowserConstants.CHROME_MAC_BINARY_PATH);
			options.addArguments(BrowserConstants.CHROME_PROXY_AUTO_DETECT);
		}
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		return new ChromeDriver(capabilities);
	}
	
	public synchronized WebDriver getDriver(){
		return driver;
	}
	
	public synchronized void close() {
		this.driver = null;
		localDriver.get().quit();
		localDriver.set(null);
	}
	
	public void closeAll() {
		for (WebDriver driver : driversPool) {
            driver.quit();
        }
	}
}
