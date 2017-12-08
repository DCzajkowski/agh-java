package lab7;

public class BoundingBox {
    public Double xmin;
    public Double ymin;
    public Double xmax;
    public Double ymax;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     *
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y) {
        if (this.isEmpty()) {
            this.xmax = x;
            this.xmin = x;
            this.ymax = y;
            this.ymin = y;
        } else {
            if (x > this.xmax) this.xmax = x;
            if (x < this.xmin) this.xmin = x;
            if (y > this.ymax) this.ymax = x;
            if (y < this.ymin) this.ymin = x;
        }
    }

    boolean contains(double x, double y) {
        return (!this.isEmpty() && x > this.xmin && x < this.xmax && y > this.ymin && y < this.ymax);
    }

    boolean contains(BoundingBox boundingBox) {
        return false;
    }

    boolean intersects(BoundingBox boundingBox) {
        return false;
    }

    BoundingBox add(BoundingBox boundingBox) {
        return this;
    }

    boolean isEmpty() {
        return (this.xmin == null || this.ymin == null || this.xmax == null || this.ymax == null);
    }

    double getCenterX() {
        return (this.xmax - this.xmin) / 2;
    }

    double getCenterY() {
        return (this.ymax - this.ymin) / 2;
    }

    double distanceTo(BoundingBox boundingBox) {
        throw new RuntimeException("Not implemented");
    }
}
