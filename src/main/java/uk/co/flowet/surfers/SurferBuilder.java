package uk.co.flowet.surfers;

import uk.co.flowet.brands.Brand;
import uk.co.flowet.exceptions.BrandToSurferMismatchException;

public class SurferBuilder {

    public static Surfer getSurferType(Brand brand, String[] args) throws BrandToSurferMismatchException {

        switch(brand){
            case SUPREME:
                return new SupremeSurfer(args);
        }

        throw new BrandToSurferMismatchException();
    }
}
