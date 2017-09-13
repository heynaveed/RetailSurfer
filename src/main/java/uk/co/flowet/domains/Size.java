package uk.co.flowet.domains;

import uk.co.flowet.exceptions.SizeArgumentNotRecognizedException;

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

        public static Supreme selectByArgument(String argument) throws SizeArgumentNotRecognizedException {
            for (Supreme supreme : values()) {
                if (supreme.size().equals(argument.toLowerCase())) {
                    return supreme;
                }
            }

            throw new SizeArgumentNotRecognizedException();
        }
    }

    public String size() {
        return size;
    }
}
