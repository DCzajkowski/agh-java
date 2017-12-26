package lab10;

import java.awt.*;

public class Star extends BaseXmasShape {
    public Color color = new Color(255, 255, 255);

    public Star(int x, int y, double scale) {
        super(x, y, scale);
    }

    public void render(Graphics2D g2d) {
        this.transform(g2d);

        g2d.setColor(this.color);

        g2d.fillPolygon(new int[]{
            55, 67, 109, 73, 83, 55, 27, 37, 1, 43
        }, new int[]{
            0, 36, 36, 54, 96, 72, 96, 54, 36, 36
        }, 10);
    }
}
