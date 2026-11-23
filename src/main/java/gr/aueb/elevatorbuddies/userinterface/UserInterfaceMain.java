package gr.aueb.elevatorbuddies.userinterface;

public class UserInterfaceMain {

    public static void main(String[] args) {
        CentralSystemClient client = new CentralSystemClient();
    // 1. Έλεγχος αν δόθηκε η παράμετρος από τη γραμμή εντολών 
    if (args.length == 0) {
        System.err.println("Σφάλμα: Δεν δόθηκε όροφος εκκίνησης.");
        System.err.println("Χρήση: java userinterface.UserInterfaceMain <starting_floor>");
        return;
    }
    int  startFloor;
    // 2. Έλεγχος αν η τιμή είναι έγκυρος αριθμός
    try {
        startFloor = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
        System.err.println("Σφάλμα: Ο όροφος πρέπει να είναι ένας ακέραιος αριθμός.");
        return;
    }
    // 3. Έλεγχος ορίων (π.χ. βάσει της εκφώνησης το κτίριο έχει συνήθως από 3 έως 200 ορόφους)
    // Μπορείς να προσαρμόσεις τα όρια αν έχετε ορίσει κάτι διαφορετικό στην ομάδα.
    int minFloor = 3; // ή 0 / 1 ανάλογα με τις προδιαγραφές σας
    int maxFloor = 200;

    if (startFloor < minFloor || startFloor > maxFloor) {
        System.err.println("Σφάλμα: Ο όροφος εκκίνησης πρέπει να είναι μεταξύ " + minFloor + " και " + maxFloor + ".");
        return;
    }
    // 4. Αρχικοποίηση της ConsoleUI περνώντας τον έγκυρο όροφο εκκίνησης
    ConsoleUI consoleUI = new ConsoleUI(
        client,
        startFloor,
        minFloor,
        maxFloor);
    consoleUI.start(); // Ή όποια μέθοδος εκκίνησης διαθέτει η ConsoleUI σας
    }
}



