package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InventoryGUI {
    JFrame frame;
    JPanel panel;
    JLabel LifePotionLabel, WeaponLabel;
    Font song;
    Color lightPurple, lighterPurple;

    public InventoryGUI(){
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Inventory");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        LifePotionLabel = new JLabel();
        LifePotionLabel.setBounds(20, 20, 200, 25);
        LifePotionLabel.setFont(song.deriveFont(20f));
        panel.add(LifePotionLabel);

        WeaponLabel = new JLabel();
        WeaponLabel.setBounds(20, 60, 200, 25);
        WeaponLabel.setFont(song.deriveFont(20f));
        panel.add(WeaponLabel);

        frame.setSize(240, 140);
        frame.setLocation(1000, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(false);

    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getLifePotionLabel() {
        return LifePotionLabel;
    }

    public JLabel getWeaponLabel() {
        return WeaponLabel;
    }

}
