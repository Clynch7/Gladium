package com.example.clynch.gladium.ui;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clynch.gladium.GlVar;
import com.example.clynch.gladium.R;
import com.example.clynch.gladium.data.DataStorage;
import com.example.clynch.gladium.logic.Game;

import org.json.JSONException;
import org.json.JSONObject;

import javax.microedition.khronos.opengles.GL;


/**
 This is the homescreen of the application
 It is a fullscreen fragment
 TODO Move all the storage into separate class
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    TextView ageTV, HPTV, weaponTV, shieldTV, goldTV, titleTV, nameTV;
    Button abilitiesBT, shopBT, arenaBT;

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
    }

    /**
     * Updates the information on the screen
     */
    private void updateGladiatorDataDisplay(String stringGlad) {
        try {
            JSONObject jsGlad = new JSONObject(stringGlad);
            nameTV.setText(jsGlad.getString(GlVar.GL_NAME_TAG));
            titleTV.setText(jsGlad.getString(GlVar.GL_TITLE_TAG));
            ageTV.setText(String.valueOf(jsGlad.getInt(GlVar.GL_AGE_TAG)));
            HPTV.setText(String.valueOf(jsGlad.getInt(GlVar.GL_CURHP_TAG) + "/" + jsGlad.getInt(GlVar.GL_MAXHP_TAG)));
            weaponTV.setText(jsGlad.getString(GlVar.GL_WEAPON_TAG));
            shieldTV.setText(jsGlad.getString(GlVar.GL_SHIELD_TAG));
            goldTV.setText(String.valueOf(jsGlad.getInt(GlVar.GL_GOLD_TAG)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        updateGladiatorDataDisplay(Game.getGladiator());
        abilitiesBT = view.findViewById(R.id.abilities_homeBT);
        abilitiesBT.setOnClickListener(this);
        shopBT = view.findViewById(R.id.shop_homeBT);
        shopBT.setOnClickListener(this);
        arenaBT = view.findViewById(R.id.arena_homeBT);
        arenaBT.setOnClickListener(this);
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

    /**
     * Handles the click events
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /**
             * Create a gladiator in JSONobject format using makeGladiatorJSON
             * Send the gladiatorJSON to the parent activity
             * Ask to change to the home fragment
             */
            case R.id.abilities_homeBT:
                DataStorage.removeSavedGladiator();
                Game.removeGladiator();
                mListener.getFragment("makeGladiator");
                break;
            case R.id.shop_homeBT:
                mListener.getFragment("shop");
                break;
            case R.id.arena_homeBT:
                mListener.getFragment("arena");
                break;
        }
    }
}
