package com.example.clynch.gladium.logic;

import com.example.clynch.gladium.logic.items.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Clynch on 2018-02-08.
 */

public class Gladiator {
    public String title, name;
    public int age, maxHP, currentHP, gold;
    public Weapon weapon;
    public Shield shield;
    Gladiator(String name, String title, int age, int gold){
        this.name = name;
        this.age = age;
        this.title = title;
        maxHP = 30;
        currentHP = 30;
        this.gold = gold;
        weapon = new EmptyFist();
        shield = new EmptyFist();
    }
    Gladiator(String stringGlad){
        try {
            JSONObject jsGlad = new JSONObject(stringGlad);
            this.name = jsGlad.getString("name");
            this.title = jsGlad.getString("title");
            this.age = jsGlad.getInt("age");
            this.maxHP = jsGlad.getInt("maxHP");
            this.currentHP = jsGlad.getInt("currentHP");
            this.gold = jsGlad.getInt("gold");
            this.weapon = ItemManager.generateWeapon(jsGlad.getString("weapon"));
            this.shield = ItemManager.generateShield(jsGlad.getString("shield"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
