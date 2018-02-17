package com.example.clynch.gladium.logic.units;

/**
 * Created by Clynch on 2018-02-16.
 */

public interface Unit {
    int getDamage();
    // Returns true if alive, false if the unit died from the damage
    boolean takeDamage(int dmg);
    int getHp();
    int getLevel();
    int getEndurance();
    String getName();
}
