package lab7;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        AdminUnitList units = new AdminUnitList();
        String filename = System.getProperty("user.dir") + ("/src/lab7/assets/admin-units.csv".replace("\\/", java.nio.file.FileSystems.getDefault().getSeparator()));

        try {
            units.read(filename);
            units.fixMissingValues();

            /* Listing all units */
            // units.list(System.out);

            /* Listing 'limit' of units starting from the 'offset' */
            // units.list(System.out, 0, 100);

            /* Listing units containing 'Kraków' in the name */
            // units.selectByName("Kraków", false).list(System.out);

            /* Listing neighbors of Kraków */
            // units.getNeighbors(units.units.get(7341), 25).list(System.out);

            /* Getting neighbors of all units. Execution time: 94.280 seconds */
            // for (int i = 0; i < units.units.size(); i++) {
            //     units.getNeighbors(units.units.get(i), 25);
            // }
        } catch (IOException e) {
            System.out.println("Failed to open a file");
        }
    }
}
