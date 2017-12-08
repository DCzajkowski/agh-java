package lab7;

import lab7.Exceptions.NoEnoughPointsException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList units = new AdminUnitList();
        String filename = System.getProperty("user.dir") + ("/src/lab7/assets/admin-units.csv".replace("\\/", java.nio.file.FileSystems.getDefault().getSeparator()));

        try {
            units.read(filename);
            units.fixMissingValues();

//            units.list(System.out);
//             units.list(System.out, 0, 100);
            AdminUnitList result = units;
//            AdminUnitList result = units.selectByName("Kraków", false);
            // AdminUnitList result = units.selectByName("małopolskie", false);

            result.units.forEach(unit -> {
                System.out.print(unit);
                try {
                    System.out.println(" " + unit.getWKT());
                } catch (NoEnoughPointsException _) {
                    //
                }
            });
//            result.list(System.out);
        } catch (IOException e) {
            System.out.println("Failed to open a file");
        }
    }
}
