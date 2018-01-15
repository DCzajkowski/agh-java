package lab12.Elevator;

import static java.lang.Thread.sleep;

public class Elevator {
    public static void main(String[] args) throws InterruptedException {
        Elevator.init();

        Elevator.scenario1();
        // Elevator.scenario2();
        // Elevator.scenario3();
    }

    /**
     * Verifies going up and down, stopping on floors when going down, coming back when going down and above floor is selected
     */
    private static void scenario1() throws InterruptedException {
        Elevator.makeInternalCall(4);
        Elevator.makeInternalCall(2);
        Elevator.makeInternalCall(6);

        sleep(5000);

        Elevator.makeExternalCall(8, false);

        sleep(1000);

        Elevator.makeInternalCall(0);

        sleep(1000);

        Elevator.makeExternalCall(9, false);
        Elevator.makeExternalCall(2, false);

        sleep(10000);

        Elevator.makeInternalCall(0);
    }

    /**
     * Verifies the elevator stops on floors (wanting to go up) when going up
     */
    private static void scenario2() throws InterruptedException {
        Elevator.makeInternalCall(9);

        sleep(1000);

        Elevator.makeExternalCall(5, true);
    }

    /**
     * Verifies elevator comes back to the floor chosen internally below, when going up
     */
    private static void scenario3() throws InterruptedException {
        Elevator.makeInternalCall(9);

        sleep(3000);

        Elevator.makeInternalCall(1);
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
