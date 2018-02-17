package com.example.clynch.gladium.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.clynch.gladium.R;

public class ShopFragment extends Fragment implements View.OnClickListener {

    Button backButton;
    private OnFragmentInteractionListener mListener;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
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
        backButton = view.findViewById(R.id.arena_back_button);
        backButton.setOnClickListener(this);
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
            case R.id.arena_back_button:
                mListener.getFragment("home");
                break;
        }
    }
}
