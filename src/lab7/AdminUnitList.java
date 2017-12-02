package lab7;

import lab6.CSVReader.CSVReader;
import lab7.Exceptions.InvalidOffsetException;
import lab7.Exceptions.InvalidParentException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminUnitList {
    protected List<AdminUnit> units = new ArrayList<>();
    protected HashMap<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    protected HashMap<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

    public AdminUnitList() {
        //
    }

    private AdminUnitList(List<AdminUnit> units) {
        this.units = units;
    }

    /**
     * Reads units from a file into a buffer
     *
     * @param filename
     * @throws InvalidParentException when there is no parent of given id found
     */
    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ",", true);

        while (reader.next()) {
            AdminUnit unit = new AdminUnit();

            if (!reader.isMissing("id")) unit.setId(reader.getLong("id"));
            if (!reader.isMissing("name")) unit.setName(reader.get("name"));
            if (!reader.isMissing("admin_level")) unit.setAdminLevel(reader.getInt("admin_level"));
            if (!reader.isMissing("population")) unit.setPopulation(reader.getDouble("population"));
            if (!reader.isMissing("area")) unit.setArea(reader.getDouble("area"));
            if (!reader.isMissing("density")) unit.setDensity(reader.getDouble("density"));

            this.idToAdminUnit.put(unit.id, unit);
            if (!reader.isMissing("parent")) this.adminUnitToParentId.put(unit, reader.getLong("parent"));

            this.units.add(unit);
        }

        this.addParentsToUnits();
        this.addChildrenToUnits();
    }

    /**
     * Adds children to units' objects
     */
    protected void addChildrenToUnits() {
        this.units.forEach(unit -> {
            if (unit.parent == null) return;

            if (this.parentIdToChildren.get(unit.parent.getId()) != null) {
                this.parentIdToChildren.get(unit.parent.getId()).add(unit);
            } else {
                this.parentIdToChildren.put(unit.parent.getId(), new ArrayList<AdminUnit>() {{
                    add(unit);
                }});
            }
        });

        this.parentIdToChildren.forEach((Long parentId, List<AdminUnit> children) -> {
            this.idToAdminUnit.get(parentId).setChildren(children);
        });
    }

    /**
     * Adds parents to units' objects
     *
     * @throws InvalidParentException when there is no parent of given id found
     */
    protected void addParentsToUnits() {
        this.adminUnitToParentId.forEach((AdminUnit unit, Long parentId) -> {
            if (this.idToAdminUnit.get(parentId) == null) throw new InvalidParentException(parentId);

            this.idToAdminUnit.get(unit.id).setParent(this.idToAdminUnit.get(parentId));
        });
    }

    /**
     * Prints every admin unit using AdminUnit.toString()
     *
     * @param out
     */
    public void list(PrintStream out) {
        this.units.forEach(out::println);
    }

    /**
     * Prints at most limit of elements starting at offset
     *
     * @param out
     * @param offset - where to start printing
     * @param limit  - hom many elements (at most) to print
     */
    public void list(PrintStream out, int offset, int limit) {
        if (offset > this.units.size() || offset < 0) {
            throw new InvalidOffsetException(this.units.size());
        }

        if (limit + offset > this.units.size()) {
            limit = this.units.size() - offset;
        }

        this.units.subList(offset, offset + limit)
            .forEach(unit -> out.println(unit.toString()));
    }

    /**
     * Appends an AdminUnit into a list
     *
     * @param unit
     */
    public void add(AdminUnit unit) {
        this.units.add(unit);
    }

    /**
     * Returns a new list containing these units, which names match the pattern
     *
     * @param pattern
     * @param regex   - is regular expression
     * @return AdminUnitList containing only matching units
     */
    public AdminUnitList selectByName(String pattern, boolean regex) {
        return new AdminUnitList(
            this.units.stream()
                .filter(unit ->
                    (regex && unit.getName().matches(pattern))
                        || (!regex && unit.getName().contains(pattern))
                )
                .collect(Collectors.toList())
        );
    }

    protected void fixMissingValues() {
        this.units.forEach(AdminUnit::fixMissingValues);
    }
}
