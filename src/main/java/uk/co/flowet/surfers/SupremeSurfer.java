package uk.co.flowet.surfers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import uk.co.flowet.actions.Click;
import uk.co.flowet.actions.Go;
import uk.co.flowet.actions.Change;
import uk.co.flowet.actions.MakePayment;
import uk.co.flowet.domains.Size;
import uk.co.flowet.exceptions.SizeArgumentNotRecognizedException;
import uk.co.flowet.tools.WaitFor;

import java.util.*;

import static uk.co.flowet.RetailResale.*;
import static uk.co.flowet.domains.Actor.TEST;
import static uk.co.flowet.domains.Button.Supreme.*;
import static uk.co.flowet.domains.Category.Supreme.*;
import static uk.co.flowet.domains.Item.*;

public class SupremeSurfer extends Surfer {

    private final Map<String, Integer> itemLinks;
    private String targetItemLink;
    private boolean shouldCheckForColour = false;
    private Document doc;
    private Elements divs;

    SupremeSurfer(){
        LOGGER.info("Supreme Surfer instantiated!");
        itemLinks = new HashMap<>();
        startURL = BRAND.URL() + "shop";
    }

    @Override
    protected void surf() {
        LOGGER.info("Now surfing Supreme...");

        final String[] firstItem = ITEMS.get(0);

        boolean releaseIsLive = false;
        int refreshCount = 0;

        do{
            Go.to(BRAND.URL() + endpoint(firstItem[CATEGORY.index()])).now();
            doc = Jsoup.parse(BROWSER.getDriver().getPageSource());
            divs = returnElements(doc, "a[class]");

            if(divs.stream().anyMatch(title -> title.text().toLowerCase()
                            .equals(firstItem[TITLE.index()].toLowerCase()))){
                LOGGER.info("Release has dropped! GO! GO! GO!");
                releaseIsLive = true;
            }
            else{
                if(refreshCount < 200){
                    refreshCount++;
                    WaitFor.around(3000).milliseconds();
                }
                else {
                    LOGGER.info("Could not find item.");
                    System.exit(0);
                }
            }

        } while(!releaseIsLive);

        for (int i = 0; i < ITEMS.size(); i++) {
            LOGGER.info("Searching for item: " + ITEMS.get(i)[0]);
            shouldCheckForColour = false;
            final String[] item = ITEMS.get(i);
            final String itemSize = item[SIZE.index()];
            String link = null;

            if(i != 0) {
                Go.to(BRAND.URL() + endpoint(item[CATEGORY.index()])).now();
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
                        targetItemLink = BRAND.URL() + key;
                    }
                });
            }
            else{
                targetItemLink = BRAND.URL() + link;
            }

            Go.to(targetItemLink).now();

            if(!itemSize.equals("none")) {
                try {
                    Size.Supreme size = Size.Supreme.selectByArgument(itemSize);
                    Change.dropdown("size").to(size.size()).now();
                } catch (SizeArgumentNotRecognizedException e) {
                    e.printStackTrace();
                }
            }

            Click.the(ADD_TO_BASKET.button()).now();
            itemLinks.clear();
        }

        WaitFor.exactly(260).milliseconds();
        Go.to(BRAND.URL() + CHECKOUT.category().endpoint()).now();
        MakePayment.with(TEST).to(BRAND).now();
        WaitFor.exactly(260).milliseconds();
        Click.the(PROCESS_PAYMENT.button()).now();
        WaitFor.exactly(60000).milliseconds();

        BROWSER.shutdown();
        TIMER.cancel();
    }

    @Override
    public void run() {
        surf();
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
