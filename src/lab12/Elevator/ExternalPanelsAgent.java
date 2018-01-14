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
            ExternalCall ec = null;

            try {
                ec = this.input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (ec == null) return;

            // ignorujemy wezwanie na piętro, na którym winda się znajduje
            if (ec.atFloor == this.elevatorCar.getFloor()) continue;

            // dodajemy do jednej z tablic zgłoszeń
            if (ec.directionUp) {
                ElevatorStops.get().setLiftStopUp(ec.atFloor);
            } else {
                ElevatorStops.get().setLiftStopDown(ec.atFloor);
            }
        }
    }
}
