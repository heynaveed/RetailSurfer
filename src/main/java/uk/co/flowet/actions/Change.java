package uk.co.flowet.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import uk.co.flowet.domains.Size;

import java.util.Random;

import static uk.co.flowet.RetailSurfer.BROWSER;
import static uk.co.flowet.RetailSurfer.LOGGER;

public class Change implements Action {

    private String div;
    private Size size;

    private Change(String div) {
        this.div = div;
    }

    public static Change dropdown(String div) {
        return new Change(div);
    }

    public Change to(Size size){
        this.size = size;
        return this;
    }

    public void now() {
        String sizeS = size.size();
        WebElement dropdown = BROWSER.findElement(By.id(div));
        String sizeString = dropdown.getText();

        if (sizeString.contains(sizeS)) {
            new Select(dropdown).selectByVisibleText(sizeS);
        } else {
            String[] sizes = sizeString.split("\n");
            sizeS = sizes[new Random().nextInt(sizes.length)];
            new Select(dropdown).selectByVisibleText(sizeS);
        }
        LOGGER.info("Selecting " + div + " dropdown for size: " + sizeS);
    }
}


