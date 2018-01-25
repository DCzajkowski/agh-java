package lab14;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {
    static int width = 700 - 6;
    static int height = 700 - 66;

    static class Ball {
        int x;
        int y;
        int size;
        double vx;
        double vy;
        Color color;

        Ball() {
            Random r = new Random();

            this.x = r.nextInt(width);
            this.y = r.nextInt(height);
            this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
            this.vx = (r.nextInt(2) == 1) ? -10 : 10;
            this.vy = (r.nextInt(2) == 1) ? -10 : 10;
            this.size = r.nextInt(15) + 5;
        }

        public void draw(Graphics2D g2d) {
            AffineTransform saveAT = g2d.getTransform();
            this.render(g2d);
            g2d.setTransform(saveAT);
        }

        public void render(Graphics2D g2d) {
            g2d.setColor(this.color);

            g2d.fillOval(this.x, this.y, this.size, this.size);
        }
    }

    List<Ball> balls = new ArrayList<>();
    Thread animation = new AnimationThread();

    class AnimationThread extends Thread {
        public void run() {
            while (true) {
                balls.forEach(ball -> {
                    ball.x += ball.vx;
                    ball.y += ball.vy;

                    if (ball.x < 0 || ball.x > width - ball.size) ball.vx *= -1;
                    if (ball.y < 0 || ball.y > height - ball.size) ball.vy *= -1;
                });

                repaint();

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BouncingBallsPanel() {
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    void onStart() {
        System.out.println("Start or resume animation thread");

        this.animation.start();
    }

    void onStop() {
        System.out.println("Suspend animation thread");

        this.animation.suspend();
    }

    void onPlus() {
        System.out.println("Add a ball");

        this.balls.add(new Ball());
    }

    void onMinus() {
        System.out.println("Remove a ball");

        if (!this.balls.isEmpty()) this.balls.remove(0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        balls.forEach(ball -> ball.draw((Graphics2D) g));
    }
}
