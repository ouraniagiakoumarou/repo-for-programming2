package gr.aueb.elevatorbuddies.userinterface;

public class UserInterfaceMain {

    public static void main(String[] args) {
        CentralSystemClient client = new CentralSystemClient();
    // 1. Έλεγχος αν δόθηκε η παράμετρος από τη γραμμή εντολών 
    if (args.length == 0) {
        System.err.println("Σφάλμα: Δεν δόθηκε όροφος εκκίνησης.");
        
    // 1. Check if arguments are provided
    
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
    // 4. Αρχικοποίηση της ConsoleUI περνώντας τον έγκυρο όροφο εκκίνησης
    ConsoleUI consoleUI = new ConsoleUI(
        client,
        startFloor,
        minFloor,
        maxFloor);
    consoleUI.start(); // Ή όποια μέθοδος εκκίνησης διαθέτει η ConsoleUI σας
    System.out.println("User Interface started successfully with starting floor: " + startFloor);

    }
}
}



