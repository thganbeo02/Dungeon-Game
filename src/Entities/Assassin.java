package Entities;

import java.util.Random;

public class Assassin extends PlayerClass {
    private String className = "assassin";
    private int baseHP = 69;
    private int baseAD = 6;
    private int baseMP = 0;
    private int baseArmor = 3;
    private int baseMR = 3;
    private int startingMana = 10;
    private int manaRegen = 3;
    private int critChance = 8;
    private double critMultiplier = 1.75;
    private int dodgeChance = 8;
    private double armorPen = 0.07;
    private double MRPen = 0;

    public Assassin() {
        HPGrowth = new int[]{0, 6, 11, 18, 25, 32, 40};
        ADGrowth = new int[]{0, 5, 10, 15, 22, 30, 40};
        MPGrowth = new int[]{0, 2, 4, 7, 10, 14, 20};
        ArmorGrowth = new int[]{0, 3, 6, 10, 14, 20, 28};
        MRGrowth = new int[]{0, 4, 8, 12, 18, 24, 33};
        bonusMana = new int[]{0, 16, 22, 28, 34, 40, 48};
        ManaRegenGrowth = new int[]{0, 1, 0, 0, 1, 0, 0};
        APenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
        MRPenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
    }

    int random2 = new Random().nextInt(2);
    int random4 = new Random().nextInt(4);

    // Assassin passive
    private final int[] passiveGoldGain = {8 + random2, 16 + random2, 24 + random2, 40 + random4, 60 + random4,
            100 + random4, 180 + random4};
    private final int[] passiveEXPGain = {4 + random2, 10 + random2, 15 + random2, 25 + random4, 40 + random4,
            65 + random4, 120 + random4};
    private final int[] passiveBaseDamage = {10, 14, 18, 22, 30, 38, 46};
    private final double passiveCounterADRatio = 100;
    private final int[] passiveBaseHeal = {4, 6, 8, 10, 13, 16, 19};
    private final double passiveHPRatio = 15;
    private final double passiveHealADRatio = 20;

    public int[] getPassiveEXPGain() {
        return passiveEXPGain;
    }

    public int[] getPassiveGoldGain() {
        return passiveGoldGain;
    }

    public String getAssassinPassive(int level, int playerAD, int playerMissingHP) {
        return "PICKPOCKET <Passive> " +
                "On Dodge, basic attack again for " + passiveCounter(level, playerAD) + " TRUE DAMAGE and heals " +
                (int) (passiveBaseHeal[level - 1] + passiveHPRatio / 100 * playerMissingHP +
                        passiveHealADRatio / 100 * playerAD) + " HP. " +
                "On Takedown, gains " + passiveGoldGain[level - 1] + " gold and " + passiveEXPGain[level - 1] + " EXP";
    }

    public String getAssassinPassiveRatio(int level) {
        return "PICKPOCKET <Passive> " +
                "On Dodge, basic attack again for " + passiveBaseDamage[level - 1] + "(+" +
                (int) passiveCounterADRatio + "%AD) TRUE DAMAGE and heals " + passiveBaseHeal[level - 1] +
                "(+" + (int) passiveHealADRatio + "%AD)(+" + (int) passiveHPRatio + "%missing HP) HP. " +
                "On Takedown, gains " + passiveGoldGain[level - 1] + " gold and " + passiveEXPGain[level - 1] + " EXP";
    }

    public int passiveCounter(int level, int playerAD) {
        return (int) (passiveBaseDamage[level - 1] + passiveCounterADRatio / 100 * playerAD);
    }

    public int passiveHeal(int level, int playerMissingHP, int playerAD) {
        return (int) (passiveBaseHeal[level - 1] + passiveHPRatio / 100 * playerMissingHP +
                passiveHealADRatio / 100 * playerAD);
    }

    // Assassin skill 1
    private final int[] skill1Mana = {6, 9, 12, 15, 18, 21, 24};
    private final int[] skill1ADGain = {3, 5, 7, 9, 13, 17, 21};
    private final int[] skill1BaseHealing = {6, 12, 18, 24, 34, 44, 54};
    private final double skill1HPRatio = 42;
    private final int skill1HPThreshold = 42;
    private final int skill1DodgeGain = 2;

    public int getSkill1DodgeGain() {
        return skill1DodgeGain;
    }

    public int[] getSkill1Mana() {
        return skill1Mana;
    }

    public int getSkill1HPThreshold() {
        return skill1HPThreshold;
    }

    public int[] getSkill1ADGain() {
        return skill1ADGain;
    }

    public String getAssassinSkill1(int level, int playerMaxHP) {
        return "STEALTH <" + skill1Mana[level - 1] + " Mana> " +
                "Gains " + skill1ADGain[level - 1] + " AD. If below " + (playerMaxHP * skill1HPThreshold / 100) +
                " HP, heals " + (int) (skill1BaseHealing[level - 1] + skill1HPRatio / 100 * playerMaxHP) +
                " HP, while gaining another " + skill1ADGain[level - 1] + " AD. Also gains " + skill1DodgeGain +
                "% Dodge Chance, stacking up 5 times";
    }

    public String getAssassinSkill1Ratio(int level) {
        return "STEALTH <" + skill1Mana[level - 1] + " Mana> " +
                "Gains " + skill1ADGain[level - 1] + " AD. If below " + skill1HPThreshold + "%HP, heals " +
                skill1BaseHealing[level - 1] + "(+" + (int) skill1HPRatio + "%HP) HP, while " +
                "gaining another " + skill1ADGain[level - 1] + " AD. Also gains " + skill1DodgeGain +
                "% Dodge Chance, stacking up 5 times";
    }

    public String getAssassinSkill1Stacked(int level, int playerMaxHP) {
        return "STEALTH <" + skill1Mana[level - 1] + " Mana> " +
                "Gains " + skill1ADGain[level - 1] + " AD. If below " + (playerMaxHP * skill1HPThreshold / 100) +
                " HP, heals " + (int) (skill1BaseHealing[level - 1] + skill1HPRatio / 100 * playerMaxHP) +
                " HP, while gaining another " + skill1ADGain[level - 1] + " AD ";
    }

    public String getAssassinSkill1StackedRatio(int level) {
        return "STEALTH <" + skill1Mana[level - 1] + " Mana> " +
                "Gains " + skill1ADGain[level - 1] + " AD. If below " + skill1HPThreshold + "%HP, heals " +
                skill1BaseHealing[level - 1] + "(+" + (int) skill1HPRatio + "%HP) HP, while " +
                "gaining another " + skill1ADGain[level - 1] + " AD.";
    }

    public int firstSkill(int playerMaxHP) {
        return (int) (8 + skill1HPRatio / 100 * playerMaxHP);
    }

    // Assassin skill 2
    private final int[] skill2Mana = {8, 12, 16, 20, 24, 28, 32};
    private final int[] skill2BaseArmorSteal = {4, 7, 10, 13, 18, 23, 28};
    private final double skill2EnemyArmorSteal = 16;
    private final double skill2APenGain = 0.03;

    public int[] getSkill2Mana() {
        return skill2Mana;
    }

    public int[] getSkill2BaseArmorSteal() {
        return skill2BaseArmorSteal;
    }

    public double getSkill2APenGain() {
        return skill2APenGain;
    }

    public String getAssassinSkill2(int level, int enemyArmor) {
        return "WEAKEN <" + skill2Mana[level - 1] + " Mana> " +
                "Basic attack Crits and steals " +
                (int) (skill2BaseArmorSteal[level - 1] + skill2EnemyArmorSteal / 100 * enemyArmor) +
                " Armor off enemy. On Execute, gains " + (int) (skill2APenGain * 100) + "% Armor Pen and Crit Damage," +
                " stacking up 10 times, else regains " + skill2Mana[level - 1] / 2 + " Mana";
    }

    public String getAssassinSkill2Ratio(int level) {
        return "WEAKEN <" + skill2Mana[level - 1] + " Mana> " +
                "Basic attack Crits and steals " + skill2BaseArmorSteal[level - 1] + "(+" +
                (int) skill2EnemyArmorSteal + "% enemy's Armor) Armor off enemy. " +
                "On Execute, gains " + (int) (skill2APenGain * 100) + "% Armor Pen and Crit Damage, " +
                "stacking up 10 times, else regains 50% Mana cost";
    }

    public String getAssassinSkill2Stacked(int level, int enemyArmor) {
        return "WEAKEN <" + skill2Mana[level - 1] + " Mana> " +
                "Basic attack Crits and steals " +
                (int) (skill2BaseArmorSteal[level - 1] + skill2EnemyArmorSteal / 100 * enemyArmor) +
                " Armor off enemy. Regains " + skill2Mana[level - 1] / 2 + " Mana if not executed";
    }

    public String getAssassinSkill2StackedRatio(int level) {
        return "WEAKEN <" + skill2Mana[level - 1] + " Mana> " +
                "Basic attack Crits and steals " + skill2BaseArmorSteal[level - 1] + "(+" +
                (int) skill2EnemyArmorSteal + "% enemy's Armor) Armor off enemy. " +
                "Regains 50% Mana cost if not executed";
    }

    public int secondSkill(int level, int enemyArmor) {
        return (int) (skill2BaseArmorSteal[level - 1] + skill2EnemyArmorSteal / 100 * enemyArmor);
    }

    // Assassin skill 3
    private final int[] skill3Mana = {6, 11, 16, 21, 26, 31, 36};
    private final int[] skill3BaseADSteal = {3, 6, 9, 12, 18, 24, 30};
    private final double skill3EnemyADSteal = 9;
    private final int[] skill3HPMRSteal = {3, 6, 9, 12, 16, 20, 24};

    public int thirdSkill(int level, int enemyAD) {
        return (int) (skill3BaseADSteal[level - 1] + skill3EnemyADSteal / 100 * enemyAD);
    }

    public double getSkill3EnemyADSteal() {
        return skill3EnemyADSteal;
    }

    public int[] getSkill3HPMRSteal() {
        return skill3HPMRSteal;
    }

    public int[] getSkill3Mana() {
        return skill3Mana;
    }

    public String getAssassinSkill3(int level, int enemyAD) {
        return "THEFT <" + skill3Mana[level - 1] + " Mana> " +
                "Steals " + (int) (skill3BaseADSteal[level - 1] + skill3EnemyADSteal / 100 * enemyAD) +
                " AD and " + skill3HPMRSteal[level - 1] + " max HP and MR from enemy ";
    }

    public String getAssassinSkill3Ratio(int level) {
        return "THEFT <" + skill3Mana[level - 1] + " Mana> " +
                "Steals " + skill3BaseADSteal[level - 1] + "(+" + (int) skill3EnemyADSteal +
                "%enemy's AD) AD and " + skill3HPMRSteal[level - 1] + " max HP and MR from enemy ";
    }

    // For buttons
    public String getSkill1Name(int level) {
        return "STEALTH <" + skill1Mana[level - 1] + " Mana>";
    }

    public String getSkill2Name(int level) {
        return "WEAKEN <" + skill2Mana[level - 1] + " Mana>";
    }

    public String getSkill3Name(int level) {
        return "THEFT <" + skill3Mana[level - 1] + " Mana>";
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
