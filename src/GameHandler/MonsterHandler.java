package GameHandler;

import Hostile.*;
import Views.DungeonGUI;

import java.util.Random;

public class MonsterHandler {
    Random random = new Random();
    Monster currentMonster;
    public String monsterName;
    public int monsterHP;
    public int monsterAD;
    public int monsterMP;
    public int monsterArmor;
    public int monsterMR;

    public int[] slimeSpawnRate = {35, 33, 30, 24, 20, 18, 15};
    public int[] zombieSpawnRate = {32, 30, 28, 23, 20, 19, 15};
    public int[] skeletonSpawnRate = {20, 20, 23, 23, 24, 24, 25};
    public int[] mimicSpawnRate = {13, 13, 15, 18, 20, 20, 22};
    //public int[] diabloSpawnRate = {0, 2, 4, 12, 16, 19, 23};

    Slime slime = new Slime();
    Zombie zombie = new Zombie();
    Skeleton skeleton = new Skeleton();
    Mimic mimic = new Mimic();
    Diablo diablo = new Diablo();

    public Monster getCurrentMonster(int lv) {
        if (random.nextInt(100) < slimeSpawnRate[lv - 1]) {
            currentMonster = slime;
        } else if (random.nextInt(100) < (slimeSpawnRate[lv - 1] + zombieSpawnRate[lv - 1])) {
            currentMonster = zombie;
        } else if (random.nextInt(100) < (slimeSpawnRate[lv - 1] + zombieSpawnRate[lv - 1] +
                skeletonSpawnRate[lv - 1])) {
            currentMonster = skeleton;
        } else if (random.nextInt(100) < (slimeSpawnRate[lv - 1] + zombieSpawnRate[lv - 1] +
                skeletonSpawnRate[lv - 1] + mimicSpawnRate[lv - 1])) {
            currentMonster = mimic;
        } else {
            currentMonster = diablo;
        }

        return currentMonster;
    }

    public MonsterHandler() {

    }

    public MonsterHandler(String monsterName, int monsterHP, int monsterAD, int monsterMP,
                          int monsterArmor, int monsterMR) {
        System.out.println(monsterName);
        this.monsterName = monsterName;
        this.monsterHP = monsterHP;
        this.monsterAD = monsterAD;
        this.monsterMP = monsterMP;
        this.monsterArmor = monsterArmor;
        this.monsterMR = monsterMR;
    }
}
