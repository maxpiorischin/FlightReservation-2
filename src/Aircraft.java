//Maxim Piorischin 501015327
/*
 * 
 * This class models an aircraft type with a model name, a maximum number of economy seats, and a max number of forst class seats 
 * 
 * Add code such that class Aircraft implements the Comparable interface
 * Compare two Aircraft objects by first comparing the number of economy seats. If the number is equal, then compare the
 * number of first class seats 
 */

public class Aircraft implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  String[][] seatLayout;
  int rows;
  int columns;
  
  public Aircraft(int seats, String model) // default constructor for non firstclass flights
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
  	this.rows = 4;
  	this.columns = numEconomySeats / rows;
  	this.seatLayout = new String[columns][rows];
  	initSeatLayout(rows, columns, numFirstClassSeats);
  }

  public Aircraft(int economy, int firstClass, String model) // Constructor for a firstclass flight
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
  	this.rows = 4;
  	this.columns = (numEconomySeats + numFirstClassSeats) / rows;
  	this.seatLayout = new String[columns][rows];
  	initSeatLayout(rows, columns, numFirstClassSeats);
  }
	/** @return number of economy seats*/
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	/** @return economy and firstclass seats combined*/
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	/** @return number of first class seats seats*/
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}
	/** @return model of aircraft*/
	public String getModel()
	{
		return model;
	}
	/**
	 * set the aircraft model
	 * @param model aircraft model*/
	public void setModel(String model)
	{
		this.model = model;
	}
	/** prints the description of the aircraft*/

	public String[][] getSeatLayout(){
		return seatLayout;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	public void initSeatLayout(int rows, int columns, int numFirstClass){
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
		for (int i = 0; i < columns; i++){
			for (int u = 0; u < rows; u++){
				if (numFirstClass > 0){
					seatLayout[i][u] =("+")+ (i + 1) + alphabet[u];
				}
				else {
					seatLayout[i][u] = (i + 1) + alphabet[u];
				}
				numFirstClass --;
			}

		}
	}

	/**
	 * compareTo method fom comparable interface which compares itself with another Aircraft
	 * @param o other Aircraft
	 * @return 1 if this plane has more seats than other, -1 if other has more, 0 is equal
	 *
	 * */
	@Override
	public int compareTo(Aircraft o) {
		if (numEconomySeats < o.numEconomySeats){
			return -1;
		}
		else if (numEconomySeats > o.numEconomySeats){
			return 1;
		} // Compared if current object had less economy seats than the other, -1 for less 1 for more
		else{
			if (numFirstClassSeats < o.numFirstClassSeats){
				return -1;
			}
			else if (numFirstClassSeats > o.numFirstClassSeats){
				return 1;
			} // If the economy seats are equal, it compares first class and returns accordingly
		}
		return 0;
	}
}
  
