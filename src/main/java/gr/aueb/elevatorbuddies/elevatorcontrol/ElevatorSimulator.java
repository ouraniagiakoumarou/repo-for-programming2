package gr.aueb.elevatorbuddies.elevatorcontrol;
public class ElevatorSimulator implements Runnable {
    private final Elevator elevator;
    private final StopManager stopManager;
    private final long floorTravelTimeMs;
    private final long doorTimeMs;
    private volatile boolean running = true;
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
                if (!stopManager.hasPendingStops()) {
                    elevator.setDirection(Direction.NONE);
                    elevator.setStatus(ElevatorStatus.IDLE);
                    Thread.sleep(200);
                    continue;
                }
                Integer targetFloor = stopManager.getNextStop();
                if (targetFloor == null) continue;
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
    public void stopSimulation() {
        this.running = false;
    }
}
