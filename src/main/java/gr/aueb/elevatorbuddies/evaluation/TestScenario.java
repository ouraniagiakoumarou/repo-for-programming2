package gr.aueb.elevatorbuddies.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestScenario {

    public static class Request {
        private final int originFloor;
        private final int destinationFloor;
        private final long delayMs;

        public Request(int originFloor, int destinationFloor, long delayMs) {
            this.originFloor = originFloor;
            this.destinationFloor = destinationFloor;
            this.delayMs = Math.max(0, delayMs);
        }

        public int getOriginFloor() {
            return originFloor;
        }

        public int getDestinationFloor() {
            return destinationFloor;
        }
        public long getDelayMs() {
            return delayMs;
        }
    }

    private final String name;
    private final String description;
    private final List<Request> requests;

    public TestScenario(String name, String description) {
        this.name = name;
        this.description = description;
        this.requests = new ArrayList<>();
    }

    public void addRequest(int originFloor, int destinationFloor, long delayMs) {
        if (originFloor == destinationFloor) {
            return;
        }
        requests.add(new Request(originFloor, destinationFloor, delayMs));
    }

    public String getName() {
        return name;
    }