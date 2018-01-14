package lab12.Elevator;

import static java.lang.Thread.currentThread;

public class Elevator {
    public static void main(String[] args) throws InterruptedException {
        Elevator.init();

        Elevator.makeInternalCall(4);
        Elevator.makeInternalCall(2);
        Elevator.makeInternalCall(6);

        currentThread();
        Thread.sleep(5000);

        Elevator.makeExternalCall(8, false);

        currentThread();
        Thread.sleep(1000);

        Elevator.makeInternalCall(0);

        currentThread();
        Thread.sleep(1000);

        Elevator.makeExternalCall(9, false);
        Elevator.makeExternalCall(2, false);

        currentThread();
        Thread.sleep(10000);

        Elevator.makeInternalCall(0);
    }

    // Creating three threads
    protected static ElevatorCar car = new ElevatorCar();
    protected static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    protected static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // Simulate calling for an elevator from an external panel
    protected static void makeExternalCall(int atFloor, boolean directionUp) {
        try {
            Elevator.externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor, directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Simulate calling for an elevator from an internal panel
    protected static void makeInternalCall(int toFloor) {
        try {
            Elevator.internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Initiate all treads
    protected static void init() {
        car.start();
        Elevator.externalPanelAgent.start();
        Elevator.internalPanelAgent.start();
    }
}
