package com.example.clynch.gladium.logic.units;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Clynch on 2018-02-17.
 */

public class UnitManager {
    public static JSONArray getAllUnits(){
        JSONArray units = new JSONArray();
        units.put("Pleb");
        units.put("Big Dragon");
        return units;
    }
    public static String[] getAllUnitsArray(){
        JSONArray units = getAllUnits();
        String[] response = new String[units.length()];
        for(int i = 0; i < units.length();i++){
            try {
                response[i] = units.get(i).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
    /**
     * This returns the classobject with the name of the spcified string
     * @param unitName
     * @return
     */
    @NonNull
    public static Unit generateUnit(String unitName){
        switch (unitName) {
            case "Pleb":
                return new Pleb();
            case "Big Dragon":
                return new BigDragon();
            default:
                return new Pleb();
        }
    }
}
