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
		super.setFlightType(FlightType.LONHAUL);
		// use the super() call to initialize all inherited variables
		// also initialize the new instance variables 
	}

	public LongHaulFlight()
	{
     // default constructor
		super();
		super.setFlightType(FlightType.LONHAUL);
		numFirstClassPassengers = 0;
	}
	
	/*
	 * Reserves a seat on a flight. Essentially just increases the number of (economy) passengers
	 */
	/**
	 * call the reserveSeat() method under with economy constant string
	 * */
	public boolean reserveSeat(Passenger passenger, String seat)
	{
		// override the inherited reserveSeat method and call the reserveSeat method below with an economy seatType
		// use the constants defined at the top
		reserveSeat(economy, passenger);
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
	public boolean reserveSeat(String seatType, Passenger passenger)
	{
		if (seatType.equals(economy)){
			super.reserveSeat(passenger);
		}
		else { // first class
			if (aircraft.getNumFirstClassSeats() > numFirstClassPassengers && noDuplicate(passenger)){
				manifest.add(passenger);
				seatMap.put(passenger.getSeat(), passenger);
				numFirstClassPassengers += 1;
				return true;
			}
			return false;
		}
		return true;
	}


	/**
	 * if firstclass, decrease firstclasspassengers by 1
	 * if economy, decrease passengers by 1
	 * */
	public void cancelSeat(String seatType, int passport, String name)
	{
		// if seat type is first class and first class passenger count is > 0
		//  decrement first class passengers
		// else
		// decrement inherited (economy) passenger count

			if (seatType.equals(firstClass) && numFirstClassPassengers > 0) {

				for (Passenger passenger : manifest){
					if (passenger.getPassport() == passport && passenger.getName().equals(name)){
						manifest.remove(passenger);
						seatMap.remove(passenger.getSeat());
						numFirstClassPassengers -= 1;
						break;
					}
				}
			} else {
				super.cancelSeatPSNGR(passport, name);
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
