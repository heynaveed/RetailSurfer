package uk.co.flowet.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import uk.co.flowet.browser.Browser;
import uk.co.flowet.tools.Text;

import java.util.LinkedList;

import static uk.co.flowet.RetailResale.*;
import static uk.co.flowet.domains.Actor.NAVEED;
import static uk.co.flowet.domains.Button.Supreme.*;
import static uk.co.flowet.payments.PaymentField.*;

public class SupremePayment implements Payment{

    @Override
    public void fillForm(Browser browser) {
        LOGGER.info("Filling in Supreme payment form...");
        final LinkedList<String> details = new Text().toList(NAVEED.path());

        for(Supreme field: Supreme.values()){

            switch(field.elementType()){
                case FIELD:
                    browser.getDriver().findElement(By.id(field.id())).clear();
                    browser.getDriver().findElement(By.id(field.id())).sendKeys(details.getFirst());
                    details.removeFirst();
                    break;
                case DROPDOWN:
                    new Select(browser.findElement(By.id(field.id()))).selectByVisibleText(details.getFirst());
                    details.removeFirst();
                    break;
                case CHECKBOX:
                    browser.getDriver().findElement(By.xpath(TERMS.button().xPath())).click();
                    break;
            }
        }
    }
}
