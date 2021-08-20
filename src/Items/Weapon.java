package Items;

import java.util.Objects;

public class Weapon extends Item {
    public String weaponName = "";
    public String tooltip = "";

    public Weapon(String weaponName, int price) {
        super(weaponName, price);
        this.weaponName = weaponName;
        this.price = price;
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
        String weaponInfo = weaponName;
        weaponInfo += " <";

        if (healing > 0) {
            weaponInfo += "+" + healing + " HP, ";
        }
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
            weaponInfo += "+" + (int) (armorPen * 100) + "% Armor Penetration, ";
        }
        if (MRPen > 0) {
            weaponInfo += "+" + (int) (MRPen * 100) + "% MR Penetration ";
        }


        weaponInfo += ">";

        return weaponInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return Objects.equals(weaponName, weapon.weaponName) && Objects.equals(tooltip, weapon.tooltip);
    }

}
