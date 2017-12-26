package lab10;

import java.awt.*;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class Branch extends BaseXmasShape {
    public static final int left = 0;
    public static final int right = 1;
    int direction;

    public Branch(int x, int y, double scale) {
        this(x, y, scale, Branch.right);
    }

    public Branch(int x, int y, double scale, int direction) {
        super(x, y, scale);
        this.direction = direction;
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 255));
        g2d.setStroke(new BasicStroke(4, CAP_ROUND, JOIN_MITER));

        int coordinate = (int) ((this.direction == Branch.right)
            ? this.x + (100 * this.scale)
            : this.x - (100 * this.scale));
        // int start = (this.x < coordinate) ? this.x : coordinate;
        // int end = (this.x >= coordinate) ? this.x : coordinate;

        // System.out.printf("Draw branch from %d to %d at %d.\n", start, end, this.y);
        g2d.drawLine(this.x, this.y, coordinate, this.y - 10);

        // g2d.setColor(new Color(68, 255, 25));
        // g2d.setStroke(new BasicStroke(5));
        //
        // g2d.drawLine(500, 590, 600, 590);
        // g2d.drawLine(400, 570, 500, 570);
    }
}
