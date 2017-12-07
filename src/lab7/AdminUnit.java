package lab7;

import java.util.List;
import java.util.stream.Collectors;

public class AdminUnit {
    protected long id;
    protected String name;
    protected int adminLevel;
    protected double population;
    protected double area;
    protected double density;
    protected AdminUnit parent;
    protected BoundingBox boundingBox = new BoundingBox();
    protected List<AdminUnit> children;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<AdminUnit> getChildren() {
        return this.children;
    }

    public void setChildren(List<AdminUnit> children) {
        this.children = children;
    }

    public void addChild(AdminUnit unit) {
        this.children.add(unit);
    }

    public void fixMissingValues() {
        if (this.parent != null && (this.parent.getArea() == 0 || this.parent.getDensity() == 0))
            this.parent.fixMissingValues();
        if (this.parent != null && this.area == 0) this.area = this.parent.getArea();
        if (this.parent != null && this.density == 0) this.density = this.parent.getDensity();
        if (this.population == 0) this.population = this.area * this.density;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("Name is %s and population is %f. Area is %f.", this.name, this.population, this.area));

        if (this.parent != null) {
            result.append(String.format(" Its parent is %s.", this.parent.getName()));
        }

        if (this.children != null && this.children.size() > 0) {
            result.append(String.format(" Its children are: %s.", String.join(", ", this.children.stream().map(AdminUnit::getName).collect(Collectors.toList()))));
        }

        return result.toString();
    }
}
