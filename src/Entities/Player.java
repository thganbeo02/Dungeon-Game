package Entities;

public class Player {
    public int level = 1;
    public final String username;
    public String className;
    public PlayerClass playerClass;
    private boolean newPlayer = false;

    public int HP;  // 0
    public int AD;  // 1
    public int MP;  // 2
    public int Armor;  // 3
    public int MR;  // 4
    public int startingMana;  // 5
    public int manaRegen;  // 6
    public int critChance;  // 7
    public double critMultiplier;  // 8
    public int dodgeChance;  // 9
    public double armorPen;  // 10
    public double MRPen;  // 11

    public Player(String username, PlayerClass playerClass) {
        newPlayer = true;
        this.username = username;
        this.playerClass = playerClass;
    }

    public Player(int level, String username, String className,
                  int HP, int AD, int MP, int Armor, int MR,
                  int startingMana, int manaRegen, int critChance, double critMultiplier,
                  int dodgeChance, double armorPen, double MRPen) {
        this.level = level;
        this.username = username;
        this.className = className;
        this.HP = HP;
        this.AD = AD;
        this.MP = MP;
        this.Armor = Armor;
        this.MR = MR;
        this.startingMana = startingMana;
        this.manaRegen = manaRegen;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.dodgeChance = dodgeChance;
        this.armorPen = armorPen;
        this.MRPen = MRPen;
    }

    public int getLevel() {
        return level;
    }

    public String getUsername() {
        return username;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }

    public String getClassName() {
        return className;
    }

    public int getAD() {
        return AD;
    }

    public int getArmor() {
        return Armor;
    }

    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

    public int getMR() {
        return MR;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public int getStartingMana() {
        return startingMana;
    }

    public int getCritChance() {
        return critChance;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    public double getArmorPen() {
        return armorPen;
    }

    public double getMRPen() {
        return MRPen;
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }

    public void setCritMultiplier(double critMultiplier) {
        this.critMultiplier = critMultiplier;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String playerInfo;

        if (newPlayer) {
            playerInfo = level + ", " + username + ", " + playerClass.toString();
            className = playerClass.getClassName();
            HP = playerClass.getBaseHP();
            AD = playerClass.getBaseAD();
            MP = playerClass.getBaseMP();
            Armor = playerClass.getBaseArmor();
            MR = playerClass.getBaseMR();
            startingMana = playerClass.getStartingMana();
            manaRegen = playerClass.getManaRegen();
            critChance = playerClass.getCritChance();
            critMultiplier = playerClass.getCritMultiplier();
            dodgeChance = playerClass.getDodgeChance();
            armorPen = playerClass.getArmorPen();
            MRPen = playerClass.getMRPen();

        } else {
            playerInfo = level + ", " + username + ", " + className + ": " +
                    HP + " HP," +
                    AD + " AD," +
                    MP + " MP," +
                    Armor + " Armor," +
                    MR + " MR," +
                    startingMana + " Mana," +
                    manaRegen + " Mana regen," +
                    critChance + "% Crit," +
                    (int) (critMultiplier * 100) + "% Crit DMG," +
                    dodgeChance + "% Dodge," +
                    (int) (armorPen * 100) + "% Armor Pen," +
                    (int) (MRPen * 100) + "% MR Pen";
        }

        return playerInfo;
    }
}
