package lab12.Elevator;

public class ElevatorCar extends Thread {
    protected enum Tour {
        UP,
        DOWN
    }

    protected enum Movement {
        STOP,
        MOVING
    }

    protected int floor = 0;
    protected Tour tour = Tour.UP;
    protected Movement movementState = Movement.STOP;

    protected int getFloor() {
        return this.floor;
    }

    public void run() {
        while (true) {
            // sleep(500);

            if (this.movementState == Movement.STOP && this.tour == Tour.DOWN) {
                if (!ElevatorStops.get().hasStopBelow(this.floor)) {
                    this.tour = Tour.UP;
                } else {
                    this.movementState = Movement.MOVING;
                }

                continue;
            }

            if (this.movementState == Movement.STOP && this.tour == Tour.UP) {
                // ???
            }

            if (this.movementState == Movement.MOVING && this.tour == Tour.DOWN) {
                if (this.floor > ElevatorStops.get().getMinSetFloor()) {
                    this.floor--;
                    System.out.println("Floor" + this.floor);
                } else {
                    this.movementState = Movement.STOP;
                    this.tour = Tour.UP;
                }

                if (ElevatorStops.get().whileMovingDownShouldStopAt(this.floor) || this.floor == ElevatorStops.get().getMinSetFloor()) {
                    this.movementState = Movement.STOP;
                    ElevatorStops.get().clearStopDown(this.floor);
                    System.out.println("STOP");
                }

                continue;
            }

            if (this.movementState == Movement.MOVING && this.tour == Tour.UP) {
                // ???
            }
        }
    }
}
