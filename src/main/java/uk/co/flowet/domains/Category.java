package uk.co.flowet.domains;

public class Category {

    private final String endpoint;

    Category(String endpoint){
        this.endpoint = endpoint;
    }

    public enum Supreme {

        JACKETS("shop/all/jackets"),
        SHIRTS("shop/all/shirts"),
        TOPS_SWEATERS("shop/all/tops_sweaters"),
        SWEATSHIRTS("shop/all/sweatshirts"),
        PANTS("shop/all/pants"),
        HATS("shop/all/hats"),
        BAGS("shop/all/bags"),
        ACCESSORIES("shop/all/accessories"),
        SHOES("shop/all/shoes"),
        SKATE("shop/all/skate"),
        CHECKOUT("checkout");

        private final Category category;

        Supreme(String endpoint) {
            this.category = new Category(endpoint);
        }

        public static String endpoint(String category) {
            for (Supreme value : values()) {
                if (value.category.endpoint.equals("shop/all/" + category)) {
                    return value.category.endpoint;
                }
            }

            throw new IllegalArgumentException("Category endpoint not found.");
        }

        public Category category() {
            return category;
        }
    }

    public String endpoint() {
        return endpoint;
    }
}
