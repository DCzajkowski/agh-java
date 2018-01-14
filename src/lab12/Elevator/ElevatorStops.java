package lab12.Elevator;

public class ElevatorStops {
    private static ElevatorStops instance;
    protected static final int FLOORS = 10;
    protected static final int MIN_FLOOR = 0;
    protected static final int MAX_FLOOR = FLOORS - 1;
    protected boolean stopsDown[] = new boolean[FLOORS];
    protected boolean stopsUp[] = new boolean[FLOORS];

    private ElevatorStops() {
        //
    }

    protected void setLiftStopUp(int floor) {
        this.stopsUp[floor] = true;
    }

    protected void setLiftStopDown(int floor) {
        this.stopsDown[floor] = true;
    }

    protected void clearStopUp(int floor) {
        this.stopsUp[floor] = false;
    }

    protected void clearStopDown(int floor) {
        this.stopsDown[floor] = false;
    }

    protected boolean hasStopAbove(int floor) {
        for (int i = floor + 1; i < ElevatorStops.MAX_FLOOR; i++) {
            if (this.stopsUp[i] || this.stopsDown[i]) {
                return true;
            }
        }

        return false;
    }

    protected boolean hasStopBelow(int floor) {
        for (int i = floor - 1; i >= ElevatorStops.MIN_FLOOR; i--) {
            if (this.stopsUp[i] || this.stopsDown[i]) {
                return true;
            }
        }

        return false;
    }

    protected int getMaxSetFloor() {
        for (int i = ElevatorStops.MAX_FLOOR - 1; i >= 0; i--) {
            if (this.stopsUp[i] || this.stopsDown[i]) {
                return i;
            }
        }

        return 0;
    }

    protected int getMinSetFloor() {
        for (int i = 0; i < ElevatorStops.MAX_FLOOR; i++) {
            if (this.stopsUp[i] || this.stopsDown[i]) {
                return i;
            }
        }

        return 0;
    }

    protected boolean whileMovingDownShouldStopAt(int floor) {
        return this.stopsDown[floor];
    }

    protected boolean whileMovingUpShouldStopAt(int floor) {
        return this.stopsUp[floor];
    }

    protected static ElevatorStops get() {
        if (ElevatorStops.instance == null) {
            ElevatorStops.instance = new ElevatorStops();
        }

        return ElevatorStops.instance;
    }
}
