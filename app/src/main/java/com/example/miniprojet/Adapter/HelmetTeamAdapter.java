package com.example.miniprojet.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.miniprojet.Model.TeamHelmet;
import com.example.miniprojet.R;

import java.util.List;

public class HelmetTeamAdapter extends ArrayAdapter<TeamHelmet> {
    private String color;

    public HelmetTeamAdapter(Context context, int resource, List<TeamHelmet> TeamHelmet, String color)
    {
        super(context,resource, TeamHelmet);
        this.color = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TeamHelmet helmet = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.team_helmet_cell, parent, false);
        }

        TextView teamName = (TextView) convertView.findViewById(R.id.nameTeam_cell);
        ImageView helmetImg = (ImageView) convertView.findViewById(R.id.helmet_cel);
        RelativeLayout layoutHelmet = (RelativeLayout) convertView.findViewById(R.id.layoutHelmet);

        teamName.setText(helmet.getName());
        helmetImg.setImageResource(helmet.getHelmet());


        if(!color.equals("w")){
            teamName.setTextColor(Color.WHITE);
            layoutHelmet.setBackgroundColor(Color.BLACK);
        }
        else{
            teamName.setTextColor(Color.BLACK);
            layoutHelmet.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }
}
