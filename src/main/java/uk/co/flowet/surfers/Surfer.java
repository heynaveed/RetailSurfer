package uk.co.flowet.surfers;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import uk.co.flowet.domains.Brand;

import java.util.TimerTask;

public abstract class Surfer extends TimerTask{

    String startURL;

    protected abstract void surf();
    static Elements returnElements(Document doc, String cssQuery){
        return doc.select(cssQuery);
    }

    public String startURL(){
        return startURL;
    }
}
