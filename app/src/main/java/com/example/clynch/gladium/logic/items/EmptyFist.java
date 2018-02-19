package com.example.clynch.gladium.logic.items;

import com.example.clynch.gladium.logic.items.Shield;
import com.example.clynch.gladium.logic.items.Weapon;

/**
 * Created by Clynch on 2018-02-08.
 */

public class EmptyFist implements Item {
    public EmptyFist(){

    }
    @Override
    public int GetAttackDamage() {
        return 1;
    }

    @Override
    public int GetBlockValue() {
        return 0;
    }
    @Override
    public String getName(){
        return "Empty Fist";
    }

    @Override
    public int getCost() {
        return 0;
    }
    @Override
    public String toString(){
        return getName();
    }
}
