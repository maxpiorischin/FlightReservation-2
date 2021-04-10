// Maxim Piorischin 501015327
/*
 * A simple class to model an electronic airline flight reservation
 * 
 * This class has been done for you
 */
/**
 * Class already done for me
 * */
public class Reservation
{
	String flightNum;
	String flightInfo;
	boolean firstClass;
	Passenger passenger;
	
	
	public Reservation(String flightNum, String info)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
	}
	public Reservation(String flightNum, String info, Passenger passenger)
	{
		this.flightNum = flightNum;
		this.flightInfo = info;
		this.firstClass = false;
		this.passenger = passenger;
	}
	
	public boolean isFirstClass()
	{
		return firstClass;
	}

	public void setFirstClass()
	{
		this.firstClass = true;
	}

	public String getFlightNum()
	{
		return flightNum;
	}
	
	public String getFlightInfo()
	{
		return flightInfo;
	}

	public Passenger getPassenger(){return passenger;}

	public void setFlightInfo(String flightInfo)
	{
		this.flightInfo = flightInfo;
	}

	public void print()
	{
		System.out.println(flightInfo);
	}
}
