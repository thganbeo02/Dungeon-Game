package Hostile;

public class Skeleton extends Monster {
    public Skeleton() {
        Name = "Skeleton";
        HP = 38;
        AD = 5;
        MP = 1;
        Armor = 0;
        MR = 0;
        Gold = 4;
        EXP = 8;
        Scale = 1.1;

        HPIncrease1 = new int[]{2, 3, 5, 8, 13, 19, 27};
        ADIncrease1 = new int[]{4, 6, 8, 11, 16, 23, 34};
        MPIncrease1 = new int[]{3, 5, 8, 11, 15, 21, 28};
        ArmorIncrease1 = new int[]{0, 1, 1, 2, 4, 6, 9};
        MRIncrease1 = new int[]{2, 3, 5, 7, 11, 15, 21};
        GoldIncrease1 = new int[]{7, 11, 18, 30, 42, 55, 72};
        EXPIncrease1 = new int[]{5, 7, 9, 13, 20, 28, 40};
        ScaleIncrease1 = new double[]{0.1, 0.135, 0.155, 0.185, 0.23, 0.27, 0.32};

        HPIncrease2 = new int[]{3, 5, 7, 10, 15, 21, 31};
        ADIncrease2 = new int[]{4, 6, 8, 13, 20, 28, 41};
        MPIncrease2 = new int[]{3, 6, 9, 13, 18, 25, 36};
        ArmorIncrease2 = new int[]{1, 1, 2, 3, 6, 9, 14};
        MRIncrease2 = new int[]{2, 4, 6, 9, 13, 18, 25};
        GoldIncrease2 = new int[]{10, 16, 24, 38, 62, 85, 115};
        EXPIncrease2 = new int[]{7, 10, 14, 18, 25, 35, 49};
        ScaleIncrease2 = new double[]{0.125, 0.145, 0.165, 0.21, 0.25, 0.3, 0.36};

        HPIncrease3 = new int[]{4, 6, 9, 14, 19, 26, 37};
        ADIncrease3 = new int[]{6, 8, 12, 18, 26, 36, 49};
        MPIncrease3 = new int[]{4, 8, 12, 16, 21, 28, 38};
        ArmorIncrease3 = new int[]{1, 2, 4, 6, 10, 14, 20};
        MRIncrease3 = new int[]{2, 3, 5, 7, 12, 19, 30};
        GoldIncrease3 = new int[]{18, 32, 49, 76, 111, 153, 213};
        EXPIncrease3 = new int[]{16, 20, 24, 30, 40, 52, 71};
        ScaleIncrease3 = new double[]{0.16, 0.19, 0.22, 0.26, 0.32, 0.4, 0.48};
    }

    public int[] spookyDodge = {10, 12, 14, 18};
    public int[] spookyADGain = {20, 40, 70, 120};
    public int[] spookyBaseHealing = {15, 30, 45, 70};
    public double spookyHealingRatio = 12;

    public int[] boneStrikeChance = {12, 16, 20, 30};
    public int[] boneStrikeArmorReduction = {10, 20, 30, 50};
    private double[] boneStrikeADRatio = {150, 175, 200, 250};

    public int skeletonSpookyHealing(int level, int enemyMissingHP) {
        return (int) (spookyBaseHealing[level - 4] + enemyMissingHP * spookyHealingRatio / 100);
    }

    public int skeletonBoneStrike(int level, int enemyAD) {
        return (int) (enemyAD * boneStrikeADRatio[level - 4] / 100);
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
