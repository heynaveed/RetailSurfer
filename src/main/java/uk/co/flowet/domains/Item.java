package uk.co.flowet.domains;

public class Item {

    public enum Supreme {

        TITLE(0), COLOR(1), CATEGORY(2), SIZE(3);

        private int index;

        Supreme(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }
    }
}
