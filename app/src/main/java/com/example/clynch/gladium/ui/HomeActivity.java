package com.example.clynch.gladium.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.clynch.gladium.R;
import com.example.clynch.gladium.data.DataStorage;
import com.example.clynch.gladium.logic.Game;

import org.json.JSONObject;

/**
 * The main activity, This activity handles any use with the gladiator.
 * The activity layout only has a view which is always filled with a fragment
 * The main fragment is the HomeFragment
 */

public class HomeActivity extends AppCompatActivity
implements HomeFragment.OnFragmentInteractionListener,
MakeGladiatorFragment.OnFragmentInteractionListener,
ArenaFragment.OnFragmentInteractionListener,
ShopFragment.OnFragmentInteractionListener,
AbilitiesFragment.OnFragmentInteractionListener{

    HomeFragment homeFragment;
    MakeGladiatorFragment makeGladiatorFragment;
    ShopFragment shopFragment;
    ArenaFragment arenaFragment;
    AbilitiesFragment abilitiesFragment;
    FragmentManager fragmentManager;
    Game game;

    /**
     * The fragments are defined and the fragmentManager to swap between them is setup
     * The starting fragment is also added to the fragment container which is the entire layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DataStorage.initialize(this);
        Game.initialize(this);

        makeGladiatorFragment = new MakeGladiatorFragment();
        homeFragment = new HomeFragment();
        shopFragment = new ShopFragment();
        arenaFragment = new ArenaFragment();
        abilitiesFragment = new AbilitiesFragment();

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(game.hasGladiator()){
            fragmentTransaction.add(R.id.fragment_container, homeFragment);
        }else{
            fragmentTransaction.add(R.id.fragment_container, makeGladiatorFragment);
        }
        fragmentTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Game.save();
    }

    @Override
    public void onMadeGladiator(JSONObject jsGlad) {
        Log.e("TAG", jsGlad.toString());
        DataStorage.saveGladiator(jsGlad);
        Log.e("TAG", DataStorage.loadGladiator());
    }

    /**
     * Help method which adds the current fragment to the backstack and goes to the desired fragment
     * @param fragment  The fragment which we want to replace the current fragment with
     */
    public void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * This method is called by the fragments and used to change to a new fragment
     * @param frag frag defines the fragment which we want to swap to
     */
    @Override
    public void getFragment(String frag) {
        Log.d("TAG", "Changing to fragment " + frag);
        switch (frag){
            case "home":
                updateInfo();
                changeFragment(homeFragment);
                break;
            case "makeGladiator":
                changeFragment(makeGladiatorFragment);
                break;
            case "shop":
                changeFragment(shopFragment);
                break;
            case "arena":
                changeFragment(arenaFragment);
                break;
            case "abilities":
                changeFragment(abilitiesFragment);
                break;
        }
    }

    @Override
    public void updateInfo() {
        homeFragment.updateGladiatorDataDisplay(Game.getGladiator());
    }
}
