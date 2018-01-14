package lab12.Elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InternalPanelAgent extends Thread {
    protected static class InternalCall {
        private final int toFloor;

        protected InternalCall(int toFloor) {
            this.toFloor = toFloor;
        }
    }

    protected InternalPanelAgent(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
    }

    protected BlockingQueue<InternalCall> input = new ArrayBlockingQueue<>(100);
    protected ElevatorCar elevatorCar;

    public void run() {
        while (true) {
            InternalCall internalCall = null;

            try {
                internalCall = this.input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (internalCall == null) return;

            if (internalCall.toFloor == this.elevatorCar.getFloor()) continue;

            if (internalCall.toFloor > this.elevatorCar.getFloor()) {
                ElevatorStops.get().setLiftStopUp(internalCall.toFloor);
            } else {
                ElevatorStops.get().setLiftStopDown(internalCall.toFloor);
            }
        }
    }
}
