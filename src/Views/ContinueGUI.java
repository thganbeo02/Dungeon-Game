package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ContinueGUI extends JFrame {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextField username;
    JButton beginButton, backButton;
    Font song;
    Color lightPurple, lighterPurple;

    public ContinueGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Continue");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        label = new JLabel("Enter your previous name!");
        label.setBounds(50, 30, 360, 40);
        label.setFont(song.deriveFont(36f));

        username = new JTextField(5);
        username.setBounds(90, 80, 200, 30);
        username.setBackground(lightPurple);
        username.setBorder(null);
        username.setFont(song.deriveFont(28f));
        panel.add(username);

        // Click to continue past adventure, after entering name
        beginButton = new JButton("Let's go!");
        beginButton.setFont(song.deriveFont(24f));
        beginButton.setBounds(200, 130, 150, 36);
        beginButton.setBackground(lightPurple);
        beginButton.setBorder(null);
        beginButton.setFocusPainted(false);
        panel.add(beginButton);

        // Click to go back to starter page
        backButton = new JButton("Back");
        backButton.setFont(song.deriveFont(24f));
        backButton.setBounds(40, 130, 150, 36);
        backButton.setBackground(lightPurple);
        backButton.setBorder(null);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        frame.setSize(400, 240);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(label);
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

    public JTextField getUsername() {
        return username;
    }

    public JButton getBeginButton() {
        return beginButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
