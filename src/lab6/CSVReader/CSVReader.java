package lab6.CSVReader;

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

    // column names in the same order as in the file
    protected List<String> columnLabels = new ArrayList<>();

    // map: column name -> column number
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

        if (!this.isValid(line)) {
            throw new RuntimeException("Invalid line encountered. There are no sufficient values in the line");
        }

        this.current = this.split(line);

        return true;
    }

    private boolean isValid(String line) {
        return this.columnLabels.isEmpty() || this.split(line).length == this.columnLabels.size();
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

    public String get(String columnName) {
        System.out.println(this.columnLabelsToInt);
        return this.get(this.getColumnIndex(columnName));
    }

    protected int getColumnIndex(String columnName) {
        if (!this.columnLabelsToInt.containsKey(columnName)) {
            throw new RuntimeException("There is no column with the name '" + columnName + "'.");
        }

        return this.columnLabelsToInt.get(columnName);
    }

    public String get(int columnIndex) {
        if (this.current == null) {
            throw new RuntimeException("No lines in a buffer. Have you ran .next() on your reader?");
        }

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

    long getLong(String columnName) {
        return Long.parseLong(this.get(columnName));
    }

    long getLong(int columnIndex) {
        return Long.parseLong(this.get(columnIndex));
    }

    List<String> getColumnLabels() {
        return this.columnLabels;
    }

    int getRecordLength() {
        return String.join(",", this.current).length();
    }

    boolean isMissing(int columnIndex) {
        return (this.get(columnIndex) != null && !this.get(columnIndex).isEmpty());
    }

    boolean isMissing(String columnName) {
        return (this.get(columnName) != null && !this.get(columnName).isEmpty());
    }
}
