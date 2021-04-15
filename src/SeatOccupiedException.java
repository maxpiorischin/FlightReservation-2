/**
 * Exception gets called when a seat attempted to be reserved is already occupied
 */
public class SeatOccupiedException extends Exception{
    public SeatOccupiedException(String seat){
        super("Seat " + seat + " already occupied");
    }
}
