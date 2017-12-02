package lab7.Exceptions;

public class InvalidParentException extends RuntimeException {
    public InvalidParentException(long id) {
        super(String.format("Parent of given id %d was not found.", id));
    }
}
