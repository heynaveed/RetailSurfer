package uk.co.flowet.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Browser extends BrowserAssertions {
    Page load(String url);
    WebDriver getDriver();
    WebElement findElementWithoutWaitingForInteractivity(By by);
    List<WebElement> findElements(By by);
    void shutdown();

    default WebElement findElement(By by) {
        waitUntil(() -> findElementWithoutWaitingForInteractivity(by).isDisplayed());

        return findElementWithoutWaitingForInteractivity(by);
    }
}
