package gr.aueb.elevatorbuddies.centralsystem;

public class CentralSystemMain {
    public static void main(String[] args) {
        // 1. Ορισμός προεπιλεγμένων τιμών ή ανάγνωση από τα arguments
        // Σύμφωνα με την ομάδα, έχουμε 4 ανελκυστήρες και ας υποθέσουμε 10 ορόφους (ή όσο ορίζει το κτίριο)
        int numberOfElevators = 4;
        int totalFloors = 10;
        
        if (args.length >= 2) {
            try {
                numberOfElevators = Integer.parseInt(args[0]);
                totalFloors = Inetger.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Σφάλμα: Οι παράμετροι πρέπει να είναι ακέραιοι αριθμοί.");
                return;
            }
        }
        // 2. Έλεγχος ορίων σύμφωνα με την εκφώνηση
        // Ανελκυστήρας: 2 έως 20, Όροφοι: 3 έως 200
        if (NumberOfElevators < 2 || numberΟfElevators > 20) {
            System.err.println("Σφάλμα: Ο αριθμός των ανελκυστήρων πρέπει να είναι 2 και 20.");
            return;
        }
        if (totalFloors < 3 || totalFloors > 200) {
            System.err.println("Σφάλμα: Ο συνολικός αριθμός ορόφων πρέπει να είναι μεταξύ 3 και 200.");
            return;
        }
        System.out.println("Εκκίνηση Κεντρικού Συστήματος με " + numberOfElevators + " ανελκυστήρες και " + totalFloors + " ορόφους...");
        
        // 3. Αρχικοποίηση βασικών κλάσεων του Κνετρικού Συστήματος
        ElevatorRegistry registry = new ElevatorRegistry(numberOfElevators);
        ElevatorAssigner assigner = new ElevatorAssigner(registry);
        ElevatorClient client = new ElevatorClient();
        RequestController controller = new RequestController(assigner, client, totalFloors);

        // 4. Εκκίνηση του REST server / μηχανισμού υποδοχής αιτημάτων
        // controller.startServer();
        System.out.println("Το Κνετρικό Σύστημα ξεκίνησε επιτυχώς και αναμένει αιτήματα.");
    }
}