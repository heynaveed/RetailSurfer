package uk.co.flowet.surfers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uk.co.flowet.brands.Brand;

import java.util.TimerTask;

public abstract class Surfer extends TimerTask{

    String startURL;
    Brand brand;

    protected abstract void surf(String[] args);

    static Elements returnElements(Document doc, String cssQuery){
        return doc.select(cssQuery);
    }

    public String startURL(){
        return startURL;
    }
}
