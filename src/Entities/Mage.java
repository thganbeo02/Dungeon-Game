package Entities;

public class Mage extends PlayerClass {
    private final String className = "mage";
    private int baseHP = 63;
    private int baseAD = 3;
    private int baseMP = 4;
    private int baseArmor = 2;
    private int baseMR = 4;
    private int startingMana = 12;
    private int manaRegen = 5;
    private int critChance = 10;
    private double critMultiplier = 1.25;
    private int dodgeChance = 5;
    private double armorPen = 0;
    private double MRPen = 0.1;

    public Mage() {
        HPGrowth = new int[]{0, 5, 10, 16, 22, 30, 40};
        ADGrowth = new int[]{0, 2, 4, 7, 10, 14, 20};
        MPGrowth = new int[]{0, 5, 10, 15, 22, 30, 40};
        ArmorGrowth = new int[]{0, 3, 6, 10, 14, 18, 25};
        MRGrowth = new int[]{0, 3, 6, 10, 15, 22, 30};
        bonusMana = new int[]{0, 16, 24, 32, 40, 48, 60};
        ManaRegenGrowth = new int[]{0, 1, 1, 1, 1, 1, 1};
        APenGrowth = new double[]{0, 0, 0, 0, 0, 0, 0};
        MRPenGrowth = new double[]{0, .1, .1, .1, .1, .1, .1};
    }

    // Mage passive
    private final double passiveMPOnHit = 50;
    private final double passiveManaHeal = 33;
    private final int passiveHPThreshold = 50;
    private final int[] passiveExtra = {1, 2, 3, 4, 5, 6, 7};

    public int[] getPassiveExtra() {
        return passiveExtra;
    }

    public int getPassiveHPThreshold() {
        return passiveHPThreshold;
    }

    public String getMagePassive(int level, int playerHP, int playerMP, int playerMana) {
        return "OMNIVAMP <Passive> " +
                "Basic attack does bonus " + (int) (passiveMPOnHit / 100 * playerMP) + " magic damage on-hit. When below " +
                playerHP * passiveHPThreshold / 100 + " HP, generate " + passiveExtra[level - 1] +
                " extra Mana, MP, and Magic Resist. On Takedown, heals for " +
                (int) (playerMana * passiveManaHeal / 100) + " HP";
    }

    public String getMagePassiveRatio(int level) {
        return "OMNIVAMP <Passive> " +
                "Basic attack does bonus (" + (int) passiveMPOnHit + "% MP) magic damage on-hit. When below " +
                passiveHPThreshold + "% HP, generate " + passiveExtra[level - 1] +
                " extra Mana, MP, and Magic Resist. On Takedown, heals for (" +
                (int) passiveManaHeal + "% current Mana) HP";
    }

    public int passive(int playerMP) {
        return (int) (passiveMPOnHit / 100 * playerMP);
    }

    public int passiveHeal(int playerMana) {
        return (int) (playerMana * passiveManaHeal / 100);
    }

    // Mage skill 1
    private final int[] skill1Mana = {10, 14, 18, 22, 26, 30, 34};
    private final int[] skill1BaseDamage = {4, 10, 16, 22, 30, 38, 46};
    private final double skill1MPRatio = 150;

    public int[] getSkill1Mana() {
        return skill1Mana;
    }

    public String getMageSkill1(int level, int playerMP) {
        return "ENCHANTED <" + skill1Mana[level - 1] + " Mana> " +
                "Spell does " + (int) (skill1BaseDamage[level - 1] + skill1MPRatio / 100 * playerMP) +
                " magic damage and reduces enemy's AD by 40%. On Crit, reduces by 50%";
    }

    public String getMageSkill1Ratio(int level) {
        return "ENCHANTED <" + skill1Mana[level - 1] + " Mana> " +
                "Spell does " + skill1BaseDamage[level - 1] + "(+" + (int) skill1MPRatio +
                "% MP) magic damage and reduces enemy's AD by 40%. On Crit, reduces by 50%";
    }

    public int firstSkill(int level, int playerMP) {
        return (int) (skill1BaseDamage[level - 1] + skill1MPRatio / 100 * playerMP);
    }

    // Mage skill 2
    private final int[] skill2Mana = {12, 16, 20, 24, 28, 32, 36};
    private final int[] skill2BaseDamage = {10, 15, 20, 25, 35, 45, 55};
    private final int[] skill2ExcessHealing = {5, 8, 11, 14, 19, 24, 29};
    private final double skill2MPRatio = 300;

    public int[] getSkill2ExcessHealing() {
        return skill2ExcessHealing;
    }

    public int[] getSkill2Mana() {
        return skill2Mana;
    }

    public String getMageSkill2(int level, int playerMP) {
        return "OVERHEAL <" + skill2Mana[level - 1] + " Mana> " +
                "Spell does " + (int) (skill2BaseDamage[level - 1] + skill2MPRatio / 100 * playerMP) +
                " magic damage and heals 80% damage dealt. " +
                "Excess healing increases max HP and Armor by " + skill2ExcessHealing[level - 1];
    }

    public String getMageSkill2Ratio(int level) {
        return "OVERHEAL <" + skill2Mana[level - 1] + " Mana> " +
                "Spell does " + skill2BaseDamage[level - 1] + "(+" + (int) skill2MPRatio +
                "% MP) magic damage and heals 80% damage dealt. " +
                "Excess healing increases max HP and Armor by " + skill2ExcessHealing[level - 1];
    }

    public int[] secondSkill(int level, int playerMP, double monsterMREffectiveness) {
        int enhancedMP = (skill2BaseDamage[level - 1] + 3 * playerMP);
        int healing = (int) (0.8 * enhancedMP * monsterMREffectiveness);
        return new int[]{enhancedMP, healing};
    }

    public int[] getSkill3Mana() {
        return skill3Mana;
    }

    // Mage skill 3
    private final int[] skill3Mana = {20, 25, 30, 35, 40, 45, 50};
    private final int[] skill3BaseDamage = {12, 18, 24, 32, 42, 52, 62};
    private final double skill3MPRatio = 400;
    private final int[] skill3MPArmorIncrease = {6, 9, 12, 15, 20, 25, 30};

    public int[] getSkill3MPArmorIncrease() {
        return skill3MPArmorIncrease;
    }

    public String getMageSkill3(int level, int playerMP) {
        return "OVERLORD <" + skill3Mana[level - 1] + " Mana> " +
                "Spell does " + (int) (skill3BaseDamage[level - 1] + skill3MPRatio /  100 * playerMP) +
                " magic damage. On Execute, increases Armor and MP by " +
                skill3MPArmorIncrease[level - 1];
    }

    public String getMageSkill3Ratio(int level) {
        return "OVERLORD <" + skill3Mana[level - 1] + " Mana> " +
                "Spell does " + skill3BaseDamage[level - 1] + "(+" + (int) skill3MPRatio + "% MP) magic damage. " +
                "On Execute, increases Armor and MP by " + skill3MPArmorIncrease[level - 1];
    }

    public int thirdSkill(int level, int playerMP) {
        return (int) (skill3BaseDamage[level - 1] + skill3MPRatio / 100 * playerMP);
    }

    // For buttons
    public String getSkill1Name(int level) {
        return "ENCHANTED <" + skill1Mana[level - 1] + " Mana>";
    }

    public String getSkill2Name(int level) {
        return "OVERHEAL <" + skill2Mana[level - 1] + " Mana>";
    }

    public String getSkill3Name(int level) {
        return "OVERLORD <" + skill3Mana[level - 1] + " Mana>";
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
