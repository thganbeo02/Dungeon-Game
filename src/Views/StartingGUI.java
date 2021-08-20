package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StartingGUI extends JFrame {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JButton continueButton;
    JButton newButton;
    Font song;
    Color lightPurple, lighterPurple;

    public StartingGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            System.out.println("WTF");
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Welcome");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        label = new JLabel("Welcome traveler!");
        label.setBounds(75, 45, 360, 40);
        label.setFont(song.deriveFont(40f));

        // Continue Button
        continueButton = new JButton("Continue adventure");
        continueButton.setFont(song.deriveFont(24f));
        continueButton.setBounds(20, 130, 170, 36);
        continueButton.setBackground(lightPurple);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
        panel.add(continueButton);

        // New Button
        newButton = new JButton("New adventure");
        newButton.setFont(song.deriveFont(24f));
        newButton.setBounds(200, 130, 170, 36);
        newButton.setBackground(lightPurple);
        newButton.setFocusPainted(false);
        newButton.setBorder(null);
        panel.add(newButton);

        frame.setSize(400, 240);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
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

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getNewButton() {
        return newButton;
    }
}
