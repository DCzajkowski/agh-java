package lab10;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.Helpers.tap;
import static java.lang.Math.sqrt;

public class DrawPanel extends JPanel {
    public List<XmasShape> shapes = new ArrayList<>();

    public DrawPanel() {
        setBackground(new Color(191, 16, 0));

        Tree tree = new Tree(Main.width / 2, Main.height - 100, 1, 400);

        for (double i = 1; i < 41; i += 2) {
            tree.addBranch((int) i * 10, (i % 4 == 0) ? Branch.right : Branch.left, (41 - i) / 20);
            tree.addBranch((int) i * 10 + 10, (i % 4 == 0) ? Branch.left : Branch.right, (41 - i) / 20);
        }

        this.shapes.add(tree);

        for (int i = 0; i < 100; i++) {
            this.shapes.add(new Star(new Random().nextInt(Main.width), new Random().nextInt(Main.height), .1));
        }

        for (int i = 0; i < 100; i++) {
            this.shapes.add(new Light(new Random().nextInt(Main.width), new Random().nextInt(Main.height), .08));
        }

        for (int i = 0; i < 100; i++) {
            double r1 = new Random().nextDouble();
            double r2 = new Random().nextDouble();

            this.shapes.add(tap(new Bubble(
                (int) ((1 - sqrt(r1)) * 300 + (sqrt(r1) * (1 - r2)) * 700 + (sqrt(r1) * r2) * 500),
                (int) ((1 - sqrt(r1)) * 540 + (sqrt(r1) * (1 - r2)) * 540 + (sqrt(r1) * r2) * 160),
                .1
            ), bubble -> {
                bubble.lineColor = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
                bubble.fillColor = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
            }));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        shapes.forEach(shape -> shape.draw((Graphics2D) g));
    }
}
