package com.example.clynch.gladium;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;

/**
 * The main activity, This activity handles any use with the gladiator.
 * The activity layout only has a view which is always filled with a fragment
 * The main fragment is the HomeFragment
 */

public class HomeActivity extends AppCompatActivity
implements HomeFragment.OnFragmentInteractionListener,
MakeGladiatorFragment.OnFragmentInteractionListener{

    HomeFragment homeFragment;
    MakeGladiatorFragment makeGladiatorFragment;
    FragmentManager fragmentManager;

    /**
     * The fragments are defined and the fragmentManager to swap between them is setup
     * The starting fragment is also added to the fragment container which is the entire layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        makeGladiatorFragment = new MakeGladiatorFragment();
        homeFragment = new HomeFragment();

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, makeGladiatorFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onMadeGladiator(JSONArray jsGlad) {
        Log.e("TAG", jsGlad.toString());
        DataStorage.saveGladiator(jsGlad, this);
        Log.e("TAG", DataStorage.loadGladiator(this));
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
                changeFragment(homeFragment);
                break;
            case "makeGladiator":
                changeFragment(makeGladiatorFragment);
                break;
        }
    }
}
