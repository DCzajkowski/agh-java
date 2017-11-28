package lab6.CSVReader.Exceptions;

public class InvalidColumnIndexException extends RuntimeException {
    public InvalidColumnIndexException(int size) {
        super(String.format("Given column index is not valid. The index must be from 0 to %d", size - 1));
    }
}
