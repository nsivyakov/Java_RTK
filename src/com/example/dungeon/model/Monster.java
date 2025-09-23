package com.example.dungeon.model;

import java.util.ArrayList;
import java.util.List;

public class Monster extends Entity {
    private int level;
    private List<Item> loot;

    public Monster(String name, int level, int hp, List<Item> loot) {
        super(name, hp);
        this.level = level;
        this.loot = loot != null ? loot : new ArrayList<>();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Item> getLoot() {
        return loot;
    }

    public void setLoot(List<Item> loot) {
        this.loot = loot;
    }
}
