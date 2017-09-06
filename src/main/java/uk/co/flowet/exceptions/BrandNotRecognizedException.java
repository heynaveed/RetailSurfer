package uk.co.flowet.exceptions;

public class BrandNotRecognizedException extends Exception{

    public BrandNotRecognizedException(){
        super("The uk.co.flowet.brands is not recognised from button command line argument.");
    }
}
