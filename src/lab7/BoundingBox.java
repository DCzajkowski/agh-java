package lab7;

import lab7.Exceptions.EmptyBoundingBoxException;

import java.util.ArrayList;
import java.util.List;

import static app.Helpers.tap;

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

    public void addPoint(double x, double y) {
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

    public boolean contains(double x, double y) {
        return (!this.isEmpty() && x > this.xmin && x < this.xmax && y > this.ymin && y < this.ymax);
    }

    public boolean contains(BoundingBox boundingBox) {
        return this.xmin < boundingBox.xmin && this.ymin < boundingBox.ymin && this.xmax > boundingBox.xmax && this.ymax > boundingBox.ymax;
    }

    public boolean intersects(BoundingBox boundingBox) {
        return (this.xmin <= boundingBox.xmin && this.xmax >= boundingBox.xmin && this.ymin <= boundingBox.ymin && this.ymax >= boundingBox.ymin)
            || (this.xmin <= boundingBox.xmax && this.xmax >= boundingBox.xmax && this.ymin <= boundingBox.ymin && this.ymax >= boundingBox.ymin)
            || (this.xmin <= boundingBox.xmax && this.xmax >= boundingBox.xmax && this.ymin <= boundingBox.ymax && this.ymax >= boundingBox.ymax)
            || (this.xmin <= boundingBox.xmin && this.xmax >= boundingBox.xmin && this.ymin <= boundingBox.ymax && this.ymax >= boundingBox.ymax);
    }

    public BoundingBox add(BoundingBox boundingBox) {
        return tap(new BoundingBox(), box -> {
            box.xmin = Math.min(this.xmin, boundingBox.xmin);
            box.ymin = Math.min(this.ymin, boundingBox.ymin);
            box.xmax = Math.max(this.xmax, boundingBox.xmax);
            box.ymax = Math.max(this.ymax, boundingBox.ymax);
        });
    }

    public boolean isEmpty() {
        return (this.xmin == null || this.ymin == null || this.xmax == null || this.ymax == null);
    }

    public double getCenterX() {
        return (this.xmax + this.xmin) / 2;
    }

    public double getCenterY() {
        return (this.ymax + this.ymin) / 2;
    }

    public double distanceTo(BoundingBox boundingBox) {
        if (this.isEmpty() || boundingBox.isEmpty()) {
            throw new EmptyBoundingBoxException();
        }

        return CoordinatesCalculator.distanceBetween(this.getCenterX(), this.getCenterY(), boundingBox.getCenterX(), boundingBox.getCenterY());
    }

    @Override
    public String toString() {
        return String.format("Bounding box most outer coordinates are: %f, %f; %f, %f.", this.xmin, this.ymin, this.xmax, this.ymax);
    }
}
