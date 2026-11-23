package gr.aueb.elevatorbuddies.elevatorcontrol;
//Εισαγωγή των nested enums από την κλάση Elevator
import gr.aueb.elevatorbuddies.elevatorcontrol.Elevator.Direction;
import gr.aueb.elevatorbuddies.elevatorcontrol.Elevator.ElevatorStatus;
//Προσομοιώνει την λειτουργία του ανελκυστήρα σε ξεχωριστό thread
//συνεργαζόμενη με τις κλάσεις Elevator και StopManager
public class ElevatorSimulator implements Runnable {
    private final Elevator elevator;
    private final StopManager stopManager;
    private final long floorTravelTimeMs;
    private final long doorTimeMs;
    //Η λέξη κλειδί volative εξασφαλίζει ότι η αλλαγή της τιμής από άλλο thread θα είναι ορατή
    private volatile boolean running = true;
    //constructor
    public ElevatorSimulator(Elevator elevator, StopManager stopManager, long floorTravelTimeMs, long doorTimeMs) {
        this.elevator = elevator;
        this.stopManager = stopManager;
        this.floorTravelTimeMs = floorTravelTimeMs;
        this.doorTimeMs = doorTimeMs;
    }
    @Override 
    public void run() {
        while (running) {
            try {
                //Έλεγχος αν ο ανελκυστήρας είναι εκτός λειτουργίας (OUT_OF_SERVICE)
                if (elevator.getStatus() == ElevatorStatus.OUT_OF_SERVICE) {
                    elevator.setDirection(Direction.NONE);
                    Thread.sleep(200);
                    continue;
                }
                //Αν δεν υπάρχουν εκκρεμείς στάσεις, μένει σε αναμονή (IDLE)
                if (!stopManager.hasPendingStops()) {
                    elevator.setDirection(Direction.NONE);
                    elevator.setStatus(ElevatorStatus.IDLE);
                    Thread.sleep(200);
                    continue;
                }
                //Παίρνει τον επόμενο όροφο στόχο
                Integer targetFloor = stopManager.getNextStop();
                if (targetFloor == null) continue;
                //Κίνηση προς τα πάνω, προς τα κάτω ή στάση στον όροφο
                int current = elevator.getCurrentFloor();
                if (current < targetFloor) {
                    elevator.setDirection(Direction.UP);
                    elevator.setStatus(ElevatorStatus.MOVING);
                    Thread.sleep(floorTravelTimeMs);
                    elevator.setCurrentFloor(current + 1);
                } else if (current > targetFloor) {
                    elevator.setDirection(Direction.DOWN);
                    elevator.setStatus(ElevatorStatus.MOVING);
                    Thread.sleep(floorTravelTimeMs);
                    elevator.setCurrentFloor(current - 1);
                } else {
                    elevator.setStatus(ElevatorStatus.STOPPED);
                    stopManager.completeStop(targetFloor);
                    Thread.sleep(doorTimeMs);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }
    //Επιτρέπει τον ασφαλή τερματισμό της προσομοίωσης απο εξωτερική κλάση
    public void stopSimulation() {
        this.running = false;
    }
}
