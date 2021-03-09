package com.example.miniprojet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.R;
import java.util.List;

public class SuperBowlAdapter extends ArrayAdapter<SuperBowl> {

    public SuperBowlAdapter(Context context, int resource, List<SuperBowl> superBowlsList)
    {
        super(context,resource, superBowlsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuperBowl superBowl = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.superbowl_cell, parent, false);
        }

        TextView sb = (TextView) convertView.findViewById(R.id.sbTextView);
        TextView winner = (TextView) convertView.findViewById(R.id.winnerTextView);
        TextView loser = (TextView) convertView.findViewById(R.id.loserTextView);

        sb.setText("sb : " + superBowl.getSb());
        winner.setText("win : " + superBowl.getWinner());
        loser.setText("loser : " + superBowl.getLoser());

        return convertView;
    }
}
