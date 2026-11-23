package gr.aueb.elevatorbuddies.userinterface;
public class CentralSystemClient {
    public String requestElevator(int originFloor, int destinationFloor) {
        System.out.println("Request sent to Central System");
        System.out.println("Origin Floor: " + originFloor);
        System.out.println("Destination Floor: " + destinationFloor);
        return "A";
    }
}