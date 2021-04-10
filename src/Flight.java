//Maxim Piorischin 501015327
import java.util.ArrayList;

/*
 *  Class to model an airline flight. In this simple system, all flights originate from Toronto
 *  
 *  This class models a simple flight that has only economy seats
 */
public class Flight
{
	public enum Status {DELAYED, ONTIME, ARRIVED, INFLIGHT};

	public String flightNum;
	public String airline;
	public String origin, dest;
	public String departureTime;
	public Status status; // see enum Status above. google this to see how to use it
	public int flightDuration;
	public Aircraft aircraft;
	protected int passengers; // count of (economy) passengers on this flight - initially 0
	public ArrayList<Passenger> passengerList;
  
	public Flight()
	{
		this.flightNum = "";
		this.airline = "Airline";
		this.dest = "unknown";
		this.origin = "Toronto";
		this.departureTime = "0000";
		this.flightDuration = 0;
		this.aircraft = new Aircraft(0, "default");
		passengers = 0;
		status = Status.ONTIME;
		passengerList = new ArrayList<>();

		// write code to initialize instance variables to default values
	}


	public Flight(String flightNum, String airline, String dest, String departure, int flightDuration, Aircraft aircraft)
	{
		this.flightNum = flightNum;
		this.airline = airline;
		this.dest = dest;
		this.origin = "Toronto";
		this.departureTime = departure;
		this.flightDuration = flightDuration;
		this.aircraft = aircraft;
		passengers = 0;
		status = Status.ONTIME;
		passengerList = new ArrayList<>(); // Arraylist holding all the Passenger objects
		
	}
	// Getter and setter Methods
	public String getFlightNum()
	{
		return flightNum;
	}
	public void setFlightNum(String flightNum)
	{
		this.flightNum = flightNum;
	}
	public String getAirline()
	{
		return airline;
	}
	public void setAirline(String airline)
	{
		this.airline = airline;
	}
	public String getOrigin()
	{
		return origin;
	}
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	public String getDest()
	{
		return dest;
	}
	public void setDest(String dest)
	{
		this.dest = dest;
	}
	public String getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(String departureTime)
	{
		this.departureTime = departureTime;
	}
	
	public Status getStatus()
	{
		return status;
	}
	public void setStatus(Status status)
	{
		this.status = status;
	}
	public int getFlightDuration()
	{
		return flightDuration;
	}
	public void setFlightDuration(int dur)
	{
		this.flightDuration = dur;
	}
	
	public int getPassengers()
	{
		return passengers;
	}
	public void setPassengers(int passengers)
	{
		this.passengers = passengers;
	}

	public ArrayList<Passenger> getPassengerList(){return passengerList;} // gets passenger list
	
	// Check to see if there is room on this flight - compare current passenger count
	// with aircraft max capacity of economy seats
	/** Checks is seats are available by comparing aircraft seats with number of passengers
	 * @return true if more seats that passengers, false otherwise
	 * */
	public boolean seatsAvailable()
	{
		// your code here
		if (aircraft.getNumSeats() > passengers) {
			return true;
		}
		return false;
	}
	
	/*
	 * Cancel a seat - essentially reduce the passenger count by 1. Make sure the count does not
	 * fall below 0 (see instance variable passenger)
	 */
	/**
	 * Cancels a seat by subtracting 1 from passenger number
	 *
	 * */
	public void cancelSeat()
	{
		if (passengers > 0) {
			passengers -= 1;
		}
	}
	/**
	 * Cancels a seat and removes passenger from arraylist, by comparing name and passport passed in with iterated iterated list of passengers
	 * @param passport passport to use in finding the Passenger
	 * @param name name to use in finding the passenger
	 *
	 * */
	public void cancelSeatPSNGR(int passport, String name)
	{

		if (passengers > 0) {
			for (Passenger passenger : passengerList){
				if (passenger.getPassport() == passport && passenger.getName().equals(name)){
					passengerList.remove(passenger);
					passengers -= 1;
					break;
				}
			}
		}
	}

	
	/*
	 * reserve a seat on this flight - essentially increases the passenger count by 1 only if there is room for more
	 * economy passengers on the aircraft used for this flight (see instance variables above)
	 */
	/**
	 * Reserves a seat
	 * adds 1 to passengers after ensuring seats are available
	 * */
	public boolean reserveSeat() // without passenger info
	{
		// your code here
		if (this.seatsAvailable()){
		passengers += 1;
		return true;}
		else{
			return false;
		}
	}
	/**
	 *Reserves a seat but with a passenger
	 * adds 1 to passengers
	 * @param passenger passenger object to add to passengerList
	 * @return true is successful
	 * */
	public boolean reserveSeat(Passenger passenger) // with passenger info
	{
			if (noDuplicate(passenger)){
				passengers += 1;
				passengerList.add(passenger);
			}
			else{
				return false;
			}
			return true;


	}
	/**
	 * Checks if there already exists a passenger in the passengerlist
	 * Iterates through passengerlist and uses passenger.equals(other passenger) to compare
	 * @param p Passenger object that is used to check if a duplicate of it exists in the list
	 * @return true if the p passed in is not in the arraylist, false if its already in it
	 * */
	public boolean noDuplicate(Passenger p){
		for (Passenger passenger : passengerList){
			if (passenger.equals(p)){
				return false;
			}
		}
		return true;
	}
	/**
	 * String which describes the object
	 * @return a string of the flight description
	 * */
	public String toString()
	{
		 return airline + "\t Flight:  " + flightNum + "\t Dest: " + dest + "\t Departing: " + departureTime + "\t Duration: " + flightDuration + "\t Status: " + status;
		
	}

  
}
