package lab7.Exceptions;

public class EmptyBoundingBoxException extends RuntimeException {
    public EmptyBoundingBoxException() {
        super("You cannot do this operation on an empty bounding box. Fill minimum and maximum values first.");
    }
}
