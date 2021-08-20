package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SkillNameGUI {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextArea skillsArea;
    JButton scalingButton, noScalingButton;
    Font song;
    Color lightPurple, lighterPurple;
    
    public SkillNameGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Game");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        skillsArea = new JTextArea();
        skillsArea.setLineWrap(true);
        skillsArea.setBackground(lighterPurple);
        skillsArea.setWrapStyleWord(true);
        skillsArea.setEditable(false);
        skillsArea.setBounds(15, 10, 255, 480);
        skillsArea.setFont(song.deriveFont(20f));
        panel.add(skillsArea);

        scalingButton = new JButton("View damage ratio");
        scalingButton.setFont(song.deriveFont(Font.BOLD, 24f));
        scalingButton.setBounds(60, 500, 160, 30);
        scalingButton.setBackground(lightPurple);
        scalingButton.setBorder(null);
        scalingButton.setFocusPainted(false);
        scalingButton.setVisible(true);
        panel.add(scalingButton);

        noScalingButton = new JButton("View damage calculated");
        noScalingButton.setFont(song.deriveFont(Font.BOLD, 24f));
        noScalingButton.setBounds(40, 500, 200, 30);
        noScalingButton.setBackground(lightPurple);
        noScalingButton.setBorder(null);
        noScalingButton.setFocusPainted(false);
        noScalingButton.setVisible(false);
        panel.add(noScalingButton);

        frame.setSize(300, 600);
        frame.setLocation(10, 30);
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

    public JLabel getLabel() {
        return label;
    }

    public JTextArea getSkillsArea() {
        return skillsArea;
    }

    public JButton getScalingButton() {
        return scalingButton;
    }

    public JButton getNoScalingButton() {
        return noScalingButton;
    }
}
