package lab6.CSVReader;

import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        try {
            Main.withHeader();
            System.out.println();
            Main.withoutHeader();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    private static void withHeader() throws IOException {
        CSVReader reader = new CSVReader("/Users/Darek/Documents/Development/AGH/agh-java/src/CSVReader.CSVReader/assets/with-header.csv", ";", true);

        while (reader.next()) {
            int id = reader.getInt("id");
            String surname = reader.get("nazwisko");
            double home = reader.getDouble("nrdomu");

            System.out.printf(Locale.US, "%d %s %f", id, surname, home);
        }
    }

    private static void withoutHeader() throws IOException {
        CSVReader reader = new CSVReader("/Users/Darek/Documents/Development/AGH/agh-java/src/CSVReader.CSVReader/assets/no-header.csv", ";", false);

        while (reader.next()) {
            int id = reader.getInt(0);
            String surname = reader.get(2);
            double home = reader.getDouble(4);

            System.out.printf(Locale.US, "%d %s %f", id, surname, home);
        }
    }
}
