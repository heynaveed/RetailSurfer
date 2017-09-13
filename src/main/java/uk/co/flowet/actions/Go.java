package uk.co.flowet.actions;

import static uk.co.flowet.RetailSurfer.BROWSER;
import static uk.co.flowet.RetailSurfer.LOGGER;

public class Go implements Action {

    private String url;

    private Go(String url) {
        this.url = url;
    }

    public static Go to(String url) {
        LOGGER.info("Going to url: " + url);
        return new Go(url);
    }

    public void now() {
        BROWSER.load(url);
    }
}
