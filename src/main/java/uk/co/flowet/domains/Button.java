package uk.co.flowet.domains;

public class Button {

    private final String xPath;
    private final String name;

    private Button(String xPath, String name){
        this.xPath = xPath;
        this.name = name;
    }

    public enum Supreme {

        ADD_TO_BASKET("//*[@id=\"add-remove-buttons\"]/input"),
        TERMS("//*[@id=\"cart-cc\"]/fieldset/p/label/div/ins"),
        PROCESS_PAYMENT("//*[@id=\"pay\"]/input");

        private final Button button;

        Supreme(String xPath) {
            this.button = new Button(xPath, this.name());
        }

        public Button button(){
            return button;
        }
    }

    public String xPath(){
        return xPath;
    }

    public String name(){
        return name;
    }
}
