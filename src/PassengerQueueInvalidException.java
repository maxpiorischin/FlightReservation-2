//Maxim Piorischin 501015327
/**
 * Exception is thrown during an error in the passenger queue use, sends the message to the exception superclass
 */

public class PassengerQueueInvalidException extends Exception{
    public PassengerQueueInvalidException(String message){
        super(message);
    }
}
