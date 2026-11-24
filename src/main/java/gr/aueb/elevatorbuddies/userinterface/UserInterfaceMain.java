package gr.aueb.elevatorbuddies.userinterface;

public class UserInterfaceMain {

    public static void main(String[] args) {
        // 1. Check if arguments are provided
    if (args.length == 0) {
        System.err.println("Error: Please provide a starting floor an an argument.");
        return;
    }
    
    int  startFloor;
    // 2. Check if the value is a valid integer
    try {
        startFloor = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
        System.err.println("Error: The floor must be an integer number.");
        return;
    }
    // 3. Check boundss (e.g. building has 3 to 200 floors)
    int minFloor = 3; 
    int maxFloor = 200;

    if (startFloor < minFloor || startFloor > maxFloor) {
        System.err.println("Error: The starting floor must be between " + minFloor + " and " + maxFloor + ".");
        return;
    }
    
    System.out.println("User Interface started successfully with starting floor: " + startFloor);
    }
}



