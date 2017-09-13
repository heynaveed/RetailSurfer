package uk.co.flowet.domains;

public enum Item {

    TITLE(0), COLOR(1), CATEGORY(2), SIZE(3), ACTOR(4);

    private int index;

    Item(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }
}
