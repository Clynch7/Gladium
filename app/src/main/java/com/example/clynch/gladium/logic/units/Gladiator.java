package com.example.clynch.gladium.logic.units;

import com.example.clynch.gladium.GlVar;
import com.example.clynch.gladium.logic.items.*;

import org.json.JSONException;
import org.json.JSONObject;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by Clynch on 2018-02-08.
 */

public class Gladiator implements Unit{
    public String title, name;
    public int age, maxHP, currentHP, gold;
    public Weapon weapon;
    public Shield shield;
    public Gladiator(String name, String title, int age, int gold){
        this.name = name;
        this.age = age;
        this.title = title;
        maxHP = 30;
        currentHP = 30;
        this.gold = gold;
        weapon = new EmptyFist();
        shield = new EmptyFist();
    }
    public Gladiator(String stringGlad){
        try {
            JSONObject jsGlad = new JSONObject(stringGlad);
            this.name = jsGlad.getString(GlVar.GL_NAME_TAG);
            this.title = jsGlad.getString(GlVar.GL_TITLE_TAG);
            this.age = jsGlad.getInt(GlVar.GL_AGE_TAG);
            this.maxHP = jsGlad.getInt(GlVar.GL_MAXHP_TAG);
            this.currentHP = jsGlad.getInt(GlVar.GL_CURHP_TAG);
            this.gold = jsGlad.getInt(GlVar.GL_GOLD_TAG);
            this.weapon = ItemManager.generateWeapon(jsGlad.getString(GlVar.GL_WEAPON_TAG));
            this.shield = ItemManager.generateShield(jsGlad.getString(GlVar.GL_SHIELD_TAG));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getDamage() {
        return this.weapon.GetAttackDamage();
    }

    @Override
    public boolean takeDamage(int dmg) {
        int dmgTaken = dmg - this.shield.GetBlockValue();
        if(dmgTaken < 0){
            dmgTaken = 0;
        }
        this.currentHP -= dmgTaken;
        return this.currentHP > 0;
    }

    @Override
    public int getHp() {
        return this.currentHP;
    }

    @Override
    public int getLevel() {
        return 5;
    }

    @Override
    public int getEndurance() {
        return 15;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
