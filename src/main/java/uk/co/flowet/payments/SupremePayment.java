package uk.co.flowet.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import uk.co.flowet.domains.Actor;
import uk.co.flowet.domains.PaymentField;
import uk.co.flowet.tools.Text;

import java.util.LinkedList;

import static uk.co.flowet.RetailSurfer.BROWSER;
import static uk.co.flowet.RetailSurfer.LOGGER;
import static uk.co.flowet.domains.Button.Supreme.TERMS;

public class SupremePayment implements Payment{

    @Override
    public void fillForm(Actor actor) {
        LOGGER.info("Filling in Supreme payment form...");
        final LinkedList<String> details = new Text().toList(actor.path());

        for(PaymentField.Supreme field: PaymentField.Supreme.values()){

            switch(field.elementType()){
                case FIELD:
                    BROWSER.getDriver().findElement(By.id(field.id())).clear();
                    BROWSER.getDriver().findElement(By.id(field.id())).sendKeys(details.getFirst());
                    details.removeFirst();
                    break;
                case DROPDOWN:
                    new Select(BROWSER.findElement(By.id(field.id()))).selectByVisibleText(details.getFirst());
                    details.removeFirst();
                    break;
                case CHECKBOX:
                    BROWSER.getDriver().findElement(By.xpath(TERMS.button().xPath())).click();
                    break;
            }
        }
    }
}
