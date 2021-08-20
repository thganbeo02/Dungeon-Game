package GameHandler;

import Entities.Player;
import Entities.PlayerClass;

import java.io.*;
import java.util.ArrayList;

public class PlayerHandler {
    private final String nameFilename;
    private final ArrayList<Player> players = new ArrayList<>();

    public PlayerHandler(String nameFilename) throws FileNotFoundException {
        this.nameFilename = nameFilename;

        File toProcess = new File(nameFilename);
        FileReader fileReader = new FileReader(toProcess);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] levelNameAndStats = line.split(": ");

                String[] nameAndClass = levelNameAndStats[0].split(", ");
                int playerLevel = Integer.parseInt(nameAndClass[0]);
                String playerName = nameAndClass[1];
                String className = nameAndClass[2];

                String[] specificStats = levelNameAndStats[1].split(",");

                int HP = Integer.parseInt(specificStats[0].replaceAll("[^0-9]", ""));
                int AD = Integer.parseInt(specificStats[1].replaceAll("[^0-9]", ""));
                int MP = Integer.parseInt(specificStats[2].replaceAll("[^0-9]", ""));
                int Armor = Integer.parseInt(specificStats[3].replaceAll("[^0-9]", ""));
                int MR = Integer.parseInt(specificStats[4].replaceAll("[^0-9]", ""));
                int Mana = Integer.parseInt(specificStats[5].replaceAll("[^0-9]", ""));
                int ManaRegen = Integer.parseInt(specificStats[6].replaceAll("[^0-9]", ""));
                int CritChance = Integer.parseInt(specificStats[7].replaceAll("[^0-9]", ""));
                int CritDMG = Integer.parseInt(specificStats[8].replaceAll("[^0-9]", ""));
                int DodgeChance = Integer.parseInt(specificStats[9].replaceAll("[^0-9]", ""));
                int APEN = Integer.parseInt(specificStats[10].replaceAll("[^0-9]", ""));
                int MPEN = Integer.parseInt(specificStats[11].replaceAll("[^0-9]", ""));

                Player oldPlayer = new Player(playerLevel, playerName, className, HP, AD, MP,
                        Armor, MR, Mana, ManaRegen, CritChance,
                        (double) CritDMG / 100, DodgeChance, (double) APEN / 100, (double) MPEN / 100);

                players.add(oldPlayer);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getSpecificPlayer(String name) {
        for (Player findPlayer : players) {
            if (name.equals(findPlayer.getUsername())) {
                System.out.println(findPlayer);
                return findPlayer;
            }
        }
        return null;
    }

    public boolean checkDuplicateUsername(String username) {
        File userInfo = new File(nameFilename);
        boolean usernameExists = false;
        try (BufferedReader br = new BufferedReader(new FileReader(userInfo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineValues = line.split(", ");
                if (lineValues[0].equals(username)) {
                    usernameExists = true;
                }
            }
            return usernameExists;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usernameExists;
    }

    public Player createAccount(String username, PlayerClass playerClass) {
        Player newPlayer = new Player(username, playerClass);
        System.out.println("From playerHandler/" + newPlayer);

        players.add(newPlayer);
        writePlayersToFile(nameFilename);
        return newPlayer;
    }

    public void writePlayersToFile(String nameFilename) {
        try {
            FileOutputStream fos = new FileOutputStream(nameFilename);
            PrintWriter pw = new PrintWriter(fos);

            for (Player player : players) {
                pw.println(player.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
