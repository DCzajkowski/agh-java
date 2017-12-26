package lab10;

import javax.swing.*;

public class Main {
    public final static int width = 1000;
    public final static int height = 700;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Christmas Tree");
        frame.setSize(Main.width, Main.height);
        frame.setContentPane(new DrawPanel());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
