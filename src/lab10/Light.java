package lab10;

import java.awt.*;

public class Light extends BaseXmasShape {
    public Light(int x, int y, double scale) {
        super(x, y, scale);
    }

    public void render(Graphics2D g2d) {
        this.transform(g2d);

        for (int i = 0; i < 12; i++) {
            g2d.drawLine(0, 0, 100, 100);
            g2d.rotate(2 * Math.PI / 12);
        }
    }
}
