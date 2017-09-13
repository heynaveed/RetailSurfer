package uk.co.flowet.surfers;

import uk.co.flowet.domains.Brand;
import uk.co.flowet.exceptions.BrandToSurferMismatchException;

public class SurferBuilder {

    public static Surfer getSurferType(Brand brand) throws BrandToSurferMismatchException {

        switch(brand){
            case SUPREME:
                return new SupremeSurfer();
        }

        throw new BrandToSurferMismatchException();
    }
}
