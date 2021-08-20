package Items;

import java.util.Arrays;

public class Inventory {

    public Item[] itemSlots;

    public Inventory() {
        itemSlots = new Item[2];
    }


    public void addItem(Item itemName) {
        if (itemName.itemName.equals("Life Potion")) {
            System.out.println("Issa life potion");
            itemSlots[0] = itemName;
        }
        if (itemName instanceof Weapon) {
            System.out.println("Issa weapon");
            itemSlots[1] = itemName;
        }
    }

    public void removeItem(Item itemName) {
        if (itemName.itemName.equals("Life Potion")) {
            System.out.println("Issa life potion");
            itemSlots[0] = itemName;
        }
        if (itemName instanceof Weapon) {
            System.out.println("Issa weapon");
            itemSlots[1] = null;
        }
    }
}
