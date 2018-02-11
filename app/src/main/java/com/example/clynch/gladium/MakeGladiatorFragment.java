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
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
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
             * Create a gladiator in JSONArray format using makeGladiatorJSON
             * Send the gladiatorJSON to the parent activity
             * Ask to change to the home fragment
             */
            case R.id.create_gladiator_button:
                JSONArray jsGladiator = makeGladiatorJSON(nameET.getText().toString(),
                        titleET.getText().toString(),
                        ageET.getText().toString(),
                        Integer.valueOf(goldET.getText().toString()));
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
    private JSONArray makeGladiatorJSON(String name, String title, String age, int gold) {
        JSONArray jsGlad = new JSONArray();
        JSONObject jsName = new JSONObject();
        JSONObject jsTitle = new JSONObject();
        JSONObject jsAge = new JSONObject();
        JSONObject jsGold = new JSONObject();
        try {
            jsName.put("name", name);
            jsTitle.put("title", title);
            jsAge.put("age", age);
            jsGold.put("gold", gold);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsGlad.put(jsName);
        jsGlad.put(jsTitle);
        jsGlad.put(jsAge);
        jsGlad.put(jsGold);
        return jsGlad;
    }

    /**
     * This interface handles the communication between this fragment and its Activity
     * This interface is implemented in the activity and anytime a method in this interface
     * is called it is run in the activity
     */
    public interface OnFragmentInteractionListener {
        // Sends a gladiator to the parent activity
        void onMadeGladiator(JSONArray jsGlad);
        // asks the activity to change fragment
        void getFragment(String home);
    }
}
