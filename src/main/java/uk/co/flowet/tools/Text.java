package uk.co.flowet.tools;

import java.io.*;
import java.util.LinkedList;

import static uk.co.flowet.RetailResale.*;

public class Text {

    public LinkedList<String> toList(String filePath) {
        LOGGER.info("Creating list from " + filePath);
        final LinkedList<String> paymentDetails = new LinkedList<>();

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;

            while((line = reader.readLine()) != null){
                paymentDetails.add(line);
            }

            is.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paymentDetails;
    }
}
