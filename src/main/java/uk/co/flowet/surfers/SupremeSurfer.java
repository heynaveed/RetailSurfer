package uk.co.flowet.surfers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.flowet.actions.Click;
import uk.co.flowet.actions.Go;
import uk.co.flowet.actions.Change;
import uk.co.flowet.payments.SupremePayment;
import uk.co.flowet.tools.WaitFor;

import java.util.*;

import static uk.co.flowet.RetailResale.*;
import static uk.co.flowet.brands.Brand.SUPREME;
import static uk.co.flowet.domains.Button.Supreme.*;
import static uk.co.flowet.domains.Category.Supreme.*;
import static uk.co.flowet.domains.Item.Supreme.*;
import static uk.co.flowet.domains.Size.Supreme.*;

public class SupremeSurfer extends Surfer {

    private final String[] args;
    private final Map<String, Integer> itemLinks;
    private String targetItemLink;
    private boolean shouldCheckForColour = false;

    SupremeSurfer(String[] args){
        this.args = args;
        LOGGER.info("Supreme Surfer instantiated!");
        this.brand = SUPREME;
        itemLinks = new HashMap<>();
        startURL = brand.URL() + "shop";
    }

    @Override
    protected void surf(String[] args) {
        LOGGER.info("Now surfing Supreme...");

        final List<String> items = Arrays.asList(args).subList(2, args.length);
        final String[] firstItem = items.get(0).split("@");

        boolean hasStarted = false;
        int refreshCount = 0;

        do{
            Go.to(brand.URL() + endpoint(firstItem[CATEGORY.index()])).now();
            Document doc = Jsoup.parse(BROWSER.getDriver().getPageSource());
            Elements divs = returnElements(doc, "a[class]");

            if(divs.stream().anyMatch(title -> title.text().toLowerCase()
                            .equals(firstItem[TITLE.index()].toLowerCase()))){
                LOGGER.info("Release has dropped! GO! GO! GO!");
                hasStarted = true;
            }
            else{
                if(refreshCount < 50){
                    refreshCount++;
                    WaitFor.around(3000).milliseconds();
                }
                else {
                    LOGGER.info("Could not find item.");
                    System.exit(0);
                }
            }

        } while(!hasStarted);

        for (int i = 0; i < items.size(); i++) {
            LOGGER.info("Searching for item: " + items.get(i));
            shouldCheckForColour = false;
            final String[] item = items.get(i).split("@");
            String link = null;

            if(i != 0) {
                Go.to(brand.URL() + endpoint(item[CATEGORY.index()])).now();
            }

            Document doc = Jsoup.parse(BROWSER.getDriver().getPageSource());
            Elements divs = returnElements(doc, "a[class]");

            for (Element div : divs) {
                final String divText = div.text().toLowerCase();

                if (checkForItem(item, divText)) {
                    link = div.attr("href");

                    if(!shouldCheckForColour) break;

                    itemLinks.merge(link, 1, (a, b) -> a + b);
                }
            }

            if(shouldCheckForColour) {
                itemLinks.forEach((key, value) -> {
                    if (value > 1) {
                        targetItemLink = brand.URL() + key;
                    }
                });
            }
            else{
                targetItemLink = brand.URL() + link;
            }

            Go.to(targetItemLink).now();

            if(!item[SIZE.index()].equals("none")) {
                Change.dropdown("size").to(MEDIUM.size()).now();
            }

            Click.the(ADD_TO_BASKET.button()).now();
            itemLinks.clear();
        }

        WaitFor.exactly(260).milliseconds();
        Go.to(SUPREME.URL() + CHECKOUT.category().endpoint()).now();
        new SupremePayment().fillForm(BROWSER);
        WaitFor.exactly(260).milliseconds();
        Click.the(PROCESS_PAYMENT.button()).now();
        WaitFor.exactly(60000).milliseconds();

        BROWSER.shutdown();
        TIMER.cancel();
    }

    @Override
    public void run() {
        surf(args);
    }

    private boolean checkForItem(String[] item, String target){
        if(!item[COLOR.index()].equals("none")){
            shouldCheckForColour = true;
        }

        if(shouldCheckForColour){
            if (target.equals(item[TITLE.index()].toLowerCase())
                    || target.equals(item[COLOR.index()].toLowerCase())) {
                return true;
            }
        }
        else if(target.equals(item[TITLE.index()].toLowerCase())){
            return true;
        }

        return false;
    }
}
