package gr.aueb.elevatorbuddies.userinterface;

import java.util.scanner;

public class ConsoleUI {

    private final Scanner scanner;
    private final CentralSystemClient centralSystemClient;
    private final int installationFloor;
    private final int minFloor;
    private final int maxFloor;

    public ConsoleUI(CentralSystemClient centralSystemClient,
    int installationFloor,
    int minFloor,
    int maxFloor) {
        this.scanner = new Scanner(System.in);
        this.centralSystemClient = centralSystemClient;
        this.installationFloor = installationFloor;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public void start() {
        boolean running = true;

        System.out.println("=== Smart Elevator System - User Interface ===");
        System.out.println("Οροφοσ εγκαταστασης¨: " + installationFloor);

        while (running) {
            System.out.println("\n1. Δηλωση οροφου προορισμου");
            System.out.println("0. Εξοδος");
            System.out.print("Επιλογη: ");

            int choice = readInt();

            if (choice == 1) {
                handleElevatorRequest();
            } else if (choice == 0) {
                running = false;
                System.out.println("Εξοδος απο το προγραμμα.");
            } else {
                System.out.println("Μη εγκυρη επιλογη. Δοκιμαστε ξανα.");
            }
        }
    }

private void handleElevatorRequest() {
    if (centralSystemClient == null) {
        System.out.println("Σφαλμα: δεν υπαρχει συνδεση με τον CentralSystemClient.");
        return;
    }

    int destinationFloor = readFloor("Δωστε οροφο προορισμου (" + minFloor + " εως " + maxFloor + "): ");
    
    if (destinationFloor == installationFloor) {
        System.out.println("Βρισκεστε ηδη σε αυτο τον οροφο (" + installationFloor + ").");
        return;
    }

    try {
        String elevatorId = centralSystemClient.requestElevator(installationFloor, destinationFloor);

        if (elevatorId != null && !elevatorId.isBlank()) {
            System.out.println("Ανελκυστηρας που ανατεθηκε: " + elevatorId);
        } else {
            System.out.println("No elevator could be assigned.");
        }
    } catch (Exception e) {
        System.out.println("Error while communicating with the system: " + e.getMessage());
    }
}

private int readFloor(String prompt) {
    while (true) {
        System.out.print(prompt);
        int floor = readInt();

        if (floor >= minFloor && floor <= maxFloor) {
            return floor;
        }

        System.out.println("Floor must be between " + minFloor + " and " + maxFloor + ".");
    }
}


private int readInt() {
    while (true) {
        String input = scanner.nextLine().trim();
        try; {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer:  ");
        }
    }
}
