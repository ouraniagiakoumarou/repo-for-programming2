package gr.aueb.elevatorbuddies.centralsystem;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
public class ElevatorRegistry {
   private Map<String, Elevator> elevators = new ConcurrentHashMap<>();
    public void  updateElevator(Elevator elevator) {
        // update the status of the elevator in the registry
        if (elevator == null) {
            return;
        }
        String elevatorId = elevator.getId();
        if (elevatorId == null || elevatorId.isBlank() ) {
            return;
        }
        if (!elevatorId.equals("A")&& !elevatorId.equals("B") && !elevatorId.equals("C") && !elevatorId.equals("D")) {
            return;
        }
        elevators.put(elevatorId, elevator);
    }
    public Elevator getElevatorStatus(String elevatorId) {
        // retrieve the status of the elevator with the given ID from the registry 
        if (elevatorId == null || elevatorId.isBlank() ) {
            return  null;
        }
         if (!elevatorId.equals("A")&& !elevatorId.equals("B") && !elevatorId.equals("C") && !elevatorId.equals("D")) {
            return  null;
         }
        Elevator result = elevators.get(elevatorId);
        if (result == null) {
            System.out.println("Elevator was not found");
            return null;
        }
        return result;
    }
    public List<Elevator> getAllElevatorStatuses() {
        // retrieve the statuses of all elevators in the registry and return them as a list
        return new ArrayList<>(elevators.values());  
    }
    public List<Elevator> getAvailableElevators() {
        //gets available elevators from the registry and returns them as a list
        return new ArrayList<>();
    }
    public void removeElevator(String elevatorId) {
        // remove the elevator with the given ID from the registry if it stops working
         if (elevatorId == null || elevatorId.isBlank() ) {
            return;
        }
        if (!elevatorId.equals("A")&& !elevatorId.equals("B") && !elevatorId.equals("C") && !elevatorId.equals("D")) {
            return;
        }
        elevators.remove(elevatorId);
    }
    public boolean isStatusFresh(String elevatorId) {
        // check if the status of the elevator with the given ID is fresh
        // waiting for Elevator class 
        return false;
        
    }
}