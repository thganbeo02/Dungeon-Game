package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NewNameGUI extends JFrame {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextField username;
    JButton nextButton;
    JButton backButton;
    Font song;
    Color lightPurple, lighterPurple;

    public NewNameGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Create Username");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        label = new JLabel("Enter your desire name!");
        label.setBounds(60, 30, 360, 40);
        label.setFont(song.deriveFont(36f));

        username = new JTextField(5);
        username.setBounds(90, 80, 200, 30);
        username.setBackground(lightPurple);
        username.setBorder(null);
        username.setFont(song.deriveFont(28f));
        panel.add(username);

        // Click to begin
        nextButton = new JButton("Next");
        nextButton.setFont(song.deriveFont(24f));
        nextButton.setBounds(200, 130, 150, 36);
        nextButton.setBackground(lightPurple);
        nextButton.setBorder(null);
        nextButton.setFocusPainted(false);
        panel.add(nextButton);

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

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
