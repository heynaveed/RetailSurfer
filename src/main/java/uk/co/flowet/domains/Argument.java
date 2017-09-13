package uk.co.flowet.domains;

public enum Argument {

    BRAND_ARG(0), DATE_ARG(1), ITEM_ARG(2);

    private int index;

    Argument(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
