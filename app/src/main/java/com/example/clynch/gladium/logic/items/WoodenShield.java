package com.example.clynch.gladium.logic.items;

/**
 * Created by Clynch on 2018-02-08.
 */

public class WoodenShield implements Item{

    @Override
    public int GetAttackDamage() {
        return 0;
    }

    @Override
    public int GetBlockValue() {
        return 2;
    }

    @Override
    public String getName() {
        return "Wooden Shield";
    }

    @Override
    public int getCost() {
        return 5;
    }

    @Override
    public String toString(){
        return getName();
    }
}
