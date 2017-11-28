package lab6.CSVReader.Exceptions;

public class ColumnNotFoundException extends RuntimeException {
    public ColumnNotFoundException(String columnName) {
        super(String.format("There is no column with the name '%s'.", columnName));
    }
}
