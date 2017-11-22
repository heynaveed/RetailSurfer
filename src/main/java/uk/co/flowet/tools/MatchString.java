package uk.co.flowet.tools;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import static uk.co.flowet.RetailSurfer.*;

public class MatchString {

    private static final String SUPREME = "Supreme®/";

    public static int byRatio(String searchName, String actualName){
        final int score = FuzzySearch.ratio(removeSupreme(searchName), removeSupreme(actualName));
        LOGGER.info("Matching '" + searchName + "' against '" + actualName + "'... " + score + "%");
        return score;
    }

    private static String removeSupreme(String itemName){
        if(itemName.contains(SUPREME)){
            itemName = itemName.replace(SUPREME, "");
        }

        return itemName;
    }
}
