package lab7.Exceptions;

public class NoEnoughPointsException extends RuntimeException {
    public NoEnoughPointsException(int size) {
        super(String.format("No enough points in the admin unit. 4 required, %d provided.", size));
    }
}
