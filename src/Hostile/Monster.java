package Hostile;

public class Monster {
    public String Name;
    public int HP;
    public int AD;
    public int MP;
    public int Armor;
    public int MR;
    public int Gold;
    public int EXP;
    public double Scale;

    public int[] HPIncrease1;
    public int[] ADIncrease1;
    public int[] MPIncrease1;
    public int[] ArmorIncrease1;
    public int[] MRIncrease1;
    public int[] GoldIncrease1;
    public int[] EXPIncrease1;
    public double[] ScaleIncrease1;

    public int[] HPIncrease2;
    public int[] ADIncrease2;
    public int[] MPIncrease2;
    public int[] ArmorIncrease2;
    public int[] MRIncrease2;
    public int[] GoldIncrease2;
    public int[] EXPIncrease2;
    public double[] ScaleIncrease2;

    public int[] HPIncrease3;
    public int[] ADIncrease3;
    public int[] MPIncrease3;
    public int[] ArmorIncrease3;
    public int[] MRIncrease3;
    public int[] GoldIncrease3;
    public int[] EXPIncrease3;
    public double[] ScaleIncrease3;

    public Monster(String Name, int HP, int AD, int MP, int Armor, int MR,
                   int Gold, int EXP, double Scale) {
        this.Name = Name;
        this.HP = HP;
        this.AD = AD;
        this.MP = MP;
        this.Armor = Armor;
        this.MR = MR;
        this.Gold = Gold;
        this.EXP = EXP;
        this.Scale = Scale;
    }

    public Monster() {

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

    public int getEXP() {
        return EXP;
    }

    public double getScale() {
        return Scale;
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
