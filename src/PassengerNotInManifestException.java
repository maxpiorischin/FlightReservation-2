// Maxim Piorischin 501015327

/**
 * This gets called if a valid passenger is not found, during a search of passenger reservations
 */
public class PassengerNotInManifestException extends Exception{
    public PassengerNotInManifestException(String name, int passport){
        super("Passenger " + name + " " + passport + " Not Found");
    }
}
