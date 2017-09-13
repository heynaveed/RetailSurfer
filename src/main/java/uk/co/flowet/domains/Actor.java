package uk.co.flowet.domains;

import uk.co.flowet.exceptions.ActorArgumentNotRecognizedException;

public enum Actor {
    TEST("test", "/TestSupremePaymentDetails.txt"),
    NAVEED("naveed", "/NaveedSupremePaymentDetails.txt");

    private final String argument;
    private final String path;

    Actor(String argument, String path){
        this.path = path;
        this.argument = argument;
    }

    public String argument() { return argument; }

    public String path(){
        return path;
    }

    public static Actor selectByArgument(String argument) throws ActorArgumentNotRecognizedException {
        for(Actor actor: values()){
            if(actor.argument().equals(argument.toLowerCase())){
                return actor;
            }
        }

        throw new ActorArgumentNotRecognizedException();
    }
}
