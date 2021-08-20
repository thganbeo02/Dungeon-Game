package Hostile;

public class Diablo extends Monster {

    public Diablo() {
        Name = "Diablo";
        HP = 44;
        AD = 3;
        MP = 8;
        Armor = 2;
        MR = 2;
        Gold = 10;
        EXP = 12;
        Scale = 1.3;

        HPIncrease1 = new int[]{3, 4, 6, 9, 14, 21, 30};
        ADIncrease1 = new int[]{2, 3, 5, 7, 10, 14, 20};
        MPIncrease1 = new int[]{3, 5, 8, 12, 17, 24, 34};
        ArmorIncrease1 = new int[]{1, 1, 3, 5, 7, 10, 14};
        MRIncrease1 = new int[]{2, 4, 6, 9, 14, 21, 30};
        GoldIncrease1 = new int[]{12, 20, 30, 52, 77, 116, 169};
        EXPIncrease1 = new int[]{10, 14, 19, 26, 40, 60, 84};
        ScaleIncrease1 = new double[]{0.15, 0.17, 0.19, 0.23, 0.3, 0.37, 0.46};

        HPIncrease2 = new int[]{3, 5, 7, 11, 16, 24, 34};
        ADIncrease2 = new int[]{3, 5, 7, 10, 14, 19, 27};
        MPIncrease2 = new int[]{3, 6, 9, 14, 20, 27, 39};
        ArmorIncrease2 = new int[]{1, 2, 4, 6, 9, 13, 18};
        MRIncrease2 = new int[]{3, 5, 7, 10, 15, 22, 32};
        GoldIncrease2 = new int[]{18, 25, 37, 62, 96, 144, 198};
        EXPIncrease2 = new int[]{14, 20, 27, 38, 52, 72, 96};
        ScaleIncrease2 = new double[]{0.15, 0.18, 0.21, 0.25, 0.32, 0.4, 0.5};

        HPIncrease3 = new int[]{4, 6, 9, 13, 21, 30, 43};
        ADIncrease3 = new int[]{5, 7, 10, 14, 19, 25, 34};
        MPIncrease3 = new int[]{8, 11, 15, 20, 26, 34, 49};
        ArmorIncrease3 = new int[]{2, 4, 6, 8, 12, 16, 23};
        MRIncrease3 = new int[]{6, 8, 10, 14, 20, 28, 40};
        GoldIncrease3 = new int[]{25, 35, 51, 76, 113, 168, 255};
        EXPIncrease3 = new int[]{22, 30, 38, 50, 69, 100, 132};
        ScaleIncrease3 = new double[]{0.15, 0.19, 0.23, 0.3, 0.37, 0.45, 0.55};

    }

    public double[] giftMPRatio = {20, 35, 50, 75};

    public int[] drainChance = {20, 26, 32, 40};
    public int[] drainMana = {6, 12, 18, 30};

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
