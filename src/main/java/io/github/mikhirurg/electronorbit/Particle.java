package io.github.mikhirurg.electronorbit;

import java.awt.*;

public class Particle {
    public double x, y;
    public double x0, y0;
    public double vx, vy;
    public double vx0, vy0;
    private final Color color;
    public final double r;
    public final double q;

    public static Particle load(String name, Color color) {
        return new Particle(
                Double.parseDouble(Application.getProperty(name + ".x")),
                Double.parseDouble(Application.getProperty(name + ".y")),
                Double.parseDouble(Application.getProperty(name + ".vx")),
                Double.parseDouble(Application.getProperty(name + ".vy")),
                Double.parseDouble(Application.getProperty(name + ".r")),
                Double.parseDouble(Application.getProperty(name + ".q")),
                color
        );
    }

    public Particle(double x, double y, double vx, double vy, double r, double q, Color color) {
        this.x0 = x;
        this.y0 = y;
        this.x = x;
        this.y = y;
        this.r = r;
        this.q = q;
        this.vx0 = vx;
        this.vy0 = vy;
        this.color = color;
    }

    public void draw(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        double nx = x;
        double ny = y;
        g2d.fillOval((int)(nx - r / 2 + width / 2), (int)(ny - r / 2 + height / 2), (int) r / 2, (int) r / 2);
    }
}
