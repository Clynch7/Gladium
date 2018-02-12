package com.example.clynch.gladium.logic.items;

/**
 * Created by Clynch on 2018-02-08.
 */

public class WoodenSword implements Weapon{
    @Override
    public int GetAttackDamage() {
        return 5;
    }
    @Override
    public String toString(){
        return "Wooden Sword";
    }
}
