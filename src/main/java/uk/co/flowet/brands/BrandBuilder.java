package uk.co.flowet.brands;

import uk.co.flowet.exceptions.BrandNotRecognizedException;

import static uk.co.flowet.RetailResale.*;

public class BrandBuilder {

    private BrandBuilder(){}

    public static Brand getBrandType(String commandArgument) throws BrandNotRecognizedException {
        Brand[] brands = Brand.values();

        for (Brand brand : brands) {
            if (brand.returnByCommandArgument(commandArgument)) {
                LOGGER.info(brand.commandArgument() + " brand chosen!");
                return brand;
            }
        }

        throw new BrandNotRecognizedException();
    }
}
