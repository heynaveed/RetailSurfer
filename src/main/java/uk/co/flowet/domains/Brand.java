package uk.co.flowet.domains;

import uk.co.flowet.exceptions.BrandArgumentNotRecognizedException;

public enum Brand {

    SUPREME("supreme", "https://www.supremenewyork.com/");

    private String argument;
    private String baseURL;

    Brand(String argument, String baseURL){
        this.argument = argument;
        this.baseURL = baseURL;
    }

    public String argument(){
        return argument;
    }

    public String URL() { return baseURL; }

    public static Brand selectByArgument(String argument) throws BrandArgumentNotRecognizedException {
        for (Brand brand : values()) {
            if (brand.argument().equals(argument.toLowerCase())) {
                return brand;
            }
        }

        throw new BrandArgumentNotRecognizedException();
    }
}
