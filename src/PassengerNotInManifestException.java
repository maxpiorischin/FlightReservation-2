// Maxim Piorischin 501015327
public class PassengerNotInManifestException extends Exception{
    public PassengerNotInManifestException(String name, int passport){
        super("Passenger " + name + " " + passport + " Not Found");
    }
}
