package lab7;

import app.Helpers;
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
    protected Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

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

            this.fillUnitWithReader(unit, reader);
            this.fillListDetails(unit, reader);
        }

        this.addParentsToUnits();
        this.addChildrenToUnits();
    }

    protected void fillListDetails(AdminUnit unit, CSVReader reader) {
        this.idToAdminUnit.put(unit.id, unit);

        if (!reader.isMissing("parent")) this.adminUnitToParentId.put(unit, reader.getLong("parent"));

        this.units.add(unit);
    }

    protected void fillUnitWithReader(AdminUnit unit, CSVReader reader) {
        if (!reader.isMissing("id")) unit.setId(reader.getLong("id"));
        if (!reader.isMissing("name")) unit.setName(reader.get("name"));
        if (!reader.isMissing("admin_level")) unit.setAdminLevel(reader.getInt("admin_level"));
        if (!reader.isMissing("population")) unit.setPopulation(reader.getDouble("population"));
        if (!reader.isMissing("area")) unit.setArea(reader.getDouble("area"));
        if (!reader.isMissing("density")) unit.setDensity(reader.getDouble("density"));

        if (!reader.isMissing("x1") && !reader.isMissing("y1"))
            unit.getBoundingBox().addPoint(reader.getDouble("x1"), reader.getDouble("y1"));
        if (!reader.isMissing("x2") && !reader.isMissing("y2"))
            unit.getBoundingBox().addPoint(reader.getDouble("x2"), reader.getDouble("y2"));
        if (!reader.isMissing("x3") && !reader.isMissing("y3"))
            unit.getBoundingBox().addPoint(reader.getDouble("x3"), reader.getDouble("y3"));
        if (!reader.isMissing("x4") && !reader.isMissing("y4"))
            unit.getBoundingBox().addPoint(reader.getDouble("x4"), reader.getDouble("y4"));
        if (!reader.isMissing("x5") && !reader.isMissing("y5"))
            unit.getBoundingBox().addPoint(reader.getDouble("x5"), reader.getDouble("y5"));
    }

    /**
     * Adds children to units' objects
     */
    protected void addChildrenToUnits() {
        this.units
            .stream()
            .filter(unit -> unit.parent != null)
            .forEach(unit -> {
                if (this.parentIdToChildren.get(unit.parent.getId()) != null) {
                    this.parentIdToChildren.get(unit.parent.getId()).add(unit);
                } else {
                    this.parentIdToChildren.put(unit.parent.getId(), Helpers.tap(new ArrayList<>(), list -> list.add(unit)));
                }
            });

        this.parentIdToChildren.forEach((parentId, children) -> this.idToAdminUnit.get(parentId).setChildren(children));
    }

    /**
     * Adds parents to units' objects
     *
     * @throws InvalidParentException when there is no parent of given id found
     */
    protected void addParentsToUnits() {
        this.adminUnitToParentId.forEach((unit, parentId) -> {
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

        this.units
            .subList(offset, offset + limit)
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
            this.units
                .stream()
                .filter(unit -> (regex && unit.getName().matches(pattern)) || (!regex && unit.getName().contains(pattern)))
                .collect(Collectors.toList())
        );
    }

    protected void fixMissingValues() {
        this.units.forEach(AdminUnit::fixMissingValues);
    }
}
