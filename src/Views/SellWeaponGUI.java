package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SellWeaponGUI extends JFrame {
    JFrame frame;
    JPanel panel;
    JLabel weaponLabel, confirmLabel;
    JTextArea weaponStatsArea;
    JButton yesButton, noButton;
    Font song;

    public SellWeaponGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            System.out.println("WTF");
            e.printStackTrace();
        }

        frame = new JFrame("Buy");
        panel = new JPanel(null);

        weaponLabel = new JLabel();
        weaponLabel.setBounds(20, 20, 345, 30);
        weaponLabel.setFont(song.deriveFont(25f));
        weaponLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(weaponLabel);

        weaponStatsArea = new JTextArea();
        weaponStatsArea.setLineWrap(true);
        weaponStatsArea.setWrapStyleWord(true);
        weaponStatsArea.setEditable(false);
        weaponStatsArea.setBounds(20, 60, 345, 80);
        weaponStatsArea.setFont(song.deriveFont(Font.BOLD, 25f));
        panel.add(weaponStatsArea);

        confirmLabel = new JLabel();
        confirmLabel.setBounds(20, 150, 345, 30);
        confirmLabel.setFont(song.deriveFont(21f));
        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(confirmLabel);

        // Continue Button
        yesButton = new JButton("YES, I'm getting a better one");
        yesButton.setFont(song.deriveFont(28f));
        yesButton.setBounds(265, 190, 100, 32);

        yesButton.setBorder(null);
        yesButton.setFocusPainted(false);
        yesButton.setVisible(false);
        panel.add(yesButton);

        // New Button
        noButton = new JButton("No, I want to keep it");
        noButton.setFont(song.deriveFont(28f));
        noButton.setBounds(20, 190, 100, 32);

        noButton.setFocusPainted(false);
        noButton.setBorder(null);
        noButton.setVisible(false);
        panel.add(noButton);

        frame.setSize(400, 280);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public JLabel getWeaponLabel() {
        return weaponLabel;
    }

    public JTextArea getWeaponStatsArea() {
        return weaponStatsArea;
    }

    public JLabel getConfirmLabel() {
        return confirmLabel;
    }

    public JButton getYesButton() {
        return yesButton;
    }

    public JButton getNoButton() {
        return noButton;
    }
}
