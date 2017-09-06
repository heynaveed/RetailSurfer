package uk.co.flowet.domains;

public class Size {

    private final String size;

    private Size(String size){
        this.size = size;
    }

    public enum Supreme {

        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large"),
        XLARGE("XLarge");

        private final Size size;

        Supreme(String size) {
            this.size = new Size(size);
        }

        public Size size(){
            return size;
        }
    }

    public String size() {
        return size;
    }
}
