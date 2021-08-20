package Entities;

public class PlayerClass {
    private String className;
    private int baseHP;  // 0
    private int baseAD;  // 1
    private int baseMP;  // 2
    private int baseArmor;  // 3
    private int baseMR;  // 4
    private int startingMana;  // 5
    private int manaRegen;  // 6
    private int critChance;  // 7
    private double critMultiplier;  // 8
    private int dodgeChance;  // 9
    private double armorPen;  // 10
    private double MRPen;  // 11

    public int[] ADGrowth;
    public int[] HPGrowth;
    public int[] MPGrowth;
    public int[] ArmorGrowth;
    public int[] MRGrowth;
    public int[] bonusMana;
    public int[] ManaRegenGrowth;
    public int[] critChanceGrowth = new int[]{0, 0, 0, 0, 0, 0, 0};
    public double[] APenGrowth;
    public double[] MRPenGrowth;

    public PlayerClass(String className) {
        this.className = className;
        baseHP = 0;
        baseAD = 0;
        baseMP = 0;
        baseArmor = 0;
        baseMR = 0;
        startingMana = 0;
        manaRegen = 0;
        critChance = 0;
        critMultiplier = 0;
        dodgeChance = 0;
        armorPen = 0;
        MRPen = 0;
    }

    public PlayerClass() {

    }

    public PlayerClass(String className,
                       int baseHP, int baseAD, int baseMP, int baseArmor, int baseMR,
                       int startingMana, int manaRegen, int critChance, double critMultiplier,
                       int dodgeChance, double armorPen, double MRPen) {
        this.className = className;
        this.baseHP = baseHP;
        this.baseAD = baseAD;
        this.baseMP = baseMP;
        this.baseArmor = baseArmor;
        this.baseMR = baseMR;
        this.startingMana = startingMana;
        this.manaRegen = manaRegen;
        this.critChance = critChance;
        this.critMultiplier = critMultiplier;
        this.dodgeChance = dodgeChance;
        this.armorPen = armorPen;
        this.MRPen = MRPen;
    }

    public String getSkill1Name(int level) {
        return "";
    }

    public String getSkill2Name(int level) {
        return "";
    }

    public String getSkill3Name(int level) {
        return "";
    }

    public String getFighterSkill1(int level) {
        return "";
    }

    public String getFighterSkill2(int level, int AD) {
        return "";
    }

    public String getFighterSkill3(int level, int MP, int enemyMissingHP) {
        return "";
    }

    public String getClassName() {
        return className;
    }

    public int getBaseAD() {
        return baseAD;
    }

    public int getBaseArmor() {
        return baseArmor;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseMP() {
        return baseMP;
    }

    public int getBaseMR() {
        return baseMR;
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

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    public void setCritMultiplier(double critMultiplier) {
        this.critMultiplier = critMultiplier;
    }

    public void setArmorPen(double armorPen) {
        this.armorPen = armorPen;
    }

    public void setMRPen(double MRPen) {
        this.MRPen = MRPen;
    }

    @Override
    public String toString() {
        return className + ": " +
                baseHP + " HP," +
                baseAD + " AD," +
                baseMP + " MP," +
                baseArmor + " Armor," +
                baseMR + " MR," +
                startingMana + " Mana," +
                manaRegen + " Mana regen," +
                critChance + "% Crit," +
                (int) (critMultiplier * 100) + "% Crit DMG," +
                dodgeChance + "% Dodge," +
                (int) (armorPen * 100) + "% Armor Pen," +
                (int) (MRPen * 100) + "% MR Pen";
    }

//        if (critChance != 0) {
//        playerInfo += ", " + critChance + "% Crit chance";
//    }
//        if (critMultiplier != 0) {
//        playerInfo += ", " + (int) critMultiplier * 100 + "% Crit damage";
//    }
//        if (dodgeChance != 0) {
//        playerInfo += ", " + dodgeChance + "% Dodge chance";
//    }
//        if (armorPen != 0) {
//        playerInfo += ", " + (int) armorPen * 100 + "% Armor penetration";
//    }
//        if (MRPen != 0) {
//        playerInfo += ", " + (int) MRPen * 100 + "% Magic penetration";
//    }
}
