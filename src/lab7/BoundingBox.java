package lab7;

import java.util.ArrayList;
import java.util.List;

public class BoundingBox {
    protected class Point {
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public Double xmin;
    public Double ymin;
    public Double xmax;
    public Double ymax;

    protected List<Point> points = new ArrayList<>();

    void addPoint(double x, double y) {
        this.points.add(new Point(x, y));

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

    @Override
    public String toString() {
        return String.format("Bounding box most outer coordinates are: %f, %f; %f, %f.", this.xmin, this.ymin, this.xmax, this.ymax);
    }
}
