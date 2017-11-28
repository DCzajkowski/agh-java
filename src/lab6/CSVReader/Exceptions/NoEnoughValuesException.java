package lab6.CSVReader.Exceptions;

public class NoEnoughValuesException extends RuntimeException {
    public NoEnoughValuesException() {
        super("Invalid line encountered. There are no sufficient values in the line.");
    }
}
