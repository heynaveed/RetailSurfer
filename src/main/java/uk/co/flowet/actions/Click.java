package uk.co.flowet.actions;

import org.openqa.selenium.By;
import uk.co.flowet.RetailResale;
import uk.co.flowet.domains.Button;

import static uk.co.flowet.RetailResale.BROWSER;

public class Click implements Action{

    private Button button;

    private Click(Button button) {
        this.button = button;
    }

    public static Click the(Button button) {
        return new Click(button);
    }

    @Override
    public void now() {
        RetailResale.LOGGER.info("Clicking " + button.name() + " button...");
        BROWSER.getDriver().findElement(By.xpath(button.xPath())).click();
    }
}
