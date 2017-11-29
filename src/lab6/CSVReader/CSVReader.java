package lab6.CSVReader;

import lab6.CSVReader.Exceptions.EmptyBufferException;
import lab6.CSVReader.Exceptions.ColumnNotFoundException;
import lab6.CSVReader.Exceptions.InvalidColumnIndexException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    protected BufferedReader reader;
    protected String delimiter;
    protected boolean hasHeader;

    /**
     * column names in the same order as in the file
     */
    protected List<String> columnLabels = new ArrayList<>();

    /**
     * map: column name -> column number
     */
    protected Map<String, Integer> columnLabelsToInt = new HashMap<>();

    protected String[] current;

    public CSVReader(String filename, String delimiter, boolean hasHeader) throws IOException {
        this(new FileReader(filename), delimiter, hasHeader);
    }

    public CSVReader(String filename, String delimiter) throws IOException {
        this(new FileReader(filename), delimiter, true);
    }

    public CSVReader(String filename) throws IOException {
        this(new FileReader(filename), ",", true);
    }

    public CSVReader(Reader reader, String delimiter, boolean hasHeader) throws IOException {
        this.reader = new BufferedReader(reader);
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;

        if (hasHeader) {
            this.parseHeader();
        }
    }

    public void parseHeader() throws IOException {
        String line = this.reader.readLine();

        if (line == null) {
            return;
        }

        String[] header = this.split(line);

        for (int i = 0; i < header.length; i++) {
            this.columnLabelsToInt.put(header[i], i);
            this.columnLabels.add(header[i]);
        }
    }

    public boolean next() {
        String line;

        try {
            line = this.reader.readLine();
        } catch (IOException e) {
            return false;
        }

        if (line == null) {
            return false;
        }

        this.current = this.split(line);

        return true;
    }

    public String get(String columnName) {
        return this.get(this.getColumnIndex(columnName));
    }

    public String get(int columnIndex) {
        if (this.current == null) throw new EmptyBufferException();
        if (columnIndex < 0 || columnIndex >= this.current.length)
            throw new InvalidColumnIndexException(this.current.length);

        return this.trimQuotes(this.current[columnIndex]);
    }

    public int getInt(String columnName) {
        return Integer.parseInt(this.get(columnName));
    }

    public int getInt(int columnIndex) {
        return Integer.parseInt(this.get(columnIndex));
    }

    public double getDouble(String columnName) {
        return Double.parseDouble(this.get(columnName));
    }

    public double getDouble(int columnIndex) {
        return Double.parseDouble(this.get(columnIndex));
    }

    public long getLong(String columnName) {
        return Long.parseLong(this.get(columnName));
    }

    public long getLong(int columnIndex) {
        return Long.parseLong(this.get(columnIndex));
    }

    public List<String> getColumnLabels() {
        return this.columnLabels;
    }

    public int getRecordLength() {
        if (this.current == null) throw new EmptyBufferException();

        return this.current.length;
    }

    public boolean isMissing(int columnIndex) {
        return columnIndex >= this.getRecordLength();
    }

    public boolean isMissing(String columnName) {
        return (!this.columnLabelsToInt.containsKey(columnName) || this.columnLabelsToInt.get(columnName) >= this.getRecordLength());
    }

    protected String[] split(String line) {
        String[] splitLine = line.split(this.delimiter + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        for (int i = 0; i < splitLine.length; i++) {
            splitLine[i] = this.trimQuotes(splitLine[i]);
        }

        return splitLine;
    }

    protected String trimQuotes(String str) {
        return str.replaceAll("^\"", "").replaceAll("\"$", "");
    }

    protected int getColumnIndex(String columnName) {
        if (!this.columnLabelsToInt.containsKey(columnName)) {
            throw new ColumnNotFoundException(columnName);
        }

        return this.columnLabelsToInt.get(columnName);
    }
}
