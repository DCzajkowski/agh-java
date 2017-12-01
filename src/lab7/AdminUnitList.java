package lab7;

import lab6.CSVReader.CSVReader;
import lab7.Exceptions.InvalidOffsetException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AdminUnitList {
    protected List<AdminUnit> units = new ArrayList<>();

    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);

        while (reader.next()) {
            AdminUnit unit = new AdminUnit();

            if (!reader.isMissing("name")) unit.setName(reader.get("name"));
            if (!reader.isMissing("admin_level")) unit.setAdminLevel(reader.getInt("admin_level"));
            if (!reader.isMissing("population")) unit.setPopulation(reader.getDouble("population"));
            if (!reader.isMissing("area")) unit.setArea(reader.getDouble("area"));
            if (!reader.isMissing("density")) unit.setDensity(reader.getDouble("density"));

            this.units.add(unit);
        }
    }

    /**
     * Prints every admin unit using AdminUnit.toString()
     *
     * @param out
     */
    void list(PrintStream out) {
        this.units.forEach(unit -> out.println(unit.toString()));
    }

    /**
     * Prints at most limit of elements starting at offset
     *
     * @param out
     * @param offset - where to start printing
     * @param limit  - hom many elements (at most) to print
     */
    void list(PrintStream out, int offset, int limit) {
        if (offset > this.units.size() || offset < 0) {
            throw new InvalidOffsetException(this.units.size());
        }

        if (limit + offset > this.units.size()) {
            limit = this.units.size() - offset;
        }

        for (int i = offset; i < offset + limit; i++) {
            try {
                out.println(this.units.get(i).toString());
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
    }

    /**
     * Appends an AdminUnit into a list
     *
     * @param unit
     */
    void add(AdminUnit unit) {
        this.units.add(unit);
    }

    /**
     * Returns a new list containing these units, which names match the pattern
     *
     * @param pattern
     * @param regex   - is regular expression
     * @return AdminUnitList containing only matching units
     */
    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList result = new AdminUnitList();

        for (AdminUnit unit : this.units) {
            if (
                (regex && unit.getName().matches(pattern))
                    || (!regex && unit.getName().contains(pattern))
                ) {
                result.add(unit);
            }
        }

        return result;
    }
}
