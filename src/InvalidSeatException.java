//Maxim Piorischin 501015327

/** This Exception is thrown when a seat attempted to be reserved is invalid, and doesnt exist in the airacraft's seatLayout
 */

public class InvalidSeatException extends Exception{
    public InvalidSeatException(String seat){
        super("Seat " + seat + " is not a valid seat");
    }
}