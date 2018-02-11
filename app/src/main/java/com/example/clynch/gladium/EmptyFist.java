package com.example.clynch.gladium;

/**
 * Created by Clynch on 2018-02-08.
 */

public class EmptyFist implements Shield, Weapon{
    EmptyFist(){

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
