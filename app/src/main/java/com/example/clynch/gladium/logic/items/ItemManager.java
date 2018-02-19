package com.example.clynch.gladium.logic.items;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Clynch on 2018-02-12.
 * Help functions for items like generating objects from Strings
 */

public class ItemManager {
    /**
     * This returns the classobject with the name of the spcified string
     * @param stringItem
     * @return
     */
    @NonNull
    public static Item generateItem(String stringItem){
        switch (stringItem) {
            case "Wooden Sword":
                return new WoodenSword();
            case "Wooden Shield":
                return new WoodenShield();
            case "Empty Fist":
                return new EmptyFist();
            default:
                return null;
        }
    }
    public static JSONArray getAllItems(){
        JSONArray items = new JSONArray();
        items.put("Wooden Sword");
        items.put("Wooden Shield");
        return items;
    }
    public static String[] getAllItemsArray(){
        JSONArray items = getAllItems();
        String[] response = new String[items.length()];
        for(int i = 0; i < items.length();i++){
            try {
                response[i] = items.get(i).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}
