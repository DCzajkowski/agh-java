package lab12.Elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExternalPanelsAgent extends Thread {
    protected static class ExternalCall {
        private final int atFloor;
        private final boolean directionUp;

        public ExternalCall(int atFloor, boolean directionUp) {
            this.atFloor = atFloor;
            this.directionUp = directionUp;
        }
    }

    private final ElevatorCar elevatorCar;
    protected BlockingQueue<ExternalCall> input = new ArrayBlockingQueue<>(100);

    protected ExternalPanelsAgent(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    public void run() {
        while (true) {
            ExternalCall externalCall = null;

            try {
                externalCall = this.input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (externalCall == null) return;

            if (externalCall.atFloor == this.elevatorCar.getFloor()) continue;

            if (externalCall.directionUp) {
                ElevatorStops.get().setLiftStopUp(externalCall.atFloor);
            } else {
                ElevatorStops.get().setLiftStopDown(externalCall.atFloor);
            }
        }
    }
}
