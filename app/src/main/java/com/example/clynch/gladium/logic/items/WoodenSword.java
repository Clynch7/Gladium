package com.example.clynch.gladium.logic.items;

/**
 * Created by Clynch on 2018-02-08.
 */

public class WoodenSword implements Item{
    @Override
    public int GetAttackDamage() {
        return 5;
    }

    @Override
    public int GetBlockValue() {
        return 0;
    }

    @Override
    public String getName() {
        return "Wooden Sword";
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
