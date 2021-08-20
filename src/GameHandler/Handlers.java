package GameHandler;

import Entities.*;
import Hostile.*;
import Items.Inventory;
import Items.Item;
import Items.Weapon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Handlers {
    private String message;

    PlayerHandler playerHandler;
    MonsterHandler monsterHandler;
    Random rand = new Random();

    PlayerClass playerClass;
    Player currentPlayer;
    int playerLevel;
    int playerEXP;
    Inventory inventory;
    Item lifePotion;

    Monster currentMonster;
    Slime slime = new Slime();
    Zombie zombie = new Zombie();
    Skeleton skeleton = new Skeleton();
    Mimic mimic = new Mimic();
    Diablo diablo = new Diablo();

    Shop shop;
    Item[] itemsInShop = new Item[6];
    Weapon weapon1;
    Weapon weapon2;
    int[] ADPrice;
    int[] MPPrice;
    int[] DEFPrice;

    int monsterGold;
    int monsterEXP;

    public Handlers() {
        try {
            createFilesIfNotExists("names.txt");
            playerHandler = new PlayerHandler("names.txt");
            monsterHandler = new MonsterHandler();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createFilesIfNotExists(String filename) {
        File file = new File(filename);
        if (!file.isFile()) {
            try {
                if (file.createNewFile())
                    System.out.println(filename + "file created");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendToHandler(String message) {
        this.message = message;
        handleInfo();
    }

    public void handleInfo() {
        String[] components = message.split("/");
        String action = components[0];

        switch (action) {
            case "Check account" -> {
                String playerName = components[1];
                currentPlayer = playerHandler.getSpecificPlayer(playerName);

                if (currentPlayer != null) {
                    sendToHandler("Give name/" + currentPlayer);
                } else {
                    System.out.println("Error bro1");
                }
            }

            case "Create account" -> {
                String playerName = components[1].split(",")[0];
                String className = components[1].split(",")[1];

                playerClass = new PlayerClass(className);

                switch (className) {
                    case "fighter" -> {
                        playerClass = new Fighter();
                        currentPlayer = playerHandler.createAccount(playerName, playerClass);
                    }
                    case "archer" -> {
                        playerClass = new Archer();
                        currentPlayer = playerHandler.createAccount(playerName, playerClass);
                    }
                    case "mage" -> {
                        playerClass = new Mage();
                        currentPlayer = playerHandler.createAccount(playerName, playerClass);
                    }
                    case "assassin" -> {
                        playerClass = new Assassin();
                        currentPlayer = playerHandler.createAccount(playerName, playerClass);
                    }
                }
                inventory = new Inventory();

                shop = new Shop(1, className);
                lifePotion = shop.getLifePotion();

                lifePotion.amount = 3;
                inventory.addItem(lifePotion);

                if (currentPlayer != null) {
                    sendToHandler("Give name/" + currentPlayer);
                } else {
                    System.out.println("Error bro1");
                }
            }

            case "Get monster" -> {
                currentMonster = monsterHandler.getCurrentMonster(currentPlayer.level);
            }

            case "Get new monster" -> {
                String difficulty = components[1];

                int playerLevel = Integer.parseInt(components[2]);

                currentMonster = monsterHandler.getCurrentMonster(playerLevel);

                if (currentMonster.Name.equals("Zombie")) {
                    System.out.println("Old armor: " + zombie.Armor);
                }

                System.out.println(currentMonster.Armor);

                switch (difficulty) {
                    case "Easy" -> {
                        slime.HP += (int) (slime.Scale * slime.HPIncrease1[playerLevel - 1]);
                        slime.AD += (int) (slime.Scale * slime.ADIncrease1[playerLevel - 1]);
                        slime.MP += (int) (slime.Scale * slime.MPIncrease1[playerLevel - 1]);
                        slime.Armor += slime.ArmorIncrease1[playerLevel - 1];
                        slime.MR += slime.MRIncrease1[playerLevel - 1];
                        slime.Gold += slime.GoldIncrease1[playerLevel - 1];
                        slime.EXP += slime.EXPIncrease1[playerLevel - 1];
                        slime.Scale += slime.ScaleIncrease1[playerLevel - 1];

                        zombie.HP += (int) (zombie.Scale * zombie.HPIncrease1[playerLevel - 1]);
                        zombie.AD += (int) (zombie.Scale * zombie.ADIncrease1[playerLevel - 1]);
                        zombie.MP += (int) (zombie.Scale * zombie.MPIncrease1[playerLevel - 1]);
                        zombie.Armor += zombie.ArmorIncrease1[playerLevel - 1];
                        zombie.MR += zombie.MRIncrease1[playerLevel - 1];
                        zombie.Gold += zombie.GoldIncrease1[playerLevel - 1];
                        zombie.EXP += zombie.EXPIncrease1[playerLevel - 1];
                        zombie.Scale += zombie.ScaleIncrease1[playerLevel - 1];

                        skeleton.HP += (int) (skeleton.Scale * skeleton.HPIncrease1[playerLevel - 1]);
                        skeleton.AD += (int) (skeleton.Scale * skeleton.ADIncrease1[playerLevel - 1]);
                        skeleton.MP += (int) (skeleton.Scale * skeleton.MPIncrease1[playerLevel - 1]);
                        skeleton.Armor += skeleton.ArmorIncrease1[playerLevel - 1];
                        skeleton.MR += skeleton.MRIncrease1[playerLevel - 1];
                        skeleton.Gold += skeleton.GoldIncrease1[playerLevel - 1];
                        skeleton.EXP += skeleton.EXPIncrease1[playerLevel - 1];
                        skeleton.Scale += skeleton.ScaleIncrease1[playerLevel - 1];

                        mimic.HP += (int) (mimic.Scale * mimic.HPIncrease1[playerLevel - 1]);
                        mimic.AD += (int) (mimic.Scale * mimic.ADIncrease1[playerLevel - 1]);
                        mimic.MP += (int) (mimic.Scale * mimic.MPIncrease1[playerLevel - 1]);
                        mimic.Armor += mimic.ArmorIncrease1[playerLevel - 1];
                        mimic.MR += mimic.MRIncrease1[playerLevel - 1];
                        mimic.Gold += mimic.GoldIncrease1[playerLevel - 1];
                        mimic.EXP += mimic.EXPIncrease1[playerLevel - 1];
                        mimic.Scale += mimic.ScaleIncrease1[playerLevel - 1];

                        diablo.HP += (int) (diablo.Scale * diablo.HPIncrease1[playerLevel - 1]);
                        diablo.AD += (int) (diablo.Scale * diablo.ADIncrease1[playerLevel - 1]);
                        diablo.MP += (int) (diablo.Scale * diablo.MPIncrease1[playerLevel - 1]);
                        diablo.Armor += diablo.ArmorIncrease1[playerLevel - 1];
                        diablo.MR += diablo.MRIncrease1[playerLevel - 1];
                        diablo.Gold += diablo.GoldIncrease1[playerLevel - 1];
                        diablo.EXP += diablo.EXPIncrease1[playerLevel - 1];
                        diablo.Scale += diablo.ScaleIncrease1[playerLevel - 1];

                    }
                    case "Medium" -> {
                        slime.HP += (int) (slime.Scale * slime.HPIncrease2[playerLevel - 1]);
                        slime.AD += (int) (slime.Scale * slime.ADIncrease2[playerLevel - 1]);
                        slime.MP += (int) (slime.Scale * slime.MPIncrease2[playerLevel - 1]);
                        slime.Armor += slime.ArmorIncrease2[playerLevel - 1] + rand.nextInt(3);
                        slime.MR += slime.MRIncrease2[playerLevel - 1] + rand.nextInt(3);
                        slime.Gold += slime.GoldIncrease2[playerLevel - 1] + rand.nextInt(3);
                        slime.EXP += slime.EXPIncrease2[playerLevel - 1] + rand.nextInt(3);
                        slime.Scale += slime.ScaleIncrease2[playerLevel - 1];

                        zombie.HP += (int) (zombie.Scale * zombie.HPIncrease2[playerLevel - 1]);
                        zombie.AD += (int) (zombie.Scale * zombie.ADIncrease2[playerLevel - 1]);
                        zombie.MP += (int) (zombie.Scale * zombie.MPIncrease2[playerLevel - 1]);
                        zombie.Armor += zombie.ArmorIncrease2[playerLevel - 1] + rand.nextInt(3);
                        zombie.MR += zombie.MRIncrease2[playerLevel - 1] + rand.nextInt(3);
                        zombie.Gold += zombie.GoldIncrease2[playerLevel - 1] + rand.nextInt(4);
                        zombie.EXP += zombie.EXPIncrease2[playerLevel - 1] + rand.nextInt(4);
                        zombie.Scale += zombie.ScaleIncrease2[playerLevel - 1];

                        skeleton.HP += (int) (skeleton.Scale * skeleton.HPIncrease2[playerLevel - 1]);
                        skeleton.AD += (int) (skeleton.Scale * skeleton.ADIncrease2[playerLevel - 1]);
                        skeleton.MP += (int) (skeleton.Scale * skeleton.MPIncrease2[playerLevel - 1]);
                        skeleton.Armor += skeleton.ArmorIncrease2[playerLevel - 1] + rand.nextInt(3);
                        skeleton.MR += skeleton.MRIncrease2[playerLevel - 1] + rand.nextInt(3);
                        skeleton.Gold += skeleton.GoldIncrease2[playerLevel - 1] + rand.nextInt(4);
                        skeleton.EXP += skeleton.EXPIncrease2[playerLevel - 1] + rand.nextInt(4);
                        skeleton.Scale += skeleton.ScaleIncrease2[playerLevel - 1];

                        mimic.HP += (int) (mimic.Scale * mimic.HPIncrease2[playerLevel - 1]);
                        mimic.AD += (int) (mimic.Scale * mimic.ADIncrease2[playerLevel - 1]);
                        mimic.MP += (int) (mimic.Scale * mimic.MPIncrease2[playerLevel - 1]);
                        mimic.Armor += mimic.ArmorIncrease2[playerLevel - 1] + rand.nextInt(4);
                        mimic.MR += mimic.MRIncrease2[playerLevel - 1] + rand.nextInt(4);
                        mimic.Gold += mimic.GoldIncrease2[playerLevel - 1] + rand.nextInt(5);
                        mimic.EXP += mimic.EXPIncrease2[playerLevel - 1] + rand.nextInt(5);
                        mimic.Scale += mimic.ScaleIncrease2[playerLevel - 1];

                        diablo.HP += (int) (diablo.Scale * diablo.HPIncrease2[playerLevel - 1]);
                        diablo.AD += (int) (diablo.Scale * diablo.ADIncrease2[playerLevel - 1]);
                        diablo.MP += (int) (diablo.Scale * diablo.MPIncrease2[playerLevel - 1]);
                        diablo.Armor += diablo.ArmorIncrease2[playerLevel - 1] + rand.nextInt(5);
                        diablo.MR += diablo.MRIncrease2[playerLevel - 1] + rand.nextInt(5);
                        diablo.Gold += diablo.GoldIncrease2[playerLevel - 1] + rand.nextInt(6);
                        diablo.EXP += diablo.EXPIncrease2[playerLevel - 1] + rand.nextInt(6);
                        diablo.Scale += diablo.ScaleIncrease2[playerLevel - 1];

                    }
                    case "Hard" -> {
                        slime.HP += (int) (slime.Scale * slime.HPIncrease3[playerLevel - 1]);
                        slime.AD += (int) (slime.Scale * slime.ADIncrease3[playerLevel - 1]);
                        slime.MP += (int) (slime.Scale * slime.MPIncrease3[playerLevel - 1]);
                        slime.Armor += slime.ArmorIncrease3[playerLevel - 1];
                        slime.MR += slime.MRIncrease3[playerLevel - 1];
                        slime.Gold += slime.GoldIncrease3[playerLevel - 1];
                        slime.EXP += slime.EXPIncrease3[playerLevel - 1];
                        slime.Scale += slime.ScaleIncrease3[playerLevel - 1];

                        zombie.HP += (int) (zombie.Scale * zombie.HPIncrease3[playerLevel - 1]);
                        zombie.AD += (int) (zombie.Scale * zombie.ADIncrease3[playerLevel - 1]);
                        zombie.MP += (int) (zombie.Scale * zombie.MPIncrease3[playerLevel - 1]);
                        zombie.Armor += zombie.ArmorIncrease3[playerLevel - 1];
                        zombie.MR += zombie.MRIncrease3[playerLevel - 1];
                        zombie.Gold += zombie.GoldIncrease3[playerLevel - 1];
                        zombie.EXP += zombie.EXPIncrease3[playerLevel - 1];
                        zombie.Scale += zombie.ScaleIncrease3[playerLevel - 1];

                        skeleton.HP += (int) (skeleton.Scale * skeleton.HPIncrease3[playerLevel - 1]);
                        skeleton.AD += (int) (skeleton.Scale * skeleton.ADIncrease3[playerLevel - 1]);
                        skeleton.MP += (int) (skeleton.Scale * skeleton.MPIncrease3[playerLevel - 1]);
                        skeleton.Armor += skeleton.ArmorIncrease3[playerLevel - 1];
                        skeleton.MR += skeleton.MRIncrease3[playerLevel - 1];
                        skeleton.Gold += skeleton.GoldIncrease3[playerLevel - 1];
                        skeleton.EXP += skeleton.EXPIncrease3[playerLevel - 1];
                        skeleton.Scale += skeleton.ScaleIncrease3[playerLevel - 1];

                        mimic.HP += (int) (mimic.Scale * mimic.HPIncrease3[playerLevel - 1]);
                        mimic.AD += (int) (mimic.Scale * mimic.ADIncrease3[playerLevel - 1]);
                        mimic.MP += (int) (mimic.Scale * mimic.MPIncrease3[playerLevel - 1]);
                        mimic.Armor += mimic.ArmorIncrease3[playerLevel - 1];
                        mimic.MR += mimic.MRIncrease3[playerLevel - 1];
                        mimic.Gold += mimic.GoldIncrease3[playerLevel - 1];
                        mimic.EXP += mimic.EXPIncrease3[playerLevel - 1];
                        mimic.Scale += mimic.ScaleIncrease3[playerLevel - 1];

                        diablo.HP += (int) (diablo.Scale * diablo.HPIncrease3[playerLevel - 1]);
                        diablo.AD += (int) (diablo.Scale * diablo.ADIncrease3[playerLevel - 1]);
                        diablo.MP += (int) (diablo.Scale * diablo.MPIncrease3[playerLevel - 1]);
                        diablo.Armor += diablo.ArmorIncrease3[playerLevel - 1];
                        diablo.MR += diablo.MRIncrease3[playerLevel - 1];
                        diablo.Gold += diablo.GoldIncrease3[playerLevel - 1];
                        diablo.EXP += diablo.EXPIncrease3[playerLevel - 1];
                        diablo.Scale += diablo.ScaleIncrease3[playerLevel - 1];

                    }

                }

                switch (currentMonster.getName()) {
                    case "Slime" -> currentMonster = slime;
                    case "Zombie" -> {
                        currentMonster = zombie;
                        System.out.println(difficulty + " New armor: " + currentMonster.Armor);
                    }
                    case "Skeleton" -> currentMonster = skeleton;
                    case "Mimic" -> currentMonster = mimic;
                    case "Diablo" -> currentMonster = diablo;
                }

                System.out.println(currentMonster);
                System.out.println(currentMonster.HP);
                System.out.println(currentMonster.Scale);
            }

            case "Get gold" -> {
                String monsterName = components[1];
                switch (monsterName) {
                    case "Slime" -> monsterGold = slime.Gold;
                    case "Zombie" -> monsterGold = zombie.Gold;
                    case "Skeleton" -> monsterGold = skeleton.Gold;
                    case "Mimic" -> monsterGold = mimic.Gold;
                    case "Diablo" -> monsterGold = diablo.Gold;
                }
            }

            case "Get EXP" -> {
                String monsterName = components[1];
                switch (monsterName) {
                    case "Slime" -> monsterEXP = slime.EXP;
                    case "Zombie" -> monsterEXP = zombie.EXP;
                    case "Skeleton" -> monsterEXP = skeleton.EXP;
                    case "Mimic" -> monsterEXP = mimic.EXP;
                    case "Diablo" -> monsterEXP = diablo.EXP;
                }


            }

            case "Buy life potion" -> {
                lifePotion.price *= 3;
            }

            case "Buy shield" -> {

            }

        }

    }

    public Item[] getHandlerItemsInShop() {
        return itemsInShop;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int[] getADPrice() {
        return ADPrice;
    }

    public int[] getMPPrice() {
        return MPPrice;
    }

    public int[] getDEFPrice() {
        return DEFPrice;
    }

    public Item getLifePotion() {
        return lifePotion;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Monster getCurrentMonster() {
        return currentMonster;
    }

    public int getMonsterGold() {
        return monsterGold;
    }

    public int getMonsterEXP() {
        return monsterEXP;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public int getPlayerEXP() {
        return playerEXP;
    }

    public String getMessage() {
        return message;
    }
}
