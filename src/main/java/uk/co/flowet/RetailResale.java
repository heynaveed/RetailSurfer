package uk.co.flowet;

import uk.co.flowet.domains.Actor;
import uk.co.flowet.domains.Brand;
import uk.co.flowet.browser.ChromeBrowser;
import uk.co.flowet.domains.Item;
import uk.co.flowet.exceptions.ActorArgumentNotRecognizedException;
import uk.co.flowet.exceptions.BrandArgumentNotRecognizedException;
import uk.co.flowet.exceptions.BrandToSurferMismatchException;
import uk.co.flowet.surfers.Surfer;
import uk.co.flowet.surfers.SurferBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.logging.Logger;

import static uk.co.flowet.domains.Argument.*;

public class RetailResale {

    public static Logger LOGGER = Logger.getLogger(RetailResale.class.getName());
    public static final Timer TIMER = new Timer();
    public static ChromeBrowser BROWSER;
    public static List<String[]> ITEMS = new ArrayList<>();
    public static Actor ACTOR;
    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Surfer SURFER;
    public static Brand BRAND;
    private static Date DATE;

    public static void main(String... args) throws ParseException, BrandArgumentNotRecognizedException,
            BrandToSurferMismatchException, ActorArgumentNotRecognizedException
    {
        LOGGER.info("BRAND_ARG: " + args[BRAND_ARG.index()]);
        LOGGER.info("SPARK_TIME: " + args[DATE_ARG.index()]);

        for(int i = 2; i < args.length; i++) {
            LOGGER.info("ITEM" + (i - 1) + ": " + args[i]);
        }

        BRAND = selectBrand(args[BRAND_ARG.index()]);
        DATE = parseDate(args[DATE_ARG.index()]);
        ITEMS = extractItems(args);
        ACTOR = Actor.selectByArgument(ITEMS.get(0)[Item.ACTOR.index()]);
        SURFER = SurferBuilder.getSurferType(BRAND);

        BROWSER = new ChromeBrowser(SURFER.startURL());

        LOGGER.info("Scheduled " + BRAND.argument() + " job at " + DATE.toString() + "...");
        TIMER.schedule(SURFER, DATE);
    }

    private static Date parseDate(String date) throws ParseException {
        LOGGER.info("Formatting spark time...");
        return DATE_FORMAT.parse(date);
    }

    private static Brand selectBrand(String brand) throws BrandArgumentNotRecognizedException {
        LOGGER.info("Attempting to build brand...");
        return Brand.selectByArgument(brand);
    }

    private static List<String[]> extractItems(String[] args){
        final List<String[]> items = new ArrayList<>();

        for(int i = 2; i < args.length; i++){
            items.add(args[i].split("@"));
        }

        return items;
    }
}
