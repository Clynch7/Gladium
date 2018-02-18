package com.example.clynch.gladium.logic;

import android.content.Context;
import android.util.Log;

import com.example.clynch.gladium.data.DataStorage;
import com.example.clynch.gladium.logic.units.Gladiator;
import com.example.clynch.gladium.logic.units.Unit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Clynch on 2018-02-12.
 * This is the head class for the game logic
 * This class should handle all the interaction with the UI layer
 * That is all input/output will pass through this class
 *
 * For the time beeing also handles interactions with the data layer
 */

public class Game {
    private static Context context;
    private static Gladiator gameGladiator;
    public static void initialize(Context ctx){
        context = ctx;
        gameGladiator = new Gladiator(DataStorage.loadGladiator());
    }

    /**
     * TODO implement a more robust check if there is a gladiator in the game
     * @return If game has a propper gladiator
     */
    public static boolean hasGladiator(){
        String gladString = DataStorage.loadGladiator();
        if(gladString == ""){
            return false;
        }else{
            return true;
        }
    }

    /**
     * This method provides the current gladiator state
     * @return gameGladiator in JSON string format
     */
    public static String getGladiator(){
        return gladiatorToJSON(gameGladiator).toString();
    }

    /**
     * Takes a Gladiator object and returns it in JSON format
     * TODO move this to the Gladiator class
     * @param gladiator
     * @return
     */
    private static JSONObject gladiatorToJSON(Gladiator gladiator){
        JSONObject jsGlad = new JSONObject();
        try {
            jsGlad.put("name", gladiator.name);
            jsGlad.put("title", gladiator.title);
            jsGlad.put("age", gladiator.age);
            jsGlad.put("currentHP", gladiator.currentHP);
            jsGlad.put("maxHP", gladiator.maxHP);
            jsGlad.put("gold", gladiator.gold);
            jsGlad.put("weapon", gladiator.weapon);
            jsGlad.put("shield", gladiator.shield);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsGlad;
    }
    public static boolean createGladiator(String gladString){
        if (gameGladiator != null) {
            return false;
        }else{
            gameGladiator = new Gladiator(gladString);
            return true;
        }
    }

    public static void save(){
        DataStorage.saveGladiator(gladiatorToJSON(gameGladiator));
    }

    public static void removeGladiator() {
        gameGladiator = null;
    }

    public static void fightUnit(Unit unit){
        Fight fight = new Fight(gameGladiator, unit);
        fight.execute();
        Log.i("Game", fight.getReport());
    }
}