//Maxim Piorischin 501015327
/**
 * Exception is thrown when a reservation is attempted but the flight is full
 * */
public class FlightFullException extends Exception{
    public FlightFullException(){
        super("Flight is full");
    } // default
    public FlightFullException(String flightnum){
        super("Flight " + flightnum + " is full");
    } // with flightnumber
    public FlightFullException(String flightnum, String seattype){ // with flightnumber and seattype
        super(seattype + " Flight " + flightnum + " is full");
    }
}
