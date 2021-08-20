package Views;

import GameHandler.StoryLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainGameGUI {
    JFrame frame;
    JPanel panel;
    JLabel label;
    JTextArea mainTextArea;
    JButton skipButton;
    JButton nextButton, statsButton, shopButton, upgradeButton, tavernButton, dungeonButton, returnButton;
    Font song;
    Color lightPurple, lighterPurple;

    public MainGameGUI() {
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

        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        mainTextArea.setBackground(lighterPurple);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextArea.setBounds(20, 20, 420, 240);
        mainTextArea.setFont(song.deriveFont(28f));
        panel.add(mainTextArea);

        // Stats of player
        statsButton = new JButton("STATS");
        statsButton.setFont(song.deriveFont(Font.BOLD, 32f));
        statsButton.setBounds(485, 20, 120, 40);
        statsButton.setBackground(lightPurple);
        statsButton.setBorder(null);
        statsButton.setFocusPainted(false);
        statsButton.setVisible(false);
        panel.add(statsButton);

        // Shop
        shopButton = new JButton("SHOP");
        shopButton.setFont(song.deriveFont(Font.BOLD, 32f));
        shopButton.setBounds(485, 90, 120, 40);
        shopButton.setBackground(lightPurple);
        shopButton.setBorder(null);
        shopButton.setFocusPainted(false);
        shopButton.setVisible(false);
        panel.add(shopButton);

        // Upgrade
        upgradeButton = new JButton("UPGRADE");
        upgradeButton.setFont(song.deriveFont(Font.BOLD, 32f));
        upgradeButton.setBounds(485, 160, 120, 40);
        upgradeButton.setBackground(lightPurple);
        upgradeButton.setBorder(null);
        upgradeButton.setFocusPainted(false);
        upgradeButton.setVisible(false);
        panel.add(upgradeButton);

        // Tavern
        tavernButton = new JButton("TAVERN");
        tavernButton.setFont(song.deriveFont(Font.BOLD, 32f));
        tavernButton.setBounds(485, 230, 120, 40);
        tavernButton.setBackground(lightPurple);
        tavernButton.setBorder(null);
        tavernButton.setFocusPainted(false);
        tavernButton.setVisible(false);
        panel.add(tavernButton);

        // Click to continue scene
        nextButton = new JButton(">");
        nextButton.setFont(song.deriveFont(Font.BOLD, 32f));
        nextButton.setBounds(485, 300, 120, 40);
        nextButton.setBackground(lightPurple);
        nextButton.setBorder(null);
        nextButton.setFocusPainted(false);
        nextButton.setVisible(false);
        panel.add(nextButton);

        // Dungeon
        dungeonButton = new JButton("To Dungeon!");
        dungeonButton.setFont(song.deriveFont(Font.BOLD, 25f));
        dungeonButton.setBounds(20, 300, 150, 40);
        dungeonButton.setBackground(lightPurple);
        dungeonButton.setBorder(null);
        dungeonButton.setFocusPainted(false);
        dungeonButton.setVisible(false);
        panel.add(dungeonButton);

        skipButton = new JButton("Skip!");
        skipButton.setFont(song.deriveFont(Font.BOLD, 25f));
        skipButton.setBounds(180, 300, 150, 40);
        skipButton.setBackground(lightPurple);
        skipButton.setBorder(null);
        skipButton.setFocusPainted(false);
        skipButton.setVisible(false);
        panel.add(skipButton);

        // Dungeon
        returnButton = new JButton("Return To Dungeon!");
        returnButton.setFont(song.deriveFont(Font.BOLD, 25f));
        returnButton.setBounds(20, 300, 200, 40);
        returnButton.setBackground(lightPurple);
        returnButton.setBorder(null);
        returnButton.setFocusPainted(false);
        returnButton.setVisible(false);
        panel.add(returnButton);

        frame.setSize(640, 400);
        frame.setLocation(320, 60);
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

    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getStatsButton() {
        return statsButton;
    }

    public JButton getShopButton() {
        return shopButton;
    }

    public JButton getUpgradeButton() {
        return upgradeButton;
    }

    public JButton getTavernButton() {
        return tavernButton;
    }

    public JButton getDungeonButton() {
        return dungeonButton;
    }

    public JButton getSkipButton() {
        return skipButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }
}
