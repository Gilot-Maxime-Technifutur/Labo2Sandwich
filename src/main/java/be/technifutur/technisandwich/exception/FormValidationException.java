package be.technifutur.technisandwich.exception;

public class FormValidationException extends RuntimeException{
    public FormValidationException(){
        super("error in form validation");
    }
    public FormValidationException(String s){
        super(s);
    }
    public FormValidationException(Throwable e){
        super("error in form validation", e);
    }
    public FormValidationException(String s, Throwable e){
        super(s, e);
    }
}
