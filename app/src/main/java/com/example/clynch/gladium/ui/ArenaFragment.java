package com.example.clynch.gladium.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.clynch.gladium.R;
import com.example.clynch.gladium.data.DataStorage;
import com.example.clynch.gladium.logic.Game;
import com.example.clynch.gladium.logic.units.UnitManager;

import org.json.JSONException;

public class ArenaFragment extends Fragment implements View.OnClickListener{

    Button backButton;
    private OnFragmentInteractionListener mListener;
    ListView unitList;
    ArenaAdapter arenaAdapter;

    public ArenaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ArenaFragment newInstance() {
        ArenaFragment fragment = new ArenaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        backButton = view.findViewById(R.id.arena_backBT);
        backButton.setOnClickListener(this);
        unitList = view.findViewById(R.id.unit_list_view);
        arenaAdapter = new ArenaAdapter(getContext(), UnitManager.getAllUnitsArray());
        unitList.setAdapter(arenaAdapter);
        Log.e("TAG", "List has " + unitList.getCount() );
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
            case R.id.arena_backBT:
                mListener.getFragment("home");
                break;
        }
    }
}
