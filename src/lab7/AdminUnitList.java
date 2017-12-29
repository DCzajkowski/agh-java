package lab7;

import lab6.CSVReader.CSVReader;
import lab7.Exceptions.InvalidOffsetException;
import lab7.Exceptions.InvalidParentException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static app.Helpers.tap;

public class AdminUnitList {
    protected List<AdminUnit> units = new ArrayList<>();
    protected Map<Long, AdminUnit> idToAdminUnit = new HashMap<>();
    protected Map<AdminUnit, Long> adminUnitToParentId = new HashMap<>();
    protected Map<Long, List<AdminUnit>> parentIdToChildren = new HashMap<>();

    public AdminUnitList() {
        //
    }

    protected AdminUnitList(List<AdminUnit> units) {
        this.units = units;
    }

    protected AdminUnitList(Stream<AdminUnit> units) {
        this.units = units.collect(Collectors.toList());
    }

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

    protected void addChildrenToUnits() {
        this.units
            .stream()
            .filter(unit -> unit.parent != null)
            .forEach(unit -> {
                if (this.parentIdToChildren.get(unit.parent.getId()) != null) {
                    this.parentIdToChildren.get(unit.parent.getId()).add(unit);
                } else {
                    this.parentIdToChildren.put(unit.parent.getId(), tap(new ArrayList<>(), list -> list.add(unit)));
                }
            });

        this.parentIdToChildren.forEach((parentId, children) -> this.idToAdminUnit.get(parentId).setChildren(children));
    }

    protected void addParentsToUnits() {
        this.adminUnitToParentId.forEach((unit, parentId) -> {
            if (this.idToAdminUnit.get(parentId) == null) throw new InvalidParentException(parentId);

            this.idToAdminUnit.get(unit.id).setParent(this.idToAdminUnit.get(parentId));
        });
    }

    public void list(PrintStream out) {
        this.units.forEach(out::println);
    }

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

    public void add(AdminUnit unit) {
        this.units.add(unit);
    }

    public AdminUnitList selectByName(String pattern, boolean regex) {
        return new AdminUnitList(
            this.units
                .stream()
                .filter(unit -> (regex && unit.getName().matches(pattern)) || (!regex && unit.getName().contains(pattern)))
        );
    }

    public AdminUnitList getNeighbors(AdminUnit unit, double maxDistance) {
        return new AdminUnitList(
            this.units
                .stream()
                .filter(u -> u.getAdminLevel() == unit.getAdminLevel())
                .filter(u -> !u.getBoundingBox().isEmpty())
                .filter(u -> !unit.getBoundingBox().isEmpty())
                .filter(u -> (u.getAdminLevel() != 8 && u.getBoundingBox().intersects(unit.getBoundingBox())) // if different than city, test for intersecting
                    || (u.getAdminLevel() == 8 && u.getBoundingBox().distanceTo(unit.getBoundingBox()) <= maxDistance)) // if city, test for distance)
                .filter(u -> u.getId() != unit.getId())
        );
    }

    protected void fixMissingValues() {
        this.units.forEach(AdminUnit::fixMissingValues);
    }

    /**
     * As per exercises' request, sorting using a class
     */
    public AdminUnitList sortInPlaceByName() {
        class AdminUnitListComparator implements Comparator<AdminUnit> {
            @Override
            public int compare(AdminUnit unit1, AdminUnit unit2) {
                return unit1.getName().compareTo(unit2.getName());
            }
        }

        return tap(this, self -> self.units.sort(new AdminUnitListComparator()));
    }

    /**
     * As per exercises' request, sorting using an anonymous class
     */
    public AdminUnitList sortInPlaceByArea() {
        return tap(this, self -> self.units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit unit1, AdminUnit unit2) {
                return Double.compare(unit1.getArea(), unit2.getArea());
            }
        }));
    }

    /**
     * As per exercises' request, sorting using a lambda function
     */
    public AdminUnitList sortInPlaceByPopulation() {
        return tap(this, self -> self.units.sort((unit1, unit2) -> Double.compare(unit1.getPopulation(), unit2.getPopulation())));
    }

    public AdminUnitList sortInPlace(Comparator<AdminUnit> comparator) {
        return tap(this, self -> self.units.sort(comparator));
    }

    public AdminUnitList sort(Comparator<AdminUnit> comparator) {
        return tap(new AdminUnitList(this.units.stream()), list -> list.sortInPlace(comparator));
    }

    public AdminUnitList filter(Predicate<AdminUnit> predicate) {
        return new AdminUnitList(this.units.stream().filter(predicate));
    }

    public AdminUnitList filter(Predicate<AdminUnit> predicate, int limit) {
        return new AdminUnitList(this.units.stream().filter(predicate).limit(limit));
    }

    public AdminUnitList filter(Predicate<AdminUnit> predicate, int offset, int limit) {
        return new AdminUnitList(this.units.stream().filter(predicate).skip(offset).limit(limit));
    }
}
