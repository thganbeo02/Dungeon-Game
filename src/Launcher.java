import Entities.*;
import GameHandler.MonsterHandler;
import GameHandler.StoryLine;
import GameHandler.*;
import Hostile.*;
import Items.Inventory;
import Items.Item;
import Items.Weapon;
import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class Launcher implements ActionListener {
    // Initialize GUIs
    StartingGUI startingGUI;
    ContinueGUI continueGUI;
    NewNameGUI newNameGUI;
    NewClassGUI newClassGUI;
    MainGameGUI mainGameGUI;
    StatsGUI statsGUI;
    ShopGUI shopGUI;
    EnemyStatsGUI enemyStatsGUI;
    DungeonGUI dungeonGUI;
    SkillNameGUI skillNameGUI;
    InventoryGUI inventoryGUI;
    BuyWeaponGUI buyWeaponGUI;
    SellWeaponGUI sellWeaponGUI;

    JTextArea combatArea;
    JButton weapon1Button, weapon2Button, lifePotionButton, quickAD, quickMP, quickDEF;
    JButton yesBuy, noBuy, yesSell, noSell;
    JLabel playerLabel, monsterLabel, buyLogLabel;
    JProgressBar playerHealthBar, playerManaBar, monsterHealthBar;

    // Initialize handlers
    StoryLine storyLine;
    Handlers handlers;
    MonsterHandler monsterHandler;

    // Initialize entities
    Fighter fighter;
    Archer archer;
    Mage mage;
    Assassin assassin;
    Slime slime;
    Zombie zombie;
    Skeleton skeleton;
    Mimic mimic;
    Diablo diablo;

    Font song;
    String usernameTyped;
    String difficulty = "(Easy)";
    Random rand = new Random();

    // Initialize player stats
    Inventory inventory;
    Item[] itemSlot;
    Player currentPlayer;
    PlayerClass playerClass;
    String playerClassName;
    int playerLevel;
    int playerHP;
    int playerAD;
    int playerMP;
    int playerArmor;
    int playerMR;
    int playerMana;
    int playerManaRegen;
    int playerCritChance;
    double playerCritMultiplier;
    int playerDodgeChance;
    double playerAPen;
    double playerMPen;

    int[] manaCost;
    int playerGold = 0;
    int playerEXP = 0;
    int goldDrop;
    int EXPDrop;
    int monsterDefeated = 0;

    // Button
    boolean attackPressed = false;
    boolean skillCalculated = true;
    boolean skillRatio = false;

    // Others
    int playerMaxHP;
    int playerMissingHP;
    int playerCritAD;
    int playerFinalADDealt;
    int playerFinalCritDealt;

    int monsterMaxHP;
    int monsterMissingHP;

    double monsterArmorEffectiveness;
    double monsterMREffectiveness;
    double playerArmorEffectiveness;
    double playerMREffectiveness;

    int assassinStack1 = 0;
    int assassinStack2 = 0;
    int archerStack3 = 0;

    // Initialize monster stats
    Monster currentMonster;
    String monsterName;
    int monsterHP;
    int monsterAD;
    int monsterMP;
    int monsterArmor;
    int monsterMR;
    int slimeTurnCountdown;
    boolean skeletonDodge = false;

    // Colors
    Color light, lighter;

    // Initialize items
    Shop shop;
    Item[] itemsInShop = new Item[6];
    Item lifePotion;
    Weapon weapon1, weapon2;
    Weapon itemClicked, itemBought;
    boolean itemBoughtBoo, weapon1Bought, weapon2Bought = false;
    boolean shieldEquipped = false;
    boolean deadshot, phantom = false;
    boolean excalibur, aegis = false;

    public Launcher() {
        init();
    }

    public static void main(String[] args) {
        new Launcher();
    }

    public void init() {
        try {
            song = Font.createFont
                    (Font.TRUETYPE_FONT, new File("Song.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // Initialize GUIs
        startingGUI = new StartingGUI();
        continueGUI = new ContinueGUI();
        newNameGUI = new NewNameGUI();
        newClassGUI = new NewClassGUI();
        mainGameGUI = new MainGameGUI();
        statsGUI = new StatsGUI();
        shopGUI = new ShopGUI();
        enemyStatsGUI = new EnemyStatsGUI();
        dungeonGUI = new DungeonGUI();
        skillNameGUI = new SkillNameGUI();
        inventoryGUI = new InventoryGUI();
        buyWeaponGUI = new BuyWeaponGUI();
        sellWeaponGUI = new SellWeaponGUI();

        fighter = new Fighter();
        archer = new Archer();
        mage = new Mage();
        assassin = new Assassin();
        slime = new Slime();
        zombie = new Zombie();
        skeleton = new Skeleton();
        mimic = new Mimic();
        diablo = new Diablo();

        slimeTurnCountdown = slime.gooTurnNumber;

        // Initialize handlers
        storyLine = new StoryLine();
        handlers = new Handlers();

        // StartingPage
        startingGUI.getNewButton().addActionListener(this);
        startingGUI.getContinueButton().addActionListener(this);

        // Continue
        continueGUI.getBeginButton().addActionListener(this);
        continueGUI.getBackButton().addActionListener(this);

        // NewName
        newNameGUI.getNextButton().addActionListener(this);
        newNameGUI.getBackButton().addActionListener(this);

        // NewClass
        newClassGUI.getFighterButton().addActionListener(this);
        newClassGUI.getFighterButton().setActionCommand("fighter");
        newClassGUI.getArcherButton().addActionListener(this);
        newClassGUI.getArcherButton().setActionCommand("archer");
        newClassGUI.getMageButton().addActionListener(this);
        newClassGUI.getMageButton().setActionCommand("mage");
        newClassGUI.getAssassinButton().addActionListener(this);
        newClassGUI.getAssassinButton().setActionCommand("assassin");

        // NextScene
        mainGameGUI.getNextButton().addActionListener(this);
        mainGameGUI.getStatsButton().addActionListener(this);
        mainGameGUI.getShopButton().addActionListener(this);
        mainGameGUI.getUpgradeButton().addActionListener(this);
        mainGameGUI.getTavernButton().addActionListener(this);
        mainGameGUI.getDungeonButton().addActionListener(this);
        mainGameGUI.getSkipButton().addActionListener(this);
        mainGameGUI.getReturnButton().addActionListener(this);
        mainGameGUI.getReturnButton().setActionCommand("return");

        // Dungeon
        dungeonGUI.getStatsButton().addActionListener(this);
        dungeonGUI.getPlayerSkillsButton().addActionListener(this);
        dungeonGUI.getEnemySkillsButton().addActionListener(this);
        dungeonGUI.getContinueButton().addActionListener(this);
        dungeonGUI.getContinueButton().setActionCommand("continue");
        dungeonGUI.getLeaveButton().addActionListener(this);
        dungeonGUI.getLeaveButton().setActionCommand("leave");
        dungeonGUI.getAgainButton().addActionListener(this);
        dungeonGUI.getSurrenderButton().addActionListener(this);

        // Skill switching
        skillNameGUI.getScalingButton().addActionListener(this);
        skillNameGUI.getNoScalingButton().addActionListener(this);

        // Stats
        statsGUI.getInventoryButton().addActionListener(this);

        // Shop
        shopGUI.getBackButton().addActionListener(this);
        shopGUI.getSellButton().addActionListener(this);
        buyLogLabel = shopGUI.getUpdateLabel();

        // Confirmation
        yesBuy = buyWeaponGUI.getYesButton();
        yesBuy.addActionListener(this);
        yesBuy.setActionCommand("Yes buy");

        noBuy = buyWeaponGUI.getNoButton();
        noBuy.addActionListener(this);
        noBuy.setActionCommand("No buy");

        yesSell = sellWeaponGUI.getYesButton();
        yesSell.addActionListener(this);
        yesSell.setActionCommand("Yes sell");

        noSell = sellWeaponGUI.getNoButton();
        noSell.addActionListener(this);
        noSell.setActionCommand("No sell");

        weapon1Button = shopGUI.getWeaponButton();
        weapon1Button.addActionListener(this);
        weapon1Button.setActionCommand("Buy weapon 1");

        weapon2Button = shopGUI.getWeapon2Button();
        weapon2Button.addActionListener(this);
        weapon2Button.setActionCommand("Buy weapon 2");

        lifePotionButton = shopGUI.getLifePotionButton();
        lifePotionButton.addActionListener(this);
        lifePotionButton.setActionCommand("Buy Life potion");

        quickAD = shopGUI.getQuickADButton();
        quickAD.addActionListener(this);
        quickAD.setActionCommand("Quick AD");

        quickMP = shopGUI.getQuickMPButton();
        quickMP.addActionListener(this);
        quickMP.setActionCommand("Quick MP");

        quickDEF = shopGUI.getQuickDEFButton();
        quickDEF.addActionListener(this);
        quickDEF.setActionCommand("Quick DEF");

        // Dungeon display
        combatArea = dungeonGUI.getMainTextArea();
        playerLabel = dungeonGUI.getPlayerLabel();
        playerHealthBar = dungeonGUI.getPlayerHealthBar();
        playerManaBar = dungeonGUI.getPlayerManaBar();
        monsterHealthBar = dungeonGUI.getEnemyHealthBar();
        monsterLabel = dungeonGUI.getEnemyLabel();

        dungeonGUI.getAttackButton().addActionListener(this);
        dungeonGUI.getAttackButton().setActionCommand("attack");
        dungeonGUI.getHealButton().addActionListener(this);
        dungeonGUI.getHealButton().setActionCommand("heal");
        dungeonGUI.getSkill1Button().addActionListener(this);
        dungeonGUI.getSkill1Button().setActionCommand("skill1");
        dungeonGUI.getSkill2Button().addActionListener(this);
        dungeonGUI.getSkill2Button().setActionCommand("skill2");
        dungeonGUI.getSkill3Button().addActionListener(this);
        dungeonGUI.getSkill3Button().setActionCommand("skill3");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // StartingPage
        if (e.getSource() == startingGUI.getContinueButton()) {
            System.out.println("StartingPage/Continue from previous adventure!");

            startingGUI.getFrame().setVisible(false);
            continueGUI.getFrame().setVisible(true);
        }

        if (e.getSource() == startingGUI.getNewButton()) {
            System.out.println("StartingPage/New adventure!");

            startingGUI.getFrame().setVisible(false);
            newNameGUI.getFrame().setVisible(true);
        }

        if (e.getSource() == dungeonGUI.getAgainButton()) {
            dungeonGUI.getFrame().dispose();
            skillNameGUI.getFrame().dispose();
            statsGUI.getFrame().dispose();
            enemyStatsGUI.getFrame().dispose();
            reset();
        }

        // Continue
        if (e.getSource() == continueGUI.getBeginButton()) {
            System.out.println("Continue/Continue adventurer!");

            usernameTyped = continueGUI.getUsername().getText();
            handlers.sendToHandler("Check account/" + usernameTyped);

            continueGUI.getFrame().setVisible(false);

            // Determine the current player
            currentPlayer = handlers.getCurrentPlayer();
        }

        // Continue
        if (e.getSource() == continueGUI.getBackButton()) {
            System.out.println("Continue/Back to starting page!");

            continueGUI.getFrame().setVisible(false);
            startingGUI.getFrame().setVisible(true);
        }

        // NewName
        if (e.getSource() == newNameGUI.getNextButton()) {
            System.out.println("NewName/Begin adventurer!");

            usernameTyped = newNameGUI.getUsername().getText();

            newNameGUI.getFrame().setVisible(false);
            newClassGUI.getFrame().setVisible(true);
        }

        // NewName
        if (e.getSource() == newNameGUI.getBackButton()) {
            System.out.println("NewName/Back to starting page!");

            newNameGUI.getFrame().setVisible(false);
            startingGUI.getFrame().setVisible(true);
        }

        if (e.getSource() == dungeonGUI.getAttackButton()) {
            System.out.println("Dungeon/Attack!");
            attackPressed = true;
        }

        // NewClass, create player
        String className;
        if (e.getActionCommand().equals("fighter") || e.getActionCommand().equals("archer") ||
                e.getActionCommand().equals("mage") || e.getActionCommand().equals("assassin")) {
            switch (e.getActionCommand()) {
                case "fighter" -> className = "fighter";
                case "archer" -> className = "archer";
                case "mage" -> className = "mage";
                case "assassin" -> className = "assassin";
                default -> throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
            }

            handlers.sendToHandler("Create account/" + usernameTyped + "," + className);
            newNameGUI.getFrame().setVisible(false);
            newClassGUI.getFrame().setVisible(false);

            // Determine the current player
            currentPlayer = handlers.getCurrentPlayer();
            playerClass = currentPlayer.playerClass;
            playerClassName = currentPlayer.getPlayerClass().getClassName();
            playerLevel = currentPlayer.level;
            playerHP = currentPlayer.HP;
            playerMaxHP = playerHP;
            playerAD = currentPlayer.AD;
            playerMP = currentPlayer.MP;
            playerArmor = currentPlayer.Armor;
            playerMR = currentPlayer.MR;
            playerMana = currentPlayer.startingMana;
            playerManaRegen = currentPlayer.manaRegen;
            playerCritChance = currentPlayer.critChance;
            playerCritMultiplier = currentPlayer.critMultiplier;
            playerDodgeChance = currentPlayer.dodgeChance;
            playerAPen = currentPlayer.armorPen;
            playerMPen = currentPlayer.MRPen;

            lifePotion = handlers.getLifePotion();
            inventory = handlers.getInventory();
            itemSlot = inventory.itemSlots;
            System.out.println(Arrays.toString(itemSlot));

            storyLine.talkToLadyTown(mainGameGUI);
        }

        // Stats
        if (e.getSource() == mainGameGUI.getStatsButton() ||
                e.getSource() == dungeonGUI.getStatsButton()) {
            System.out.println("Stats/Get player stats");
            statsGUI.getFrame().setVisible(true);
            updatePlayerStatsGUI();
        }

        // Shop
        if (e.getSource() == mainGameGUI.getShopButton()) {
            mainGameGUI.getFrame().setVisible(false);
            shopGUI.getFrame().setVisible(true);
            shopGUI.getBackButton().setVisible(true);
            shopGUI.getSellButton().setVisible(true);

            switch (playerClassName) {
                case "fighter" -> {
                    light = new Color(182, 182, 182);
                    lighter = new Color(198, 198, 198);
                }
                case "archer" -> {
                    light = new Color(167, 217, 152);
                    lighter = new Color(183, 226, 171);
                }
                case "mage" -> {
                    light = new Color(145, 199, 255);
                    lighter = new Color(165, 207, 252);
                }
                case "assassin" -> {
                    light = new Color(255, 150, 150);
                    lighter = new Color(255, 166, 166);
                }
            }

            shopGUI.getWeaponPanel().setBackground(lighter);
            weapon1Button.setBackground(light);
            weapon2Button.setBackground(light);
            shopGUI.getBoostPanel().setBackground(lighter);
            shopGUI.getLifePotionButton().setBackground(light);
            shopGUI.getQuickADButton().setBackground(light);
            shopGUI.getQuickMPButton().setBackground(light);
            shopGUI.getQuickDEFButton().setBackground(light);

            buyWeaponGUI.getPanel().setBackground(lighter);
            buyWeaponGUI.getWeaponStatsArea().setBackground(lighter);
            yesBuy.setBackground(light);
            noBuy.setBackground(light);

            sellWeaponGUI.getPanel().setBackground(lighter);
            sellWeaponGUI.getWeaponStatsArea().setBackground(lighter);
            yesSell.setBackground(light);
            noSell.setBackground(light);

            storyLine.talkToShopkeeper(shopGUI);

            shop = new Shop(playerLevel, playerClassName);
            itemsInShop = shop.getItemsInShop();
            System.out.println("From launcher: " + Arrays.toString(itemsInShop));

            itemsInShop[0] = lifePotion;
            weapon1 = (Weapon) itemsInShop[1];
            weapon2 = (Weapon) itemsInShop[2];

            shopGUI.getLifePotionButton().setText("(" + lifePotion.price + "g) " + itemsInShop[0].itemName);
            weapon1Button.setText("(" + weapon1.price + "g) " + weapon1.itemName);
            weapon2Button.setText("(" + weapon2.price + "g) " + weapon2.itemName);

            shopGUI.getQuickADButton().setText(itemsInShop[3].toString());
            shopGUI.getQuickMPButton().setText(itemsInShop[4].toString());
            shopGUI.getQuickDEFButton().setText(itemsInShop[5].toString());

            if (itemBoughtBoo) {
                updateShop();
            }

        }

        // Shop, buy weapons
        if (e.getActionCommand().equals("Buy weapon 1") || e.getActionCommand().equals("Buy weapon 2")) {
            switch (e.getActionCommand()) {
                case "Buy weapon 1" -> itemClicked = weapon1;
                case "Buy weapon 2" -> itemClicked = weapon2;
            }


            if (itemClicked != null && itemClicked.price != 0) {
                buyWeaponGUI.getFrame().setVisible(true);
                buyWeaponGUI.getWeaponLabel().setText(itemClicked.itemAndCost());
                buyWeaponGUI.getWeaponStatsArea().setText("");
                buyWeaponGUI.getWeaponStatsArea().append(itemClicked.itemStats() + "\n");
                buyWeaponGUI.getConfirmLabel().setText("Do you want to buy this item?");

                yesBuy.setVisible(true);
                noBuy.setVisible(true);

            }
        }

        if (e.getActionCommand().equals("Yes buy") || e.getActionCommand().equals("No buy")) {

            if (itemSlot[1] != null) {
                itemBoughtBoo = true;
            }

            switch (e.getActionCommand()) {
                case "Yes buy" -> {
                    if (itemSlot[1] != null) {
                        buyWeaponGUI.getFrame().setVisible(false);
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("You currently have a weapon equipped!");
                        break;
                    }

                    itemBought = itemClicked;

                    if (itemBought.equals(weapon1)) {
                        weapon1Bought = true;
                    } else if (itemBought.equals(weapon2)) {
                        weapon2Bought = true;
                    }

                    if (playerGold >= itemBought.price) {
                        playerGold -= itemBought.price;

                        playerMaxHP += itemBought.HP;
                        playerHP += itemBought.HP;
                        playerAD += itemBought.AD;
                        playerMP += itemBought.MP;
                        playerArmor += itemBought.armor;
                        playerMR += itemBought.MR;
                        playerMana += itemBought.currentMana;
                        playerManaRegen += itemBought.manaRegen;
                        playerCritChance += itemBought.critChance;
                        playerCritMultiplier += itemBought.critMultiplier;
                        playerDodgeChance += itemBought.dodgeChance;
                        playerAPen += itemBought.armorPen;
                        playerMPen += itemBought.MRPen;

                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("You just bought " + itemBought.itemName);
                        itemBoughtBoo = true;
                        inventory.addItem(itemBought);

                        if (itemBought.equals(shop.shield)) {
                            shieldEquipped = true;
                        }

                        switch (itemBought.itemName) {
                            case "EXCALIBUR" -> excalibur = true;
                            case "AEGIS" -> aegis = true;
                            case "DEADSHOT" -> deadshot = true;
                            case "PHANTOM" -> phantom = true;
                        }

                        buyWeaponGUI.getFrame().setVisible(false);
                        updatePlayerStatsGUI();
                    } else {
                        buyLogLabel.setFont(song.deriveFont(20f));
                        buyLogLabel.setText("You don't have enough money... Fight more monsters!");
                        buyWeaponGUI.getFrame().setVisible(false);
                        itemBought = null;
                    }


                }
                case "No buy" -> {
                    buyWeaponGUI.getFrame().setVisible(false);
                    System.out.println(itemBoughtBoo);
                }
            }

            updateShop();
        }

        // Shop, buy quick boosts
        if (e.getActionCommand().equals("Quick AD") || e.getActionCommand().equals("Quick MP") ||
                e.getActionCommand().equals("Quick DEF") || e.getActionCommand().equals("Buy Life potion")) {
            int[] ADPrice = shop.getQuickADPriceByLevel();

            int[] MPPrice = shop.getQuickMPPriceByLevel();

            int[] DEFPrice = shop.getQuickDEFPriceByLevel();
            switch (e.getActionCommand()) {
                case "Quick AD" -> {
                    if (playerGold >= ADPrice[playerLevel - 1]) {
                        playerGold -= ADPrice[playerLevel - 1];
                        playerAD += itemsInShop[3].AD;
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("You gain " + itemsInShop[3].AD + " AD");
                    } else {
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("Not enough money!");
                    }
                }
                case "Quick MP" -> {
                    if (playerGold >= MPPrice[playerLevel - 1]) {
                        playerGold -= MPPrice[playerLevel - 1];
                        playerMP += itemsInShop[4].MP;
                        playerMana += itemsInShop[4].currentMana;
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("You gain " + itemsInShop[4].MP + " MP and " +
                                itemsInShop[4].currentMana + " Mana");
                    } else {
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("Not enough money!");
                    }
                }
                case "Quick DEF" -> {
                    if (playerGold >= DEFPrice[playerLevel - 1]) {
                        playerGold -= DEFPrice[playerLevel - 1];
                        playerArmor += itemsInShop[5].armor;
                        playerMaxHP += itemsInShop[5].HP;
                        playerHP += itemsInShop[5].HP;
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("You gain " + itemsInShop[5].HP + " max HP and " +
                                itemsInShop[5].armor + " Armor");
                    } else {
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("Not enough money!");
                    }
                }
                case "Buy Life potion" -> {
                    if (playerGold >= lifePotion.price) {
                        playerGold -= lifePotion.price;
                        lifePotion.amount++;
                        handlers.sendToHandler("Buy life potion");
                        lifePotion = handlers.getLifePotion();
                        shopGUI.getLifePotionButton().setText
                                ("(" + lifePotion.price + "g) " + lifePotion.itemName);
                        buyLogLabel.setFont(song.deriveFont(20f));
                        buyLogLabel.setText("You bought a Life potion, but the price just triples!");
                    } else {
                        buyLogLabel.setFont(song.deriveFont(28f));
                        buyLogLabel.setText("Not enough money!");
                    }
                }
            }
            updatePlayerStatsGUI();
        }

        // Shop, sell weapons
        if (e.getSource() == shopGUI.getSellButton()) {
            if (itemSlot[1] == null) {
                buyLogLabel.setFont(song.deriveFont(20f));
                buyLogLabel.setText("You can't sell nothing... Buy something first!");
            } else {
                System.out.println("Sell " + itemSlot[1]);
                sellWeaponGUI.getFrame().setVisible(true);
                sellWeaponGUI.getWeaponLabel().setText(itemSlot[1].itemAndCost());
                sellWeaponGUI.getWeaponStatsArea().setText("");
                sellWeaponGUI.getWeaponStatsArea().append(itemSlot[1].itemStats().replace('+', '-') + "\n");
                sellWeaponGUI.getConfirmLabel().setText("Do you want to sell this item for " +
                        itemSlot[1].price / 3 + "g?");

                yesSell.setVisible(true);
                noSell.setVisible(true);
            }

        }

        if (e.getActionCommand().equals("Yes sell") || e.getActionCommand().equals("No sell")) {
            System.out.println("Current item want to sell: " + itemSlot[1]);
            switch (e.getActionCommand()) {
                case "Yes sell" -> {
                    playerGold += itemBought.price / 3;

                    playerMaxHP -= itemBought.HP;
                    playerHP -= itemBought.HP;
                    playerAD -= itemBought.AD;
                    playerMP -= itemBought.MP;
                    playerArmor -= itemBought.armor;
                    playerMR -= itemBought.MR;
                    playerMana -= itemBought.currentMana;
                    playerManaRegen -= itemBought.manaRegen;
                    playerCritChance -= itemBought.critChance;
                    playerCritMultiplier -= itemBought.critMultiplier;
                    playerDodgeChance -= itemBought.dodgeChance;
                    playerAPen -= itemBought.armorPen;
                    playerMPen -= itemBought.MRPen;

                    buyLogLabel.setFont(song.deriveFont(28f));
                    buyLogLabel.setText("You just sold " + itemBought.itemName);
                    itemBoughtBoo = false;
                    shieldEquipped = false;

                    inventory.removeItem(itemBought);

                    sellWeaponGUI.getFrame().setVisible(false);
                    updatePlayerStatsGUI();
                    itemBought = null;
                    updateShop();
                }
                case "No sell" -> sellWeaponGUI.getFrame().setVisible(false);
            }
        }

        // Shop, back to MainGame
        if (e.getSource() == shopGUI.getBackButton()) {
            buyLogLabel.setText("");
            shopGUI.getFrame().setVisible(false);
            mainGameGUI.getFrame().setVisible(true);
        }

        // MainGame, next button
        if (e.getSource() == mainGameGUI.getNextButton()) {
            mainGameGUI.getNextButton().setVisible(false);
            System.out.println("NewName/To next scene!");
            switch (storyLine.getGameScene()) {
                case TALKING_TOWN -> storyLine.talkToLadyShop(mainGameGUI);
                case TALKING_SHOP -> storyLine.talkToLadyUpgrade(mainGameGUI);
                case TALKING_UPGRADE -> storyLine.talkToLadyTavern(mainGameGUI);
                case TALKING_TAVERN -> storyLine.talkToLadyDungeon(mainGameGUI);
            }
        }

        // Inventory, view Inventory
        if (e.getSource() == statsGUI.getInventoryButton()) {
            inventoryGUI.getFrame().setVisible(true);
            if (lifePotion.amount == 0) {
                inventoryGUI.getLifePotionLabel().setText("--- No healing potions ---");
            } else {
                inventoryGUI.getLifePotionLabel().setText(lifePotion.itemName + " x " + lifePotion.amount);
            }

            if (itemSlot[1] == null) {
                inventoryGUI.getWeaponLabel().setText("--- No current weapon ---");
            } else {
                inventoryGUI.getWeaponLabel().setText(itemSlot[1].itemName);
            }
        }

        if (e.getSource() == skillNameGUI.getScalingButton()) {
            skillCalculated = false;
            skillRatio = true;
            switch (playerClassName) {
                case "fighter" -> fighterUpdateSkillsRatioGUI();
                case "archer" -> archerUpdateSkillsRatioGUI();
                case "mage" -> mageUpdateSkillsRatioGUI();
                case "assassin" -> assassinUpdateSkillsRatioGUI();

            }

            skillNameGUI.getScalingButton().setVisible(false);
            skillNameGUI.getNoScalingButton().setVisible(true);
        }

        if (e.getSource() == skillNameGUI.getNoScalingButton()) {
            skillRatio = false;
            skillCalculated = true;
            switch (playerClassName) {
                case "fighter" -> fighterUpdateSkillsGUI();
                case "archer" -> archerUpdateSkillsGUI();
                case "mage" -> mageUpdateSkillsGUI();
                case "assassin" -> assassinUpdateSkillsGUI();
            }

            skillNameGUI.getNoScalingButton().setVisible(false);
            skillNameGUI.getScalingButton().setVisible(true);

        }

        // MainGame, To dungeon!
        if (e.getSource() == mainGameGUI.getDungeonButton() ||
                e.getSource() == mainGameGUI.getSkipButton()) {

            if (e.getSource() == mainGameGUI.getSkipButton()) {
                storyLine.talkToLadyDungeon(mainGameGUI);
                mainGameGUI.getShopButton().setVisible(true);
                mainGameGUI.getUpgradeButton().setVisible(true);
                mainGameGUI.getTavernButton().setVisible(true);
            }

            mainGameGUI.getFrame().setVisible(false);
            mainGameGUI.getDungeonButton().setVisible(false);
            mainGameGUI.getSkipButton().setVisible(false);
            dungeonGUI.getFrame().setVisible(true);
            skillNameGUI.getFrame().setVisible(true);

            updatePlayerStatsGUI();

            dungeonGUI.getSkill1Button().setText
                    (currentPlayer.playerClass.getSkill1Name(playerLevel));
            dungeonGUI.getSkill2Button().setText
                    (currentPlayer.playerClass.getSkill2Name(playerLevel));
            dungeonGUI.getSkill3Button().setText
                    (currentPlayer.playerClass.getSkill3Name(playerLevel));

            handlers.sendToHandler("Get monster/");
            currentMonster = handlers.getCurrentMonster();
            System.out.println("From launcher/" + currentMonster);

            monsterName = currentMonster.getName();
            monsterHP = currentMonster.getHP();
            monsterAD = currentMonster.getAD();
            monsterMP = currentMonster.getMP();
            monsterArmor = currentMonster.getArmor();
            monsterMR = currentMonster.getMR();

            monsterHandler = new MonsterHandler(monsterName, monsterHP, monsterAD, monsterMP,
                    monsterArmor, monsterMR);

            monsterMaxHP = monsterHP;

            playerHealthBar.setMinimum(0);
            playerHealthBar.setMaximum(playerHP);
            playerHealthBar.setValue(playerHP);
            playerHealthBar.setString(playerHP + "/" + playerMaxHP + " HP");

            playerLabel.setText(currentPlayer.getUsername());
            playerManaBar.setMinimum(0);
            playerManaBar.setMaximum(1);
            playerManaBar.setValue(playerMana);
            playerManaBar.setString(playerMana + " Mana");

            monsterLabel.setText(monsterName);
            monsterHealthBar.setMinimum(0);
            monsterHealthBar.setMaximum(monsterMaxHP);
            monsterHealthBar.setValue(monsterMaxHP);
            monsterHealthBar.setString(monsterHP + "/" + monsterMaxHP + " HP");

            updateEnemyStatsGUI();

            combatArea.setText("");
            combatArea.append(monsterName + " has appeared!\n");
            combatArea.append("<" + monsterHP + " HP, " + monsterAD + " AD, " +
                    monsterMP + " MP, " + monsterArmor + " Armor, " + monsterMR + " MR>\n");

        }

        // Dungeon, view player skills
        if (e.getSource() == dungeonGUI.getPlayerSkillsButton()) {
            System.out.println("Dungeon/View player skills");
            skillNameGUI.getFrame().setVisible(true);
        }

        // Dungeon, view enemy skills
        if (e.getSource() == dungeonGUI.getEnemySkillsButton()) {
            System.out.println("Dungeon/View enemy skills");
            enemyStatsGUI.getFrame().setVisible(true);
        }

        // Dungeon, healing
        if (e.getSource() == dungeonGUI.getHealButton()) {
            combatArea.setText("");
            if (lifePotion.amount > 0) {
                playerHP += lifePotion.healing;
                if (playerHP > playerMaxHP) {
                    playerHP = playerMaxHP;
                }

                lifePotion.amount--;
                combatArea.append("You just heal back " + lifePotion.healing + " HP!\n");
            } else {
                combatArea.append("You run out of healing potions... Visit SHOP or defeat monsters" +
                        " for a chance to obtain!\n");
            }
            updatePlayerStatsGUI();
        }

        // Dungeon, action!
        if (e.getActionCommand().equals("attack") || e.getActionCommand().equals("skill1")
                || e.getActionCommand().equals("skill2") || e.getActionCommand().equals("skill3")) {
            boolean executed = true;
            int[] defenseEffectiveness = new int[]{25, 30, 35, 40, 40, 40, 40};

            monsterArmorEffectiveness = (defenseEffectiveness[playerLevel - 1] /
                    (defenseEffectiveness[playerLevel - 1] + (1 - playerAPen) * monsterArmor));
            monsterMREffectiveness = (defenseEffectiveness[playerLevel - 1] /
                    (defenseEffectiveness[playerLevel - 1] + (1 - playerMPen) * monsterMR));
            playerArmorEffectiveness = (20 / (double) (20 + playerArmor));
            playerMREffectiveness = 20 / (double) (20 + playerMR);

            playerCritAD = (int) (playerAD * playerCritMultiplier);
            playerFinalADDealt = (int) (playerAD * monsterArmorEffectiveness);
            playerFinalCritDealt = (int) (playerCritAD * monsterArmorEffectiveness);
            combatArea.setText("");

            if (monsterHP > 0) {
                // Player choice
                switch (e.getActionCommand()) {
                    // Basic attack
                    case "attack" -> {
                        if (playerLevel >= 4) {
                            switch (monsterName) {
                                case "Slime" -> {
                                    int slimeGooHealing = slime.slimeGooHealing(playerLevel, monsterMissingHP);
                                    slimeTurnCountdown -= 1;

                                    if (slimeTurnCountdown == 0) {
                                        monsterHP += slimeGooHealing;

                                        if (monsterHP > monsterMaxHP) {
                                            monsterHP = monsterMaxHP;
                                        }

                                        combatArea.append("Slime heals for " + slimeGooHealing + " HP!\n");
                                        slimeTurnCountdown = slime.gooTurnNumber;

                                    }

                                }
                                case "Skeleton" -> {
                                    int skeletonSpookyHealing = skeleton.skeletonSpookyHealing(playerLevel, monsterMissingHP);

                                    if (rand.nextInt(100) < skeleton.spookyDodge[playerLevel - 4]) {
                                        skeletonDodge = true;
                                        monsterAD += skeleton.spookyADGain[playerLevel - 4];
                                        monsterHP += skeletonSpookyHealing;

                                        if (monsterHP > monsterMaxHP) {
                                            monsterHP = monsterMaxHP;
                                        }

                                        combatArea.append
                                                ("Skeleton dodges your attack, heals back " + skeletonSpookyHealing);
                                        combatArea.append(" HP, and gains some AD!\n");

                                    }
                                }
                            }
                        }

                        if (!skeletonDodge) {
                            switch (playerClassName) {
                                case "fighter" -> {
                                    // Player attack first
                                    if (rand.nextInt(100) < playerCritChance) {
                                        combatArea.append("You CRITICAL STRIKE for " + playerCritAD +
                                                " physical damage and break some of its armor!\n");
                                        if (monsterArmor < 10) {
                                            monsterArmor -= 1;
                                        } else {
                                            monsterArmor *= 0.8;
                                        }

                                    } else {
                                        combatArea.append("You strike for " + playerAD +
                                                " physical damage, piercing through its armor.\n");
                                    }

                                    monsterHP -= playerFinalADDealt;


                                }
                                case "archer" -> {
                                    // Player attack first
                                    if (rand.nextInt(100) < playerCritChance) {
                                        monsterHP -= playerFinalADDealt * playerCritMultiplier;
                                        int HPGain = archer.getPassiveHPCrit();
                                        playerMaxHP += HPGain;
                                        playerHP += HPGain;

                                        combatArea.append("You CRITICAL STRIKE for " +
                                                playerCritAD + " physical damage and gain " + HPGain + " HP and Armor\n");

                                    } else {
                                        monsterHP -= playerFinalADDealt;

                                        combatArea.append("You strike for " + playerAD + " physical damage\n");
                                    }

                                    if (deadshot) {
                                        combatArea.append("DEADSHOT does an additional " +
                                                (int) (playerAD * shop.bow.trueDamage / 100) + " TRUE DAMAGE on-hit!\n");

                                        monsterHP -= playerAD * shop.bow.trueDamage / 100;
                                    }

                                    int statsSteal = archer.getPassiveStatsSteal()[playerLevel - 1];
                                    boolean statsStolen = false;

                                    // Steal stats
                                    if (rand.nextInt(100) < archer.getPassiveStealChance()) {
                                        if (monsterAD > statsSteal) {
                                            monsterAD -= statsSteal;
                                            playerAD += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (monsterArmor > statsSteal) {
                                            monsterArmor -= statsSteal;
                                            playerArmor += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (monsterMR > statsSteal) {
                                            monsterMR -= statsSteal;
                                            playerMR += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (statsStolen) {
                                            combatArea.append("You steal " + statsSteal +
                                                    " AD, Armor, and MR from " + monsterName + "! HOW?\n");
                                        }
                                    }

                                }
                                case "mage" -> {
                                    int extra = mage.getPassiveExtra()[playerLevel - 1];

                                    int mageOnHit = mage.passive(playerMP);
                                    int rawMixed = mageOnHit + playerAD;

                                    monsterHP = (int)
                                            (monsterHP - (mageOnHit * monsterMREffectiveness + playerFinalADDealt));

                                    combatArea.append("You strike for " + rawMixed + " mixed damage\n");

                                    if (playerHP <= playerMaxHP * mage.getPassiveHPThreshold() / 100) {
                                        playerMana += extra;
                                        playerMP += extra;
                                        playerMR += extra;

                                        combatArea.append("Furthermore, you generate " + extra +
                                                " extra Mana, MP, and Magic Resist!\n");
                                    }
                                }
                                case "assassin" -> {
                                    // Player attack first
                                    if (rand.nextInt(100) < playerCritChance) {
                                        monsterHP = (int) (monsterHP - playerFinalADDealt * playerCritMultiplier);

                                        combatArea.append("You CRITICAL STRIKE for " + playerCritAD + " physical damage!\n");

                                    } else {
                                        monsterHP -= playerFinalADDealt;

                                        combatArea.append("You strike for " + playerAD + " physical damage\n");
                                    }

                                }
                            }
                            playerMana += playerManaRegen;
                        }

                        skeletonDodge = false;

                        // Monster dead
                        if (monsterHP <= 0) {
                            break;
                        }

                        // Monster attack
                        if (playerLevel >= 4) {
                            int monsterEnhancedDamage;
                            switch (monsterName) {
                                case "Zombie" -> {
                                    int zombieToughenChance = zombie.zombieToughenChance[playerLevel - 4];
                                    int zombieBiteChance = zombie.zombieBiteChance[playerLevel - 4];

                                    if (rand.nextInt(100) < zombieToughenChance) {
                                        monsterArmor += zombie.zombieToughenArmor[playerLevel - 4];
                                        monsterMR += zombie.zombieToughenMR[playerLevel - 4];
                                        combatArea.append("Zombie forms an iron skin around itself!\n");

                                    } else if (rand.nextInt(100) < zombieToughenChance + zombieBiteChance) {
                                        if (playerHP > playerMaxHP * 0.5) {
                                            monsterEnhancedDamage = zombie.zombieBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie bites the living out of you, dealing " +
                                                    monsterEnhancedDamage + " magic damage!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                        } else {
                                            monsterEnhancedDamage = zombie.zombieEnhancedBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie CHOMPS HARDER, dealing " + monsterEnhancedDamage +
                                                    " magic damage and removing some MR off you!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                            playerMR -= zombie.zombieBiteMRRemove[playerLevel - 4];
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {
                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP -= monsterAD * playerArmorEffectiveness;
                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Slime" -> {
                                    int slimeDivisionHPGain = slime.slimeDivisionHPGain(playerLevel, monsterHP);
                                    int slimeDivisionChance = slime.divisionChance[playerLevel - 4];

                                    if (rand.nextInt(100) < slimeDivisionChance) {
                                        monsterEnhancedDamage = slime.slimeDivisionDamage(playerLevel, monsterMP);
                                        playerHP = (int) (playerHP -
                                                (monsterAD * playerArmorEffectiveness +
                                                        monsterEnhancedDamage * playerMREffectiveness));

                                        monsterMaxHP += slimeDivisionHPGain;
                                        monsterHP += slimeDivisionHPGain;

                                        combatArea.append("Slime gains " + slimeDivisionHPGain +
                                                " max HP and slaps you with an additional " +
                                                monsterEnhancedDamage + " magic damage!\n");

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {

                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Skeleton" -> {
                                    int skeletonBoneStrikeChance = skeleton.boneStrikeChance[playerLevel - 4];

                                    if (rand.nextInt(100) < playerDodgeChance) {
                                        if (playerClassName.equals("assassin")) {
                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                            monsterHP -= assassinPassiveDamage;
                                            combatArea.append
                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                            playerHP += assassinPassiveHeal;
                                            if (playerHP > playerMaxHP) {
                                                playerHP = playerMaxHP;
                                            }

                                        } else {
                                            combatArea.append
                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                        }
                                    } else if (rand.nextInt(100) < skeletonBoneStrikeChance) {
                                        monsterEnhancedDamage = skeleton.skeletonBoneStrike(playerLevel, monsterAD);
                                        playerHP = (int) (playerHP - monsterEnhancedDamage * playerArmorEffectiveness);
                                        playerArmor -= skeleton.boneStrikeArmorReduction[playerLevel - 4];
                                        combatArea.append("Skeleton enhances its attack and does " +
                                                monsterEnhancedDamage + " AD, taking away some of your armor!\n");


                                    } else {
                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                        combatArea.append(monsterName + " strikes for ");
                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                    }

                                }
                                case "Mimic" -> {
                                    int mimicChance = mimic.mimicChance[playerLevel - 4];

                                    if (rand.nextInt(100) < mimicChance) {
                                        int ADStolen = mimic.mimicStealAD(playerAD);
                                        int ArmorStolen = mimic.mimicStealAD(playerArmor);

                                        playerAD -= ADStolen;
                                        playerArmor -= ArmorStolen;

                                        monsterAD += ADStolen;
                                        monsterArmor += ArmorStolen;

                                        monsterMaxHP += mimic.mimicHPGain[playerLevel - 4];
                                        monsterHP += mimic.mimicHPGain[playerLevel - 4];

                                        combatArea.append("Mimic just robs ");
                                        combatArea.append(ADStolen + " AD, ");
                                        combatArea.append(ArmorStolen + " Armor off you and gains ");
                                        combatArea.append(mimic.mimicHPGain[playerLevel - 4] + " max HP!\n");


                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }

                                case "Diablo" -> {
                                    int drainChance = diablo.drainChance[playerLevel - 4];

                                    if (rand.nextInt(100) < drainChance) {
                                        int manaStolen = diablo.drainMana[playerLevel - 4];

                                        if (playerMana < manaStolen) {
                                            monsterMP += playerMana;
                                            playerMana = 0;

                                            combatArea.append
                                                    ("Diablo drains all of your MANA and gains all of them!\n");
                                        } else {
                                            playerMana -= manaStolen;
                                            monsterMP += manaStolen;

                                            combatArea.append("Diablo drains " + manaStolen
                                                    + " Mana and gains all of them!\n");
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {
                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            monsterEnhancedDamage =
                                                    (int) (monsterAD + diablo.giftMPRatio[playerLevel - 4] / 100 * monsterMP);

                                            int halfComponent = monsterEnhancedDamage / 2;

                                            playerHP = (int) (playerHP -
                                                    (halfComponent * playerArmorEffectiveness +
                                                            halfComponent * playerMREffectiveness));

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterEnhancedDamage + " mixed damage, but you block some.\n");

                                        }
                                    }
                                }
                            }

                        } else {
                            if (rand.nextInt(100) < playerDodgeChance) {
                                if (playerClassName.equals("assassin")) {
                                    int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                    int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                    monsterHP -= assassinPassiveDamage;
                                    combatArea.append
                                            ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                    " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                    playerHP += assassinPassiveHeal;
                                    if (playerHP > playerMaxHP) {
                                        playerHP = playerMaxHP;
                                    }
                                } else {
                                    combatArea.append
                                            ("You just dodge " + monsterName + "'s attack!\n");
                                }
                            } else {
                                playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                combatArea.append(monsterName + " strikes for ");
                                combatArea.append(monsterAD + " physical damage, but you block some.\n");

                            }
                        }
                    }

                    case "skill1" -> {
                        switch (playerClassName) {
                            case "fighter" -> {
                                manaCost = fighter.getSkill1Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int statIncrease = fighter.firstSkill(playerLevel);

                                    if (playerLevel > 4) {
                                        playerAD += statIncrease;
                                        playerMP += statIncrease;
                                        playerArmor += statIncrease;
                                        playerMR += statIncrease;
                                        playerHP += fighter.skill1Heal[playerLevel - 1];

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }

                                        combatArea.append("All defensive and offensive stats of yours " +
                                                "just increase by " + statIncrease + " and you heal " +
                                                fighter.skill1Heal[playerLevel - 1] + " HP!\n");


                                    } else if (rand.nextInt(100) < 50) {
                                        playerArmor += statIncrease;
                                        playerMR += statIncrease;
                                        playerHP += statIncrease;

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }
                                        combatArea.append("You heal and increase your Armor and Magic Resist " +
                                                "by " + statIncrease + "!\n");

                                    } else {
                                        playerAD += statIncrease;
                                        playerMP += statIncrease;
                                        combatArea.append("Your AD and MP just increase by " +
                                                statIncrease + "!\n");

                                    }


                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }

                            }
                            case "archer" -> {
                                manaCost = archer.getSkill1Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    // Monster attack
                                    if (playerLevel >= 4) {
                                        int monsterEnhancedDamage;
                                        switch (monsterName) {
                                            case "Zombie" -> {
                                                int zombieToughenChance = zombie.zombieToughenChance[playerLevel - 4];
                                                int zombieBiteChance = zombie.zombieBiteChance[playerLevel - 4];

                                                if (rand.nextInt(100) < zombieToughenChance) {
                                                    monsterArmor += zombie.zombieToughenArmor[playerLevel - 4];
                                                    monsterMR += zombie.zombieToughenMR[playerLevel - 4];
                                                    combatArea.append("Zombie forms an iron skin around itself!\n");

                                                } else if (rand.nextInt(100) < zombieToughenChance + zombieBiteChance) {
                                                    if (playerHP > playerMaxHP * 0.5) {
                                                        monsterEnhancedDamage = zombie.zombieBite(playerLevel, monsterAD, monsterMP);
                                                        combatArea.append("Zombie bites the living out of you, dealing " +
                                                                monsterEnhancedDamage + " magic damage!\n");
                                                        playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                                    } else {
                                                        monsterEnhancedDamage = zombie.zombieEnhancedBite(playerLevel, monsterAD, monsterMP);
                                                        combatArea.append("Zombie CHOMPS HARDER, dealing " + monsterEnhancedDamage +
                                                                " magic damage and removing some MR off you!\n");
                                                        playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                                        playerMR -= zombie.zombieBiteMRRemove[playerLevel - 4];
                                                    }

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {
                                                        if ("assassin".equals(playerClassName)) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP -= monsterAD * playerArmorEffectiveness;
                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }
                                            case "Slime" -> {
                                                int slimeDivisionHPGain = slime.slimeDivisionHPGain(playerLevel, monsterHP);
                                                int slimeDivisionChance = slime.divisionChance[playerLevel - 4];

                                                if (rand.nextInt(100) < slimeDivisionChance) {
                                                    monsterEnhancedDamage = slime.slimeDivisionDamage(playerLevel, monsterMP);
                                                    playerHP = (int) (playerHP -
                                                            (monsterAD * playerArmorEffectiveness +
                                                                    monsterEnhancedDamage * playerMREffectiveness));

                                                    monsterMaxHP += slimeDivisionHPGain;
                                                    monsterHP += slimeDivisionHPGain;

                                                    combatArea.append("Slime gains " + slimeDivisionHPGain +
                                                            " max HP and slaps you with an additional " +
                                                            monsterEnhancedDamage + " magic damage!\n");

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {
                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }
                                            case "Skeleton" -> {
                                                int skeletonBoneStrikeChance = skeleton.boneStrikeChance[playerLevel - 4];

                                                if (rand.nextInt(100) < playerDodgeChance) {
                                                    if (playerClassName.equals("assassin")) {
                                                        int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                        int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                        monsterHP -= assassinPassiveDamage;
                                                        combatArea.append
                                                                ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                        " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                        playerHP += assassinPassiveHeal;
                                                        if (playerHP > playerMaxHP) {
                                                            playerHP = playerMaxHP;
                                                        }

                                                    } else {
                                                        combatArea.append
                                                                ("You just dodge " + monsterName + "'s attack!\n");
                                                    }
                                                } else if (rand.nextInt(100) < skeletonBoneStrikeChance) {
                                                    monsterEnhancedDamage = skeleton.skeletonBoneStrike(playerLevel, monsterAD);
                                                    playerHP = (int) (playerHP - monsterEnhancedDamage * playerArmorEffectiveness);
                                                    playerArmor -= skeleton.boneStrikeArmorReduction[playerLevel - 4];


                                                    combatArea.append("Skeleton enhances its attack and does " +
                                                            monsterEnhancedDamage + " AD, taking away some of your armor!\n");


                                                } else {
                                                    playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                                    combatArea.append(monsterName + " strikes for ");
                                                    combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                }

                                            }
                                            case "Mimic" -> {
                                                int mimicChance = mimic.mimicChance[playerLevel - 4];

                                                if (rand.nextInt(100) < mimicChance) {
                                                    int ADStolen = mimic.mimicStealAD(playerAD);
                                                    int ArmorStolen = mimic.mimicStealAD(playerArmor);

                                                    playerAD -= ADStolen;
                                                    playerArmor -= ArmorStolen;

                                                    monsterAD += ADStolen;
                                                    monsterArmor += ArmorStolen;

                                                    combatArea.append("Mimic just robs ");
                                                    combatArea.append(ADStolen + " AD and " + ArmorStolen + " Armor off you!\n");

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {

                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);
                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }

                                            case "Diablo" -> {
                                                int drainChance = diablo.drainChance[playerLevel - 4];

                                                if (rand.nextInt(100) < drainChance) {
                                                    int manaStolen = diablo.drainMana[playerLevel - 4];

                                                    if (playerMana < manaStolen) {
                                                        monsterMP += playerMana;
                                                        playerMana = 0;

                                                        combatArea.append
                                                                ("Diablo drains all of your MANA and gains all of them!\n");
                                                    } else {
                                                        playerMana -= manaStolen;
                                                        monsterMP += manaStolen;

                                                        combatArea.append("Diablo drains " + manaStolen
                                                                + " Mana and gains all of them!\n");
                                                    }

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {
                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        monsterEnhancedDamage =
                                                                (int) (monsterAD + diablo.giftMPRatio[playerLevel - 4] / 100 * monsterMP);

                                                        int halfComponent = monsterEnhancedDamage / 2;

                                                        playerHP = (int) (playerHP -
                                                                (halfComponent * playerArmorEffectiveness +
                                                                        halfComponent * playerMREffectiveness));
                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterEnhancedDamage + " mixed damage, but you block some.\n");

                                                    }
                                                }
                                            }
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {
                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP -= monsterAD * playerArmorEffectiveness;
                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");
                                        }
                                    }

                                    int enhancedAD = archer.firstSkill
                                            (playerLevel, playerAD, playerMP, monsterArmorEffectiveness)[0];
                                    int healing;
                                    playerCritAD = (int) (enhancedAD * playerCritMultiplier);

                                    // Archer attack second
                                    if (rand.nextInt(100) < playerCritChance) {
                                        int HPGain = archer.getPassiveHPCrit();
                                        playerMaxHP += HPGain;
                                        playerHP += HPGain;

                                        healing = (int) (archer.firstSkill
                                                (playerLevel, playerAD, playerMP, monsterArmorEffectiveness)[1] *
                                                playerCritMultiplier);

                                        monsterHP -= playerFinalADDealt * playerCritMultiplier;
                                        playerHP += healing;

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }

                                        combatArea.append("Your enhanced arrow CRITS for " + playerCritAD +
                                                " physical damage, healing " + healing + " HP! You also gain " +
                                                HPGain + " HP and Armor\n");

                                    } else {
                                        // Skill 1
                                        healing = archer.firstSkill
                                                (playerLevel, playerAD, playerMP, monsterArmorEffectiveness)[1];

                                        monsterHP = (int)
                                                (monsterHP - enhancedAD * monsterArmorEffectiveness);
                                        playerHP += healing;

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }

                                        combatArea.append("You enhance your arrow to deal " + enhancedAD +
                                                " physical damage, and heal " + healing + " HP\n");
                                    }

                                    if (deadshot) {
                                        combatArea.append("DEADSHOT does an additional " +
                                                (int) (playerAD * shop.bow.trueDamage / 100) + " TRUE DAMAGE on-hit!\n");

                                        monsterHP -= playerAD * shop.bow.trueDamage / 100;
                                    }

                                    int statsSteal = archer.getPassiveStatsSteal()[playerLevel - 1];
                                    boolean statsStolen = false;

                                    if (rand.nextInt(100) < 33) {
                                        if (monsterAD > statsSteal) {
                                            monsterAD -= statsSteal;
                                            playerAD += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (monsterArmor > statsSteal) {
                                            monsterArmor -= statsSteal;
                                            playerArmor += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (monsterMR > statsSteal) {
                                            monsterMR -= statsSteal;
                                            playerMR += statsSteal;
                                            statsStolen = true;
                                        }

                                        if (statsStolen) {
                                            combatArea.append("You steal " + statsSteal +
                                                    " AD, Armor, and MR from " + monsterName + "! HOW?\n");
                                        }
                                    }

                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }

                            }
                            case "mage" -> {
                                manaCost = mage.getSkill1Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int magicDMG = mage.firstSkill(playerLevel, playerMP);

                                    if (rand.nextInt(100) < playerCritChance) {
                                        monsterHP = (int)
                                                (monsterHP - magicDMG * playerCritMultiplier * monsterMREffectiveness);
                                        monsterAD *= 0.75;

                                        combatArea.append("You ROASTED " + monsterName + " for " +
                                                (int) (magicDMG * playerCritMultiplier) +
                                                " magic damage, taking a LOT of its AD away!\n");

                                    } else {
                                        monsterHP = (int) (monsterHP - magicDMG * monsterArmorEffectiveness);
                                        monsterAD *= 0.8;

                                        combatArea.append("You zap " + monsterName + " for " + magicDMG +
                                                " magic damage, taking some of its AD away!\n");
                                    }
                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }

                            }
                            case "assassin" -> {
                                manaCost = assassin.getSkill1Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    playerAD += assassin.getSkill1ADGain()[playerLevel - 1];
                                    combatArea.append("You gain " + assassin.getSkill1ADGain()[playerLevel - 1] +
                                            " AD\n");

                                    if (playerHP <= playerMaxHP * assassin.getSkill1HPThreshold() / 100) {

                                        int healing = assassin.firstSkill(playerMaxHP);

                                        playerHP += healing;

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }

                                        combatArea.append("You evade and heal " + healing + " HP, ");

                                        playerAD += assassin.getSkill1ADGain()[playerLevel - 1];
                                        combatArea.append("and gain another " +
                                                assassin.getSkill1ADGain()[playerLevel - 1] + " AD!\n");

                                        if (assassinStack1 < 5) {
                                            playerDodgeChance += assassin.getSkill1DodgeGain();
                                            assassinStack1++;
                                            combatArea.append("You also gain " + assassin.getSkill1DodgeGain() +
                                                    "% Dodge Chance while at low health!\n");
                                        }
                                    }

                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }

                            }
                        }
                    }

                    case "skill2" -> {
                        boolean skill2Used = false;
                        switch (playerClassName) {
                            case "fighter" -> {
                                manaCost = fighter.getSkill2Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int enhancedAD = fighter.secondSkill(playerLevel, playerAD);

                                    if (monsterArmor <= 3) {
                                        monsterArmor = 0;
                                    } else {
                                        monsterArmor *= (100 - fighter.getSkill2ArmorRemoved()) / 100;
                                    }

                                    combatArea.append("You enhance your punch to deal " +
                                            enhancedAD + " physical damage, stun the enemy and remove " +
                                            "some of its armor!\n");

                                    monsterHP = (int)
                                            (monsterHP - enhancedAD * monsterArmorEffectiveness);

                                    if (monsterHP <= 0) {
                                        executed = false;
                                        playerMaxHP += fighter.getSkill2HPGain()[playerLevel - 1];
                                        playerHP += fighter.getSkill2HPGain()[playerLevel - 1];
                                        combatArea.append(monsterName + " is EXECUTED and " +
                                                "you gain some permanent HP!\n");
                                    } else {
                                        playerMana += (manaCost[playerLevel - 1] / 4);

                                    }
                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }
                                skill2Used = true;
                            }
                            case "archer" -> {
                                manaCost = archer.getSkill2Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int[] statIncrease = archer.secondSkill(playerLevel);
                                    int ADIncrease = statIncrease[0];
                                    int MPIncrease = statIncrease[1];
                                    int DEFIncrease = statIncrease[2];

                                    playerAD += ADIncrease;
                                    playerMP += MPIncrease;
                                    playerArmor += DEFIncrease;
                                    playerMR += DEFIncrease;

                                    if (playerLevel < 5) {
                                        combatArea.append("You gain " + ADIncrease + " AD and " +
                                                MPIncrease + " MP!\n");
                                    } else {
                                        combatArea.append("You gain " + DEFIncrease +
                                                " AD, MP, and even Armor and Magic Resist!\n");

                                    }

                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");

                                }
                                skill2Used = true;
                            }
                            case "mage" -> {
                                manaCost = mage.getSkill2Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    // Monster attack
                                    if (playerLevel >= 4) {
                                        int monsterEnhancedDamage;
                                        switch (monsterName) {
                                            case "Zombie" -> {
                                                int zombieToughenChance = zombie.zombieToughenChance[playerLevel - 4];
                                                int zombieBiteChance = zombie.zombieBiteChance[playerLevel - 4];

                                                if (rand.nextInt(100) < zombieToughenChance) {
                                                    monsterArmor += zombie.zombieToughenArmor[playerLevel - 4];
                                                    monsterMR += zombie.zombieToughenMR[playerLevel - 4];
                                                    combatArea.append("Zombie forms an iron skin around itself!\n");

                                                } else if (rand.nextInt(100) < zombieToughenChance + zombieBiteChance) {
                                                    if (playerHP > playerMaxHP * 0.5) {
                                                        monsterEnhancedDamage = zombie.zombieBite(playerLevel, monsterAD, monsterMP);
                                                        combatArea.append("Zombie bites the living out of you, dealing " +
                                                                monsterEnhancedDamage + " magic damage!\n");
                                                        playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                                    } else {
                                                        monsterEnhancedDamage = zombie.zombieEnhancedBite(playerLevel, monsterAD, monsterMP);
                                                        combatArea.append("Zombie CHOMPS HARDER, dealing " + monsterEnhancedDamage +
                                                                " magic damage and removing some MR off you!\n");
                                                        playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                                        playerMR -= zombie.zombieBiteMRRemove[playerLevel - 4];
                                                    }

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {


                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }
                                            case "Slime" -> {
                                                int slimeDivisionHPGain = slime.slimeDivisionHPGain(playerLevel, monsterHP);
                                                int slimeDivisionChance = slime.divisionChance[playerLevel - 4];

                                                if (rand.nextInt(100) < slimeDivisionChance) {
                                                    monsterEnhancedDamage = slime.slimeDivisionDamage(playerLevel, monsterMP);
                                                    playerHP = (int) (playerHP -
                                                            (monsterAD * playerArmorEffectiveness +
                                                                    monsterEnhancedDamage * playerMREffectiveness));

                                                    monsterMaxHP += slimeDivisionHPGain;
                                                    monsterHP += slimeDivisionHPGain;


                                                    combatArea.append("Slime gains " + slimeDivisionHPGain +
                                                            " max HP and slaps you with an additional " +
                                                            monsterEnhancedDamage + " magic damage!\n");

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {


                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }
                                            case "Skeleton" -> {
                                                int skeletonBoneStrikeChance = skeleton.boneStrikeChance[playerLevel - 4];

                                                if (rand.nextInt(100) < playerDodgeChance) {


                                                    if (playerClassName.equals("assassin")) {
                                                        int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                        int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                        monsterHP -= assassinPassiveDamage;
                                                        combatArea.append
                                                                ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                        " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                        playerHP += assassinPassiveHeal;
                                                        if (playerHP > playerMaxHP) {
                                                            playerHP = playerMaxHP;
                                                        }

                                                    } else {
                                                        combatArea.append
                                                                ("You just dodge " + monsterName + "'s attack!\n");
                                                    }
                                                } else if (rand.nextInt(100) < skeletonBoneStrikeChance) {
                                                    monsterEnhancedDamage = skeleton.skeletonBoneStrike(playerLevel, monsterAD);
                                                    playerHP = (int) (playerHP - monsterEnhancedDamage * playerArmorEffectiveness);
                                                    playerArmor -= skeleton.boneStrikeArmorReduction[playerLevel - 4];


                                                    combatArea.append("Skeleton enhances its attack and does " +
                                                            monsterEnhancedDamage + " AD, taking away some of your armor!\n");


                                                } else {
                                                    playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                                    combatArea.append(monsterName + " strikes for ");
                                                    combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                }

                                            }
                                            case "Mimic" -> {
                                                int mimicChance = mimic.mimicChance[playerLevel - 4];

                                                if (rand.nextInt(100) < mimicChance) {
                                                    int ADStolen = mimic.mimicStealAD(playerAD);
                                                    int ArmorStolen = mimic.mimicStealAD(playerArmor);

                                                    playerAD -= ADStolen;
                                                    playerArmor -= ArmorStolen;

                                                    monsterAD += ADStolen;
                                                    monsterArmor += ArmorStolen;

                                                    combatArea.append("Mimic just robs ");
                                                    combatArea.append(ADStolen + " AD and " + ArmorStolen + " Armor off you!\n");


                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {


                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                                    }
                                                }
                                            }

                                            case "Diablo" -> {
                                                int drainChance = diablo.drainChance[playerLevel - 4];

                                                if (rand.nextInt(100) < drainChance) {
                                                    int manaStolen = diablo.drainMana[playerLevel - 4];

                                                    if (playerMana < manaStolen) {
                                                        monsterMP += playerMana;
                                                        playerMana = 0;

                                                        combatArea.append
                                                                ("Diablo drains all of your MANA and gains all of them!\n");
                                                    } else {
                                                        playerMana -= manaStolen;
                                                        monsterMP += manaStolen;

                                                        combatArea.append("Diablo drains " + manaStolen
                                                                + " Mana and gains all of them!\n");
                                                    }

                                                } else {
                                                    if (rand.nextInt(100) < playerDodgeChance) {


                                                        if (playerClassName.equals("assassin")) {
                                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                            monsterHP -= assassinPassiveDamage;
                                                            combatArea.append
                                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                            playerHP += assassinPassiveHeal;
                                                            if (playerHP > playerMaxHP) {
                                                                playerHP = playerMaxHP;
                                                            }
                                                        } else {
                                                            combatArea.append
                                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                                        }
                                                    } else {
                                                        monsterEnhancedDamage =
                                                                (int) (monsterAD + diablo.giftMPRatio[playerLevel - 4] / 100 * monsterMP);

                                                        int halfComponent = monsterEnhancedDamage / 2;

                                                        playerHP = (int) (playerHP -
                                                                (halfComponent * playerArmorEffectiveness +
                                                                        halfComponent * playerMREffectiveness));


                                                        combatArea.append(monsterName + " strikes for ");
                                                        combatArea.append(monsterEnhancedDamage + " mixed damage, but you block some.\n");

                                                    }
                                                }

                                            }
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }

                                    }

                                    int magicDMG = mage.secondSkill
                                            (playerLevel, playerMP, monsterMREffectiveness)[0];
                                    int healing;

                                    // Mage attack second
                                    if (rand.nextInt(100) < playerCritChance) {
                                        healing = (int) (mage.secondSkill
                                                (playerLevel, playerMP, monsterMREffectiveness)[1] * playerCritMultiplier);

                                        monsterHP = (int)
                                                (monsterHP - magicDMG * playerCritMultiplier * monsterMREffectiveness);
                                        playerHP += healing;

                                        if (playerHP > playerMaxHP) {
                                            playerHP = playerMaxHP;
                                        }

                                        combatArea.append("Your lightning SPLASHES for " +
                                                (int) (magicDMG * playerCritMultiplier) + " magic damage, healing " +
                                                healing + " HP!\n");

                                    } else {
                                        healing = mage.secondSkill(playerLevel, playerMP, monsterMREffectiveness)[1];

                                        monsterHP = (int)
                                                (monsterHP - magicDMG * monsterMREffectiveness);
                                        playerHP += healing;

                                        combatArea.append("You enhance your lightning to deal " +
                                                magicDMG + " magic damage and heal " +
                                                healing + " HP\n");

                                        if (playerHP > playerMaxHP) {
                                            playerMaxHP += mage.getSkill2ExcessHealing()[playerLevel - 1];
                                            playerHP = playerMaxHP;

                                            playerArmor += mage.getSkill2ExcessHealing()[playerLevel - 1];

                                            combatArea.append("Your spell vamp is so strong it increases your " +
                                                    "max HP and Armor by " +
                                                    mage.getSkill2ExcessHealing()[playerLevel - 1] + "!\n");
                                        }
                                    }


                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }
                                skill2Used = true;
                            }
                            case "assassin" -> {
                                manaCost = assassin.getSkill2Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];
                                    monsterHP = (int) (monsterHP - playerFinalADDealt * playerCritMultiplier);

                                    int armorSteal = assassin.secondSkill(playerLevel, monsterArmor);

                                    if (monsterArmor > armorSteal) {
                                        monsterArmor -= armorSteal;
                                    } else {
                                        monsterArmor = 0;
                                    }

                                    playerArmor += armorSteal;
                                    combatArea.append("You CRITICAL STRIKE for " + playerCritAD + " physical damage, " +
                                            "and even steal " + armorSteal + " Armor off " + monsterName + "!\n");

                                    if (monsterHP <= 0 && assassinStack2 < 10) {
                                        executed = false;
                                        playerCritMultiplier += assassin.getSkill2APenGain();
                                        playerAPen += assassin.getSkill2APenGain();
                                        assassinStack2++;

                                        combatArea.append("You gain a bit of Crit Damage and Armor Pen!\n");

                                    } else {
                                        playerMana += (manaCost[playerLevel - 1] / 2);
                                    }
                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                    skill2Used = true;
                                }
                            }
                        }

                        if (monsterHP < 0 || skill2Used) {
                            break;
                        }

                        // Monster attack
                        if (playerLevel >= 4) {
                            int monsterEnhancedDamage;
                            switch (monsterName) {
                                case "Zombie" -> {
                                    int zombieToughenChance = zombie.zombieToughenChance[playerLevel - 4];
                                    int zombieBiteChance = zombie.zombieBiteChance[playerLevel - 4];

                                    if (rand.nextInt(100) < zombieToughenChance) {
                                        monsterArmor += zombie.zombieToughenArmor[playerLevel - 4];
                                        monsterMR += zombie.zombieToughenMR[playerLevel - 4];
                                        combatArea.append("Zombie forms an iron skin around itself!\n");

                                    } else if (rand.nextInt(100) < zombieToughenChance + zombieBiteChance) {
                                        if (playerHP > playerMaxHP * 0.5) {
                                            monsterEnhancedDamage = zombie.zombieBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie bites the living out of you, dealing " +
                                                    monsterEnhancedDamage + " magic damage!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                        } else {
                                            monsterEnhancedDamage = zombie.zombieEnhancedBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie CHOMPS HARDER, dealing " + monsterEnhancedDamage +
                                                    " magic damage and removing some MR off you!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                            playerMR -= zombie.zombieBiteMRRemove[playerLevel - 4];
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Slime" -> {
                                    int slimeDivisionHPGain = slime.slimeDivisionHPGain(playerLevel, monsterHP);
                                    int slimeDivisionChance = slime.divisionChance[playerLevel - 4];

                                    if (rand.nextInt(100) < slimeDivisionChance) {
                                        monsterEnhancedDamage = slime.slimeDivisionDamage(playerLevel, monsterMP);
                                        playerHP -= (monsterAD * playerArmorEffectiveness +
                                                monsterEnhancedDamage * playerMREffectiveness);

                                        monsterMaxHP += slimeDivisionHPGain;
                                        monsterHP += slimeDivisionHPGain;


                                        combatArea.append("Slime gains " + slimeDivisionHPGain +
                                                " max HP and slaps you with an additional " +
                                                monsterEnhancedDamage + " magic damage!\n");

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Skeleton" -> {
                                    int skeletonBoneStrikeChance = skeleton.boneStrikeChance[playerLevel - 4];

                                    if (rand.nextInt(100) < playerDodgeChance) {


                                        if (playerClassName.equals("assassin")) {
                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                            monsterHP -= assassinPassiveDamage;
                                            combatArea.append
                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                            playerHP += assassinPassiveHeal;
                                            if (playerHP > playerMaxHP) {
                                                playerHP = playerMaxHP;
                                            }

                                        } else {
                                            combatArea.append
                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                        }
                                    } else if (rand.nextInt(100) < skeletonBoneStrikeChance) {
                                        monsterEnhancedDamage = skeleton.skeletonBoneStrike(playerLevel, monsterAD);
                                        playerHP = (int) (playerHP - monsterEnhancedDamage * playerArmorEffectiveness);
                                        playerArmor -= skeleton.boneStrikeArmorReduction[playerLevel - 4];


                                        combatArea.append("Skeleton enhances its attack and does " +
                                                monsterEnhancedDamage + " AD, taking away some of your armor!\n");


                                    } else {
                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                        combatArea.append(monsterName + " strikes for ");
                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                    }

                                }
                                case "Mimic" -> {
                                    int mimicChance = mimic.mimicChance[playerLevel - 4];

                                    if (rand.nextInt(100) < mimicChance) {
                                        int ADStolen = mimic.mimicStealAD(playerAD);
                                        int ArmorStolen = mimic.mimicStealAD(playerArmor);

                                        playerAD -= ADStolen;
                                        playerArmor -= ArmorStolen;

                                        monsterAD += ADStolen;
                                        monsterArmor += ArmorStolen;

                                        combatArea.append("Mimic just robs ");
                                        combatArea.append(ADStolen + " AD and " + ArmorStolen + " Armor off you!\n");


                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }

                                case "Diablo" -> {
                                    int drainChance = diablo.drainChance[playerLevel - 4];

                                    if (rand.nextInt(100) < drainChance) {
                                        int manaStolen = diablo.drainMana[playerLevel - 4];

                                        if (playerMana < manaStolen) {
                                            monsterMP += playerMana;
                                            playerMana = 0;

                                            combatArea.append
                                                    ("Diablo drains all of your MANA and gains all of them!\n");
                                        } else {
                                            playerMana -= manaStolen;
                                            monsterMP += manaStolen;

                                            combatArea.append("Diablo drains " + manaStolen
                                                    + " Mana and gains all of them!\n");
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            monsterEnhancedDamage =
                                                    (int) (monsterAD + diablo.giftMPRatio[playerLevel - 4] / 100 * monsterMP);

                                            int halfComponent = monsterEnhancedDamage / 2;

                                            playerHP = (int) (playerHP -
                                                    (halfComponent * playerArmorEffectiveness +
                                                            halfComponent * playerMREffectiveness));


                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterEnhancedDamage + " mixed damage, but you block some.\n");

                                        }
                                    }

                                }
                            }

                        } else {
                            if (rand.nextInt(100) < playerDodgeChance) {
                                if (playerClassName.equals("assassin")) {
                                    int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                    int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                    monsterHP -= assassinPassiveDamage;
                                    combatArea.append
                                            ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                    " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                    playerHP += assassinPassiveHeal;
                                    if (playerHP > playerMaxHP) {
                                        playerHP = playerMaxHP;
                                    }
                                } else {
                                    combatArea.append
                                            ("You just dodge " + monsterName + "'s attack!\n");
                                }
                            } else {
                                playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                combatArea.append(monsterName + " strikes for ");
                                combatArea.append(monsterAD + " physical damage, but you block some.\n");

                            }

                        }
                    }

                    case "skill3" -> {
                        boolean skill3Used = false;
                        switch (playerClassName) {
                            case "fighter" -> {
                                manaCost = fighter.getSkill3Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int skill3Damage = fighter.thirdSkill
                                            (playerLevel, playerMP, monsterMissingHP);

                                    combatArea.append("You slam your holy sword down, " +
                                            "dealing " + skill3Damage + " TRUE DAMAGE and " +
                                            "stunning the monster! ");
                                    monsterHP = monsterHP - skill3Damage;

                                    if (monsterHP <= 0) {
                                        executed = false;
                                        playerMana += fighter.getSkill3ManaGain()[playerLevel - 1];
                                        playerAD += fighter.getSkill3ADGain()[playerLevel - 1];
                                        playerArmor += fighter.getSkill3ADGain()[playerLevel - 1];
                                        combatArea.append(monsterName + " is EXECUTED and " +
                                                "you gain some Mana, AD, and Armor!\n");
                                    }


                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }
                                skill3Used = true;

                            }
                            case "archer" -> {
                                manaCost = archer.getSkill3Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int skill3Damage = archer.thirdSkill
                                            (playerLevel, playerMP, playerAD);

                                    double monsterMRIgnored =
                                            (double) archer.getSkill3MRIgnored()[playerLevel - 1] / 100;

                                    monsterMR *= (1 - monsterMRIgnored);

                                    monsterHP = (int)
                                            (monsterHP - skill3Damage * monsterMREffectiveness);

                                    combatArea.append("You fire a blazing arrow towards " + monsterName +
                                            ", dealing " + skill3Damage + " magic damage! ");

                                    monsterMR /= (1 - monsterMRIgnored);

                                    if (monsterHP < 0) {
                                        executed = false;
                                        int gold = archer.getSkill3GoldGain()[playerLevel - 1];
                                        playerGold += gold;
                                        combatArea.append("Furthermore, " + monsterName +
                                                " is burnt to death and drops " + gold + " gold!\n");

                                        if (archerStack3 < 8) {
                                            playerCritMultiplier += archer.getSkill3CritDMGGain();
                                            playerCritChance += archer.getSkill3CritChanceGain();
                                            archerStack3++;
                                            combatArea.append("You gain a bit of Crit Chance and Damage as well!\n");
                                        }

                                    }

                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                    skill3Used = true;
                                }

                            }
                            case "mage" -> {
                                manaCost = mage.getSkill3Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int magicDMG = mage.thirdSkill(playerLevel, playerMP);
                                    int magicComponent;
                                    int trueDamageComponent;
                                    int finalMagicDMG;

                                    if (rand.nextInt(100) < playerCritChance) {
                                        magicComponent = magicDMG / 2;
                                        trueDamageComponent = magicDMG / 2;
                                        finalMagicDMG = (int)
                                                (magicComponent * monsterMREffectiveness + trueDamageComponent);

                                        combatArea.append("The giant fireball becomes a SUPERNOVA, dealing " +
                                                finalMagicDMG + " magic and TRUE DAMAGE!\n");

                                    } else {
                                        finalMagicDMG = (int) (magicDMG * monsterMREffectiveness);

                                        combatArea.append("The giant fireball burns " + monsterName + ", dealing " +
                                                finalMagicDMG + " magic damage\n");
                                    }

                                    monsterHP = monsterHP - finalMagicDMG;

                                    if (monsterHP <= 0) {
                                        executed = false;
                                        playerArmor += mage.getSkill3MPArmorIncrease()[playerLevel - 1];
                                        playerMP += mage.getSkill3MPArmorIncrease()[playerLevel - 1];

                                        combatArea.append(monsterName + " is EXECUTED and " +
                                                "you gain some Armor and MP!\n");
                                    }

                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...\n");
                                }

                            }
                            case "assassin" -> {
                                manaCost = assassin.getSkill3Mana();
                                if (playerMana >= manaCost[playerLevel - 1]) {
                                    playerMana -= manaCost[playerLevel - 1];

                                    int ADSteal = assassin.thirdSkill(playerLevel, monsterAD);
                                    int HPMRSteal = assassin.getSkill3HPMRSteal()[playerLevel - 1];

                                    monsterHP -= HPMRSteal;
                                    playerMaxHP += HPMRSteal;
                                    playerHP += HPMRSteal;

                                    if (monsterAD > ADSteal) {
                                        monsterAD -= ADSteal;
                                        playerAD += ADSteal;
                                    } else {
                                        playerAD += (monsterAD - 1);
                                        monsterAD = 1;
                                    }

                                    if (monsterMR > HPMRSteal) {
                                        monsterMR -= HPMRSteal;
                                        playerMR += HPMRSteal;
                                    } else {
                                        playerMR += HPMRSteal;
                                        monsterMR = 0;
                                    }

                                    combatArea.append("You rob " + HPMRSteal + " HP, MR and " + ADSteal + " AD off " +
                                            monsterName + "... Brutal!\n");


                                } else {
                                    combatArea.append("Not enough mana... Basic attack to get more...!\n");
                                }
                                skill3Used = true;
                            }
                        }

                        if (monsterHP < 0 || skill3Used) {
                            break;
                        }

                        // Monster attack
                        if (playerLevel >= 4) {
                            int monsterEnhancedDamage;
                            switch (monsterName) {
                                case "Zombie" -> {
                                    int zombieToughenChance = zombie.zombieToughenChance[playerLevel - 4];
                                    int zombieBiteChance = zombie.zombieBiteChance[playerLevel - 4];

                                    if (rand.nextInt(100) < zombieToughenChance) {
                                        monsterArmor += zombie.zombieToughenArmor[playerLevel - 4];
                                        monsterMR += zombie.zombieToughenMR[playerLevel - 4];
                                        combatArea.append("Zombie forms an iron skin around itself!\n");

                                    } else if (rand.nextInt(100) < zombieToughenChance + zombieBiteChance) {
                                        if (playerHP > playerMaxHP * 0.5) {
                                            monsterEnhancedDamage = zombie.zombieBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie bites the living out of you, dealing " +
                                                    monsterEnhancedDamage + " magic damage!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                        } else {
                                            monsterEnhancedDamage = zombie.zombieEnhancedBite(playerLevel, monsterAD, monsterMP);
                                            combatArea.append("Zombie CHOMPS HARDER, dealing " + monsterEnhancedDamage +
                                                    " magic damage and removing some MR off you!\n");
                                            playerHP -= monsterEnhancedDamage * playerMREffectiveness;
                                            playerMR -= zombie.zombieBiteMRRemove[playerLevel - 4];
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Slime" -> {
                                    int slimeDivisionHPGain = slime.slimeDivisionHPGain(playerLevel, monsterHP);
                                    int slimeDivisionChance = slime.divisionChance[playerLevel - 4];

                                    if (rand.nextInt(100) < slimeDivisionChance) {
                                        monsterEnhancedDamage = slime.slimeDivisionDamage(playerLevel, monsterMP);
                                        playerHP = (int) (playerHP -
                                                (monsterAD * playerArmorEffectiveness +
                                                        monsterEnhancedDamage * playerMREffectiveness));

                                        monsterMaxHP += slimeDivisionHPGain;
                                        monsterHP += slimeDivisionHPGain;


                                        combatArea.append("Slime gains " + slimeDivisionHPGain +
                                                " max HP and slaps you with an additional " +
                                                monsterEnhancedDamage + " magic damage!\n");

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }
                                case "Skeleton" -> {
                                    int skeletonBoneStrikeChance = skeleton.boneStrikeChance[playerLevel - 4];

                                    if (rand.nextInt(100) < playerDodgeChance) {


                                        if (playerClassName.equals("assassin")) {
                                            int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                            int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                            monsterHP -= assassinPassiveDamage;
                                            combatArea.append
                                                    ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                            " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                            playerHP += assassinPassiveHeal;
                                            if (playerHP > playerMaxHP) {
                                                playerHP = playerMaxHP;
                                            }

                                        } else {
                                            combatArea.append
                                                    ("You just dodge " + monsterName + "'s attack!\n");
                                        }
                                    } else if (rand.nextInt(100) < skeletonBoneStrikeChance) {
                                        monsterEnhancedDamage = skeleton.skeletonBoneStrike(playerLevel, monsterAD);
                                        playerHP = (int) (playerHP - monsterEnhancedDamage * playerArmorEffectiveness);
                                        playerArmor -= skeleton.boneStrikeArmorReduction[playerLevel - 4];


                                        combatArea.append("Skeleton enhances its attack and does " +
                                                monsterEnhancedDamage + " AD, taking away some of your armor!\n");


                                    } else {
                                        playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                        combatArea.append(monsterName + " strikes for ");
                                        combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                    }

                                }
                                case "Mimic" -> {
                                    int mimicChance = mimic.mimicChance[playerLevel - 4];

                                    if (rand.nextInt(100) < mimicChance) {
                                        int ADStolen = mimic.mimicStealAD(playerAD);
                                        int ArmorStolen = mimic.mimicStealAD(playerArmor);

                                        playerAD -= ADStolen;
                                        playerArmor -= ArmorStolen;

                                        monsterAD += ADStolen;
                                        monsterArmor += ArmorStolen;

                                        combatArea.append("Mimic just robs ");
                                        combatArea.append(ADStolen + " AD and " + ArmorStolen + " Armor off you!\n");


                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterAD + " physical damage, but you block some.\n");

                                        }
                                    }
                                }

                                case "Diablo" -> {
                                    int drainChance = diablo.drainChance[playerLevel - 4];

                                    if (rand.nextInt(100) < drainChance) {
                                        int manaStolen = diablo.drainMana[playerLevel - 4];

                                        if (playerMana < manaStolen) {
                                            monsterMP += playerMana;
                                            playerMana = 0;

                                            combatArea.append
                                                    ("Diablo drains all of your MANA and gains all of them!\n");
                                        } else {
                                            playerMana -= manaStolen;
                                            monsterMP += manaStolen;

                                            combatArea.append("Diablo drains " + manaStolen
                                                    + " Mana and gains all of them!\n");
                                        }

                                    } else {
                                        if (rand.nextInt(100) < playerDodgeChance) {


                                            if (playerClassName.equals("assassin")) {
                                                int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                                int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                                monsterHP -= assassinPassiveDamage;
                                                combatArea.append
                                                        ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                                " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                                playerHP += assassinPassiveHeal;
                                                if (playerHP > playerMaxHP) {
                                                    playerHP = playerMaxHP;
                                                }
                                            } else {
                                                combatArea.append
                                                        ("You just dodge " + monsterName + "'s attack!\n");
                                            }
                                        } else {
                                            monsterEnhancedDamage =
                                                    (int) (monsterAD + diablo.giftMPRatio[playerLevel - 4] / 100 * monsterMP);

                                            int halfComponent = monsterEnhancedDamage / 2;

                                            playerHP = (int) (playerHP -
                                                    (halfComponent * playerArmorEffectiveness +
                                                            halfComponent * playerMREffectiveness));


                                            combatArea.append(monsterName + " strikes for ");
                                            combatArea.append(monsterEnhancedDamage + " mixed damage, but you block some.\n");

                                        }
                                    }

                                }
                            }

                        } else {
                            if (rand.nextInt(100) < playerDodgeChance) {

                                if (playerClassName.equals("assassin")) {
                                    int assassinPassiveDamage = assassin.passiveCounter(playerLevel, playerAD);
                                    int assassinPassiveHeal = assassin.passiveHeal(playerLevel, playerMissingHP, playerAD);
                                    monsterHP -= assassinPassiveDamage;
                                    combatArea.append
                                            ("You dodge " + monsterName + "'s attack, return " + assassinPassiveDamage +
                                                    " TRUE DAMAGE, and heals you " + assassinPassiveHeal + " HP!\n");

                                    playerHP += assassinPassiveHeal;
                                    if (playerHP > playerMaxHP) {
                                        playerHP = playerMaxHP;
                                    }
                                } else {
                                    combatArea.append
                                            ("You just dodge " + monsterName + "'s attack!\n");
                                }
                            } else {
                                playerHP = (int) (playerHP - monsterAD * playerArmorEffectiveness);

                                combatArea.append(monsterName + " strikes for ");
                                combatArea.append(monsterAD + " physical damage, but you block some.\n");

                            }

                        }


                    }
                }


            }
            playerMissingHP = playerMaxHP - playerHP;
            monsterMissingHP = monsterMaxHP - monsterHP;

            //Player dead
            if (playerHP <= 0) {
                combatArea.append("You have been slainnn... Better luck next time!\n");
                handlers.createFilesIfNotExists("results.txt");
                writeResults();
                newOrQuit();
            }

            if (monsterHP <= 0 && playerHP > 0) {
                slimeTurnCountdown = slime.gooTurnNumber;
                monsterMissingHP = 0;

                if (executed) {
                    combatArea.append("With a mighty blow, you chop down " + monsterName + "!\n");
                }
                monsterDefeated++;

                handlers.sendToHandler("Get gold/" + monsterName);
                handlers.sendToHandler("Get EXP/" + monsterName);
                goldDrop = handlers.getMonsterGold();
                EXPDrop = handlers.getMonsterEXP();

                playerGold += goldDrop;
                playerEXP += EXPDrop;

                // Level Up
                boolean levelUp = false;
                String[] levelUpText = {"Level Up! You are now level 2, gaining some wonderful stats! " +
                        "You can now buy weapons to further boost yourself!",
                        "Level Up! You are now level 3, stronger than ever!",
                        "Level Up! You are now level 4! The fun only has just begun...",
                        "Level Up! You are now level 5! The monsters have gotten A LOT stronger...",
                        "Level Up! You are now level 6, heading towards the unclaimed title... " +
                                "Can you do it?",
                        "ASCENSION! You are now at the final level, becoming ONE TRUE DUNGEON MASTER!"};

                int[] healingGrowth = {4, 4, 4, 4, 4, 4};

                if (playerLevel == 1 && playerEXP >= 1000) {  // 1000 EXP to level 2
                    playerLevel++;
                    playerEXP -= 1000;
                    levelUp = true;
                } else if (playerLevel == 2 && playerEXP >= 2000) {  // 2000 EXP to level 3
                    playerLevel++;
                    playerEXP -= 2000;
                    levelUp = true;
                } else if (playerLevel == 3 && playerEXP >= 3000) {  // 3000 EXP to level 4
                    playerLevel++;
                    playerEXP -= 3000;
                    levelUp = true;
                } else if (playerLevel == 4 && playerEXP >= 5000) {  // 5000 EXP to level 5
                    playerLevel++;
                    playerEXP -= 5000;
                    levelUp = true;
                } else if (playerLevel == 5 && playerEXP >= 8000) {  // 8000 EXP to level 6
                    playerLevel++;
                    playerEXP -= 8000;
                    levelUp = true;
                } else if (playerLevel == 6 && playerEXP >= 13000) {  // 13000 EXP to level 7
                    playerLevel++;
                    playerEXP -= 13000;
                    levelUp = true;
                }

                switch (playerClassName) {
                    case "fighter" -> {
                        int fighterPassiveHeal = fighter.passiveHeal(playerLevel, playerMissingHP);
                        int fighterPassiveArmor = fighter.passiveArmorMR(playerLevel)[0];
                        int fighterPassiveMR = fighter.passiveArmorMR(playerLevel)[1];

                        playerHP += fighterPassiveHeal;
                        if (playerHP > playerMaxHP) {
                            playerHP = playerMaxHP;
                        }

                        playerMaxHP += fighter.passiveHPGain[playerLevel - 1];
                        playerHP += fighter.passiveHPGain[playerLevel - 1];

                        playerArmor += fighterPassiveArmor;
                        playerMR += fighterPassiveMR;
                        combatArea.append(monsterName + " drops " + goldDrop +
                                " gold and " + EXPDrop + " EXP!\n");
                        combatArea.append("You absorb the spirit of " + monsterName +
                                ", healing some HP and gaining some sweet defensive stats!\n");
                        playerMissingHP = playerMaxHP - playerHP;

                        if (aegis) {
                            combatArea.append("AEGIS grants you " +
                                    (int) (playerAD * shop.shield.aegisADArmorGain / 100) + " AD ");
                            combatArea.append("and " +
                                    (int) (playerArmor * shop.shield.aegisADArmorGain / 100) + " Armor!\n");

                            playerAD += playerAD * shop.shield.aegisADArmorGain / 100;
                            playerArmor += playerArmor * shop.shield.aegisADArmorGain / 100;
                        }

                    }
                    case "archer" -> {
                        playerAD += archer.getPassiveADGain()[playerLevel - 1];
                        combatArea.append(monsterName + " drops " + goldDrop +
                                " gold and " + EXPDrop + " EXP, ");
                        combatArea.append("and you gain some AD as well!\n");

                    }
                    case "mage" -> {
                        int magePassiveHeal = mage.passiveHeal(playerMana);

                        playerHP += magePassiveHeal;
                        if (playerHP > playerMaxHP) {
                            playerHP = playerMaxHP;
                        }
                        combatArea.append(monsterName + " drops " + goldDrop +
                                " gold and " + EXPDrop + " EXP!\n");
                        combatArea.append("The essence of Mana heals you for " + magePassiveHeal + " HP!\n");

                    }
                    case "assassin" -> {
                        int assassinPassiveGold = assassin.getPassiveGoldGain()[playerLevel - 1];
                        playerGold += assassinPassiveGold;
                        combatArea.append(monsterName + " drops " + goldDrop +
                                " gold and " + EXPDrop + " EXP, ");
                        combatArea.append("and you snatch some more gold and EXP as well!\n");

                    }
                }

                if (levelUp) {
                    playerMaxHP += playerClass.HPGrowth[playerLevel - 1];
                    playerHP = playerMaxHP;
                    playerAD += playerClass.ADGrowth[playerLevel - 1];
                    playerMP += playerClass.MPGrowth[playerLevel - 1];
                    playerArmor += playerClass.ArmorGrowth[playerLevel - 1];
                    playerMR += playerClass.MRGrowth[playerLevel - 1];
                    playerMana += playerClass.bonusMana[playerLevel - 1];
                    playerManaRegen += playerClass.ManaRegenGrowth[playerLevel - 1];
                    playerAPen += playerClass.APenGrowth[playerLevel - 1];
                    playerCritChance += playerClass.critChanceGrowth[playerLevel - 1];

                    combatArea.append(levelUpText[playerLevel - 2]);
                    lifePotion.healing += healingGrowth[playerLevel - 2];
                }

                continueOrLeave();
            }

            updatePlayerStatsGUI();
            updateEnemyStatsGUI();
        }

        // Dungeon, next monster
        if (e.getActionCommand().equals("continue") || e.getActionCommand().equals("return")) {
            continueOption();

            if (rand.nextInt(100) < 60) {
                handlers.sendToHandler("Get new monster/Easy/" + playerLevel);
                difficulty = "(Easy)";
            } else if (rand.nextInt(100) < 90) {
                handlers.sendToHandler("Get new monster/Medium/" + playerLevel);
                difficulty = "(Medium)";
            } else {
                handlers.sendToHandler("Get new monster/Hard/" + playerLevel);
                difficulty = "(Hard)";
            }

            currentMonster = handlers.getCurrentMonster();

            monsterName = currentMonster.getName();
            monsterHP = currentMonster.getHP();
            monsterAD = currentMonster.getAD();
            monsterMP = currentMonster.getMP();
            monsterArmor = currentMonster.getArmor();
            monsterMR = currentMonster.getMR();

            monsterHandler = new MonsterHandler(monsterName, monsterHP, monsterAD, monsterMP,
                    monsterArmor, monsterMR);

            monsterMaxHP = monsterHP;

            monsterLabel.setText(monsterName);
            monsterHealthBar.setMinimum(0);
            monsterHealthBar.setMaximum(monsterMaxHP);
            monsterHealthBar.setValue(monsterMaxHP);
            monsterHealthBar.setString(monsterHP + "/" + monsterMaxHP + " HP");
            updateEnemyStatsGUI();
            combatArea.setText("");
            combatArea.append(monsterName + " has appeared! " + difficulty + "\n");
            combatArea.append("<" + monsterHP + " HP, " + monsterAD + " AD, " +
                    monsterMP + " MP, " + monsterArmor + " Armor, " + monsterMR + " MR>\n");
            updatePlayerStatsGUI();

            if (shieldEquipped) {
                int oldMonsterAD = monsterAD;
                monsterAD *= (1 - shop.shield.monsterADReduction);
                combatArea.append("Your shield reduces " + monsterName + "'s AD from " + oldMonsterAD +
                        " to " + monsterAD + "!\n");
            }
        }

        // Dungeon, to main screen
        if (e.getSource() == dungeonGUI.getLeaveButton()) {
            dungeonGUI.getFrame().setVisible(false);
            mainGameGUI.getFrame().setVisible(true);
            mainGameGUI.getReturnButton().setVisible(true);
            mainGameGUI.getDungeonButton().setVisible(false);
        }

        // Dungeon, surrender
        if (e.getSource() == dungeonGUI.getSurrenderButton()) {
            System.exit(0);
        }

    }

    private void reset() {
        playerClassName = "";
        playerLevel = 1;
        playerHP = 0;
        playerMaxHP = 0;
        playerAD = 0;
        playerMP = 0;
        playerArmor = 0;
        playerMR = 0;
        playerMana = 0;
        playerManaRegen = 0;
        playerCritChance = 0;
        playerCritMultiplier = 0;
        playerDodgeChance = 0;
        playerAPen = 0;
        playerMPen = 0;

        newNameGUI.getFrame().setVisible(true);
    }

    public void fighterUpdateSkillsRatioGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append(fighter.getFighterPassiveRatio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(fighter.getFighterSkill1(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(fighter.getFighterSkill2Ratio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(fighter.getFighterSkill3Ratio(playerLevel) + "\n\n");
    }

    public void archerUpdateSkillsRatioGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append(archer.getArcherPassive(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(archer.getArcherSkill1Ratio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(archer.getArcherSkill2(playerLevel) + "\n\n");

        if (archerStack3 != 8) {
            skillNameGUI.getSkillsArea().append(archer.getArcherSkill3Ratio(playerLevel) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append(archer.getArcherSkill3StackedRatio(playerLevel) + "\n\n");
        }

    }

    public void mageUpdateSkillsRatioGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append(mage.getMagePassiveRatio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(mage.getMageSkill1Ratio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(mage.getMageSkill2Ratio(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(mage.getMageSkill3Ratio(playerLevel) + "\n\n");
    }

    public void assassinUpdateSkillsRatioGUI() {
        skillNameGUI.getSkillsArea().setText("");

        skillNameGUI.getSkillsArea().append(assassin.getAssassinPassiveRatio(playerLevel) + "\n\n");

        if (assassinStack1 != 5) {
            skillNameGUI.getSkillsArea().append(assassin.getAssassinSkill1Ratio(playerLevel) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append(assassin.getAssassinSkill1StackedRatio(playerLevel) + "\n\n");
        }

        if (assassinStack2 != 10) {
            skillNameGUI.getSkillsArea().append(assassin.getAssassinSkill2Ratio(playerLevel) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append(assassin.getAssassinSkill2StackedRatio(playerLevel) + "\n\n");
        }

        skillNameGUI.getSkillsArea().append(assassin.getAssassinSkill3Ratio(playerLevel) + "\n\n");
    }

    public void fighterUpdateSkillsGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append
                (fighter.getFighterPassive(playerLevel, playerMissingHP) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (fighter.getFighterSkill1(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (fighter.getFighterSkill2(playerLevel, playerAD, monsterArmor) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (fighter.getFighterSkill3(playerLevel, playerMP, monsterMissingHP) + "\n\n");

        dungeonGUI.getSkill1Button().setText
                (fighter.getSkill1Name(playerLevel));
        dungeonGUI.getSkill2Button().setText
                (fighter.getSkill2Name(playerLevel));
        dungeonGUI.getSkill3Button().setText
                (fighter.getSkill3Name(playerLevel));
    }

    public void archerUpdateSkillsGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append(archer.getArcherPassive(playerLevel) + "\n\n");
        skillNameGUI.getSkillsArea().append(archer.getArcherSkill1(playerLevel, playerAD, playerMP) + "\n\n");
        skillNameGUI.getSkillsArea().append(archer.getArcherSkill2(playerLevel) + "\n\n");

        if (archerStack3 != 8) {
            skillNameGUI.getSkillsArea().append(archer.getArcherSkill3(playerLevel, playerAD, playerMP) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append
                    (archer.getArcherSkill3Stacked(playerLevel, playerAD, playerMP) + "\n\n");
        }

        dungeonGUI.getSkill1Button().setText
                (archer.getSkill1Name(playerLevel));
        dungeonGUI.getSkill2Button().setText
                (archer.getSkill2Name(playerLevel));
        dungeonGUI.getSkill3Button().setText
                (archer.getSkill3Name(playerLevel));
    }

    public void mageUpdateSkillsGUI() {
        skillNameGUI.getSkillsArea().setFont(song.deriveFont(18f));
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append
                (mage.getMagePassive(playerLevel, playerMaxHP, playerMP, playerMana) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (mage.getMageSkill1(playerLevel, playerMP) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (mage.getMageSkill2(playerLevel, playerMP) + "\n\n");
        skillNameGUI.getSkillsArea().append
                (mage.getMageSkill3(playerLevel, playerMP) + "\n\n");

        dungeonGUI.getSkill1Button().setText
                (mage.getSkill1Name(playerLevel));
        dungeonGUI.getSkill2Button().setText
                (mage.getSkill2Name(playerLevel));
        dungeonGUI.getSkill3Button().setText
                (mage.getSkill3Name(playerLevel));
    }

    public void assassinUpdateSkillsGUI() {
        skillNameGUI.getSkillsArea().setText("");
        skillNameGUI.getSkillsArea().append
                (assassin.getAssassinPassive(playerLevel, playerAD, playerMissingHP) + "\n\n");


        if (assassinStack1 != 5) {
            skillNameGUI.getSkillsArea().append
                    (assassin.getAssassinSkill1(playerLevel, playerMaxHP) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append
                    (assassin.getAssassinSkill1Stacked(playerLevel, playerMaxHP) + "\n\n");
        }

        if (assassinStack2 != 10) {
            skillNameGUI.getSkillsArea().append
                    (assassin.getAssassinSkill2(playerLevel, monsterArmor) + "\n\n");
        } else {
            skillNameGUI.getSkillsArea().append
                    (assassin.getAssassinSkill2Stacked(playerLevel, monsterArmor) + "\n\n");
        }

        skillNameGUI.getSkillsArea().append
                (assassin.getAssassinSkill3(playerLevel, monsterAD) + "\n\n");

        dungeonGUI.getSkill1Button().setText
                (assassin.getSkill1Name(playerLevel));
        dungeonGUI.getSkill2Button().setText
                (assassin.getSkill2Name(playerLevel));
        dungeonGUI.getSkill3Button().setText
                (assassin.getSkill3Name(playerLevel));
    }

    public void updatePlayerStatsGUI() {
        playerHealthBar.setMaximum(playerMaxHP);
        playerHealthBar.setValue(playerHP);
        playerHealthBar.setString(playerHP + "/" + playerMaxHP + " HP");
        playerManaBar.setValue(playerMana);
        playerManaBar.setString(playerMana + " Mana");

        statsGUI.getNameLabel().setText(currentPlayer.getUsername() +
                " <" + currentPlayer.getClassName() + ">");
        statsGUI.getLevelLabel().setText("Level " + playerLevel +
                " <" + playerGold + " Gold, " + playerEXP + " EXP>");
        statsGUI.getHPLabel().setText("Health Points: " + playerHP + "/" + playerMaxHP);
        statsGUI.getManaLabel().setText("Mana: " + playerMana);
        statsGUI.getADLabel().setText("Attack Damage: " + playerAD);
        statsGUI.getMPLabel().setText("Magic Power: " + playerMP);
        statsGUI.getArmorLabel().setText("Armor: " + playerArmor);
        statsGUI.getMRLabel().setText("Magic Resist: " + playerMR);
        statsGUI.getManaRegenLabel().setText("Mana Regen: " + playerManaRegen);
        statsGUI.getMonsterLabel().setText("Monsters Defeated: " + monsterDefeated);

        if (currentPlayer.getCritChance() != 0) {
            statsGUI.getCritChanceLabel().setText
                    ("Crit Chance: " + playerCritChance + "%");
        }
        if (currentPlayer.getCritMultiplier() != 0) {
            statsGUI.getCritDamageLabel().setText
                    ("Crit Damage: " + (int) (100 * playerCritMultiplier) + "%");
        }
        if (currentPlayer.getDodgeChance() != 0) {
            statsGUI.getDodgeLabel().setText
                    ("Dodge Chance: " + playerDodgeChance + "%");
        }

        switch (playerClassName) {
            case "fighter", "archer", "assassin" -> {
                if (playerAPen != 0) {
                    statsGUI.getPenLabel().setText("Armor Penetration: " +
                            (int) (100 * playerAPen) + "%");
                } else {
                    statsGUI.getPenLabel().setText("");
                }

            }

            case "mage" -> {
                if (playerMPen != 0) {
                    statsGUI.getPenLabel().setText("MR Penetration: " +
                            (int) (100 * playerMPen) + "%");
                } else {
                    statsGUI.getPenLabel().setText("");
                }

            }
        }

        if (skillCalculated) {
            switch (playerClassName) {
                case "fighter" -> fighterUpdateSkillsGUI();
                case "archer" -> archerUpdateSkillsGUI();
                case "mage" -> mageUpdateSkillsGUI();
                case "assassin" -> assassinUpdateSkillsGUI();
            }

        } else if (skillRatio) {
            switch (playerClassName) {
                case "fighter" -> fighterUpdateSkillsRatioGUI();
                case "archer" -> archerUpdateSkillsRatioGUI();
                case "mage" -> mageUpdateSkillsRatioGUI();
                case "assassin" -> assassinUpdateSkillsRatioGUI();
            }
        }


        if (lifePotion.amount == 0) {
            inventoryGUI.getLifePotionLabel().setText("--- No healing potions ---");
        } else {
            inventoryGUI.getLifePotionLabel().setText(lifePotion.itemName + " x " + lifePotion.amount);
        }

        if (itemSlot[1] == null) {
            inventoryGUI.getWeaponLabel().setText("--- No current weapon ---");
        } else {
            inventoryGUI.getWeaponLabel().setText(itemSlot[1].itemName);
        }
    }

    public void updateShop() {
        System.out.println("Item bought/" + itemBought);
        System.out.println("Item 1 in shop/" + itemsInShop[1]);
        System.out.println("Item 2 in shop/" + itemsInShop[2]);
        shopGUI.getLifePotionButton().setText
                ("(" + lifePotion.price + "g) " + lifePotion.itemName);

        if (itemBought == null) {
            weapon1Button.setText("(" + weapon1.price + "g) " + weapon1.itemName);
            weapon1Button.addActionListener(this);
            weapon2Button.setText("(" + weapon2.price + "g) " + weapon2.itemName);
            weapon2Button.addActionListener(this);
        } else if (weapon1Bought && itemBought.equals(itemsInShop[1])) {
            weapon1Button.setText("-----");
            weapon1Button.removeActionListener(this);
        } else if (weapon2Bought && itemBought.equals(itemsInShop[2])) {
            weapon2Button.setText("-----");
            weapon2Button.removeActionListener(this);
        } else if (itemSlot[1] != null) {
            weapon1Button.setText("(" + weapon1.price + "g) " + weapon1.itemName);
            weapon1Button.addActionListener(this);
            weapon2Button.setText("(" + weapon2.price + "g) " + weapon2.itemName);
            weapon2Button.addActionListener(this);

        }

        shopGUI.getQuickADButton().setText(itemsInShop[3].toString());
        shopGUI.getQuickMPButton().setText(itemsInShop[4].toString());
        shopGUI.getQuickDEFButton().setText(itemsInShop[5].toString());
    }

    public void updateEnemyStatsGUI() {
        monsterHealthBar.setMaximum(monsterMaxHP);
        monsterHealthBar.setValue(monsterHP);
        monsterHealthBar.setString(monsterHP + "/" + monsterMaxHP + " HP");

        enemyStatsGUI.getNameLabel().setText(monsterName + " " + difficulty);
        enemyStatsGUI.getHPLabel().setText("Health Points: " + monsterHP + "/" + monsterMaxHP);
        enemyStatsGUI.getADLabel().setText("Attack Damage: " + monsterAD);
        enemyStatsGUI.getMPLabel().setText("Magic Power: " + monsterMP);
        enemyStatsGUI.getArmorLabel().setText("Armor: " + monsterArmor);
        enemyStatsGUI.getMRLabel().setText("Magic Resist: " + monsterMR);
    }

    public void continueOrLeave() {
        dungeonGUI.getAttackButton().setVisible(false);
        dungeonGUI.getHealButton().setVisible(false);
        dungeonGUI.getSkill1Button().setVisible(false);
        dungeonGUI.getSkill2Button().setVisible(false);
        dungeonGUI.getSkill3Button().setVisible(false);
        combatArea.setSize(460, 200);

        dungeonGUI.getContinueButton().setVisible(true);
        dungeonGUI.getLeaveButton().setVisible(true);
    }

    public void continueOption() {
        mainGameGUI.getFrame().setVisible(false);
        dungeonGUI.getFrame().setVisible(true);
        dungeonGUI.getAttackButton().setVisible(true);
        dungeonGUI.getHealButton().setVisible(true);
        dungeonGUI.getSkill1Button().setVisible(true);
        dungeonGUI.getSkill2Button().setVisible(true);
        dungeonGUI.getSkill3Button().setVisible(true);
        combatArea.setSize(460, 150);

        dungeonGUI.getContinueButton().setVisible(false);
        dungeonGUI.getLeaveButton().setVisible(false);

        updatePlayerStatsGUI();
    }

    public void newOrQuit() {
        dungeonGUI.getAttackButton().setVisible(false);
        dungeonGUI.getHealButton().setVisible(false);
        dungeonGUI.getSkill1Button().setVisible(false);
        dungeonGUI.getSkill2Button().setVisible(false);
        dungeonGUI.getSkill3Button().setVisible(false);

        dungeonGUI.getAgainButton().setVisible(true);
        dungeonGUI.getSurrenderButton().setVisible(true);

    }

    public void writeResults() {
        try (FileWriter f = new FileWriter("results.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {
            p.println(playerLevel + ", " + playerClassName + ": " +
                    playerMaxHP + " HP, " + playerAD + " AD, " + playerMP + " MP, " +
                    playerArmor + " Armor, " + playerMR + " MR, " + monsterDefeated + " monsters defeated");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

}


