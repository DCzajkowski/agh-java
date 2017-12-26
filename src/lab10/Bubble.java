package lab10;

import java.awt.*;

public class Bubble extends BaseXmasShape {
    public Color lineColor;
    public Color fillColor;

    public Bubble(int x, int y, double scale) {
        super(x, y, scale);
    }

    public void render(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(1));

        g2d.setColor(this.fillColor);
        g2d.fillOval(this.x, this.y, (int) (100 * this.scale), (int) (100 * this.scale));
        g2d.setColor(this.lineColor);
        g2d.drawOval(this.x, this.y, (int) (100 * this.scale), (int) (100 * this.scale));
    }
}
