package lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        this.reader = new BufferedReader(new FileReader(filename));
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

        String[] header = line.split(this.delimiter);

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

        this.current = line.split(this.delimiter);

        return true;
    }

    public String get(String columnName) {
        return this.get(columnLabelsToInt.get(columnName));
    }

    public String get(int i) {
        return this.current[i];
    }

    public int getInt(String columnName) {
        return Integer.parseInt(this.get(columnName));
    }

    public int getInt(int i) {
        return Integer.parseInt(this.get(i));
    }

    public double getDouble(String columnName) {
        return Double.parseDouble(this.get(columnName));
    }

    public double getDouble(int i) {
        return Double.parseDouble(this.get(i));
    }
}
