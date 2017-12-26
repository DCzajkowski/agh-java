package lab10;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class BaseXmasShape implements XmasShape {
    public int x;
    public int y;
    public double scale;

    public BaseXmasShape(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    public void transform(Graphics2D g2d) {
        g2d.translate(this.x, this.y);
        g2d.scale(this.scale, this.scale);
    }

    public void draw(Graphics2D g2d) {
        AffineTransform saveAT = g2d.getTransform();
        // this.transform(g2d);
        this.render(g2d);
        g2d.setTransform(saveAT);
    }
}
