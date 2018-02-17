package com.example.clynch.gladium.logic.units;

import java.util.Random;

/**
 * Created by Clynch on 2018-02-17.
 */

public class BigDragon implements Unit {
    int minDamage, maxDamage, maxHP, HP, level, endurance;
    String name;
    public BigDragon(){
        endurance = 40;
        minDamage = 30;
        maxDamage = 50;
        maxHP = 100;
        HP = maxHP;
        level = 25;
        name = "A big Dragon";
    }
    @Override
    public int getDamage() {
        Random random = new Random();
        return minDamage + random.nextInt(maxDamage-minDamage);
    }

    @Override
    public boolean takeDamage(int dmg) {
        if(dmg < 0){
            dmg = 0;
        }
        this.HP -= dmg;
        return this.HP > 0;
    }

    @Override
    public int getHp() {
        return this.HP;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEndurance() {
        return this.endurance;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
