package lab7.Exceptions;

public class InvalidOffsetException extends RuntimeException {
    public InvalidOffsetException(int size) {
        super(String.format("Given offset was out of bounds. You need to provide a number from 0 to %d", size));
    }
}
