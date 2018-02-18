package com.example.clynch.gladium.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clynch.gladium.R;
import com.example.clynch.gladium.logic.Game;
import com.example.clynch.gladium.logic.units.UnitManager;

import org.json.JSONException;

/**
 * Created by Clynch on 2018-02-17.
 */
class ArenaAdapter extends ArrayAdapter<String> {
    String[] units;

    ArenaAdapter(Context context, String[] units) {
        super(context, R.layout.fragment_arena);
        this.units = units;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.unit,parent,false);

        String unitName = "";
        try {
            unitName = UnitManager.getAllUnits().getString(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView nameTV = customView.findViewById(R.id.unit_nameTV);
        TextView levelTV = customView.findViewById(R.id.unit_leveTV);
        Button fightBT = customView.findViewById(R.id.unit_fightBT);

        fightBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.fightUnit(UnitManager.generateUnit(units[position]));
            }
        });
        levelTV.setText(String.valueOf(UnitManager.generateUnit(units[position]).getLevel()));
        nameTV.setText(UnitManager.generateUnit(units[position]).getName());

        return customView;
    }

    @Override
    public int getCount(){
        return units.length;
    }

}