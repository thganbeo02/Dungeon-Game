package Entities;

import java.util.Random;

public class Archer extends PlayerClass {
    private final String className = "archer";
    private final int baseHP = 67;
    private int baseAD = 5;
    private int baseMP = 2;
    private int baseArmor = 2;
    private int baseMR = 2;
    private int startingMana = 9;
    private int manaRegen = 4;
    private int critChance = 10;
    private double critMultiplier = 1.5;
    private int dodgeChance = 7;
    private double armorPen = 0;
    private double MRPen = 0;

    public Archer() {
        HPGrowth = new int[]{0, 6, 12, 22, 30, 40, 50};
        ADGrowth = new int[]{0, 6, 12, 18, 24, 32, 44};
        MPGrowth = new int[]{0, 3, 6, 10, 15, 20, 26};
        ArmorGrowth = new int[]{0, 3, 6, 10, 15, 21, 28};
        MRGrowth = new int[]{0, 2, 4, 7, 12, 18, 25};
        bonusMana = new int[]{0, 16, 22, 30, 38, 46, 54};
        ManaRegenGrowth = new int[]{0, 1, 0, 0, 1, 0, 1};
        APenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
        MRPenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
    }

    // Archer passive
    private final int passiveStealChance = 30;
    private final int[] passiveADGain = {3, 5, 7, 9, 13, 17, 21};
    private final int[] passiveStatsSteal = {2, 3, 4, 5, 8, 11, 15};
    private final int passiveHPCrit = 3;

    public int getPassiveStealChance() {
        return passiveStealChance;
    }

    public int[] getPassiveADGain() {
        return passiveADGain;
    }

    public int[] getPassiveStatsSteal() {
        return passiveStatsSteal;
    }

    public int getPassiveHPCrit() {
        return passiveHPCrit;
    }

    public String getArcherPassive(int level) {
        return "ROBIN HOOD <Passive> On Takedown, gains " + passiveADGain[level - 1] + " AD. " +
                "Basic attack has a " + passiveStealChance + "% chance to steal " + passiveStatsSteal[level - 1] +
                " AD, Armor, and MR. On Crit, also gains " + passiveHPCrit + " max HP and Armor";
    }

    // Archer skill 1
    private final int[] skill1Mana = {8, 10, 12, 14, 16, 18, 20};
    private final int[] skill1BaseDamage = {6, 12, 18, 24, 36, 48, 60};
    private final double skill1ADRatio = 175;
    private final double skill1HealRatio = 75;
    private final double skill1MPRatio = 8;

    public int[] getSkill1Mana() {
        return skill1Mana;
    }

    public int[] getSkill1BaseDamage() {
        return skill1BaseDamage;
    }

    public String getArcherSkill1(int level, int playerAD, int playerMP) {
        double healingRatio = skill1HealRatio + skill1MPRatio / 100 * playerMP;
        if (healingRatio >= 120) {
            healingRatio = 120;
        }

        return "QUENCH <" + skill1Mana[level - 1] + " Mana> " +
                "Basic attack does " + (int) (skill1BaseDamage[level - 1] + skill1ADRatio / 100 * playerAD) +
                " physical damage and heals " + (int) healingRatio + "% damage dealt";
    }

    public String getArcherSkill1Ratio(int level) {
        return "QUENCH <" + skill1Mana[level - 1] + " Mana> " +
                "Basic attack does " + skill1BaseDamage[level - 1] + "(+" + (int) skill1ADRatio +
                "%AD) physical damage and heals " + (int) (skill1HealRatio) + "(+" + (int) skill1MPRatio
                + "%MP)% damage dealt (capped at 120%)";
    }

    public int[] firstSkill(int level, int playerAD, int playerMP, double monsterArmorEffectiveness) {
        int enhancedAD = (int) (skill1BaseDamage[level - 1] + skill1ADRatio / 100 * playerAD);

        double healingRatio = (int) (skill1HealRatio + skill1MPRatio / 100 * playerMP);

        if (healingRatio >= 120) {
            healingRatio = 120;
        }

        int healing = (int) (healingRatio / 100 * enhancedAD * monsterArmorEffectiveness);
        return new int[]{enhancedAD, healing};
    }

    // Archer skill 2
    private final int[] skill2Mana = {10, 13, 16, 19, 20, 25, 28};
    private final int[] skill2AD = {6, 9, 12, 15, 16, 20, 24};
    private final int[] skill2MP = {3, 6, 9, 12, 16, 20, 24};
    private final int[] skill2ArmorAndMR = {0, 0, 0, 0, 16, 20, 24};

    public int[] getSkill2Mana() {
        return skill2Mana;
    }

    public int[] getSkill2AD() {
        return skill2AD;
    }

    public int[] getSkill2MP() {
        return skill2MP;
    }

    public int[] getSkill2ArmorAndMR() {
        return skill2ArmorAndMR;
    }

    public String getArcherSkill2(int level) {
        if (level < 5) {
            return "WILD <" + skill2Mana[level - 1] + " Mana> " +
                    "Increases AD by " + skill2AD[level - 1] + " and MP by " + skill2MP[level - 1];
        } else {
            return "WILD <" + skill3Mana[level - 1] + " Mana> " +
                    "Increases AD, MP, Armor and MR by " + skill2ArmorAndMR[level - 1];

        }
    }

    public int[] secondSkill(int level) {
        return new int[]{skill2AD[level - 1], skill2MP[level - 1], skill2ArmorAndMR[level - 1]};
    }

    // Archer skill 3
    private final int[] skill3Mana = {6, 7, 8, 9, 10, 11, 12};
    private final int[] skill3BaseDamage = {6, 9, 12, 15, 18, 21, 24};
    private final double skill3MPRatio = 200;
    private final double skill3ADRatio = 80;
    private final int[] skill3MRIgnored = {0, 10, 20, 30, 45, 60, 75};
    private final int[] skill3GoldGain = {33, 99, 333, 999, 3333, 9999, 33333};

    private final int skill3CritChanceGain = 3;
    private final double skill3CritDMGGain = 0.03;

    public int[] getSkill3Mana() {
        return skill3Mana;
    }

    public int[] getSkill3BaseDamage() {
        return skill3BaseDamage;
    }

    public double getSkill3MPRatio() {
        return skill3MPRatio;
    }

    public int[] getSkill3MRIgnored() {
        return skill3MRIgnored;
    }

    public int[] getSkill3GoldGain() {
        return skill3GoldGain;
    }

    public int getSkill3CritChanceGain() {
        return skill3CritChanceGain;
    }

    public double getSkill3CritDMGGain() {
        return skill3CritDMGGain;
    }

    public String getArcherSkill3(int level, int playerAD, int playerMP) {
        return "HOTSHOT <" + skill3Mana[level - 1] + " Mana> " +
                "Fires a piercing arrow that does " +
                (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * playerMP + skill3ADRatio / 100 * playerAD) +
                " magic damage. On Execute, gains " + skill3GoldGain[level - 1] + " gold and " +
                skill3CritChanceGain + "% Crit chance and Crit damage, stacking up 8 times";
    }

    public String getArcherSkill3Stacked(int level, int playerAD, int playerMP) {
        return "HOTSHOT <" + skill3Mana[level - 1] + " Mana> " +
                "Fires a piercing arrow that does " +
                (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * playerMP + skill3ADRatio / 100 * playerAD) +
                " magic damage. On Execute, gains " + skill3GoldGain[level - 1] + " gold";
    }

    public String getArcherSkill3Ratio(int level) {
        return "HOTSHOT <" + skill3Mana[level - 1] + " Mana> " +
                "Fires a piercing arrow that does " +
                skill3BaseDamage[level - 1] + "(+" + (int) skill3MPRatio + "%MP)(+" + (int) skill3ADRatio +
                "%AD) magic damage, ignoring " + skill3MRIgnored[level - 1] + "% of enemy's MR. " +
                "On Execute, gains " + skill3GoldGain[level - 1] + " gold and " + skill3CritChanceGain +
                "% Crit chance and Crit damage, stacking up 8 times";
    }

    public String getArcherSkill3StackedRatio(int level) {
        return "HOTSHOT <" + skill3Mana[level - 1] + " Mana> " +
                "Fires a piercing arrow that does " +
                skill3BaseDamage[level - 1] + "(+" + (int) skill3MPRatio + "%MP)(+" + (int) skill3ADRatio +
                "%AD) magic damage, ignoring " + skill3MRIgnored[level - 1] + "% of enemy's MR. " +
                "On Execute, gains " + skill3GoldGain[level - 1] + " gold";
    }

    public int thirdSkill(int level, int playerMP, int playerAD) {
        return (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * playerMP + skill3ADRatio / 100 * playerAD);
    }

    // For buttons
    public String getSkill1Name(int level) {
        return "QUENCH <" + skill1Mana[level - 1] + " Mana>";
    }

    public String getSkill2Name(int level) {
        return "WILD <" + skill2Mana[level - 1] + " Mana>";
    }

    public String getSkill3Name(int level) {
        return "HOTSHOT <" + skill3Mana[level - 1] + " Mana>";
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
}
