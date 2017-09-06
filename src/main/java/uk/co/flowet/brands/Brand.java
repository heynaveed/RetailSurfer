package uk.co.flowet.brands;

public enum Brand {

    SUPREME("supreme", "https://www.supremenewyork.com/");

    private String commandArgument;
    private String baseURL;

    Brand(String commandArgument, String baseURL){
        this.commandArgument = commandArgument;
        this.baseURL = baseURL;
    }

    public String commandArgument(){
        return commandArgument;
    }

    public String URL() { return baseURL; }

    public boolean returnByCommandArgument(String commandArgument){
        return this.commandArgument.equals(commandArgument);
    }
}
