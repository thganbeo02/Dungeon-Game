package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EnemyStatsGUI {
    JFrame frame;
    JPanel panel;
    JLabel NameLabel, HPLabel, ADLabel, MPLabel, ArmorLabel, MRLabel;
    Font song;
    Color lightPurple, lighterPurple, green, purple, yellow, orange, brown, darkRed, lightBlue;

    public EnemyStatsGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lighterPurple = new Color(222, 195, 252);

        green = new Color(28, 104, 19);
        purple = new Color(104, 12, 113);
        yellow = new Color(187, 173, 37, 200);
        orange = new Color(210, 75, 13);
        brown = new Color(90, 35, 12);
        darkRed = new Color(75, 4, 4);
        lightBlue = new Color(8, 108, 104);

        frame = new JFrame("Enemy Stats");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        NameLabel = new JLabel();
        NameLabel.setBounds(20, 20, 150, 30);
        NameLabel.setFont(song.deriveFont(24f));
        panel.add(NameLabel);

        HPLabel = new JLabel();
        HPLabel.setBounds(20, 55, 240, 25);
        HPLabel.setFont(song.deriveFont(21f));
        HPLabel.setForeground(green);
        panel.add(HPLabel);

        ADLabel = new JLabel();
        ADLabel.setBounds(20, 85, 180, 25);
        ADLabel.setFont(song.deriveFont(21f));
        ADLabel.setForeground(Color.RED);
        panel.add(ADLabel);

        MPLabel = new JLabel();
        MPLabel.setBounds(20, 115, 180, 25);
        MPLabel.setFont(song.deriveFont(21f));
        MPLabel.setForeground(purple);
        panel.add(MPLabel);

        ArmorLabel = new JLabel();
        ArmorLabel.setBounds(220, 85, 180, 25);
        ArmorLabel.setFont(song.deriveFont(21f));
        ArmorLabel.setForeground(Color.RED);
        panel.add(ArmorLabel);

        MRLabel = new JLabel();
        MRLabel.setBounds(220, 115, 180, 25);
        MRLabel.setFont(song.deriveFont(21f));
        MRLabel.setForeground(purple);
        panel.add(MRLabel);

        frame.setSize(400, 200);
        frame.setLocation(420, 460);
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

    public JLabel getNameLabel() {
        return NameLabel;
    }

    public JLabel getADLabel() {
        return ADLabel;
    }

    public JLabel getArmorLabel() {
        return ArmorLabel;
    }

    public JLabel getHPLabel() {
        return HPLabel;
    }

    public JLabel getMPLabel() {
        return MPLabel;
    }

    public JLabel getMRLabel() {
        return MRLabel;
    }
}
