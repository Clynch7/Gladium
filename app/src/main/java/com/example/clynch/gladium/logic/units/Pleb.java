package com.example.clynch.gladium.logic.units;

/**
 * Created by Clynch on 2018-02-17.
 */

public class Pleb implements Unit {

    int maxHP, HP, damage, level;
    String name;

    public Pleb(){
        maxHP = 10;
        HP = 10;
        damage = 2;
        level = 1;
        name = "Plebian";
    }
    @Override
    public int getDamage() {
        return damage;
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
        return 10;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
