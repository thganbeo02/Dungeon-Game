package Items;

public class Item {
    public String itemName = "";
    public int price;
    public String tooltip = "";

    public int amount;
    public int healing;

    public int HP;  // 0
    public int AD;  // 1
    public int MP;  // 2
    public int armor;  // 3
    public int MR;  // 4
    public int currentMana;  // 5
    public int manaRegen;  // 6
    public int critChance;  // 7
    public double critMultiplier;  // 8
    public int dodgeChance;  // 9
    public double armorPen;  // 10
    public double MRPen;  // 11

    public double lifeSteal;
    public double trueDamage;

    public double monsterADReduction;
    public double aegisADArmorGain;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName, int price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String itemAndCost() {
        return itemName + " (" + price + "g)";
    }

    public String itemStats() {
        String weaponStats = "";

        if (HP > 0) {
            weaponStats += "+" + HP + " max HP, ";
        }
        if (AD > 0) {
            weaponStats += "+" + AD + " AD, ";
        }
        if (MP > 0) {
            weaponStats += "+" + MP + " MP, ";
        }
        if (armor > 0) {
            weaponStats += "+" + armor + " Armor, ";
        }
        if (MR > 0) {
            weaponStats += "+" + MR + " MR, ";
        }
        if (currentMana > 0) {
            weaponStats += "+" + currentMana + " Mana, ";
        }
        if (manaRegen > 0) {
            weaponStats += "+" + manaRegen + " Mana Regen, ";
        }
        if (critChance > 0) {
            weaponStats += "+" + critChance + "% Crit Chance, ";
        }
        if (critMultiplier > 0) {
            weaponStats += "+" + (int) (critMultiplier * 100) + "% Crit Damage, ";
        }
        if (dodgeChance > 0) {
            weaponStats += "+" + dodgeChance + "% Dodge Chance, ";
        }
        if (armorPen > 0) {
            weaponStats += "+" + (int) (armorPen * 100) + "% Armor Penetration ";
        }
        if (MRPen > 0) {
            weaponStats += "+" + (int) (MRPen * 100) + "% MR Penetration";
        }

        if (!tooltip.equals("")) {
            weaponStats += "\n" + tooltip;
        }
        return weaponStats;
    }

    @Override
    public String toString() {
        String weaponInfo = "(" + price + " g) ";

        if (HP > 0) {
            weaponInfo += "+" + HP + " max HP, ";
        }
        if (AD > 0) {
            weaponInfo += "+" + AD + " AD, ";
        }
        if (MP > 0) {
            weaponInfo += "+" + MP + " MP, ";
        }
        if (armor > 0) {
            weaponInfo += "+" + armor + " Armor, ";
        }
        if (MR > 0) {
            weaponInfo += "+" + MR + " MR, ";
        }
        if (currentMana > 0) {
            weaponInfo += "+" + currentMana + " Mana, ";
        }
        if (manaRegen > 0) {
            weaponInfo += "+" + manaRegen + " Mana Regen, ";
        }
        if (critChance > 0) {
            weaponInfo += "+" + critChance + "% Crit Chance, ";
        }
        if (critMultiplier > 0) {
            weaponInfo += "+" + (int) (critMultiplier * 100) + "% Crit Damage, ";
        }
        if (dodgeChance > 0) {
            weaponInfo += "+" + dodgeChance + "% Dodge Chance, ";
        }
        if (armorPen > 0) {
            weaponInfo += "+" + (int) (armorPen * 100) + "% Armor Penetration ";
        }
        if (MRPen > 0) {
            weaponInfo += "+" + (int) (MRPen * 100) + "% MR Penetration ";
        }

        return weaponInfo;
    }
}
