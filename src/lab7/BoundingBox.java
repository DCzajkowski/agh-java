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

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     *
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y) {
        return (!this.isEmpty() && x > this.xmin && x < this.xmax && y > this.ymin && y < this.ymax);
    }

    /**
     * Sprawdza czy dany BB zawiera boundingBox
     *
     * @param boundingBox
     * @return
     */
    boolean contains(BoundingBox boundingBox) {
        return false;
    }

    /**
     * Sprawdza, czy dany BB przecina się z boundingBox
     *
     * @param boundingBox
     * @return
     */
    boolean intersects(BoundingBox boundingBox) {
        return false;
    }

    /**
     * Powiększa rozmiary tak, aby zawierał boundingBox oraz poprzednią wersję this
     *
     * @param boundingBox
     * @return
     */
    BoundingBox add(BoundingBox boundingBox) {
        return this;
    }

    /**
     * Sprawdza czy BB jest pusty
     *
     * @return
     */
    boolean isEmpty() {
        return (this.xmin == null || this.ymin == null || this.xmax == null || this.ymax == null);
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     *
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() {
        return (this.xmax - this.xmin) / 2;
    }

    /**
     * Oblicza i zwraca współrzędną y środka
     *
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() {
        return (this.ymax - this.ymin) / 2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz boundingBox
     *
     * @param boundingBox prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox boundingBox) {
        throw new RuntimeException("Not implemented");
    }
}
