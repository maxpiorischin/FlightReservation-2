//Maxim Piorischin 501015327
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class FlightManager
{
  // Contains list of Flights departing from Toronto in a single day
  Map<String, Flight> flights = new TreeMap<>();
  
  String[] cities = 	{"Dallas", "New York", "London", "Paris", "Tokyo"};

  Map<String, Integer> flightTimeMap = new HashMap<>(); //Using a map to set the times for each flight duration! Destination name is the key, and the duration is the value.
  

  
  // Contains list of available airplane types and their seat capacity
  ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>();
  
  Random random = new Random(); // random number generator - google "Java class Random". Use this in generateFlightNumber
  
  
  public FlightManager() {

      // Create some aircraft types with max seat capacities
      airplanes.add(new Aircraft(12, "Bombardier 5000"));
      airplanes.add(new Aircraft(88, "Boeing 737"));
      airplanes.add(new Aircraft(160, "Airbus 320"));
      airplanes.add(new Aircraft(36, "Dash-8 100"));
      airplanes.add(new Aircraft(444, 16, "Boeing 747"));

      flightTimeMap.put("DALLAS", 3);
      flightTimeMap.put("NEWYORK", 1);
      flightTimeMap.put("LONDON", 7);
      flightTimeMap.put("PARIS", 8);
      flightTimeMap.put("TOKYO", 16);

      // generate flights
      String flightNum;
      Flight flight;
      Aircraft airplaneToUse = null;
      try {
          Scanner scanner = new Scanner(new File("src\\flights.txt"));
          while (scanner.hasNextLine()) {
              String line = scanner.nextLine();
              Scanner lineScanner = new Scanner(line);
              String airline = lineScanner.next().replaceAll("_", " ");
              String des = lineScanner.next().replaceAll("_", " ");
              String dep = lineScanner.next();
              String capacity = lineScanner.next();
              int dur = flightTimeMap.get(des.replaceAll(" ", "").toUpperCase());

              flightNum = generateFlightNumber(airline);
              if (dur > 10) { //todo check what durarion is considered long haul
                  for (Aircraft aircraft : airplanes){
                      if (aircraft.getNumSeats() > Integer.parseInt(capacity) && aircraft.getNumFirstClassSeats() > 0){// find an aircraft with first class seats
                          airplaneToUse = aircraft;
                          break;
                      }
                  }
                  flight = new LongHaulFlight(flightNum, airline, des, dep, dur, airplaneToUse);
              }
              else{
                  for (Aircraft aircraft : airplanes){
                      if (aircraft.getNumSeats() > Integer.parseInt(capacity)){ //find an aircraft with the minimum required amount of seats
                          airplaneToUse = aircraft;
                          break;
                      }
                  }
                  flight = new Flight(flightNum, airline, des, dep, dur, airplaneToUse);
              }
              flights.put(flightNum, flight);
          }
      }
      catch (FileNotFoundException f){
          System.out.println(f.getMessage());
      }
  }
  
  /*
   * This private helper method generates and returns a flight number string from the airline name parameter
   * For example, if parameter string airline is "Air Canada" the flight number should be "ACxxx" where xxx is 
   * a random 3 digit number between 101 and 300 (Hint: use class Random - see variable random at top of class)
   * you can assume every airline name is always 2 words. 
   * 
   */
    /**
     * Generates a flight number with the Random class
     * First takes the first letters in the two words of the airline name (Eg Air Canada AC)
     * Generates a random number from 0 to 199 and adds 101 to result to make it from 101 to 300
     * @param airline airline name passed in
     * */
  private String generateFlightNumber(String airline)
  {
    // Your code here
      String name = airline.charAt(0) +  "" + airline.charAt(airline.indexOf(" ") + 1);
      return name + (random.nextInt(199) + 101);
  }

  // Prints all flights in flights array list (see class Flight toString() method) 
  // This one is done for you!
    /**
     * Prints all the flights in the flights Arraylist
     * */
  public void printAllFlights()
  {
      for (String key : flights.keySet()){
          System.out.println(flights.get(key));
      }
  }

  public void printManifest(String flightnum){
      for (String key : flights.keySet()){
          Flight flight = flights.get(key);
          if (flight.getFlightNum().equals(flightnum)){
              for (Passenger passenger : flight.getManifest()){
                  System.out.println(passenger.toManifString());
              }
          }
      }
  }
  
  // Given a flight number (e.g. "UA220"), check to see if there are economy seats available
  // if so return true, if not return false
    /**
     *Checks if seats are available by using the flightnumber to iterate through the flights and find the flight,
     * uses the .seatsAvailable() method from the Flight class to detemine if there are any available
     * @param flightNum used to find flight
     * @throws FlightNotFoundException if a flight isnt found
     * @throws FlightFullException if a flight is full
     * @return true if flight is successfully checked
     * */
  public boolean seatsAvailable(String flightNum) throws FlightNotFoundException, FlightFullException {
      for (String key : flights.keySet()){
          Flight flight = flights.get(key);
          if (flight.getFlightNum().equals(flightNum)) {
              if (flight.seatsAvailable()) {
                  return true;
              } else {
                  throw new FlightFullException(flightNum);
              }
          }
      }
      throw new FlightNotFoundException();
  }
    /**
     * Reserves seat on flight
     * Iterates through flights, finds a flight with the flight number parameter
     * Use flight.seatsAvailable(), return a new reservation
     * @param flightNum number used to find flight
     * @param name used in creating Passenger
     * @param passport used in creating Passenger
     * @throws FlightFullException if flight is full
     * @throws FlightNotFoundException if flight is not found
     * @throws DuplicateException if a duplicate passenger is found
     * @return reservation with parameters flightnum and flight.toString()
     * */
  public Reservation reserveSeatOnFlightPSNGR(String flightNum, String name, int passport, String seatType, String seat) throws FlightFullException, FlightNotFoundException, DuplicateException, InvalidSeatException {
      for (String key : flights.keySet()){
          Flight flight = flights.get(key);
            if (flight.getFlightNum().equals(flightNum)) {
                if (!isDuplicateSeat(seat, flight)) { //checks if seat already exists
                    if (validSeat(seat, flight)) { // checks if seat is a valid seat
                        if (seatType.equals(LongHaulFlight.firstClass) && flight instanceof LongHaulFlight) {//firstclass
                            LongHaulFlight longflight = (LongHaulFlight) flight; //cast to a long haul flight
                            Passenger passenger = new Passenger(name, passport, seat);
                            if (longflight.reserveSeat(LongHaulFlight.firstClass, passenger)) {
                                if (longflight.numFirstClassPassengers < longflight.aircraft.numFirstClassSeats) {
                                    Reservation newRes = new Reservation(flightNum, longflight.toString() + " " + passenger.toString() + "\n         FCL", passenger);
                                    newRes.setFirstClass();
                                    return newRes;
                                } else {
                                    throw new FlightFullException(LongHaulFlight.firstClass, flightNum);
                                }

                            } else {
                                throw new DuplicateException("Passenger");
                            }
                        } else { //economy seat
                            if (flight.seatsAvailable()) {
                                Passenger passenger = new Passenger(name, passport, seat);
                                if (flight.reserveSeat(passenger)) {
                                    return new Reservation(flightNum, flight.toString() + " " + passenger.toString(), passenger);
                                } else {
                                    throw new DuplicateException("Passenger");
                                }
                            } else {
                                throw new FlightFullException(flightNum);
                            }
                        }
                    }
                    else{
                        throw new InvalidSeatException(seat);
                    }
                }
                else{
                    throw new DuplicateException("Seat " + seat);
                }
            }
            }
        throw new FlightNotFoundException(flightNum);
        }


    /**
     * Cancel an existing passenger reservation
     * search through flights and use.cancelseat() on the one that matches flight number from res
     * @param res reservation for finding flightnumber
     * @param name for cancelseatPSNGR
     * @param passport for cancelseatPSNGR
     * @return true if successful
     * */
    public boolean cancelReservationPSNGR(Reservation res, int passport, String name) {
        for (String key : flights.keySet()){
            Flight flight = flights.get(key);
            if (flight.getFlightNum().equals(res.getFlightNum())) {
                if (res.isFirstClass()){
                    LongHaulFlight longflight = (LongHaulFlight) flight;
                    longflight.cancelSeat(LongHaulFlight.firstClass, passport, name);
                }
                else {
                    flight.cancelSeatPSNGR(passport, name);
                }
                return true;
            }
        }
        return false;
    }

    public void printSeats(String flightnum) throws FlightNotFoundException {
        boolean found = false;
        for (String key : flights.keySet()) {
            Flight flight = flights.get(key);
            if (flight.getFlightNum().equals(flightnum)){
                for (int i = 0; i < flight.getAircraft().getColumns(); i ++){
                    for (int u = 0; u < flight.getAircraft().getRows(); u++){
                        String seat = (flight.getAircraft().getSeatLayout()[i][u]);
                        if (isDuplicateSeat(seat, flight)){
                            System.out.print("XX ");
                        }
                        else{
                            System.out.print(seat + " ");
                        }

                    }
                    System.out.println();
                }
            }
            found = true;
        }
        if (!found){
            throw new FlightNotFoundException();
        }
        System.out.println("XX = Occupied    + = First Class");

    }

    public boolean isDuplicateSeat(String seat, Flight flight){
        for (String key : flight.seatMap.keySet()){
            if (key.equalsIgnoreCase(seat)){
                return true;
            }
        }
        return false;
    }
    public boolean validSeat(String seat, Flight flight){
        String[][] arr = flight.getAircraft().getSeatLayout();
        for (String[] arr1 : arr){
            for (String elem : arr1){
                if (elem.equalsIgnoreCase(seat)){
                    return true;
                }
            }
        }
        return false;
    }

  // Prints all aircraft in airplanes array list. 
  // See class Aircraft for a print() method
    /**
     * performs aircraft.print() on all aircrafts in airplanes arraylist
     * */
  public void printAllAircraft()
  {
  	for (Aircraft aircraft : airplanes){
  	    aircraft.print();
    }
  }
  
  // Sort the array list of Aircraft objects 
  // This is one line of code. Make sure class Aircraft implements the Comparable interface
    /**
     * sort aircrafts using the compareto method in aircraft
     * */
  public void sortAircraft()
  {
  	Collections.sort(airplanes);
  }

}
