package com.example.miniprojet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.miniprojet.Model.SuperBowl;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;

import java.util.ArrayList;
import java.util.List;

public class SuperBowlAdapter extends ArrayAdapter<SuperBowl> {
    private  ArrayList<TeamHelmet> teamHelmetArrayList;

    public SuperBowlAdapter(Context context, int resource, List<SuperBowl> superBowlsList, ArrayList<TeamHelmet> ArrayList)
    {
        super(context,resource, superBowlsList);

        teamHelmetArrayList = ArrayList;
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
        ImageView winnerHelmet = (ImageView) convertView.findViewById(R.id.helmetWinner);

        TextView loser = (TextView) convertView.findViewById(R.id.loserTextView);
        ImageView looserHelmet = (ImageView) convertView.findViewById(R.id.helmetLooser);

        sb.setText("Super bowl " + superBowl.getSb());
        winner.setText(superBowl.getWinner());
        loser.setText(superBowl.getLoser());

        for (int i = 0; i < teamHelmetArrayList.size(); i++){
            if (teamHelmetArrayList.get(i).getName().equals(superBowl.getWinner())){
                winnerHelmet.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }
            else if(teamHelmetArrayList.get(i).getName().equals(superBowl.getLoser())){
                looserHelmet.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }
        }

        return convertView;
    }


}
