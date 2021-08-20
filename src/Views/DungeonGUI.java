package Views;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DungeonGUI {
    JFrame frame;
    JPanel panel, healthBarPanel;
    JLabel playerLabel, enemyLabel;
    JTextArea mainTextArea;
    JButton statsButton, playerSkillsButton, enemySkillsButton, continueButton, leaveButton;
    JButton attackButton, healButton, skill1Button, skill2Button, skill3Button;
    JButton againButton, surrenderButton;
    JProgressBar playerHealthBar, playerManaBar, enemyHealthBar;
    Font song;
    Color lightPurple, lighterPurple, green, lightGreen;

    public DungeonGUI() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        green = new Color(28, 104, 19);
        lightGreen = new Color(39, 171, 103);
        lightPurple = new Color(215, 181, 253);
        lighterPurple = new Color(222, 195, 252);

        frame = new JFrame("Dungeon");
        panel = new JPanel(null);
        panel.setBackground(lighterPurple);

        playerLabel = new JLabel();
        playerLabel.setBounds(20, 20, 130, 20);
        playerLabel.setFont(song.deriveFont(20f));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(playerLabel);

        enemyLabel = new JLabel();
        enemyLabel.setBounds(20, 45, 130, 20);
        enemyLabel.setFont(song.deriveFont(20f));
        enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(enemyLabel);

        mainTextArea = new JTextArea();
        mainTextArea.setLineWrap(true);
        mainTextArea.setBackground(lighterPurple);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextArea.setBounds(20, 80, 460, 150);
        mainTextArea.setFont(song.deriveFont(20f));
        panel.add(mainTextArea);

        playerHealthBar = new JProgressBar();
        playerHealthBar.setFont(song.deriveFont(Font.BOLD, 18f));
        playerHealthBar.setBounds(150, 20, 220, 20);
        playerHealthBar.setStringPainted(true);
        playerHealthBar.setForeground(lightGreen);
        panel.add(playerHealthBar);

        playerManaBar = new JProgressBar();
        playerManaBar.setFont(song.deriveFont(Font.BOLD, 18f));
        playerManaBar.setBounds(375, 20, 100, 20);
        playerManaBar.setStringPainted(true);
        playerManaBar.setForeground(Color.BLUE);
        panel.add(playerManaBar);

        enemyHealthBar = new JProgressBar();
        enemyHealthBar.setFont(song.deriveFont(Font.BOLD, 18f));
        enemyHealthBar.setBounds(150, 45, 220, 20);
        enemyHealthBar.setStringPainted(true);
        enemyHealthBar.setForeground(lightGreen);
        panel.add(enemyHealthBar);

        // Stats of player
        statsButton = new JButton("STATS");
        statsButton.setFont(song.deriveFont(Font.BOLD, 32f));
        statsButton.setBounds(490, 20, 120, 40);
        statsButton.setBackground(lightPurple);
        statsButton.setBorder(null);
        statsButton.setFocusPainted(false);
        statsButton.setVisible(true);
        panel.add(statsButton);

        // View Player's skills
        playerSkillsButton = new JButton();
        playerSkillsButton.setBounds(490, 80, 120, 70);
        playerSkillsButton.setBackground(lightPurple);
        playerSkillsButton.setLayout(new BorderLayout());
        JLabel label1 = new JLabel("PLAYER");
        JLabel label2 = new JLabel("SKILLS");
        label1.setFont(song.deriveFont(Font.BOLD, 32f));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(song.deriveFont(Font.BOLD, 32f));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        playerSkillsButton.setBorder(null);
        playerSkillsButton.setFocusPainted(false);
        playerSkillsButton.setVisible(true);
        playerSkillsButton.add(BorderLayout.NORTH, label1);
        playerSkillsButton.add(BorderLayout.SOUTH, label2);
        panel.add(playerSkillsButton);

        // View Monster's skills
        enemySkillsButton = new JButton();
        enemySkillsButton.setBounds(490, 170, 120, 70);
        enemySkillsButton.setBackground(lightPurple);
        enemySkillsButton.setLayout(new BorderLayout());
        JLabel label3 = new JLabel("ENEMY");
        JLabel label4 = new JLabel("SKILLS");
        label3.setFont(song.deriveFont(Font.BOLD, 32f));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label4.setFont(song.deriveFont(Font.BOLD, 32f));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        enemySkillsButton.setBorder(null);
        enemySkillsButton.setFocusPainted(false);
        enemySkillsButton.setVisible(true);
        enemySkillsButton.add(BorderLayout.NORTH, label3);
        enemySkillsButton.add(BorderLayout.SOUTH, label4);
        panel.add(enemySkillsButton);

        // Basic attack
        attackButton = new JButton("Attack");
        attackButton.setFont(song.deriveFont(30f));
        attackButton.setBounds(20, 260, 100, 40);
        attackButton.setBackground(lightPurple);
        attackButton.setBorder(null);
        attackButton.setFocusPainted(false);
        panel.add(attackButton);

        // Heal
        healButton = new JButton("Heal");
        healButton.setFont(song.deriveFont(30f));
        healButton.setBounds(130, 260, 100, 40);
        healButton.setBackground(lightPurple);
        healButton.setBorder(null);
        healButton.setFocusPainted(false);
        panel.add(healButton);

        // Continue
        continueButton = new JButton("Continue?");
        continueButton.setFont(song.deriveFont(Font.BOLD, 30f));
        continueButton.setBounds(240, 310, 100, 40);
        continueButton.setBackground(lightPurple);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);
        continueButton.setVisible(false);
        panel.add(continueButton);

        // Again
        againButton = new JButton("Again?");
        againButton.setFont(song.deriveFont(Font.BOLD, 30f));
        againButton.setBounds(240, 310, 100, 40);
        againButton.setBackground(lightPurple);
        againButton.setBorder(null);
        againButton.setFocusPainted(false);
        againButton.setVisible(false);
        panel.add(againButton);

        // Leave
        leaveButton = new JButton("Leave");
        leaveButton.setFont(song.deriveFont(Font.BOLD, 30f));
        leaveButton.setBounds(350, 310, 100, 40);
        leaveButton.setBackground(lightPurple);
        leaveButton.setBorder(null);
        leaveButton.setFocusPainted(false);
        leaveButton.setVisible(false);
        panel.add(leaveButton);

        // Surrender
        surrenderButton = new JButton("Surrender");
        surrenderButton.setFont(song.deriveFont(Font.BOLD, 30f));
        surrenderButton.setBounds(350, 310, 150, 40);
        surrenderButton.setBackground(lightPurple);
        surrenderButton.setBorder(null);
        surrenderButton.setFocusPainted(false);
        surrenderButton.setVisible(false);
        panel.add(surrenderButton);

        // Basic attack
        skill1Button = new JButton();
        skill1Button.setFont(song.deriveFont(24f));
        skill1Button.setBounds(20, 310, 190, 40);
        skill1Button.setBackground(lightPurple);
        skill1Button.setBorder(null);
        skill1Button.setFocusPainted(false);
        panel.add(skill1Button);

        // Basic attack
        skill2Button = new JButton();
        skill2Button.setFont(song.deriveFont(24f));
        skill2Button.setBounds(220, 310, 190, 40);
        skill2Button.setBackground(lightPurple);
        skill2Button.setBorder(null);
        skill2Button.setFocusPainted(false);
        panel.add(skill2Button);

        // Basic attack
        skill3Button = new JButton();
        skill3Button.setFont(song.deriveFont(24f));
        skill3Button.setBounds(420, 310, 190, 40);
        skill3Button.setBackground(lightPurple);
        skill3Button.setBorder(null);
        skill3Button.setFocusPainted(false);
        panel.add(skill3Button);

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

    public JPanel getHealthBarPanel() {
        return healthBarPanel;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public JLabel getEnemyLabel() {
        return enemyLabel;
    }

    public JTextArea getMainTextArea() {
        return mainTextArea;
    }

    public JProgressBar getPlayerHealthBar() {
        return playerHealthBar;
    }

    public JProgressBar getPlayerManaBar() {
        return playerManaBar;
    }

    public JProgressBar getEnemyHealthBar() {
        return enemyHealthBar;
    }

    public JButton getStatsButton() {
        return statsButton;
    }

    public JButton getPlayerSkillsButton() {
        return playerSkillsButton;
    }

    public JButton getEnemySkillsButton() {
        return enemySkillsButton;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getLeaveButton() {
        return leaveButton;
    }

    public JButton getAgainButton() {
        return againButton;
    }

    public JButton getSurrenderButton() {
        return surrenderButton;
    }

    public JButton getAttackButton() {
        return attackButton;
    }

    public JButton getHealButton() {
        return healButton;
    }

    public JButton getSkill1Button() {
        return skill1Button;
    }

    public JButton getSkill2Button() {
        return skill2Button;
    }

    public JButton getSkill3Button() {
        return skill3Button;
    }
}
