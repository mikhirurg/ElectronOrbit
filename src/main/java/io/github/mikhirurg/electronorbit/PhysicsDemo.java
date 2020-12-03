package io.github.mikhirurg.electronorbit;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.awt.*;

public class PhysicsDemo extends JFrame {

    private void buildGui() {
        setTitle(Application.getString("title"));

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 10;
        PhysicsPanel physicsPanel = new PhysicsPanel();
        add(physicsPanel, c);

        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets.right = 5;
        c.insets.bottom = 10;
        c.insets.top = 10;
        JLabel ex0 = new JLabel("Ex0:");
        add(ex0, c);

        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField ex0Field = new JTextField(Application.getProperty("electron.x"));
        add(ex0Field, c);

        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel ey0 = new JLabel("Ey0:");
        add(ey0, c);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField ey0Field = new JTextField(Application.getProperty("electron.y"));
        add(ey0Field, c);

        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel px0 = new JLabel("Px0:");
        add(px0, c);

        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField px0Field = new JTextField(Application.getProperty("proton.x"));
        add(px0Field, c);

        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel py0 = new JLabel("Py0:");
        add(py0, c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField py0Field = new JTextField(Application.getProperty("proton.y"));
        add(py0Field, c);

        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel eVx = new JLabel("Evx:");
        add(eVx, c);

        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField eVxField = new JTextField(Application.getProperty("electron.vx"));
        add(eVxField, c);

        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel eVy = new JLabel("Evy:");
        add(eVy, c);

        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField eVyField = new JTextField(Application.getProperty("electron.vy"));
        add(eVyField, c);

        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel pVx = new JLabel("Pvx:");
        add(pVx, c);

        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField pVxField = new JTextField(Application.getProperty("proton.vx"));
        add(pVxField, c);

        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        JLabel pVy = new JLabel("Pvy:");
        add(pVy, c);

        c.gridx = 4;
        c.gridy = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField pVyField = new JTextField(Application.getProperty("proton.vy"));
        add(pVyField, c);

        JButton start = new JButton(Application.getString("button.start"));
        JButton apply = new JButton(Application.getString("button.apply"));
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(apply, c);
        apply.addActionListener(e -> {
            start.setText(Application.getString("button.start"));
            physicsPanel.stop();
            try {
                double ex = Double.parseDouble(ex0Field.getText());
                double ey = Double.parseDouble(ey0Field.getText());
                double evx = Double.parseDouble(eVxField.getText());
                double evy = Double.parseDouble(eVyField.getText());
                physicsPanel.electron = new Particle(
                        ex, ey,
                        evx, evy,
                        Double.parseDouble(Application.getProperty("electron.r")),
                        Double.parseDouble(Application.getProperty("electron.q")),
                        Color.blue
                );
                double px = Double.parseDouble(px0Field.getText());
                double py = Double.parseDouble(py0Field.getText());
                double pvx = Double.parseDouble(pVxField.getText());
                double pvy = Double.parseDouble(pVyField.getText());
                physicsPanel.proton = new Particle(
                        px, py,
                        pvx, pvy,
                        Double.parseDouble(Application.getProperty("proton.r")),
                        Double.parseDouble(Application.getProperty("proton.q")),
                        Color.red
                );
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, Application.getString("message.wronginput"));
            }
        });

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(start, c);
        start.addActionListener(e -> {
            if (start.getText().equals(Application.getString("button.start"))) {
                start.setText(Application.getString("button.stop"));
                physicsPanel.start();
            } else {
                start.setText(Application.getString("button.start"));
                physicsPanel.stop();
            }

        });

        JTextArea area = new JTextArea();
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 4;
        c.gridheight = 1;
        area.setPreferredSize(new Dimension(250, 150));
        area.setEditable(false);
        physicsPanel.timer.addActionListener(e -> {
            area.setText(
                    "Xe(t) = " + physicsPanel.electron.x + "\n" +
                            "Ye(t) = " + physicsPanel.electron.y + "\n" +
                            "Vex(t) = " + physicsPanel.electron.vx + "\n" +
                            "Vey(t) = " + physicsPanel.electron.vy + "\n" +
                            "Xp(t) = " + physicsPanel.proton.x + "\n" +
                            "Yp(t) = " + physicsPanel.proton.y + "\n" +
                            "Vpx(t) = " + physicsPanel.proton.vx + "\n" +
                            "Vpy(t) = " + physicsPanel.proton.vy
            );
        });
        add(area, c);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public PhysicsDemo() {
        buildGui();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhysicsDemo().setVisible(true));
    }
}
