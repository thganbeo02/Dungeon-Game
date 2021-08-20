package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StatsGUI {
    JFrame frame;
    JPanel panel;
    JLabel NameLabel, LevelLabel, HPLabel, ManaLabel, ADLabel, MPLabel, ArmorLabel, MRLabel,
            ManaRegenLabel, CritChanceLabel, CritDamageLabel, DodgeLabel, PenLabel, MonsterLabel;
    JButton inventoryButton;
    Font song;
    Color lightPurple, lighterPurple, green, purple, yellow, orange, brown, darkRed, lightBlue;

    public StatsGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lighterPurple = new Color(222, 195, 252);
        lightPurple = new Color(215, 181, 253);
        green = new Color(28, 104, 19);
        purple = new Color(104, 12, 113);
        yellow = new Color(187, 173, 37, 200);
        orange = new Color(210, 75, 13);
        brown = new Color(90, 35, 12);
        darkRed = new Color(75, 4, 4);
        lightBlue = new Color(8, 108, 104);

        frame = new JFrame("Game");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        NameLabel = new JLabel();
        NameLabel.setBounds(20, 10, 240, 32);
        NameLabel.setFont(song.deriveFont(30f));
        panel.add(NameLabel);

        LevelLabel = new JLabel();
        LevelLabel.setBounds(20, 45, 280, 32);
        LevelLabel.setFont(song.deriveFont(24f));
        panel.add(LevelLabel);

        HPLabel = new JLabel();
        HPLabel.setBounds(20, 100, 200, 25);
        HPLabel.setFont(song.deriveFont(21f));
        HPLabel.setForeground(green);
        panel.add(HPLabel);

        ManaLabel = new JLabel();
        ManaLabel.setBounds(20, 125, 200, 25);
        ManaLabel.setFont(song.deriveFont(21f));
        ManaLabel.setForeground(Color.BLUE);
        panel.add(ManaLabel);

        ADLabel = new JLabel();
        ADLabel.setBounds(20, 150, 200, 25);
        ADLabel.setFont(song.deriveFont(21f));
        ADLabel.setForeground(Color.RED);
        panel.add(ADLabel);

        MPLabel = new JLabel();
        MPLabel.setBounds(20, 175, 200, 25);
        MPLabel.setFont(song.deriveFont(21f));
        MPLabel.setForeground(purple);
        panel.add(MPLabel);

        ArmorLabel = new JLabel();
        ArmorLabel.setBounds(20, 200, 200, 25);
        ArmorLabel.setFont(song.deriveFont(21f));
        ArmorLabel.setForeground(Color.RED);
        panel.add(ArmorLabel);

        MRLabel = new JLabel();
        MRLabel.setBounds(20, 225, 200, 25);
        MRLabel.setFont(song.deriveFont(21f));
        MRLabel.setForeground(purple);
        panel.add(MRLabel);

        ManaRegenLabel = new JLabel();
        ManaRegenLabel.setBounds(20, 250, 200, 25);
        ManaRegenLabel.setFont(song.deriveFont(21f));
        ManaRegenLabel.setForeground(Color.BLUE);
        panel.add(ManaRegenLabel);

        CritChanceLabel = new JLabel();
        CritChanceLabel.setBounds(20, 275, 200, 25);
        CritChanceLabel.setFont(song.deriveFont(21f));
        CritChanceLabel.setForeground(darkRed);
        panel.add(CritChanceLabel);

        CritDamageLabel = new JLabel();
        CritDamageLabel.setBounds(20, 300, 200, 25);
        CritDamageLabel.setFont(song.deriveFont(21f));
        CritDamageLabel.setForeground(darkRed);
        panel.add(CritDamageLabel);

        DodgeLabel = new JLabel();
        DodgeLabel.setBounds(20, 325, 200, 25);
        DodgeLabel.setFont(song.deriveFont(21f));
        DodgeLabel.setForeground(lightBlue);
        panel.add(DodgeLabel);

        PenLabel = new JLabel();
        PenLabel.setBounds(20, 350, 200, 25);
        PenLabel.setFont(song.deriveFont(21f));
        PenLabel.setForeground(Color.RED);
        panel.add(PenLabel);

        MonsterLabel = new JLabel();
        MonsterLabel.setBounds(20, 375, 200, 25);
        MonsterLabel.setFont(song.deriveFont(21f));
        panel.add(MonsterLabel);

        inventoryButton = new JButton("Inventory");
        inventoryButton.setFont(song.deriveFont(Font.BOLD, 25f));
        inventoryButton.setBounds(45, 410, 200, 40);
        inventoryButton.setBackground(lightPurple);
        inventoryButton.setBorder(null);
        inventoryButton.setFocusPainted(false);
        inventoryButton.setVisible(true);
        panel.add(inventoryButton);

        frame.setSize(300, 500);
        frame.setLocation(970, 50);
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

    public JLabel getLevelLabel() {
        return LevelLabel;
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

    public JLabel getManaLabel() {
        return ManaLabel;
    }

    public JLabel getManaRegenLabel() {
        return ManaRegenLabel;
    }

    public JLabel getCritChanceLabel() {
        return CritChanceLabel;
    }

    public JLabel getCritDamageLabel() {
        return CritDamageLabel;
    }

    public JLabel getDodgeLabel() {
        return DodgeLabel;
    }

    public JLabel getPenLabel() {
        return PenLabel;
    }

    public JLabel getMonsterLabel() {
        return MonsterLabel;
    }

    public JLabel getMPLabel() {
        return MPLabel;
    }

    public JLabel getMRLabel() {
        return MRLabel;
    }

    public JButton getInventoryButton() {
        return inventoryButton;
    }
}
