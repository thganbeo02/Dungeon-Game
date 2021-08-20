package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShopGUI {
    JFrame frame;
    JPanel panel, weaponPanel, boostPanel;
    JLabel label;
    JTextArea mainTextArea;
    JButton backButton, sellButton;
    JButton lifePotionButton, weaponButton, weapon2Button, quickADButton, quickMPButton, quickDEFButton;
    JLabel weaponLabel, boostLabel, updateLabel;
    Font song;
    Color lightPurple, lighterPurple, lightGreen, lighterGreen, lightBlack, lighterBlack;

    public ShopGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Shop");
        frame.setSize(640, 400);
        frame.setLocation(320, 60);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(false);

        weaponPanel = new JPanel(null);
        weaponPanel.setBounds(20, 80, 190, 200);
        frame.add(weaponPanel);

        boostPanel = new JPanel(null);
        boostPanel.setBounds(220, 80, 375, 200);
        frame.add(boostPanel);

        panel = new JPanel(null);
        panel.setBounds(20, 20, 600, 300);
        panel.setBackground(lighterPurple);
        frame.add(panel);

        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        mainTextArea.setBackground(lighterPurple);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextArea.setBounds(20, 20, 600, 40);
        mainTextArea.setFont(song.deriveFont(28f));
        panel.add(mainTextArea);

        weaponLabel = new JLabel("WEAPONS");
        weaponLabel.setBounds(30, 20, 150, 50);
        weaponLabel.setFont(song.deriveFont(36f));
        weaponPanel.add(weaponLabel);

        weaponButton = new JButton();
        weaponButton.setBounds(5, 85, 180, 50);
        weaponButton.setFont(song.deriveFont(Font.BOLD, 21f));
        weaponButton.setBorder(null);
        weaponButton.setFocusPainted(false);
        weaponButton.setVisible(true);
        weaponPanel.add(weaponButton);

        weapon2Button = new JButton();
        weapon2Button.setBounds(5, 145, 180, 50);
        weapon2Button.setFont(song.deriveFont(Font.BOLD, 21f));
        weapon2Button.setBorder(null);
        weapon2Button.setFocusPainted(false);
        weapon2Button.setVisible(true);
        weaponPanel.add(weapon2Button);

        boostLabel = new JLabel("QUICK BOOSTS");
        boostLabel.setBounds(110, 20, 200, 50);
        boostLabel.setFont(song.deriveFont(36f));
        boostPanel.add(boostLabel);

        lifePotionButton = new JButton();
        lifePotionButton.setBounds(5, 85, 135, 50);
        lifePotionButton.setFont(song.deriveFont(Font.BOLD, 21f));
        lifePotionButton.setBorder(null);
        lifePotionButton.setFocusPainted(false);
        lifePotionButton.setVisible(true);
        boostPanel.add(lifePotionButton);

        quickADButton = new JButton();
        quickADButton.setFont(song.deriveFont(Font.BOLD, 21f));
        quickADButton.setBounds(5, 145, 135, 50);
        quickADButton.setBorder(null);
        quickADButton.setFocusPainted(false);
        quickADButton.setVisible(true);
        boostPanel.add(quickADButton);

        quickMPButton = new JButton();
        quickMPButton.setBounds(145, 85, 225, 50);
        quickMPButton.setFont(song.deriveFont(Font.BOLD, 18f));
        quickMPButton.setBackground(lightPurple);
        quickMPButton.setBorder(null);
        quickMPButton.setFocusPainted(false);
        quickMPButton.setVisible(true);
        boostPanel.add(quickMPButton);

        quickDEFButton = new JButton();
        quickDEFButton.setBounds(145, 145, 225, 50);
        quickDEFButton.setFont(song.deriveFont(Font.BOLD, 18f));
        quickDEFButton.setBorder(null);
        quickDEFButton.setFocusPainted(false);
        quickDEFButton.setVisible(true);
        boostPanel.add(quickDEFButton);

        updateLabel = new JLabel();
        updateLabel.setBackground(lighterPurple);
        updateLabel.setBounds(130, 305, 350, 30);
        updateLabel.setFont(song.deriveFont(25f));
        updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(updateLabel);

        // Dungeon
        backButton = new JButton("Back");
        backButton.setFont(song.deriveFont(Font.BOLD, 24f));
        backButton.setBounds(20, 300, 100, 40);
        backButton.setBackground(lightPurple);
        backButton.setBorder(null);
        backButton.setFocusPainted(false);
        backButton.setVisible(false);
        panel.add(backButton);

        // Dungeon
        sellButton = new JButton("Sell");
        sellButton.setFont(song.deriveFont(Font.BOLD, 24f));
        sellButton.setBounds(495, 300, 100, 40);
        sellButton.setBackground(lightPurple);
        sellButton.setBorder(null);
        sellButton.setFocusPainted(false);
        sellButton.setVisible(false);
        panel.add(sellButton);
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

    public JPanel getWeaponPanel() {
        return weaponPanel;
    }

    public JLabel getWeaponLabel() {
        return weaponLabel;
    }

    public JButton getWeaponButton() {
        return weaponButton;
    }

    public JButton getWeapon2Button() {
        return weapon2Button;
    }

    public JPanel getBoostPanel() {
        return boostPanel;
    }

    public JLabel getBoostLabel() {
        return boostLabel;
    }

    public JButton getLifePotionButton() {
        return lifePotionButton;
    }

    public JButton getQuickADButton() {
        return quickADButton;
    }

    public JButton getQuickMPButton() {
        return quickMPButton;
    }

    public JButton getQuickDEFButton() {
        return quickDEFButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSellButton() {
        return sellButton;
    }

    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public JLabel getUpdateLabel() {
        return updateLabel;
    }
}
