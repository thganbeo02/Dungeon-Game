package Entities;

public class Fighter extends PlayerClass {
    private final String className = "fighter";
    private int baseHP = 77;
    private int baseAD = 6;
    private int baseMP = 1;
    private int baseArmor = 4;
    private int baseMR = 3;
    private int startingMana = 8;
    private int manaRegen = 3;
    private int critChance = 8;
    private double critMultiplier = 1;
    private int dodgeChance = 5;
    private double armorPen = 0.1;
    private double MRPen = 0;

    public Fighter() {
        HPGrowth = new int[]{0, 6, 10, 15, 22, 30, 40};
        ADGrowth = new int[]{0, 8, 17, 25, 35, 46, 60};
        MPGrowth = new int[]{0, 2, 5, 8, 12, 17, 25};
        ArmorGrowth = new int[]{0, 5, 9, 14, 20, 28, 40};
        MRGrowth = new int[]{0, 2, 5, 8, 12, 17, 25};
        bonusMana = new int[]{0, 16, 21, 26, 31, 36, 46};
        ManaRegenGrowth = new int[]{0, 0, 1, 0, 1, 0, 1};
        critChanceGrowth = new int[]{0, 2, 2, 2, 2, 2, 2};
        APenGrowth = new double[]{0, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
        MRPenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
    }

    // Fighter's passive
    private final int[] passiveBaseHeal = {8, 14, 20, 26, 36, 46, 56};
    private final double[] passiveHPRatio = {42, 45, 48, 51, 54, 57, 60};
    private final int[] passiveArmorGain = {3, 6, 9, 12, 16, 20, 24};
    private final int[] passiveMRGain = {1, 3, 5, 7, 10, 13, 15};
    public final int[] passiveHPGain = {1, 3, 5, 7, 10, 13, 15};

    public int[] getPassiveHPGain() {
        return passiveHPGain;
    }

    public String getFighterPassive(int level, int playerMissingHP) {
        return "ABSORB <Passive> " +
                "On Takedown, heals for " + passiveHeal(level, playerMissingHP) + " HP and gains " +
                passiveArmorGain[level - 1] + " Armor, " + passiveMRGain[level - 1] + " Magic Resist, and " +
                passiveHPGain[level - 1] + " max HP. On Crit, reduces monster's armor by 20%, but cannot Crit";
    }

    public String getFighterPassiveRatio(int level) {
        return "ABSORB <PASSIVE> " +
                "On Takedown, heals for " + (passiveBaseHeal[level - 1]) + "(+" + (int) passiveHPRatio[level - 1] +
                "% missing HP) HP, gains " + passiveArmorGain[level - 1] + " Armor, " +
                passiveMRGain[level - 1] + " Magic Resist, and " + passiveHPGain[level - 1] + " max HP. " +
                "On Crit, reduces monster's armor by 20%, but cannot Crit";
    }

    public int passiveHeal(int level, int playerMissingHP) {
        return (int) (passiveBaseHeal[level - 1] + passiveHPRatio[level - 1] / 100 * playerMissingHP);
    }

    public int[] passiveArmorMR(int level) {
        return new int[]{passiveArmorGain[level - 1], passiveMRGain[level - 1]};
    }

    // Fighter skill 1
    private final int[] skill1Mana = {8, 11, 14, 17, 20, 23, 26};
    public final int[] skill1StatIncrease = {4, 8, 12, 16, 20, 26, 32};
    public final int[] skill1Heal = {4, 8, 12, 16, 40, 80, 150};

    public String getFighterSkill1(int level) {
        if (level < 5) {
            return "STRENGTHEN <" + skill1Mana[level - 1] + " Mana> Increases Armor and MR " +
                    "and heals or AD and MP by " + skill1StatIncrease[level - 1];
        } else {
            return "STRENGTHEN <" + skill1Mana[level - 1] + " Mana> Increases Armor, MR, AD, and MP by " +
                    skill1StatIncrease[level - 1] + " and heals " + skill1Heal[level - 1] + " HP";
        }
    }

    public int firstSkill(int level) {
        return skill1StatIncrease[level - 1];
    }

    // Fighter skill 2
    private final int[] skill2Mana = {8, 12, 16, 20, 24, 28, 32};
    private final int[] skill2BaseDamage = {8, 18, 28, 38, 60, 72, 84};
    private final double skill2ADRatio = 300;
    private final double skill2ArmorRemoved = 30;
    private final int[] skill2HPGain = {3, 6, 9, 12, 17, 22, 27};

    public double getSkill2ArmorRemoved() {
        return skill2ArmorRemoved;
    }

    public int[] getSkill2HPGain() {
        return skill2HPGain;
    }

    public String getFighterSkill2(int level, int playerAD, int enemyArmor) {
        return "JUSTICE <" + skill2Mana[level - 1] + " Mana> Basic attack does " +
                (int) (skill2BaseDamage[level - 1] + skill2ADRatio / 100 * playerAD) +
                " physical damage and stuns for a turn, and reduces " +
                "monster's armor by " + (int) (enemyArmor * skill2ArmorRemoved / 100) + ". " +
                "On Execute, gains " + skill2HPGain[level - 1] + " max HP, " +
                "else regains " + skill2Mana[level - 1] / 4 + " Mana";
    }

    public String getFighterSkill2Ratio(int level) {
        return "JUSTICE <" + skill2Mana[level - 1] + " Mana> Basic attack does " +
                (skill2BaseDamage[level - 1]) + "(+" + (int) skill2ADRatio + "% AD) physical damage, stuns " +
                "for a turn, and reduces monster's armor by 40%. " +
                "On Execute, gains " + skill2HPGain[level - 1] + " max HP, else regains 25% Mana cost";
    }

    public int secondSkill(int level, int playerAD) {
        return (int) (skill2BaseDamage[level - 1] + skill2ADRatio / 100 * playerAD);
    }

    public int[] skill3Mana = {21, 28, 35, 42, 49, 56, 63};
    public int[] skill3BaseDamage = {6, 12, 18, 24, 36, 48, 60};
    public double skill3MPRatio = 200;
    public double[] skill3HPRatio = {24, 30, 36, 42, 48, 54, 60};
    public int[] skill3ManaGain = {8, 16, 24, 32, 40, 48, 56};
    public int[] skill3ADGain = {2, 4, 6, 8, 12, 16, 20};


    public int[] getSkill3ManaGain() {
        return skill3ManaGain;
    }

    public int[] getSkill3ADGain() {
        return skill3ADGain;
    }

    public String getFighterSkill3(int level, int MP, int enemyMissingHP) {
        return "EXECUTION <" + skill3Mana[level - 1] + " Mana> Slams down a holy sword that does " +
                (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * MP +
                        skill3HPRatio[level - 1] / 100 * enemyMissingHP) + " TRUE DAMAGE. " + "On Execute, regains " +
                skill3ManaGain[level - 1] + " mana and " + skill3ADGain[level - 1] + " AD and Armor";
    }

    public String getFighterSkill3Ratio(int level) {
        return "EXECUTION <" + skill3Mana[level - 1] + " Mana> Slams down a holy sword that does " +
                (skill3BaseDamage[level - 1]) + "(+" + (int) skill3MPRatio + "% MP)(+" +
                (int) skill3HPRatio[level - 1] + "% enemy's missing HP) TRUE DAMAGE. " + "On Execute, regains " +
                skill3ManaGain[level - 1] + " mana and " + skill3ADGain[level - 1] + " AD and Armor";
    }

    public int thirdSkill(int level, int MP, int enemyMissingHP) {
        return (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * MP +
                skill3HPRatio[level - 1] / 100 * enemyMissingHP);
    }

    public String getSkill1Name(int level) {
        return "STRENGTHEN <" + skill1Mana[level - 1] + " Mana>";
    }

    public String getSkill2Name(int level) {
        return "JUSTICE <" + skill2Mana[level - 1] + " Mana>";
    }

    public String getSkill3Name(int level) {
        return "EXECUTION <" + skill3Mana[level - 1] + " Mana>";
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

    public int[] getSkill1Mana() {
        return skill1Mana;
    }

    public int[] getSkill2Mana() {
        return skill2Mana;
    }

    public int[] getSkill3Mana() {
        return skill3Mana;
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
