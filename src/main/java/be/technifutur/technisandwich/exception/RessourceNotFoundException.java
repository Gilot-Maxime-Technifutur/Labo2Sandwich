package be.technifutur.technisandwich.exception;

public class RessourceNotFoundException extends RuntimeException{
    public RessourceNotFoundException(){
        super("error in form validation");
    }
    public RessourceNotFoundException(String s){
        super(s);
    }
    public RessourceNotFoundException(Throwable e){
        super("error in form validation", e);
    }
    public RessourceNotFoundException(String s, Throwable e){
        super(s, e);
    }
}
