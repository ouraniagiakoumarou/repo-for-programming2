package gr.aueb.elevatorbuddies.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSenario {

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