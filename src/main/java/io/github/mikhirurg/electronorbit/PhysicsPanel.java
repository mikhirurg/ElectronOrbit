package io.github.mikhirurg.electronorbit;

import javax.swing.*;
import java.awt.*;

public class PhysicsPanel extends JPanel {
    public Particle electron;
    public Particle proton;
    public final Timer timer;
    private long startTime;
    private final int width;
    private final int height;
    private final int delay = 500;

    public PhysicsPanel() {
        electron = Particle.load("electron", Color.BLUE);
        proton = Particle.load("proton", Color.RED);
        width = Integer.parseInt(Application.getProperty("panel.width"));
        height = Integer.parseInt(Application.getProperty("panel.height"));
        timer = new Timer(20, e -> repaint());

        electron.x0 = electron.x;
        electron.y0 = electron.y;
        proton.x0 = proton.x;
        proton.y0 = proton.y;

        electron.x = electron.x0 + electron.vx0 * delay / 1e3;
        electron.y = electron.y0 + electron.vy0 * delay / 1e3;
        proton.x = proton.x0 + proton.vx0 * delay / 1e3;
        proton.y = proton.y0 + proton.vy0 * delay / 1e3;

        setPreferredSize(new Dimension(width, height));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        long currentTime = System.currentTimeMillis();
        double time = (currentTime - startTime) / 1e3;
        double R;
        double beta = 1.0 / 1836;
        int n = 100;
        double h = (delay / 1e3) / n;

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
        g2d.drawString(String.valueOf(time), 0, g2d.getFontMetrics().getHeight());

        R = Math.sqrt((electron.x - proton.x) * (electron.x - proton.x)
                + (electron.y - proton.y) * (electron.y - proton.y));

        for (int i = 0; i < n; i++) {
            double ex = electron.x;
            double ey = electron.y;
            double px = proton.x;
            double py = proton.y;
            double vex = electron.vx;
            double vey = electron.vy;
            double vpx = proton.vx;
            double vpy = proton.vy;

            R = Math.sqrt((electron.x - proton.x) * (electron.x - proton.x)
                    + (electron.y - proton.y) * (electron.y - proton.y));

            electron.x = h * electron.vx0 + electron.x0;
            electron.y = h * electron.vy0 + electron.y0;

            proton.x = h * proton.vx0 + proton.x0;
            proton.y = h * proton.vy0 + proton.y0;

            electron.vx = -h * electron.vy0 + h * (proton.x0 - electron.x0) / (R * R * R) + electron.vx0;
            electron.vy = h * electron.vx0 + h * (proton.y0 - electron.y0) / (R * R * R) + electron.vy0;

            proton.vx = h * beta * (proton.vy0 - (proton.x0 - electron.x0) / (R * R * R)) + proton.vx0;
            proton.vy = -h * beta * (proton.vx0 + (proton.y0 - electron.y0) / (R * R * R)) + proton.vy0;


            electron.x0 = ex;
            electron.y0 = ey;
            electron.vx0 = vex;
            electron.vy0 = vey;

            proton.x0 = px;
            proton.y0 = py;
            proton.vx0 = vpx;
            proton.vy0 = vpy;

            electron.draw(g, width, height);
            proton.draw(g, width, height);

        }

        electron.x = h * electron.vx0 + electron.x0;
        electron.y = h * electron.vy0 + electron.y0;

        proton.x = h * proton.vx0 + proton.x0;
        proton.y = h * proton.vy0 + proton.y0;

        electron.vx = -h * electron.vy0 + h * (proton.x0 - electron.x0) / (R * R * R) + electron.vx0;
        electron.vy = h * electron.vx0 + h * (proton.y0 - electron.y0) / (R * R * R) + electron.vy0;

        proton.vx = h * beta * (proton.vy0 - (proton.x0 - electron.x0) / (R * R * R)) + proton.vx0;
        proton.vy = -h * beta * (proton.vx0 + (proton.y0 - electron.y0) / (R * R * R)) + proton.vy0;
    }


    public void start() {
        startTime = System.currentTimeMillis();
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
