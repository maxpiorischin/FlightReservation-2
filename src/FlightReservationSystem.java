// Maxim Piorischin 501015327
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Flight System for one single day at YYZ (Print this in title) Departing flights!!


public class FlightReservationSystem
{
	public static FlightManager manager;
	public static ArrayList<Reservation> myReservations;
	public static void main(String[] args)
	{
		System.out.println("Flight System for one single day at YYZ Departing flights!!");
		// Create a FlightManager object
		try { //catch exceptions in the initialization of the manager
			manager = new FlightManager();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}

		// List of reservations that have been made
		myReservations = new ArrayList<Reservation>();	// my flight reservations

		Scanner scanner = new Scanner(System.in); //scanner

		System.out.print(">");

		while (scanner.hasNextLine())
		{
			String inputLine = scanner.nextLine();
			if (inputLine == null || inputLine.equals("")) continue;

			// The command line is a scanner that scans the inputLine string
			// For example: list AC201
			Scanner commandLine = new Scanner(inputLine);
			
			// The action string is the command to be performed (e.g. list, cancel etc)
			String action = commandLine.next();

			if (action == null || action.equals("")) continue;

			if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")) //quits program
				return;
			
			// List all flights
			else if (action.equalsIgnoreCase("LIST")) // use printAllFlights
			{
				manager.printAllFlights(); 
			}

			/**
			 * get flightnum, firstname, lastname, passport number from scanner
			 * concatenate first and last name
			 * try creating a reservation and adding to myreservations
			 * catch any exceptions and print their message
			 * */
			else if (action.equalsIgnoreCase("RES")) // todo change this command to res, comment all useless code out
			{
				if (commandLine.hasNext()){
					try {
						String flightNum = commandLine.next();
						String fName = commandLine.next() + " ";
						String lName = commandLine.next();
						String name = fName.concat(lName);
						int passport = commandLine.nextInt();
						String seat = commandLine.next();
						if (Character.toString(seat.charAt(seat.length()-1)).equals("+")){ //check if last character of seat is a + indicating first class
							resSeat(flightNum, name, passport, LongHaulFlight.firstClass, seat);
						}
						else{ //economy
							resSeat(flightNum, name, passport, LongHaulFlight.economy, seat);
						}
					}
					catch(NoSuchElementException e){
						System.out.println("Invalid Information, Format must be >respsngr flight# firstname lastname passport# seat");
					}
				}
			}

			// Query the flight manager to see if seats are still available for a specific flight (example input: seats AC220)
		  // This one is done for you as a guide for other commands
			/**
			 * get flightnum from scannerm use manager.seatsavailable() with flightnum parameter
			 * */
			else if (action.equalsIgnoreCase("SEATS"))
			{
				String flightNum;

				if (commandLine.hasNext())
				{
					flightNum = commandLine.next();
					try {
						manager.printSeats(flightNum);

					}
					catch (FlightNotFoundException f) {
						System.out.println(f.getMessage());
					}
				}
			}

			/**
			 * get flightnum, firstname, lastname, passport number from scanner
			 * concatenate first and last name
			 * try cancelling a reservation and removing to myreservations
			 * exist boolean checks if a reservation existed or to ensure the wright exception was being used
			 * catch any exceptions and print their message
			 * */
			else if (action.equalsIgnoreCase("CANCEL")) {//format must be >cancelpsngr AC101 Maxim Piorischin 123
				String flightNum;
				if (commandLine.hasNext()) {
					try {
						flightNum = commandLine.next();
						String fName = commandLine.next() + " ";
						String lName = commandLine.next();
						String name = fName.concat(lName);
						int passport = commandLine.nextInt();
						cancSeat(flightNum, name, passport);
					}
					catch (NoSuchElementException e){
						System.out.println("Invalid Information, Format must be >cancelpsngr flight# firstname lastname passport#");
					}
				}
			}
      // Print all the reservations in array list myReservations
			/**
			 * iterate through reservations and print all reservations
			 * */
			else if (action.equalsIgnoreCase("MYRES"))
			{
				for (Reservation reservation : myReservations){
					reservation.print();
				}
			}

			else if (action.equalsIgnoreCase("PASMAN")){
				if (commandLine.hasNext()){
					String flightNum = commandLine.next();
					manager.printManifest(flightNum);
				}
			}




			// Print the list of aircraft (see class Manager)
			else if (action.equalsIgnoreCase("CRAFT"))
		  {
			  manager.printAllAircraft();
			}
			// These commands can be left until we study Java interfaces
			// Feel free to implement the code in class Manager if you already understand interface Comparable
			// and interface Comparator
			/**
			 * Sorts activate the manager.sortBy method
			 * */
			else if (action.equalsIgnoreCase("SORTCRAFT"))
		  {
		  	manager.sortAircraft();
		  }

			System.out.print("\n>");
		}
	}
	public static void resSeat(String flightNum, String name, int passport, String seatType, String seat){ // shortcut method for reserving a seat
		try {
			Reservation reservation = manager.reserveSeatOnFlightPSNGR(flightNum, name, passport, seatType, seat);
			myReservations.add(reservation);
			reservation.print();
		} catch (FlightFullException | FlightNotFoundException | DuplicateException | InvalidSeatException f) {
			System.out.println(f.getMessage());
		}
	}

	public static void cancSeat(String flightNum, String name, int passport){
		boolean exists = false;
		for (Reservation reservation : myReservations) {
			if (reservation.getFlightNum().equals(flightNum) && name.equals(reservation.getPassenger().getName()) && passport == reservation.getPassenger().getPassport()) {
				manager.cancelReservationPSNGR(reservation, passport, name);
				myReservations.remove(reservation);
				exists = true;
				break;

			}
		}
		try {
			if (!exists) {
				throw new ReservationNotFoundException();
			}
		} catch (ReservationNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	
}

