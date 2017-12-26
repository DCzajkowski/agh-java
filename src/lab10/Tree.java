package lab10;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class Tree extends BaseXmasShape {
    protected List<Branch> branches = new ArrayList<>();

    public int height;
    public final int baseHeight = 40;

    public Tree(int x, int y, double scale, int height) {
        super(x, y, scale);
        this.height = height;
    }

    public void addBranch(int height, int direction, double scale) {
        this.branches.add(new Branch(this.x, this.y - this.baseHeight - height, scale, direction));
    }

    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(255, 255, 255));
        g2d.setStroke(new BasicStroke(5, CAP_ROUND, JOIN_MITER));

        this.addBase(g2d);
        this.branches.forEach(branch -> branch.draw(g2d));
        this.addStar(g2d);
    }

    protected void addBase(Graphics2D g2d) {
        g2d.fillPolygon(new int[]{this.x - 20, this.x + 20, this.x + 10, this.x - 10}, new int[]{this.y, this.y, this.y - this.baseHeight, this.y - this.baseHeight}, 4);

        g2d.drawLine(this.x, this.y - this.baseHeight, this.x, this.y - this.baseHeight - this.height - 20);
    }

    protected void addStar(Graphics2D g2d) {
        g2d.setColor(new Color(255, 231, 0));
        g2d.translate(this.x - 20, this.y - this.baseHeight - this.height - 45);
        g2d.scale(.4, .4);

        g2d.fillPolygon(new int[]{
            55, 67, 109, 73, 83, 55, 27, 37, 1, 43
        }, new int[]{
            0, 36, 36, 54, 96, 72, 96, 54, 36, 36
        }, 10);
    }
}
