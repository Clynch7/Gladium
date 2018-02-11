package com.example.clynch.gladium;

/**
 * Created by Clynch on 2018-02-08.
 */

public class Gladiator {
    String title, name;
    int age, maxHP, currentHP, gold;
    Weapon weapon;
    Shield shield;
    Gladiator(){
        name = "Glad";
        age = 25;
        title = "Novice";
        maxHP = 30;
        currentHP = 30;
        gold = 10;
        weapon = new EmptyFist();
        shield = new EmptyFist();
    }
}
