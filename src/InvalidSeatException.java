/** This Exception is thrown when a seat attempted to reserve is invalid
 */

public class InvalidSeatException extends Exception{
    public InvalidSeatException(String seat){
        super("Seat " + seat + " is not a valid seat");
    }
}