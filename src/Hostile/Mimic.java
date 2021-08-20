package Hostile;

public class Mimic extends Monster {
    public Mimic() {
        Name = "Mimic";
        HP = 45;
        AD = 6;
        MP = 0;
        Armor = 1;
        MR = 0;
        Gold = 9;
        EXP = 10;
        Scale = 1.2;

        HPIncrease1 = new int[]{3, 5, 7, 10, 14, 21, 31};
        ADIncrease1 = new int[]{3, 5, 8, 10, 15, 22, 34};
        MPIncrease1 = new int[]{1, 2, 3, 6, 10, 14, 20};
        ArmorIncrease1 = new int[]{0, 1, 3, 5, 7, 11, 17};
        MRIncrease1 = new int[]{0, 2, 3, 4, 6, 9, 14};
        GoldIncrease1 = new int[]{12, 18, 26, 44, 64, 95, 130};
        EXPIncrease1 = new int[]{10, 13, 16, 21, 28, 38, 50};
        ScaleIncrease1 = new double[]{0.12, 0.15, 0.18, 0.21, 0.26, 0.32, 0.38};

        HPIncrease2 = new int[]{4, 6, 9, 12, 17, 24, 35};
        ADIncrease2 = new int[]{5, 7, 9, 12, 18, 25, 36};
        MPIncrease2 = new int[]{2, 3, 5, 7, 10, 15, 23};
        ArmorIncrease2 = new int[]{1, 2, 4, 6, 9, 14, 19};
        MRIncrease2 = new int[]{1, 2, 4, 6, 8, 12, 17};
        GoldIncrease2 = new int[]{15, 23, 33, 52, 76, 111, 154};
        EXPIncrease2 = new int[]{14, 18, 23, 28, 40, 56, 76};
        ScaleIncrease2 = new double[]{0.13, 0.16, 0.19, 0.24, 0.3, 0.36, 0.44};

        HPIncrease3 = new int[]{6, 9, 12, 16, 22, 30, 42};
        ADIncrease3 = new int[]{6, 9, 13, 17, 23, 32, 44};
        MPIncrease3 = new int[]{3, 4, 6, 8, 12, 18, 26};
        ArmorIncrease3 = new int[]{2, 3, 5, 8, 11, 16, 23};
        MRIncrease3 = new int[]{2, 3, 5, 8, 12, 16, 24};
        GoldIncrease3 = new int[]{24, 36, 60, 86, 121, 169, 236};
        EXPIncrease3 = new int[]{25, 30, 37, 50, 70, 95, 124};
        ScaleIncrease3 = new double[]{0.14, 0.18, 0.22, 0.28, 0.34, 0.42, 0.5};

    }

    public int[] mimicChance = {8, 12, 16, 20};
    public double mimicSteal = 6;
    public int[] mimicHPGain = {90, 180, 270, 666};

    public int mimicStealAD(int playerAD) {
        return (int) (playerAD * mimicSteal / 100);
    }

    public int mimicStealArmor(int playerArmor) {
        return (int) (playerArmor * mimicSteal / 100);
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
