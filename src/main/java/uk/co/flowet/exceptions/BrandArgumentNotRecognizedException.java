package uk.co.flowet.exceptions;

public class BrandArgumentNotRecognizedException extends Exception{

    public BrandArgumentNotRecognizedException(){
        super("The uk.co.flowet.brands is not recognised from button command line argument.");
    }
}
