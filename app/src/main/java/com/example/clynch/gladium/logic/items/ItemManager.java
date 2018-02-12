package com.example.clynch.gladium.logic.items;

import android.support.annotation.NonNull;

/**
 * Created by Clynch on 2018-02-12.
 * Help functions for items like generating objects from Strings
 */

public class ItemManager {
    /**
     * This returns the classobject with the name of the spcified string
     * @param stringWeapon
     * @return
     */
    @NonNull
    public static Weapon generateWeapon(String stringWeapon){
        switch (stringWeapon) {
            case "Wooden Sword":
                return new WoodenSword();
            default:
                return new EmptyFist();
        }
    }
    /**
     * This returns the classobject with the name of the spcified string
     * @param stringShield  the name of the object
     * @return  the class object itself
     */
    @NonNull
    public static Shield generateShield(String stringShield){
        switch (stringShield) {
            case "Wooden Shield":
                return new WoodenShield();
            default:
                return new EmptyFist();
        }
    }
}
