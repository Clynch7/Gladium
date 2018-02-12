package com.example.clynch.gladium.data;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Clynch on 2018-02-11.
 * Used for saving data to internal files
 * Used to save for example gladiator data between usages
 */

public class DataStorage {
    private static final String GLADIATOR_FILE = "/gladiator";

    public static boolean saveGladiator(JSONObject gladiator, Context context){
        return saveGladiator(gladiator, GLADIATOR_FILE, context);
    }

    private static boolean saveGladiator(JSONObject gladiator, String fileName, Context context) {
        try {
            File path = context.getFilesDir();
            File file = new File(path,fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(gladiator.toString().getBytes());
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String loadGladiator(Context context){
        return loadGladiator(GLADIATOR_FILE, context);
    }

    private static String loadGladiator(String file, Context context) {
        InputStream inputStream;
        String s = "";
        try {
            inputStream = new FileInputStream(context.getFilesDir() + file);
            s = convertStreamToString(inputStream);
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return s;
        }
    }
    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
