package lab7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList units = new AdminUnitList();
        String filename = System.getProperty("user.dir") + ("/src/lab7/assets/admin-units.csv".replace("\\/", java.nio.file.FileSystems.getDefault().getSeparator()));

        try {
            units.read(filename);
            units.list(System.out, 2525, 10);
        } catch (IOException e) {
            System.out.println("Failed to open a file");
        }
    }
}
