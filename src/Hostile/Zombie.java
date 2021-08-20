package Hostile;

public class Zombie extends Monster {
    public Zombie() {
        Name = "Zombie";
        HP = 45;
        AD = 3;
        MP = 0;
        Armor = 0;
        MR = 0;
        Gold = 3;
        EXP = 5;
        Scale = 1;

        HPIncrease1 = new int[]{3, 5, 8, 12, 18, 27, 42};
        ADIncrease1 = new int[]{2, 3, 5, 8, 13, 20, 30};
        MPIncrease1 = new int[]{0, 1, 2, 4, 7, 12, 19};
        ArmorIncrease1 = new int[]{0, 2, 4, 6, 9, 14, 22};
        MRIncrease1 = new int[]{0, 1, 2, 4, 6, 8, 12};
        GoldIncrease1 = new int[]{5, 8, 12, 20, 32, 44, 61};
        EXPIncrease1 = new int[]{3, 5, 7, 10, 14, 18, 24};
        ScaleIncrease1 = new double[]{0.1, 0.125, 0.15, 0.175, 0.22, 0.26, 0.34};

        HPIncrease2 = new int[]{5, 7, 10, 15, 22, 30, 45};
        ADIncrease2 = new int[]{3, 4, 6, 9, 14, 22, 34};
        MPIncrease2 = new int[]{1, 2, 3, 5, 8, 14, 22};
        ArmorIncrease2 = new int[]{1, 3, 5, 7, 12, 18, 25};
        MRIncrease2 = new int[]{1, 2, 4, 6, 9, 12, 18};
        GoldIncrease2 = new int[]{8, 12, 18, 25, 35, 52, 77};
        EXPIncrease2 = new int[]{6, 8, 10, 13, 18, 25, 35};
        ScaleIncrease2 = new double[]{0.12, 0.14, 0.16, 0.2, 0.24, 0.28, 0.38};

        HPIncrease3 = new int[]{7, 10, 15, 22, 32, 45, 64};
        ADIncrease3 = new int[]{4, 6, 9, 13, 17, 23, 32};
        MPIncrease3 = new int[]{1, 2, 4, 7, 11, 16, 25};
        ArmorIncrease3 = new int[]{3, 5, 7, 10, 15, 21, 30};
        MRIncrease3 = new int[]{2, 4, 6, 9, 12, 16, 22};
        GoldIncrease3 = new int[]{15, 22, 32, 47, 72, 94, 127};
        EXPIncrease3 = new int[]{10, 14, 18, 24, 32, 40, 52};
        ScaleIncrease3 = new double[]{0.14, 0.17, 0.2, 0.24, 0.28, 0.36, 0.44};
    }

    public int[] zombieToughenChance = {12, 16, 20, 30};
    public int[] zombieToughenArmor = {40, 80, 120, 180};
    public int[] zombieToughenMR = {20, 40, 60, 88};

    public int[] zombieBiteChance = {9, 15, 21, 33};
    public double zombieBiteADRatio = 100;
    public double[] zombieBiteMPRatio = {5, 10, 15, 25};
    public double[] zombieBonusDMG = {120, 125, 130, 150};
    public int[] zombieBiteMRRemove = {10, 25, 40, 75};

    public int zombieBite(int level, int enemyAD, int enemyMP) {
        return (int) (zombieBiteADRatio / 100 * enemyAD + zombieBiteMPRatio[level - 4] / 100 * enemyMP);
    }

    public int zombieEnhancedBite(int level, int enemyAD, int enemyMP) {
        return (int) (zombieBite(level, enemyAD, enemyMP) * (zombieBonusDMG[level - 4] / 100));
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
