package com.example.clynch.gladium.ui;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clynch.gladium.data.DataStorage;
import com.example.clynch.gladium.logic.Game;


import com.example.clynch.gladium.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbilitiesFragment extends Fragment implements View.OnClickListener {

    Button updateBT, removeGladBT, resetHPBT, backBT;

    private OnFragmentInteractionListener mListener;
    public AbilitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_abilities, container, false);
        updateBT = view.findViewById(R.id.update_gladiatorBT);
        updateBT.setOnClickListener(this);
        removeGladBT = view.findViewById(R.id.remove_gladiatorBT);
        removeGladBT.setOnClickListener(this);
        resetHPBT = view.findViewById(R.id.reset_hpBT);
        resetHPBT.setOnClickListener(this);
        backBT = view.findViewById(R.id.abilities_backBT);
        backBT.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeFragment.OnFragmentInteractionListener) {
            mListener = (AbilitiesFragment.OnFragmentInteractionListener) context;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_gladiatorBT:
                mListener.updateInfo();
                mListener.getFragment("home");
                break;
            case R.id.remove_gladiatorBT:
                DataStorage.removeSavedGladiator();
                Game.removeGladiator();
                mListener.getFragment("makeGladiator");
                break;
            case R.id.reset_hpBT:
                Game.resetHP();
                mListener.getFragment("home");
                break;
            case R.id.abilities_backBT:
                Log.e("TAG", "B BT");
                mListener.getFragment("home");
                break;

        }
    }

    public interface OnFragmentInteractionListener{
        void getFragment(String frag);
        void updateInfo();
    }

}
