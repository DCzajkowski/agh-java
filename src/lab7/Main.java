package lab7;

import java.io.IOException;
import java.util.Comparator;

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

            /* Listing neighbors of gmina Zielonki (not a city) */
            // units.getNeighbors(units.units.get(54), 0).list(System.out); // distance is ignored for non-cities

            /* Listing neighbors of Smardzowice (city) */
            // units.getNeighbors(units.units.get(15212), 10).list(System.out);

            /* Getting neighbors of all units. Execution time: 94.280 seconds */
            // for (int i = 0; i < units.units.size(); i++) {
            //     units.getNeighbors(units.units.get(i), 25);
            // }

            /* Sorting in place */
            // units.sortInPlaceByName().list(System.out, 0, 100);
            // units.sortInPlaceByArea().list(System.out, 0, 100);

            /* Sorting by return */
            // AdminUnitList unitsSorted = units.sort(Comparator.comparing(AdminUnit::getName));

            // units.list(System.out, 0, 5);
            // unitsSorted.list(System.out, 0, 5);

            /* Filtering */
            // units.filter(unit -> unit.name.startsWith("Ż")).sortByArea().list(System.out);
            // units.filter(unit -> unit.name.startsWith("A"), 2, 5).list(System.out);

            /* Query */
            // new AdminUnitQuery()
            //     .selectFrom(units)
            //     .where(a -> a.area > 1000)
            //     .or(a -> a.name.startsWith("Sz"))
            //     .sort(Comparator.comparingDouble(a -> a.area))
            //     .limit(100)
            //     .execute()
            //     .list(System.out);
        } catch (IOException e) {
            System.out.println("Failed to open a file");
        }
    }
}
