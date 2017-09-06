package uk.co.flowet.domains;

public enum Actor {
    NAVEED("/NaveedSupremePaymentDetails.txt");

    private final String path;

    Actor(String path){
        this.path = path;
    }

    public String path(){
        return path;
    }
}
