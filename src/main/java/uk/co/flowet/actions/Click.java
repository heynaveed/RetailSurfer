package uk.co.flowet.actions;

import org.openqa.selenium.By;
import uk.co.flowet.RetailSurfer;
import uk.co.flowet.domains.Button;

import static uk.co.flowet.RetailSurfer.BROWSER;

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
        RetailSurfer.LOGGER.info("Clicking " + button.name() + " button...");
        BROWSER.getDriver().findElement(By.xpath(button.xPath())).click();
    }
}
