package com.example.clynch.gladium;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 This is the homescreen of the application
 It is a fullscreen fragment
 TODO Move all the storage into separate class
 */
public class HomeFragment extends Fragment {

    TextView ageTV, HPTV, weaponTV, shieldTV, goldTV, titleTV, nameTV;
    public static final String PREFS_NAME = "gladiator_data_file";
    public static final String PREFS_GLAD_NAME = "gladiatorName";
    public static final String PREFS_GLAD_TITLE = "gladiatorTitle";
    public static final String PREFS_GLAD_AGE = "gladiatorAge";
    public static final String PREFS_GLAD_MAXHP = "gladiatorMaxHP";
    public static final String PREFS_GLAD_CURRENTHP = "gladiatorCurrentHP";
    public static final String PREFS_GLAD_GOLD = "gladiatorGold";
    public static final String PREFS_GLAD_MADE = "gladiatorMade";
    public static final String PREFS_GLAD_WEAPON = "gladiatorWeapon";
    public static final String PREFS_GLAD_SHIELD = "gladiatorShield";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Gladiator gladiator;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  preferences = getSharedPreferences(PREFS_NAME, 0);
 /*       createGladiator();
        if(preferences.getBoolean(PREFS_GLAD_MADE,false)){
            loadGladiatorData();
        }
        updateGladiatorDataDisplay();*/
    }

    /**
     * Updates the information on the screen
     */
    private void updateGladiatorDataDisplay() {
        nameTV.setText(gladiator.name);
        titleTV.setText(gladiator.title);
        ageTV.setText(String.valueOf(gladiator.age));
        HPTV.setText(gladiator.currentHP + "/" + gladiator.maxHP);
        weaponTV.setText(gladiator.weapon.toString());
        shieldTV.setText(gladiator.shield.toString());
        goldTV.setText(String.valueOf(gladiator.gold));
    }

    private void createGladiator() {
        gladiator = new Gladiator();
    }

    /**
     * Loads the gladiator from the previously saved state
     * TODO Move this to other class
     * */

    private void loadGladiatorData() {
        gladiator.name = preferences.getString(PREFS_GLAD_NAME, "GLADIATOR");
        gladiator.title = preferences.getString(PREFS_GLAD_TITLE, "TITLE");
        gladiator.age = preferences.getInt(PREFS_GLAD_AGE, 0);
        gladiator.maxHP = (preferences.getInt(PREFS_GLAD_MAXHP, 0));
        gladiator.currentHP = preferences.getInt(PREFS_GLAD_CURRENTHP, 0);
        gladiator.weapon = GenerateWeaponFromString(preferences.getString(PREFS_GLAD_WEAPON, "WEAPON"));
        gladiator.shield = GenerateShieldFromString(preferences.getString(PREFS_GLAD_SHIELD, "SHIELD"));
        gladiator.gold = (preferences.getInt(PREFS_GLAD_GOLD, 0));
    }

    /**
     * This returns the classobject with the name of the spcified string
     * @param weaponString
     * @return
     */
    @NonNull
    private Weapon GenerateWeaponFromString(String weaponString) {
        switch (weaponString){
            case "WoodenSword":
                return new WoodenSword();
            default:
                return new EmptyFist();
        }
    }
    /**
     * This returns the classobject with the name of the spcified string
     * @param shieldString  the name of the object
     * @return  the class object itself
     */
    @NonNull
    private Shield GenerateShieldFromString(String shieldString) {
        switch (shieldString){
            case "WoodenShield":
                return new WoodenShield();
            default:
                return new EmptyFist();
        }
    }

    /**
     * This saves the gladiator to preferences
     * TODO Move this to other class
     */
    private void saveGladiatorData(){
        editor = preferences.edit();
        editor.putBoolean(PREFS_GLAD_MADE, true);
        editor.putString(PREFS_GLAD_NAME, gladiator.name);
        editor.putString(PREFS_GLAD_TITLE, gladiator.title);
        editor.putString(PREFS_GLAD_WEAPON, gladiator.weapon.toString());
        editor.putString(PREFS_GLAD_SHIELD, gladiator.shield.toString());
        editor.putInt(PREFS_GLAD_CURRENTHP, gladiator.currentHP);
        editor.putInt(PREFS_GLAD_MAXHP, gladiator.maxHP);
        editor.putInt(PREFS_GLAD_AGE, gladiator.age);
        editor.putInt(PREFS_GLAD_GOLD, gladiator.gold);
        editor.commit();
    }

    /**
     * Handles all the view stuff
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        titleTV = view.findViewById(R.id.title_valueTV);
        ageTV = view.findViewById(R.id.age_valueTV);
        HPTV = view.findViewById(R.id.hp_valueTV);
        weaponTV = view.findViewById(R.id.weapon_valueTV);
        shieldTV = view.findViewById(R.id.shield_valueTV);
        goldTV = view.findViewById(R.id.gold_valueTV);
        nameTV = view.findViewById(R.id.glad_nameTV);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void getFragment(String s);
    }
}
