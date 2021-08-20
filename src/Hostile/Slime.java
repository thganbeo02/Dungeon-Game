package Hostile;

public class Slime extends Monster {
    public Slime() {
        Name = "Slime";
        HP = 49;
        AD = 2;
        MP = 1;
        Armor = 2;
        MR = 0;
        Gold = 4;
        EXP = 6;
        Scale = 1;

        HPIncrease1 = new int[]{4, 6, 9, 12, 18, 28, 40};
        ADIncrease1 = new int[]{2, 3, 5, 8, 13, 18, 26};
        MPIncrease1 = new int[]{2, 4, 6, 9, 13, 20, 28};
        ArmorIncrease1 = new int[]{0, 1, 2, 5, 8, 12, 18};
        MRIncrease1 = new int[]{0, 1, 2, 4, 7, 12, 20};
        GoldIncrease1 = new int[]{6, 9, 14, 20, 29, 40, 52};
        EXPIncrease1 = new int[]{3, 4, 5, 7, 9, 12, 16};
        ScaleIncrease1 = new double[]{0.08, 0.1, 0.12, 0.15, 0.2, 0.25, 0.33};

        HPIncrease2 = new int[]{5, 7, 11, 16, 25, 36, 52};
        ADIncrease2 = new int[]{2, 4, 6, 10, 14, 20, 28};
        MPIncrease2 = new int[]{3, 5, 7, 11, 16, 24, 33};
        ArmorIncrease2 = new int[]{1, 2, 3, 6, 10, 16, 23};
        MRIncrease2 = new int[]{1, 2, 3, 6, 9, 13, 20};
        GoldIncrease2 = new int[]{8, 12, 18, 26, 37, 50, 69};
        EXPIncrease2 = new int[]{6, 7, 8, 10, 13, 17, 25};
        ScaleIncrease2 = new double[]{0.1, 0.12, 0.14, 0.17, 0.22, 0.27, 0.35};

        HPIncrease3 = new int[]{7, 10, 15, 22, 34, 50, 69};
        ADIncrease3 = new int[]{3, 5, 8, 13, 19, 27, 37};
        MPIncrease3 = new int[]{4, 7, 10, 16, 24, 35, 48};
        ArmorIncrease3 = new int[]{2, 4, 6, 9, 13, 20, 28};
        MRIncrease3 = new int[]{2, 4, 6, 9, 14, 19, 27};
        GoldIncrease3 = new int[]{12, 20, 32, 46, 66, 88, 110};
        EXPIncrease3 = new int[]{10, 12, 15, 20, 27, 34, 46};
        ScaleIncrease3 = new double[]{0.12, 0.15, 0.18, 0.21, 0.25, 0.29, 0.38};
    }

    public int gooTurnNumber = 4;
    private int[] gooBaseHealing = {60, 120, 180, 300};
    private double gooMissingHPRatio = 7;

    public String goo(int level, int enemyMissingHP) {
        return "Every 4 turns, heals " + slimeGooHealing(level, enemyMissingHP) + " HP";
    }

    public String gooRatio(int level) {
        return "Every 4 turns, heals " + gooBaseHealing[level - 4] + "(+" +
                (int) gooMissingHPRatio + "% missing HP) HP";
    }

    public int[] divisionChance = {18, 24, 30, 40};
    private int[] divisionBaseHPGain = {50, 200, 350, 666};
    private double divisionCurrentHPRatio = 5;
    private double[] divisionMPRatio = {20, 35, 50, 75};

    public int slimeGooHealing(int level, int enemyMissingHP) {
        return (int) (gooBaseHealing[level - 4] + gooMissingHPRatio / 100 * enemyMissingHP);
    }

    public int slimeDivisionHPGain(int level, int enemyCurrentHP) {
        return (int) (divisionBaseHPGain[level - 4] + divisionCurrentHPRatio / 100 * enemyCurrentHP);
    }

    public int slimeDivisionDamage(int level, int enemyMP) {
        return (int) (divisionMPRatio[level - 4] / 100 * enemyMP);
    }

    public String getName() {
        return Name;
    }

    public int getHP() {
        return HP;
    }

    public int getAD() {
        return AD;
    }

    public int getMP() {
        return MP;
    }

    public int getArmor() {
        return Armor;
    }

    public int getMR() {
        return MR;
    }

    public int getGold() {
        return Gold;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setAD(int AD) {
        this.AD = AD;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setArmor(int Armor) {
        this.Armor = Armor;
    }

    public void setMR(int MR) {
        this.MR = MR;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public void setGold(int Gold) {
        this.Gold = Gold;
    }

    public void setScale(double Scale) {
        this.Scale = Scale;
    }

    @Override
    public String toString() {
        return Name + "," +
                HP + " HP," +
                AD + " AD," +
                MP + " MP," +
                Armor + " Armor," +
                MR + " MR";
    }
}
