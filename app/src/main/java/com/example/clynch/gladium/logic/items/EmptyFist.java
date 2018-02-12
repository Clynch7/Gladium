package com.example.clynch.gladium.logic.items;

import com.example.clynch.gladium.logic.items.Shield;
import com.example.clynch.gladium.logic.items.Weapon;

/**
 * Created by Clynch on 2018-02-08.
 */

public class EmptyFist implements Shield, Weapon {
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
    public String toString(){
        return "Empty Fist";
    }
}
