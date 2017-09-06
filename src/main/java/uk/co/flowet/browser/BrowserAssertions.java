package uk.co.flowet.browser;

import junit.framework.AssertionFailedError;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Supplier;

public interface BrowserAssertions {
    WebDriver getDriver();
    default void waitUntil(Supplier<Boolean> condition) {
        try {
            new WebDriverWait(getDriver(), 25).until((com.google.common.base.Predicate<WebDriver>) webDriver -> condition.get());
        } catch (TimeoutException e) {
            throw new AssertionFailedError("Timeout waiting");
        }
    }
}
