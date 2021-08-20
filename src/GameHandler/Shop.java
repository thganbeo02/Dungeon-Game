package GameHandler;

import Items.Item;
import Items.Weapon;

public class Shop {
    Item lifePotion;
    int healingPrice = 40;
    Item ADBoost, MPBoost, DEFBoost;
    public Weapon sword, shield;
    public Weapon bow, hood;
    public Weapon orb, cape;
    public Weapon dagger, shiv;

    public final Weapon[] weaponsInShop = new Weapon[2];
    public final Item[] itemsInShop = new Item[6];
    public int[] quickADPriceByLevel = {60, 180, 540, 1620, 4860, 7777, 8888};
    public int[] quickMPPriceByLevel = {70, 210, 630, 1890, 5670, 8888, 9999};
    public int[] quickDEFPriceByLevel = {80, 240, 720, 2160, 6480, 9999, 11111};
    public int[] quickADByLevel = {4, 8, 18, 35, 70, 140, 280};
    public int[] quickMPByLevel = {4, 8, 18, 35, 70, 140, 280};
    public int[] quickManaByLevel = {2, 4, 8, 13, 20, 30, 45};
    public int[] quickHPByLevel = {2, 4, 8, 13, 20, 30, 45};
    public int[] quickArmorByLevel = {2, 4, 8, 13, 20, 30, 45};

    public int[] getQuickADPriceByLevel() {
        return quickADPriceByLevel;
    }

    public int[] getQuickMPPriceByLevel() {
        return quickMPPriceByLevel;
    }

    public int[] getQuickDEFPriceByLevel() {
        return quickDEFPriceByLevel;
    }

    public Shop(int level, String className) {
        lifePotion = new Item("Life Potion", healingPrice);
        lifePotion.healing = 16;

        ADBoost = new Item("AD Boost", quickADPriceByLevel[level - 1]);
        ADBoost.AD = quickADByLevel[level - 1];

        MPBoost = new Item("MP Boost", quickMPPriceByLevel[level - 1]);
        MPBoost.MP = quickMPByLevel[level - 1];
        MPBoost.currentMana = quickManaByLevel[level - 1];

        DEFBoost = new Item("DEF Boost", quickDEFPriceByLevel[level - 1]);
        DEFBoost.HP = quickHPByLevel[level - 1];
        DEFBoost.armor = quickArmorByLevel[level - 1];

        switch (level) {
            case 1 -> {
                weaponsInShop[0] = new Weapon("", 0);
                weaponsInShop[1] = new Weapon("", 0);
            }
            case 2 -> {
                switch (className) {
                    case "fighter" -> {
                        // Sword I (+18 AD, +18 Mana, +10% Armor Penetration)
                        sword = new Weapon("Sword I", 900);
                        sword.AD = 18;
                        sword.currentMana = 18;
                        sword.armorPen = 0.1;

                        // Shield I (+21 HP, +21 Armor, -12% Mob AD)
                        shield = new Weapon("Shield I", 900);
                        shield.HP = 21;
                        shield.armor = 21;
                        shield.monsterADReduction = 0.12;
                        shield.tooltip = "* Reduces monster's AD by 12% *".toUpperCase();

                        weaponsInShop[0] = sword;
                        weaponsInShop[1] = shield;
                    }

                    case "archer" -> {
                        // Bow I (+22 AD, +15 MP, +6% Dodge Chance, +8% Crit Chance)
                        bow = new Weapon("Bow I", 900);
                        bow.AD = 22;
                        bow.MP = 15;
                        bow.dodgeChance = 6;
                        bow.critChance = 8;

                        // Hood I (+24 HP, +10 AD, +24 Armor, +12% Armor Pen)
                        hood = new Weapon("Hood I", 900);
                        hood.HP = 24;
                        hood.AD = 10;
                        hood.armor = 24;
                        hood.armorPen = 0.12;

                        weaponsInShop[0] = bow;
                        weaponsInShop[1] = hood;
                    }

                    case "mage" -> {
                        // Orb I (+12 MP, +1 Mana Regen, +5% Crit chance)
                        orb = new Weapon("Orb I", 900);
                        orb.MP = 12;
                        orb.manaRegen = 1;
                        orb.critChance = 5;

                        // Cape I (+18 MP, +12 HP, +12 Mana, +5% Dodge chance)
                        cape = new Weapon("Cape I", 900);
                        cape.MP = 18;
                        cape.HP = 12;
                        cape.currentMana = 12;
                        cape.dodgeChance = 5;

                        weaponsInShop[0] = orb;
                        weaponsInShop[1] = cape;
                    }

                    case "assassin" -> {
                        // Dagger I (+12 HP, +18 AD, +6% Dodge chance, +6% Crit chance)
                        dagger = new Weapon("Dagger I", 900);
                        dagger.HP = 12;
                        dagger.AD = 18;
                        dagger.dodgeChance = 6;
                        dagger.critChance = 6;

                        // Shiv I (+24 AD, +20 Armor, +10% Armor Penetration)
                        shiv = new Weapon("Shiv I", 900);
                        shiv.AD = 24;
                        shiv.armor = 20;
                        shiv.armorPen = 0.1;

                        weaponsInShop[0] = dagger;
                        weaponsInShop[1] = shiv;
                    }

                }
            }
            case 3 -> {
                switch (className) {
                    case "fighter" -> {
                        // Sword II (+35 AD, +30 Mana, +17% Armor Penetration, 4% Dodge Chance)
                        sword = new Weapon("Sword II", 2700);
                        sword.AD = 35;
                        sword.currentMana = 35;
                        sword.armorPen = 0.17;
                        sword.dodgeChance = 4;

                        // Shield II (+28 HP, +28 Armor, -16% Mob AD)
                        shield = new Weapon("Shield II", 2700);
                        shield.HP = 28;
                        shield.armor = 28;
                        shield.monsterADReduction = 0.16;
                        shield.tooltip = "* Reduces monster's AD by 16% *".toUpperCase();

                        weaponsInShop[0] = sword;
                        weaponsInShop[1] = shield;
                    }

                    case "archer" -> {
                        // Bow II (+50 AD, +25 MP, +9% Dodge Chance, +12% Crit Chance)
                        bow = new Weapon("Bow II", 2700);
                        bow.AD = 50;
                        bow.MP = 25;
                        bow.dodgeChance = 9;
                        bow.critChance = 12;

                        // Hood II (+36 HP, +24 AD, +40 Armor, +30 MR, +20% Armor Pen)
                        hood = new Weapon("Hood II", 2700);
                        hood.HP = 36;
                        hood.AD = 24;
                        hood.armor = 40;
                        hood.MR = 30;
                        hood.armorPen = 0.20;

                        weaponsInShop[0] = bow;
                        weaponsInShop[1] = hood;
                    }

                    case "mage" -> {
                        // Orb II (+20 MP, +1 Mana Regen, +8% Crit chance, +15% MR Penetration)
                        orb = new Weapon("Orb II", 2700);
                        orb.MP = 20;
                        orb.manaRegen = 1;
                        orb.critChance = 8;
                        orb.MRPen = 0.15;

                        // Cape II (+30 MP, +20 HP, +20 Mana, +8% Dodge chance)
                        cape = new Weapon("Cape II", 2700);
                        cape.MP = 30;
                        cape.HP = 20;
                        cape.currentMana = 20;
                        cape.dodgeChance = 8;

                        weaponsInShop[0] = orb;
                        weaponsInShop[1] = cape;
                    }

                    case "assassin" -> {
                        // Dagger II (+20 HP, +30 AD, +8% Dodge Chance, +9% Crit Chance, +15% Crit Damage)
                        dagger = new Weapon("Dagger II", 2700);
                        dagger.HP = 20;
                        dagger.AD = 30;
                        dagger.dodgeChance = 8;
                        dagger.critChance = 9;
                        dagger.critMultiplier = 0.15;

                        // Shiv II (+48 AD, +48 Armor, +48 MR, +20% Armor Penetration)
                        shiv = new Weapon("Shiv II", 2700);
                        shiv.AD = 48;
                        shiv.armor = 48;
                        shiv.MR = 48;
                        shiv.armorPen = 0.2;

                        weaponsInShop[0] = dagger;
                        weaponsInShop[1] = shiv;
                    }

                }
            }
            case 4 -> {
                switch (className) {
                    case "fighter" -> {
                        // Sword III (+64 AD, +45 Mana, +23% Armor Penetration, 7% Dodge Chance, +8% Crit Chance)
                        sword = new Weapon("Sword III", 8100);
                        sword.AD = 64;
                        sword.currentMana = 45;
                        sword.armorPen = 0.23;
                        sword.dodgeChance = 7;
                        sword.critChance = 8;

                        // Shield III (+40 HP, +40 Armor, -21% Mob AD)
                        shield = new Weapon("Shield III", 8100);
                        shield.HP = 40;
                        shield.armor = 40;
                        shield.monsterADReduction = 0.21;
                        shield.tooltip = "* Reduces monster's AD by 21% *".toUpperCase();

                        weaponsInShop[0] = sword;
                        weaponsInShop[1] = shield;
                    }

                    case "archer" -> {
                        // Bow III (+80 AD, +50 MP, +12% Dodge Chance, +16% Crit Chance)
                        bow = new Weapon("Bow III", 8100);
                        bow.AD = 80;
                        bow.MP = 50;
                        bow.dodgeChance = 12;
                        bow.critChance = 16;

                        // Hood III (+54 HP, +40 AD, +60 Armor, +40 MR, +33% Armor Pen)
                        hood = new Weapon("Hood III", 8100);
                        hood.HP = 54;
                        hood.AD = 40;
                        hood.armor = 60;
                        hood.MR = 40;
                        hood.armorPen = 0.33;

                        weaponsInShop[0] = bow;
                        weaponsInShop[1] = hood;
                    }

                    case "mage" -> {
                        // Orb III (+30 MP, +2 Mana Regen, +12% Crit chance, +20% MR Penetration)
                        orb = new Weapon("Orb III", 8100);
                        orb.MP = 30;
                        orb.manaRegen = 2;
                        orb.critChance = 12;
                        orb.MRPen = 0.2;

                        // Cape III (+50 MP, +30 HP, +30 Mana, +12% Dodge chance)
                        cape = new Weapon("Cape III", 8100);
                        cape.MP = 50;
                        cape.HP = 30;
                        cape.currentMana = 30;
                        cape.dodgeChance = 12;

                        weaponsInShop[0] = orb;
                        weaponsInShop[1] = cape;
                    }

                    case "assassin" -> {
                        // Dagger III (+36 HP, +50 AD, +10% Dodge Chance, +12% Crit Chance, +20% Crit Damage)
                        dagger = new Weapon("Dagger III", 8100);
                        dagger.HP = 36;
                        dagger.AD = 50;
                        dagger.dodgeChance = 10;
                        dagger.critChance = 12;
                        dagger.critMultiplier = 0.2;

                        // Shiv III (+78 AD, +78 Armor, +78 MR, +25% Armor Penetration)
                        shiv = new Weapon("Shiv III", 8100);
                        shiv.AD = 78;
                        shiv.armor = 78;
                        shiv.MR = 78;
                        shiv.armorPen = 0.25;

                        weaponsInShop[0] = dagger;
                        weaponsInShop[1] = shiv;
                    }

                }
            }
            case 5, 6, 7 -> {
                switch (className) {
                    case "fighter" -> {
                        // EXCALIBUR (+111 AD, +70 Mana, +25% Armor Penetration, 11% Dodge Chance, +15% Crit Chance)
                        sword = new Weapon("EXCALIBUR", 19999);
                        sword.AD = 111;
                        sword.currentMana = 70;
                        sword.armorPen = 0.25;
                        sword.dodgeChance = 11;
                        sword.critChance = 15;
                        sword.tooltip = "* On Crit, heals 10% max HP *".toUpperCase();

                        // AEGIS (+64 HP, +64 Armor, -28% Mob AD)
                        shield = new Weapon("AEGIS", 19999);
                        shield.HP = 64;
                        shield.armor = 64;
                        shield.monsterADReduction = 0.28;
                        shield.aegisADArmorGain = 5;
                        shield.tooltip = "* Reduces monster's AD by 28%.\n" +
                                "On Takedown, gains (5% current Armor) Armor and AD *".toUpperCase();

                        weaponsInShop[0] = sword;
                        weaponsInShop[1] = shield;
                    }

                    case "archer" -> {
                        // DEADSHOT (+120 AD, +75 MP, +15% Dodge Chance, +21% Crit Chance, +21% Crit damage)
                        bow = new Weapon("DEADSHOT", 19999);
                        bow.AD = 120;
                        bow.MP = 75;
                        bow.dodgeChance = 15;
                        bow.critChance = 21;
                        bow.critMultiplier = 0.21;
                        bow.trueDamage = 7;
                        bow.tooltip = "* Basic attack does an additional (7% AD) TRUE DAMAGE *".toUpperCase();

                        // PHANTOM (+77 HP, +60 AD, +100 Armor, +90 MR, +55% Armor Pen)
                        hood = new Weapon("PHANTOM", 19999);
                        hood.HP = 77;
                        hood.AD = 60;
                        hood.armor = 100;
                        hood.MR = 90;
                        hood.armorPen = 0.55;
                        hood.tooltip = "* Gains 5% Lifesteal *".toUpperCase();

                        weaponsInShop[0] = bow;
                        weaponsInShop[1] = hood;
                    }

                    case "mage" -> {
                        // MERLIN'S WAND (+50 MP, +2 Mana Regen, +20% Crit chance, +33% MR Penetration)
                        orb = new Weapon("MERLIN'S WAND", 19999);
                        orb.MP = 50;
                        orb.manaRegen = 2;
                        orb.critChance = 20;
                        orb.MRPen = 0.33;
                        orb.tooltip = "* On Takedown, gains (10% current Mana) MP *".toUpperCase();

                        // MERLIN'S STAFF (+88 MP, +50 HP, +50 Mana, +16% Dodge chance)
                        cape = new Weapon("MERLIN'S STAFF", 19999);
                        cape.MP = 88;
                        cape.HP = 50;
                        cape.currentMana = 50;
                        cape.dodgeChance = 16;
                        cape.tooltip = "* All skills cost 5 Mana less *".toUpperCase();

                        weaponsInShop[0] = orb;
                        weaponsInShop[1] = cape;
                    }

                    case "assassin" -> {
                        // CURSED BLADE (+60 HP, +90 AD, +14% Dodge Chance, +16% Crit Chance, +25% Crit Damage)
                        dagger = new Weapon("CURSED BLADE", 19999);
                        dagger.HP = 60;
                        dagger.AD = 90;
                        dagger.dodgeChance = 14;
                        dagger.critChance = 16;
                        dagger.critMultiplier = 0.25;
                        dagger.tooltip = "* Basic attack has 5% chance to instantly execute and gain " +
                                "5% of their AD, Armor, and MR, then heals to full HP *".toUpperCase();

                        // BLOODTHIRST (+120 AD, +120 Armor, +120 MR, +35% Armor Penetration)
                        shiv = new Weapon("BLOODTHIRST", 19999);
                        shiv.AD = 120;
                        shiv.armor = 120;
                        shiv.MR = 120;
                        shiv.armorPen = 0.35;
                        shiv.tooltip = "* Gains 5% Lifesteal *".toUpperCase();

                        weaponsInShop[0] = dagger;
                        weaponsInShop[1] = shiv;
                    }

                }
            }

        }
    }

    public Weapon[] getWeaponsInShop() {
        return weaponsInShop;
    }

    public Item[] getItemsInShop() {
        itemsInShop[0] = lifePotion;
        itemsInShop[1] = weaponsInShop[0];
        itemsInShop[2] = weaponsInShop[1];
        itemsInShop[3] = ADBoost;
        itemsInShop[4] = MPBoost;
        itemsInShop[5] = DEFBoost;
        return itemsInShop;
    }

    public Item getLifePotion() {
        return lifePotion;
    }
}
