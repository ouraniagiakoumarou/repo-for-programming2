package gr.aueb.elevatorbuddies.centralsystem;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class ElevatorRegistry {
   private Map<String, Elevator> elevators = new HashMap<>();
    public void  updateElevator(Elevator elevator) {
        // update the status of the elevator in the registry
        if (elevator == null) {
            return;
        }
        String elevatorId = elevator.getId();
        if (elevatorId == null || elevatorId.isBlank()) {
            return;
        }
        elevators.put(elevatorId, elevator);

    }
    public Elevator getElevatorStatus(String elevatorId) {
        // retrieve the status of the elevator with the given ID from the registry
        
    }
    public List<Elevator> getAllElevatorStatuses() {
        // retrieve the statuses of all elevators in the registry and return them as a list
        
    }
    public List<Elevator> getAvailableElevators() {
        //gets available elevators from the registry and returns them as a list

    }
    public void removeElevator(String elevatorId) {
        // remove the elevator with the given ID from the registry if it stops working

    }
    public boolean isStatusFresh(String elevatorId) {
        // check if the status of the elevator with the given ID is fresh
        
    }


}