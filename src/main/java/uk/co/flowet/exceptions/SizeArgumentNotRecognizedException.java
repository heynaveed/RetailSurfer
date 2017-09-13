package uk.co.flowet.exceptions;

public class SizeArgumentNotRecognizedException extends Exception{

    public SizeArgumentNotRecognizedException(){
        super("The size is not recognised from the command line argument.");
    }
}
