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
            // odczytaj wezwanie z kolejki
            // w zależności od aktualnego piętra, na którym jest winda,
            // umieść przystanek w odpowiedniej tablicy ''EleveatorStops''
        }
    }
}
