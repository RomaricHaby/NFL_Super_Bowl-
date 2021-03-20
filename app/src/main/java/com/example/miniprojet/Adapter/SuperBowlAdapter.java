package com.example.miniprojet.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
    private String color;
    private String colorTeam;

    public SuperBowlAdapter(Context context, int resource, List<SuperBowl> superBowlsList, ArrayList<TeamHelmet> ArrayList, String color,String colorTeam)
    {
        super(context,resource, superBowlsList);

        teamHelmetArrayList = ArrayList;
        this.color = color;
        this.colorTeam = colorTeam;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuperBowl superBowl = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.superbowl_cell, parent, false);
        }

        TextView sb = (TextView) convertView.findViewById(R.id.sbTextView);
        TextView vs = (TextView) convertView.findViewById(R.id.Vs);


        TextView winner = (TextView) convertView.findViewById(R.id.winnerTextView);
        ImageView winnerHelmet = (ImageView) convertView.findViewById(R.id.helmetWinner);

        TextView loser = (TextView) convertView.findViewById(R.id.loserTextView);
        ImageView looserHelmet = (ImageView) convertView.findViewById(R.id.helmetLooser);

        TextView splitBarre = (TextView) convertView.findViewById(R.id.splitBarre);
        splitBarre.setBackgroundColor(Color.parseColor(colorTeam));

        sb.setText("Super bowl " + superBowl.getSb());
        winner.setText(superBowl.getWinner());
        loser.setText(superBowl.getLoser());


        for (int i = 0; i < teamHelmetArrayList.size(); i++){
            //Colts, Raiders, RAM have changed names over the years so I manage the specific cases to display the correct helmet
            if(superBowl.getWinner().equals("Baltimore Colts")){
                winnerHelmet.setImageResource(teamHelmetArrayList.get(10).getHelmet());
            }
            else if(superBowl.getWinner().equals("Los Angeles Raiders")){
                winnerHelmet.setImageResource(teamHelmetArrayList.get(16).getHelmet());
            }
            else if(superBowl.getWinner().equals("St. Louis Rams")){
                winnerHelmet.setImageResource(teamHelmetArrayList.get(30).getHelmet());
            }
            else if (teamHelmetArrayList.get(i).getName().equals(superBowl.getWinner())){
                winnerHelmet.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }


            if(superBowl.getLoser().equals("Baltimore Colts")){
                looserHelmet.setImageResource(teamHelmetArrayList.get(10).getHelmet());
            }
            else if(superBowl.getLoser().equals("Los Angeles Raiders")){
                looserHelmet.setImageResource(teamHelmetArrayList.get(16).getHelmet());
            }
            else if(superBowl.getLoser().equals("St. Louis Rams")){
                looserHelmet.setImageResource(teamHelmetArrayList.get(30).getHelmet());
            }
            else if(teamHelmetArrayList.get(i).getName().equals(superBowl.getLoser())){
                looserHelmet.setImageResource(teamHelmetArrayList.get(i).getHelmet());
            }
        }


        if(!color.equals("w")){
            sb.setTextColor(Color.WHITE);
            winner.setTextColor(Color.WHITE);
            loser.setTextColor(Color.WHITE);
            vs.setTextColor(Color.WHITE);
        }
        else{
            sb.setTextColor(Color.BLACK);
            winner.setTextColor(Color.BLACK);
            loser.setTextColor(Color.BLACK);
            vs.setTextColor(Color.BLACK);
        }

        return convertView;
    }


}
