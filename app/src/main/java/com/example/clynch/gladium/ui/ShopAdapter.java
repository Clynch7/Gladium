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
import com.example.clynch.gladium.logic.items.ItemManager;
import com.example.clynch.gladium.logic.units.UnitManager;

import org.json.JSONException;

/**
 * Created by Clynch on 2018-02-17.
 */
class ShopAdapter extends ArrayAdapter<String> {
    String[] items;

    ShopAdapter(Context context, String[] items) {
        super(context, R.layout.fragment_arena);
        this.items = items;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.item,parent,false);


        TextView nameTV = customView.findViewById(R.id.item_nameTV);
        TextView costTV = customView.findViewById(R.id.item_costTV);
        Button fightBT = customView.findViewById(R.id.item_buyBT);

        fightBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.buyItem(items[position]);
            }
        });
        costTV.setText(String.valueOf(ItemManager.generateItem(items[position]).getCost()));
        nameTV.setText(ItemManager.generateItem(items[position]).getName());

        return customView;
    }

    @Override
    public int getCount(){
        return items.length;
    }

}