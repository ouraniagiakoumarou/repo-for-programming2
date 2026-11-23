package gr.aueb.elevatorbuddies.centralsystem;

import java.util.List;


public class ElevatorAssigner {
    
    private final ElevatorRegistry elevatorRegistry;
    private final int totalFloors;

    public ElevatorAssigner(ElevatorRegistry elevatorRegistry, int totalFloors) {
        this.elevatorRegistry = elevatorRegistry;
        this.totalFloors = totalFloors;
    }

    public String assignElevator(int orgin, int destination) {
        if (orgin == destination) {
            return null; //??
        }

        boolean passengerGoingUp = destination > orgin;
        List<ElevatorStatus> elevators = elevatorRegistry.getAllElevators //εκρεμεί μέθοδος απο elevator Registry

        String bestElevarorID = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (ElevatorStatus elevator : elevators) {
            if (!elevator.isAvailable()) {
                continue;
            }

            double score = calculateScore(elevator, orgin, passengersGoingUp);
            if (score > bestScore) {
                bestScore = score;
                bestElevatorID = elevator.getID();
            }
        }
        return bestElevatorID;
    }

    private double calculateScore(ElevatorStatus elevator, int orgin, boolean passengersGoingUp) {
        int distance = Math.abs(elevator.getCurrentFloor() - orgin);
        double normalizedDistancePenalty = (totalFloors > 0) ? (distance*100.0)/totalFloors : distance * 10.0;

        double score = 1000.0 - normalizedDistancePenalty;

        if (passengersGoingUp && elevator.isMovingUp() && elevator.getCurrentFloor() <= orgin) {
            score += 50.0;
        } else if (!passengerGoingUp && elevator.isMovingDown() && elevator.getCurrentFloor() >= orgin) {
            score += 50.0;
        }

        score -=elevator.getScheduledStopsCount() * 5.0;

        return score;
    }

}