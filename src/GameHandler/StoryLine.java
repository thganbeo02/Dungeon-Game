package GameHandler;

import Views.MainGameGUI;
import Views.ShopGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoryLine implements ActionListener {
    GameScene gameScene;
    Timer timer;

    private final String LADY_TOWN1 = "\"Hi there! Welcome to the town. " +
            "Thank you for choosing to fight with us.";
    private final String LADY_TOWN2 = "Start by clicking on STATS to see your profile.\"";

    private final String LADY_SHOP1 = "\"Click on SHOP to buy items to help " +
            "defeat monsters in the dungeon, ";
    private final String LADY_SHOP2 = "The higher level you are, the better the weapons!\"";

    private final String LADY_BLACKSMITH1 = "\"Click on UPGRADE to buy items to further " +
            "enhance your existing weapons, ";
    private final String LADY_BLACKSMITH2 = "However, you can only upgrade items when you're level 5, " +
            "so defeat as many monsters as possible!\"";

    private final String LADY_TAVERN1 = "\"Last but not least, if you ever feel low on HP, " +
            "you can always visit the TAVERN.";
    private final String LADY_TAVERN2 = "Keep in mind that the more health you heal, the more it cost!\"";

    private final String LADY_DUNGEON1 = "\"Once you are ready, click TO DUNGEON! to begin your quest!";
    private final String LADY_DUNGEON2 = "Good luck slaying those monsters!\"";

    private final String SHOPKEEPER1 = "\"Welcome to the SHOP! What would you like to buy?\"";

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public enum GameScene {
        TALKING_TOWN, TALKING_SHOP, TALKING_UPGRADE, TALKING_TAVERN, TALKING_DUNGEON;

    }


    public void talkToLadyTown(MainGameGUI mainGameGUI) {
        gameScene = GameScene.TALKING_TOWN;

        mainGameGUI.getFrame().setVisible(true);
        mainGameGUI.getMainTextArea().setText("ANGELA: ");
        mainGameGUI.getMainTextArea().append(LADY_TOWN1 + "\n");
        mainGameGUI.getSkipButton().setVisible(true);

        timer = new Timer(1000, e -> mainGameGUI.getMainTextArea().append(LADY_TOWN2 + "\n"));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getNextButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getStatsButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();
    }

    public void talkToLadyShop(MainGameGUI mainGameGUI) {
        gameScene = GameScene.TALKING_SHOP;

        mainGameGUI.getFrame().setVisible(true);
        mainGameGUI.getMainTextArea().setText("ANGELA: ");
        mainGameGUI.getMainTextArea().append(LADY_SHOP1 + "\n");

        timer = new Timer(1000, e -> mainGameGUI.getMainTextArea().append(LADY_SHOP2 + "\n"));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getNextButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getShopButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();
    }

    public void talkToLadyUpgrade(MainGameGUI mainGameGUI) {
        gameScene = GameScene.TALKING_UPGRADE;

        mainGameGUI.getFrame().setVisible(true);
        mainGameGUI.getMainTextArea().setText("ANGELA: ");
        mainGameGUI.getMainTextArea().append(LADY_BLACKSMITH1 + "\n");

        timer = new Timer(1000, e -> mainGameGUI.getMainTextArea().append(LADY_BLACKSMITH2 + "\n"));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getNextButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getUpgradeButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();
    }

    public void talkToLadyTavern(MainGameGUI mainGameGUI) {
        gameScene = GameScene.TALKING_TAVERN;

        mainGameGUI.getFrame().setVisible(true);
        mainGameGUI.getMainTextArea().setText("ANGELA: ");
        mainGameGUI.getMainTextArea().append(LADY_TAVERN1 + "\n");

        timer = new Timer(1000, e -> mainGameGUI.getMainTextArea().append(LADY_TAVERN2 + "\n"));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getNextButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getTavernButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();
    }

    public void talkToLadyDungeon(MainGameGUI mainGameGUI) {
        gameScene = GameScene.TALKING_DUNGEON;

        mainGameGUI.getFrame().setVisible(true);
        mainGameGUI.getMainTextArea().setText("ANGELA: ");
        mainGameGUI.getMainTextArea().append(LADY_DUNGEON1 + "\n");

        timer = new Timer(1000, e -> mainGameGUI.getMainTextArea().append(LADY_DUNGEON2 + "\n"));
        timer.setRepeats(false);
        timer.start();

        timer = new Timer(1000, e -> mainGameGUI.getDungeonButton().setVisible(true));
        timer.setRepeats(false);
        timer.start();

    }

    public void talkToShopkeeper(ShopGUI shopGUI) {
        shopGUI.getMainTextArea().setText("JOHN: ");
        shopGUI.getMainTextArea().append(SHOPKEEPER1 + "\n");
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public void setGameScene(GameScene gameScene) {
        this.gameScene = gameScene;
    }
}

