// Maxim Piorischin 501015327
/**
 * Passenger has a name, passport, seatnum
 * initialize them based off of what was passed in
 * */
public class Passenger {
    private String name;
    private int passport;
    private String seat;
    public Passenger(String name, int passport, String seat){
        this.name = name;
        this.passport = passport;
        this.seat = seat;
    }
    /**
     * getter methods
     * */
    public String getName(){
        return name;
    }
    public int getPassport(){
        return passport;
    }
    public String getSeat(){
        return seat;
    }

    /**
     * equals method which checks if 2 Passengers are the same with name and passport
     * @return true if successful
     * */
    public boolean equals (Passenger p){
        if (this.name.equals(p.getName()) && this.passport == p.getPassport()){
            return true;
        }
        return false;
    }
    /**
     * method which returns a string of object information
     * */
    public String toString(){
        return "Name: "+ this.name + " Passport Number: " + this.passport + " Seat Number: " + this.seat;
    }

    public String toManifString(){
        return this.name + " " + this.passport + " " + this.seat;
    }
}
