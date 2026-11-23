package gr.aueb.elevatorbuddies.elevatorcontrol;

/**
 * Η κλάση Elevator αναπαριστά έναν ανελκυστήρα και κρατάει την κατάστασή του:
 * αναγνωριστικό, τρέχων όροφο, κετεύθυνση και κατάσταση λειτουργίας.
 * 
 * Οι μέθοδοι είναι synchronized γιατί την ίδια Elevator θα τη τη διαβάζει
 * ο REST και θα την αλλάζει ο ElevatorSimulator σε άλλο thread
 * οπότε έτσι αποφεύγουμε να δοθούν ως στοιχεία νέος όροφος και παλιά κατεύθυνση
 */
public class Elevator {
    public enum Direction { UP, DOWN, NONE } //οι πιθανές κατευθύνσεις είναι: πάνω, κάτω ή δεν κινείται
    public enum ElevatorState { IDLE, MOVING, STOPPED, OUT_OF_SERVICE } //οι πιθανές καταστάσεις είναι: αδρανής, κινείται, σταματημένος σε όροφο ή εκτός λειτουργίας 
    
    private final String id; //ταυτότητα ανελκυστήρα π.χ. "Α" -δεν αλλάζει ποτέ-
    private final int minFloor; //κατώτατο όριο ορόφου-χαμηλότερος όροφος κτηρίου
    private final int maxFloor; //ανώτατο όριο ορόρφου-ψηλότερος όροφος κτηρίου
    
    private int currentFloor; //τωρινός όροφος ανελκυστήρα
    private Direction direction; //κατεύθυνση ανελκυστήρα
    private ElevatorState state; //κατάσταση ανελκυστήρα

    public Elevator(String id, int initialFloor, int minFloor, int maxFloor) {
        if (initialFloor < minFloor || initialFloor > maxFloor) {
            throw new IllegalArgumentException("Ο όροφος είναι εκτός ορίων");
        }
        this.id = id;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.currentFloor = initialFloor;
        this.direction = Direction.NONE; //αρχικά: δεν κινείται
        this.state = ElevatorState.IDLE; //αρχικά: αδρανής
    }
    // Getters: επιστρέφουν την τρέχουσα τιμή

    //Δεν χρειάζεται synchronized η μέθοδος αυτή γιατί το id δε θα αλλάξει ποτέ
    public String getId() {
        return id;
    }

    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

    public synchronized Direction getDirection() {
        return direction;
    }

    public synchronized ElevatorState getState() {
        return state;
    }

    //Setters: αλλάζουν την τιμή

    //Αλλαγή ορόφου, όταν αυτός είναι εντός ορίων
    public synchronized void setCurrentFloor(int floor) {
        if (floor < minFloor || floor > maxFloor) {
            throw new IllegalArgumentException("Ο όροφος είναι εκτός ορίων");
        }
        this.currentFloor = floor;
    }

    public synchronized void setDirection(Direction direction) {
        this.direction = direction;
    }

    public synchronized void setState(ElevatorState state) {
        this.state = state;
    }
}
