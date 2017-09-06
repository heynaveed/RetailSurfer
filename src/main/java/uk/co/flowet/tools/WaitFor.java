package uk.co.flowet.tools;

import java.util.Random;

import static uk.co.flowet.RetailResale.*;

public class WaitFor {

    private static final Random RANDOM = new Random();
    private long milliseconds;

    private WaitFor(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public static WaitFor exactly(long milliseconds) {
        milliseconds = milliseconds + RANDOM.nextInt(10);
        LOGGER.info("Waiting exactly " + milliseconds + " milliseconds...");
        return new WaitFor(milliseconds);
    }

    public static WaitFor around(long milliseconds) {
        final int multiplyer = RANDOM.nextInt(2) == 0 ? -1 : 1;
        milliseconds = milliseconds + (RANDOM.nextInt(100)*multiplyer);
        LOGGER.info("Waiting around " + milliseconds + " milliseconds...");
        return new WaitFor(milliseconds);
    }

    public void milliseconds() {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
