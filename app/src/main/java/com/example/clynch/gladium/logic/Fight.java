package com.example.clynch.gladium.logic;

import com.example.clynch.gladium.logic.units.*;

/**
 * Created by Clynch on 2018-02-17.
 */

public class Fight {
    Unit u1, u2;
    boolean excecuted;
    String report;
    Fight(Unit unit1, Unit unit2){
        u1 = unit1;
        u2 = unit2;
        excecuted = false;
        report = "not fought";
    }
    public Unit execute(){
        report = "Fought";
        int end1 = u1.getEndurance();
        int dmg1 = u1.getDamage();
        int end2 = u2.getEndurance();
        int dmg2 = u2.getDamage();
        while(true){
            end1 --;
            end2 --;
            if(end1 == 0){
                report = u2.getName() + " Won as " + u1.getName() + " couldent fight no more";
                return u2;
            }else if(end2 == 0){
                report = u1.getName() + " Won as " + u2.getName() + " couldent fight no more";
                return u1;
            }
            if (!u2.takeDamage(dmg1)){
                report = u1.getName() + " Won as " + u2.getName() + " has " + u2.getHp() + " HP left";
                return u1;
            }if (!u1.takeDamage(dmg2)){
                report = u2.getName() + " Won as " + u1.getName() + " has " + u1.getHp() + " HP left";
                return u2;
            }
        }
    }
    public String getReport(){
        return this.report;
    }
}
