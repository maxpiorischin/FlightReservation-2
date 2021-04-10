//Maxim Piorischin 501015327
/**
 * Throw this exception if there is a duplicate passenger with the same name and passport number
 * passes the message to the super class
 * */
public class DuplicateException extends Exception{
    public DuplicateException(){
        super("Passenger already exists!");
    }
}
