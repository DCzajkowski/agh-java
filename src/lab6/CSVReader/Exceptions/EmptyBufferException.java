package lab6.CSVReader.Exceptions;

public class EmptyBufferException extends RuntimeException {
    public EmptyBufferException() {
        super("No lines in a buffer. Have you ran .next() on your reader?");
    }
}
