package com.example.clynch.gladium.ui;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clynch.gladium.GlVar;
import com.example.clynch.gladium.R;
import com.example.clynch.gladium.logic.Game;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This fragment is used to create a gladiator
 * Current state is only visual
 * TODO update interface with relevant value
 * TODO Send the gldiator information up to the activity
 * Interactions with views needs to be handled inside the onCreateView method
 *
 * */


public class MakeGladiatorFragment extends Fragment implements View.OnClickListener {

    Button createGladiatorBT;
    EditText nameET, titleET, ageET, goldET;
    TextView pointsLeftET;

    private OnFragmentInteractionListener mListener;

    public MakeGladiatorFragment() {
        // Required empty public constructor
    }

    public static MakeGladiatorFragment newInstance() {
        MakeGladiatorFragment fragment = new MakeGladiatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Here all the views interacting with the XML is set up
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_gladiator, container, false);
        createGladiatorBT = view.findViewById(R.id.create_gladiator_button);
        createGladiatorBT.setOnClickListener(this);
        nameET = view.findViewById(R.id.nameET);
        titleET = view.findViewById(R.id.titleET);
        ageET = view.findViewById(R.id.ageET);
        goldET = view.findViewById(R.id.goldET);
        pointsLeftET = view.findViewById(R.id.points_leftTV);
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
            case R.id.create_gladiator_button:
                JSONObject jsGladiator = makeGladiatorJSON(nameET.getText().toString(),
                        titleET.getText().toString(),
                        Integer.valueOf(ageET.getText().toString()),
                        Integer.valueOf(goldET.getText().toString()));
                Game.createGladiator(jsGladiator.toString());
                nameET.setText("");
                titleET.setText("");
                ageET.setText("");
                goldET.setText("");
                mListener.onMadeGladiator(jsGladiator);
                mListener.getFragment("home");
                break;
        }
    }

    /**
     * This creates a JSON array containing JSONobjects for each field in the gladiator
     * @param name
     * @param title
     * @param age
     * @param gold
     * @return
     */
    private JSONObject makeGladiatorJSON(String name, String title, int age, int gold) {
        JSONObject jsGlad = new JSONObject();
        try {
            jsGlad.put(GlVar.GL_NAME_TAG, name);
            jsGlad.put(GlVar.GL_TITLE_TAG, title);
            jsGlad.put(GlVar.GL_AGE_TAG, age);
            jsGlad.put(GlVar.GL_GOLD_TAG, gold);
            jsGlad.put(GlVar.GL_CURHP_TAG, 50);
            jsGlad.put(GlVar.GL_MAXHP_TAG, 50);
            jsGlad.put(GlVar.GL_SHIELD_TAG, "EmptyFist");
            jsGlad.put(GlVar.GL_WEAPON_TAG, "EmptyFist");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsGlad;
    }

    /**
     * This interface handles the communication between this fragment and its Activity
     * This interface is implemented in the activity and anytime a method in this interface
     * is called it is run in the activity
     */
    public interface OnFragmentInteractionListener {
        // Sends a gladiator to the parent activity
        void onMadeGladiator(JSONObject jsGlad);
        // asks the activity to change fragment
        void getFragment(String home);
    }
}
