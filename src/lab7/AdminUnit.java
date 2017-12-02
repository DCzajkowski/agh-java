package lab7;

public class AdminUnit {
    protected long id;
    protected String name;
    protected int adminLevel;
    protected double population;
    protected double area;
    protected double density;
    protected AdminUnit parent;
    protected BoundingBox boundingbox = new BoundingBox();

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

    public BoundingBox getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(BoundingBox boundingbox) {
        this.boundingbox = boundingbox;
    }

    @Override
    public String toString() {
        return (this.parent != null)
            ? String.format("Name is %s and population is %f. Area is %f. Its parent is %s.", this.name, this.population, this.area, this.parent.getName())
            : String.format("Name is %s and population is %f. Area is %f.", this.name, this.population, this.area);
    }
}
