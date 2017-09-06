package uk.co.flowet;

import uk.co.flowet.brands.Brand;
import uk.co.flowet.brands.BrandBuilder;
import uk.co.flowet.browser.ChromeBrowser;
import uk.co.flowet.exceptions.BrandNotRecognizedException;
import uk.co.flowet.exceptions.BrandToSurferMismatchException;
import uk.co.flowet.surfers.Surfer;
import uk.co.flowet.surfers.SurferBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Logger;

public class RetailResale {

    public static Logger LOGGER = Logger.getLogger(RetailResale.class.getName());
    public static final Timer TIMER = new Timer();
    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Surfer SURFER;
    private static Brand BRAND;
    private static Date DATE;
    public static ChromeBrowser BROWSER;

    public static void main(String... args){
        LOGGER.info("BRAND: " + args[0]);
        LOGGER.info("SPARK_TIME: " + args[1]);

        for(int i = 2; i < args.length; i++) {
            LOGGER.info("ITEM" + (i - 1) + ": " + args[i]);
        }

        try {
            LOGGER.info("Formatting spark time...");
            DATE = DATE_FORMAT.parse(args[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            LOGGER.info("Attempting to build brand...");
            BRAND = BrandBuilder.getBrandType(args[0]);
        } catch (BrandNotRecognizedException e) {
            e.printStackTrace();
        }

        try {
            LOGGER.info("Attempting to build surfer...");
            SURFER = SurferBuilder.getSurferType(BRAND, args);
        } catch(BrandToSurferMismatchException e){
            e.printStackTrace();
        }

        BROWSER = new ChromeBrowser(SURFER.startURL());

        LOGGER.info("Scheduled " + args[0] + " job at " + DATE.toString() + "...");
        TIMER.schedule(SURFER, DATE);
    }
}
