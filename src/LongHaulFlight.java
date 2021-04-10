//Maxim Piorischin 501015327
/*
 * A long haul flight is a flight that travels thousands of kilometers and typically has separate seating areas 
 */

public class LongHaulFlight extends Flight
{
	int numFirstClassPassengers;
	String seatType;
	
	// Possible seat types
	public static final String firstClass = "First Class Seat";
	public static final String economy 		= "Economy Seat";  
	

	public LongHaulFlight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		super(flightNum, airline, dest, departure, flightDuration, aircraft);
		numFirstClassPassengers = 0;
		// use the super() call to initialize all inherited variables
		// also initialize the new instance variables 
	}

	public LongHaulFlight()
	{
     // default constructor
		super();
		numFirstClassPassengers = 0;
	}
	
	/*
	 * Reserves a seat on a flight. Essentially just increases the number of (economy) passengers
	 */
	/**
	 * call the reserveSeat() method under with economy constant string
	 * */
	public boolean reserveSeat()
	{
		// override the inherited reserveSeat method and call the reserveSeat method below with an economy seatType
		// use the constants defined at the top
		reserveSeat(economy);
		return true;
	}

	/*
	 * Reserves a seat on a flight. Essentially just increases the number of passengers, depending on seat type (economy or first class)
	 */
	/**
	 * if economy seat, call super() to flight
	 * if first class, check if seats available and add 1 to firstclasspassengers
	 * @return true if successful
	 * */
	public boolean reserveSeat(String seatType)
	{
		// if seat type is economy 
		//			call the superclass method reserveSeat() and return the result
		// else if the seat type is first class then 
		// 			check to see if there are more first class seats available (use the aircraft method to get the max first class seats
		// 			of this airplane
		//    	if there is a seat available, increment first class passenger count (see instance variable at the top of the class)
		//    	return true;
		// else return false
		if (seatType.equals(economy)){
			super.reserveSeat();
		}
		else { // first class
			if (aircraft.getNumFirstClassSeats() > numFirstClassPassengers){
				numFirstClassPassengers += 1;
				return true;
			}
			return false;
		}
		return true;
	}
	
	// Cancel a seat
	/**
	 * call the cancelSeat() method under with economy constant string
	 * */
	public void cancelSeat()
	{
	  // override the inherited cancelSeat method and call the cancelSeat method below with an economy seatType
		// use the constants defined at the top
		cancelSeat(economy);
	}
	/**
	 * if firstclass, decrease firstclasspassengers by 1
	 * if economy, decrease passengers by 1
	 * */
	public void cancelSeat(String seatType)
	{
		// if seat type is first class and first class passenger count is > 0
		//  decrement first class passengers
		// else
		// decrement inherited (economy) passenger count

			if (seatType.equals(firstClass) && numFirstClassPassengers > 0) {
				numFirstClassPassengers -= 1;
			} else {
				passengers -= 1;
			}
		}
	// return the total passenger count of economy passengers *and* first class passengers
	// use instance variable at top and inherited method that returns economy passenger count
	/**
	 * @return amount of total passengers
	 * */
	public int getPassengerCount()
	{
		return numFirstClassPassengers + this.getPassengers();
	}
	//override the flight toString() to include Long Haul at the end
	/**
	 * @return string of passenger information
	 * */
	public String toString(){
		return super.toString() +  " Long Haul";
	}
}
