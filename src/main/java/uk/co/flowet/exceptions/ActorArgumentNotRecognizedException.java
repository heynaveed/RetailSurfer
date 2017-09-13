package uk.co.flowet.exceptions;

public class ActorArgumentNotRecognizedException extends Exception{

    public ActorArgumentNotRecognizedException(){
        super("The actor is not recognised from the command line argument.");
    }
}
