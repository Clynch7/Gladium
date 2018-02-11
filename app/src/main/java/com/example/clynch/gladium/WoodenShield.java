package com.example.clynch.gladium;

/**
 * Created by Clynch on 2018-02-08.
 */

public class WoodenShield implements Shield{

    @Override
    public int GetBlockValue() {
        return 2;
    }
    @Override
    public String toString(){
        return "Wooden Shield";
    }
}
