package uk.co.flowet.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static uk.co.flowet.RetailResale.LOGGER;

public class ChromeBrowser implements Browser {

    static {
        String chromeDriverPath = System.getenv("CHROME_DRIVER") != null
                ? "/usr/local/bin/chromedriver"
                : "src/main/resources/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    private final ChromeDriver driver;

    public ChromeBrowser(String startURL) {
        LOGGER.info("Launching Chrome Browser...");
        String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36";
        ChromeOptions co = new ChromeOptions();
        co.addArguments("--user-agent=" + userAgent, "--start-maximized");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, co);

        this.driver = new ChromeDriver(cap);
        driver.get(startURL);
        LOGGER.info("Going to start URL: " + startURL);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    @Override
    public Page load(String url) {
        this.driver.get(url);
        return () -> url;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public WebElement findElementWithoutWaitingForInteractivity(By by) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);

        webDriverWait.until((com.google.common.base.Predicate<WebDriver>) webDriver -> {
            try {
                WebElement element = webDriver.findElement(by);
                return true;
            } catch (WebDriverException e) {
                return false;
            }
        });

        return driver.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public void shutdown() {
        LOGGER.info("Shutting down browser...");
        driver.close();
        driver.quit();
    }
}
