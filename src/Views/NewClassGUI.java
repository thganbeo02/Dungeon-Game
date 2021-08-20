package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NewClassGUI extends JFrame {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JButton fighterButton, archerButton, mageButton, assassinButton;
    Font song;
    Color lightPurple, lighterPurple;

    public NewClassGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Choose class");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        label = new JLabel("Choose a class, traveler!");
        label.setBounds(55, 30, 360, 40);
        label.setFont(song.deriveFont(36f));
        panel.add(label);

        fighterButton = new JButton("Fighter");
        fighterButton.setFont(song.deriveFont(24f));
        fighterButton.setBounds(25, 100, 70, 36);
        fighterButton.setBackground(lightPurple);
        fighterButton.setBorder(null);
        fighterButton.setFocusPainted(false);
        panel.add(fighterButton);

        archerButton = new JButton("Archer");
        archerButton.setFont(song.deriveFont(24f));
        archerButton.setBounds(110, 100, 70, 36);
        archerButton.setBackground(lightPurple);
        archerButton.setBorder(null);
        archerButton.setFocusPainted(false);
        panel.add(archerButton);

        mageButton = new JButton("Mage");
        mageButton.setFont(song.deriveFont(24f));
        mageButton.setBounds(200, 100, 70, 36);
        mageButton.setBackground(lightPurple);
        mageButton.setBorder(null);
        mageButton.setFocusPainted(false);
        panel.add(mageButton);

        assassinButton = new JButton("Assassin");
        assassinButton.setFont(song.deriveFont(24f));
        assassinButton.setBounds(290, 100, 70, 36);
        assassinButton.setBackground(lightPurple);
        assassinButton.setBorder(null);
        assassinButton.setFocusPainted(false);
        panel.add(assassinButton);

        frame.setSize(400, 240);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(false);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getFighterButton() {
        return fighterButton;
    }

    public JButton getArcherButton() {
        return archerButton;
    }

    public JButton getMageButton() {
        return mageButton;
    }

    public JButton getAssassinButton() {
        return assassinButton;
    }
}
